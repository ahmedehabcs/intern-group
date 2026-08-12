package com.talabaty.backend.repository;

import com.talabaty.backend.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Get all active restaurants sorted by name
    @EntityGraph(attributePaths = "categories")
    List<Restaurant> findDistinctByIsActiveTrueOrderByNameAsc();

    // Search active restaurants by name
    @EntityGraph(attributePaths = "categories")
    List<Restaurant> findDistinctByIsActiveTrueAndNameContainingIgnoreCaseOrderByNameAsc(
            String name
    );

    // Get active restaurants by category
    @EntityGraph(attributePaths = "categories")
    List<Restaurant> findDistinctByIsActiveTrueAndCategories_IdOrderByNameAsc(
            Long categoryId
    );

}
