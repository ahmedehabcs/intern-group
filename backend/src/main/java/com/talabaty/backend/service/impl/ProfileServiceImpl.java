package com.talabaty.backend.service.impl;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.talabaty.backend.dto.request.CustomerProfileUpdateRequest;
import com.talabaty.backend.dto.request.DriverProfileUpdateRequest;
import com.talabaty.backend.dto.response.CustomerProfileResponse;
import com.talabaty.backend.dto.response.DriverProfileResponse;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.model.DeliveryProfile;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.CustomerProfileRepository;
import com.talabaty.backend.repository.DeliveryProfileRepository;
import com.talabaty.backend.repository.UserRepository;
import com.talabaty.backend.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final DeliveryProfileRepository deliveryProfileRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProfileServiceImpl(UserRepository userRepository,
                              CustomerProfileRepository customerProfileRepository,
                              DeliveryProfileRepository deliveryProfileRepository) {
        this.userRepository = userRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.deliveryProfileRepository = deliveryProfileRepository;
    }

//    @Override
//    public Object getProfile(String userEmail) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
@Override
public Object getProfile(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            ));
        switch (user.getRole()) {
            case CUSTOMER:
                CustomerProfile customerProfile = user.getCustomerProfile();
                if (customerProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer profile not found for this user.");
                }
                return new CustomerProfileResponse(
                        user.getEmail(),
                        customerProfile.getName(),
                        customerProfile.getPhoneNumber()
                );
            case DRIVER:
                DeliveryProfile deliveryProfile = user.getDeliveryProfile();
                if (deliveryProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver profile not found for this user.");
                }
                return new DriverProfileResponse(
                        user.getEmail(),
                        deliveryProfile.getName(),
                        deliveryProfile.getPhoneNumber(),
                        deliveryProfile.getVehicleType(),
                        deliveryProfile.getLicenseNumber(),
                        deliveryProfile.getNationalId()
                );
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile retrieval is not supported for this user role.");
        }
    }

//    @Override
//    @Transactional
//    public void updateProfile(String userEmail, JsonNode requestBody) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
@Override
@Transactional
public void updateProfile(Long userId, JsonNode requestBody) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            ));
        switch (user.getRole()) {
            case CUSTOMER:
                CustomerProfile customerProfile = user.getCustomerProfile();
                if (customerProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer profile not found for this user.");
                }
                try {
                    CustomerProfileUpdateRequest customerRequest = objectMapper.treeToValue(requestBody, CustomerProfileUpdateRequest.class);
                    updateCustomerProfile(customerProfile, customerRequest);
                    customerProfileRepository.save(customerProfile);
                } catch (JacksonException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body: " + e.getMessage());
                }
                break;
            case DRIVER:
                DeliveryProfile deliveryProfile = user.getDeliveryProfile();
                if (deliveryProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver profile not found for this user.");
                }
                try {
                    DriverProfileUpdateRequest driverRequest = objectMapper.treeToValue(requestBody, DriverProfileUpdateRequest.class);
                    updateDriverProfile(deliveryProfile, driverRequest);
                    deliveryProfileRepository.save(deliveryProfile);
                } catch (JacksonException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body: " + e.getMessage());
                }
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile updates are not supported for this user role.");
        }
    }

    private void updateCustomerProfile(CustomerProfile profile, CustomerProfileUpdateRequest request) {
        if (request.getName() != null) {
            validateNotBlank("name", request.getName());
            profile.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            validateNotBlank("phoneNumber", request.getPhoneNumber());
            profile.setPhoneNumber(request.getPhoneNumber());
        }
    }

    private void updateDriverProfile(DeliveryProfile profile, DriverProfileUpdateRequest request) {
        if (request.getName() != null) {
            validateNotBlank("name", request.getName());
            profile.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            validateNotBlank("phoneNumber", request.getPhoneNumber());
            profile.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getVehicleType() != null) {
            validateNotBlank("vehicleType", request.getVehicleType());
            profile.setVehicleType(request.getVehicleType());
        }
        if (request.getLicenseNumber() != null) {
            validateNotBlank("licenseNumber", request.getLicenseNumber());
            profile.setLicenseNumber(request.getLicenseNumber());
        }
        if (request.getNationalId() != null) {
            validateNotBlank("nationalId", request.getNationalId());
            profile.setNationalId(request.getNationalId());
        }
    }

    private void validateNotBlank(String fieldName, String value) {
        if (value.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fieldName + " cannot be blank");
        }
    }
}
