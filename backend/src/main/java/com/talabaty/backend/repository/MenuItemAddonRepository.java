package com.talabaty.backend.repository;

import com.talabaty.backend.model.MenuItemAddon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemAddonRepository extends JpaRepository<MenuItemAddon, Long> {

    List<MenuItemAddon> findByAddonGroupIdAndIsAvailableTrue(Long addonGroupId);
    List<MenuItemAddon>
    findByAddonGroupIdInAndIsAvailableTrueOrderByNameAsc(
            List<Long> addonGroupIds
    );

    @Query("SELECT DISTINCT ma FROM MenuItemAddon ma " +
           "JOIN ma.addonGroup ag " +
           "JOIN ag.menuItems mi " +
           "JOIN mi.menuSection ms " +
           "WHERE ms.restaurant.id = :restaurantId")
    List<MenuItemAddon> findByRestaurantId(Long restaurantId);

    @Query("SELECT ma FROM MenuItemAddon ma " +
           "JOIN ma.addonGroup ag " +
           "JOIN ag.menuItems mi " +
           "JOIN mi.menuSection ms " +
           "WHERE ma.id = :id AND ms.restaurant.id = :restaurantId")
    Optional<MenuItemAddon> findByIdAndRestaurantId(Long id, Long restaurantId);
}
