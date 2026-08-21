package com.talabaty.backend.repository;

import com.talabaty.backend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByMenuSectionIdAndIsAvailableTrueOrderByNameAsc(
            Long menuSectionId
    );

    List<MenuItem>
    findByIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrueAndNameContainingIgnoreCaseOrderByNameAsc(
            String name
    );

    List<MenuItem>
    findByIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrueAndDescriptionContainingIgnoreCaseOrderByNameAsc(
            String description
    );
    @EntityGraph(attributePaths = {
            "menuSection",
            "menuSection.restaurant",
            "addonGroups"
    })
    Optional<MenuItem>
    findByIdAndIsAvailableTrueAndMenuSectionIsActiveTrueAndMenuSectionRestaurantIsActiveTrue(
            Long id
    );

    @EntityGraph(attributePaths = "menuSection")
    List<MenuItem> findByMenuSectionRestaurantIdOrderByMenuSectionNameAscNameAsc(
            Long restaurantId
    );

    @EntityGraph(attributePaths = "menuSection")
    Optional<MenuItem> findByIdAndMenuSectionRestaurantId(
            Long id,
            Long restaurantId
    );

    @EntityGraph(attributePaths = "menuSection")
    Optional<MenuItem> findByIdAndMenuSectionId(
            Long id,
            Long menuSectionId
    );
}
