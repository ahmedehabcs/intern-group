package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.UpdateProfileRequest;
import com.talabaty.backend.dto.response.ProfileResponse;
import com.talabaty.backend.model.CustomerProfile;
import com.talabaty.backend.model.DeliveryProfile;
import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.CustomerProfileRepository;
import com.talabaty.backend.repository.DeliveryProfileRepository;
import com.talabaty.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerProfileRepository customerProfileRepository;
    @Mock
    private DeliveryProfileRepository deliveryProfileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void getsCustomerProfileWithoutExposingDriverFields() {
        User user = customerUser();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ProfileResponse response = profileService.getProfile(user.getEmail());

        assertEquals(Role.CUSTOMER, response.getRole());
        assertEquals("Customer", response.getName());
        assertEquals(15, response.getLoyaltyPoints());
        assertNull(response.getVehicleType());
    }

    @Test
    void updatesAndReturnsDriverProfile() {
        User user = driverUser();
        UpdateProfileRequest request = updateRequest();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ProfileResponse response = profileService.updateProfile(user.getEmail(), request);

        assertEquals("Updated", response.getName());
        assertEquals("motorcycle", response.getVehicleType());
        assertEquals("LIC-2", response.getLicenseNumber());
        assertEquals("NID-2", response.getNationalId());
        verify(deliveryProfileRepository).save(user.getDeliveryProfile());
    }

    @Test
    void rejectsIncompleteDriverFullUpdate() {
        User user = driverUser();
        UpdateProfileRequest request = updateRequest();
        request.setNationalId(" ");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> profileService.updateProfile(user.getEmail(), request)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void rejectsUnsupportedRoleWithForbidden() {
        User user = new User("admin@example.com", "password", Role.ADMIN);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> profileService.getProfile(user.getEmail())
        );

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
    }

    @Test
    void reportsMissingRoleProfile() {
        User user = new User("missing@example.com", "password", Role.CUSTOMER);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> profileService.getProfile(user.getEmail())
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    private User customerUser() {
        User user = new User("customer@example.com", "password", Role.CUSTOMER);
        CustomerProfile profile = new CustomerProfile();
        profile.setUser(user);
        profile.setName("Customer");
        profile.setPhoneNumber(201001234567L);
        profile.setLoyaltyPoints(15);
        user.setCustomerProfile(profile);
        return user;
    }

    private User driverUser() {
        User user = new User("driver@example.com", "password", Role.DRIVER);
        DeliveryProfile profile = new DeliveryProfile();
        profile.setUser(user);
        profile.setName("Driver");
        profile.setPhoneNumber(201009876543L);
        profile.setVehicleType("car");
        profile.setLicenseNumber("LIC-1");
        profile.setNationalId("NID-1");
        profile.setOnline(true);
        user.setDeliveryProfile(profile);
        return user;
    }

    private UpdateProfileRequest updateRequest() {
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setName("Updated");
        request.setPhoneNumber(201001112222L);
        request.setVehicleType("motorcycle");
        request.setLicenseNumber("LIC-2");
        request.setNationalId("NID-2");
        return request;
    }
}
