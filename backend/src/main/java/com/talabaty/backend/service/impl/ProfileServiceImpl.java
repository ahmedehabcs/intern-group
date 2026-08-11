package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.UpdateProfileRequest;
import com.talabaty.backend.dto.response.ProfileResponse;
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

    public ProfileServiceImpl(
            UserRepository userRepository,
            CustomerProfileRepository customerProfileRepository,
            DeliveryProfileRepository deliveryProfileRepository
    ) {
        this.userRepository = userRepository;
        this.customerProfileRepository = customerProfileRepository;
        this.deliveryProfileRepository = deliveryProfileRepository;
    }

    @Override
    @Transactional
    public ProfileResponse getProfile(String userEmail) {
        return toProfileResponse(findUser(userEmail));
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(String userEmail, UpdateProfileRequest request) {
        User user = findUser(userEmail);

        switch (user.getRole()) {
            case CUSTOMER:
                CustomerProfile customerProfile = requireCustomerProfile(user);
                customerProfile.setName(request.getName());
                customerProfile.setPhoneNumber(request.getPhoneNumber());
                customerProfileRepository.save(customerProfile);
                break;

            case DRIVER:
                validateDriverFields(request);
                DeliveryProfile deliveryProfile = requireDeliveryProfile(user);
                deliveryProfile.setName(request.getName());
                deliveryProfile.setPhoneNumber(request.getPhoneNumber());
                deliveryProfile.setVehicleType(request.getVehicleType());
                deliveryProfile.setLicenseNumber(request.getLicenseNumber());
                deliveryProfile.setNationalId(request.getNationalId());
                deliveryProfileRepository.save(deliveryProfile);
                break;

            default:
                throw unsupportedRole();
        }

        return toProfileResponse(user);
    }

    private User findUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private ProfileResponse toProfileResponse(User user) {
        return switch (user.getRole()) {
            case CUSTOMER -> {
                CustomerProfile profile = requireCustomerProfile(user);
                yield new ProfileResponse(
                        user.getId(), user.getEmail(), user.getRole(),
                        profile.getName(), profile.getPhoneNumber(), profile.getLoyaltyPoints(),
                        null, null, null, null
                );
            }
            case DRIVER -> {
                DeliveryProfile profile = requireDeliveryProfile(user);
                yield new ProfileResponse(
                        user.getId(), user.getEmail(), user.getRole(),
                        profile.getName(), profile.getPhoneNumber(), null,
                        profile.getVehicleType(), profile.getLicenseNumber(),
                        profile.getNationalId(), profile.getOnline()
                );
            }
            default -> throw unsupportedRole();
        };
    }

    private CustomerProfile requireCustomerProfile(User user) {
        if (user.getCustomerProfile() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer profile not found for this user.");
        }
        return user.getCustomerProfile();
    }

    private DeliveryProfile requireDeliveryProfile(User user) {
        if (user.getDeliveryProfile() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery profile not found for this user.");
        }
        return user.getDeliveryProfile();
    }

    private void validateDriverFields(UpdateProfileRequest request) {
        if (isBlank(request.getVehicleType())
                || isBlank(request.getLicenseNumber())
                || isBlank(request.getNationalId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Vehicle type, license number, and national ID are required for drivers."
            );
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private ResponseStatusException unsupportedRole() {
        return new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Profiles are not supported for this user role."
        );
    }
}
