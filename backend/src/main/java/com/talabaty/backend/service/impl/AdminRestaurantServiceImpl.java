package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.AssignKitchenManagerRequest;
import com.talabaty.backend.dto.request.CreateRestaurantRequest;
import com.talabaty.backend.dto.request.UpdateRestaurantRequest;
import com.talabaty.backend.dto.response.RestaurantAdminResponse;
import com.talabaty.backend.model.Category;
import com.talabaty.backend.model.Governorate;
import com.talabaty.backend.model.KitchenManager;
import com.talabaty.backend.model.Restaurant;
import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.CategoryRepository;
import com.talabaty.backend.repository.GovernorateRepository;
import com.talabaty.backend.repository.KitchenManagerRepository;
import com.talabaty.backend.repository.RestaurantRepository;
import com.talabaty.backend.repository.UserRepository;
import com.talabaty.backend.service.AdminRestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRestaurantServiceImpl implements AdminRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GovernorateRepository governorateRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final KitchenManagerRepository kitchenManagerRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminRestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                      GovernorateRepository governorateRepository,
                                      CategoryRepository categoryRepository,
                                      UserRepository userRepository,
                                      KitchenManagerRepository kitchenManagerRepository,
                                      PasswordEncoder passwordEncoder) {
        this.restaurantRepository = restaurantRepository;
        this.governorateRepository = governorateRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.kitchenManagerRepository = kitchenManagerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RestaurantAdminResponse createRestaurant(CreateRestaurantRequest request) {
        Governorate governorate = governorateRepository.findById(request.getGovernorateId())
                .orElseThrow(() -> new EntityNotFoundException("Governorate not found"));

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setPhone(request.getPhone());
        restaurant.setEmail(request.getEmail());
        restaurant.setAddress(request.getAddress());
        restaurant.setGovernorate(governorate);
        restaurant.setDescription(request.getDescription());
        restaurant.setLogoUrl(request.getLogoUrl());
        restaurant.setDeliveryFee(request.getDeliveryFee());
        restaurant.setActive(true);

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            restaurant.setCategories(loadCategories(request.getCategoryIds()));
        }

        try {
            Restaurant saved = restaurantRepository.save(restaurant);
            return toAdminResponse(saved);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone or email already in use by another restaurant");
        }
    }

    @Override
    @Transactional
    public RestaurantAdminResponse updateRestaurant(Long id, UpdateRestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (request.getName() != null) restaurant.setName(request.getName());
        if (request.getPhone() != null) restaurant.setPhone(request.getPhone());
        if (request.getEmail() != null) restaurant.setEmail(request.getEmail());
        if (request.getAddress() != null) restaurant.setAddress(request.getAddress());
        if (request.getDescription() != null) restaurant.setDescription(request.getDescription());
        if (request.getLogoUrl() != null) restaurant.setLogoUrl(request.getLogoUrl());
        if (request.getDeliveryFee() != null) restaurant.setDeliveryFee(request.getDeliveryFee());

        if (request.getGovernorateId() != null) {
            Governorate governorate = governorateRepository.findById(request.getGovernorateId())
                    .orElseThrow(() -> new EntityNotFoundException("Governorate not found"));
            restaurant.setGovernorate(governorate);
        }

        if (request.getCategoryIds() != null) {
            restaurant.setCategories(loadCategories(request.getCategoryIds()));
        }

        try {
            Restaurant saved = restaurantRepository.save(restaurant);
            return toAdminResponse(saved);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone or email already in use by another restaurant");
        }
    }

    @Override
    @Transactional
    public RestaurantAdminResponse deactivateRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        restaurant.setActive(false);
        Restaurant saved = restaurantRepository.save(restaurant);
        return toAdminResponse(saved);
    }

    private List<Category> loadCategories(List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more category IDs are invalid");
        }
        return categories;
    }

    private RestaurantAdminResponse toAdminResponse(Restaurant restaurant) {
        List<String> categoryNames = restaurant.getCategories() == null
                ? new ArrayList<>()
                : restaurant.getCategories().stream().map(Category::getName).collect(Collectors.toList());

        return new RestaurantAdminResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getPhone(),
                restaurant.getEmail(),
                restaurant.getAddress(),
                restaurant.getGovernorate() != null ? restaurant.getGovernorate().getName() : null,
                restaurant.getDescription(),
                restaurant.getLogoUrl(),
                restaurant.getActive(),
                restaurant.getDeliveryFee(),
                categoryNames
        );
    }
    @Override
    @Transactional(readOnly = true)
    public List<RestaurantAdminResponse> searchRestaurants(String search) {
        List<Restaurant> restaurants = (search == null || search.isBlank())
                ? restaurantRepository.findAllByOrderByNameAsc()
                : restaurantRepository.findByNameContainingIgnoreCaseOrderByNameAsc(search);

        return restaurants.stream()
                .map(this::toAdminResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void assignKitchenManager(Long restaurantId, AssignKitchenManagerRequest request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.KITCHEN_MANAGER);
        user.setEmailVerified(true);

        User savedUser = userRepository.save(user);

        KitchenManager kitchenManager = new KitchenManager();
        kitchenManager.setUser(savedUser);
        kitchenManager.setRestaurant(restaurant);

        kitchenManagerRepository.save(kitchenManager);
    }
}