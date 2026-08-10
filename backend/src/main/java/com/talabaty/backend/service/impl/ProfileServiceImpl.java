package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.UpdateProfileRequest;
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
    public void updateProfile(String userEmail, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        switch (user.getRole()) {
            case CUSTOMER:
                CustomerProfile customerProfile = user.getCustomerProfile();
                if (customerProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer profile not found for this user.");
                }
                customerProfile.setName(request.getName());
                customerProfile.setPhoneNumber(request.getPhoneNumber());
                customerProfileRepository.save(customerProfile);
                break;

            case DRIVER:
                DeliveryProfile deliveryProfile = user.getDeliveryProfile();
                if (deliveryProfile == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery profile not found for this user.");
                }
                deliveryProfile.setName(request.getName());
                deliveryProfile.setPhoneNumber(request.getPhoneNumber());
                deliveryProfile.setVehicleType(request.getVehicleType());
                deliveryProfile.setLicenseNumber(request.getLicenseNumber());
                deliveryProfile.setNationalId(request.getNationalId());
                deliveryProfileRepository.save(deliveryProfile);
                break;

            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profile updates are not supported for this user role.");
        }
    }
}
