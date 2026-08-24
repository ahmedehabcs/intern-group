package com.talabaty.backend.repository;

import com.talabaty.backend.model.AddonGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddonGroupRepository extends JpaRepository<AddonGroup, Long> {

    @Query("SELECT DISTINCT ag FROM AddonGroup ag " +
            "JOIN ag.menuItems mi " +
            "JOIN mi.menuSection ms " +
            "WHERE ms.restaurant.id = :restaurantId")
    List<AddonGroup> findByRestaurantId(Long restaurantId);

    @Query("SELECT DISTINCT ag FROM AddonGroup ag " +
            "JOIN ag.menuItems mi " +
            "JOIN mi.menuSection ms " +
            "WHERE ag.id = :id AND ms.restaurant.id = :restaurantId")
    Optional<AddonGroup> findByIdAndRestaurantId(Long id, Long restaurantId);

    // Same as findByRestaurantId but excludes soft-deleted addon groups
    @Query("SELECT DISTINCT ag FROM AddonGroup ag " +
            "JOIN ag.menuItems mi " +
            "JOIN mi.menuSection ms " +
            "WHERE ms.restaurant.id = :restaurantId AND ag.isDeleted = false")
    List<AddonGroup> findByRestaurantIdAndIsDeletedFalse(Long restaurantId);

    // Same as findByIdAndRestaurantId but excludes soft-deleted addon groups
    @Query("SELECT DISTINCT ag FROM AddonGroup ag " +
            "JOIN ag.menuItems mi " +
            "JOIN mi.menuSection ms " +
            "WHERE ag.id = :id AND ms.restaurant.id = :restaurantId AND ag.isDeleted = false")
    Optional<AddonGroup> findByIdAndRestaurantIdAndIsDeletedFalse(Long id, Long restaurantId);
}