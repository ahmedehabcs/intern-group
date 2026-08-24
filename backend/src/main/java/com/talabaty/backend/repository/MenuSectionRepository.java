package com.talabaty.backend.repository;

import com.talabaty.backend.model.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, Long> {

    List<MenuSection> findByRestaurantIdAndIsActiveTrueOrderByNameAsc(
            Long restaurantId
    );

    Optional<MenuSection> findByIdAndRestaurantId(Long id, Long restaurantId);
}