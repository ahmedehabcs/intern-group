package com.talabaty.backend.user.service.impl;

import com.talabaty.backend.user.dto.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.talabaty.backend.user.entity.*;
import com.talabaty.backend.user.repository.*;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private CustomerProfileRepository customerRepo;
    @Autowired private DeliveryProfileRepository deliveryRepo;
    // @Autowired private PasswordEncoder passwordEncoder; // هنحتاجها بعدين لتشفير الباسورد

    @Transactional
    public String registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Error: Email is already taken!";
        }

        // 1. إنشاء وحفظ الـ User الأساسي
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // لازم يتشفر لاحقاً
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setEmailVerified(false);

        user = userRepository.save(user); // حفظنا اليوزر عشان ناخد الـ ID بتاعه

        // 2. بناء البروفايل المناسب حسب الـ Role
        if (user.getRole() == Role.CUSTOMER) {
            CustomerProfile customer = new CustomerProfile();
            customer.setUser(user);
            customer.setName(request.getName());
            customer.setPhoneNumber(request.getPhoneNumber());
            customer.setLoyaltyPoints(0);

            customerRepo.save(customer);

        } else if (user.getRole() == Role.DRIVER) {
            DeliveryProfile driver = new DeliveryProfile();
            driver.setUser(user);
            driver.setName(request.getName());
            driver.setPhoneNumber(request.getPhoneNumber());
            driver.setVehicleType(request.getVehicleType());
            driver.setLicenseNumber(request.getLicenseNumber());
            driver.setNationalId(request.getNationalId());
            driver.setOnline(false);

            deliveryRepo.save(driver);
        }

        return "User registered successfully!";
    }
}