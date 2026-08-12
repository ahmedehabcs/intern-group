package com.talabaty.backend.repository;
import com.talabaty.backend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByMenuSectionIdAndIsAvailableTrue(Long menuSectionId);

    @Query("""
            SELECT menuItem
            FROM MenuItem menuItem
            JOIN FETCH menuItem.menuSection menuSection
            JOIN FETCH menuSection.restaurant restaurant
            WHERE menuItem.isAvailable = true
              AND menuSection.isActive = true
              AND restaurant.isActive = true
              AND (
                  LOWER(menuItem.name) LIKE LOWER(CONCAT('%', :search, '%'))
                  OR LOWER(COALESCE(menuItem.description, '')) LIKE LOWER(CONCAT('%', :search, '%'))
              )
            ORDER BY menuItem.name ASC
            """)
    List<MenuItem> searchActiveMenuItems(@Param("search") String search);
}
