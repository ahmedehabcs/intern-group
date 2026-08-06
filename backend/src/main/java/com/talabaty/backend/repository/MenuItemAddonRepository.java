package com.talabaty.backend.repository;

import com.talabaty.backend.model.MenuItemAddon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemAddonRepository extends JpaRepository<MenuItemAddon, Long> {

    List<MenuItemAddon> findByAddonGroupIdAndIsAvailableTrue(Long addonGroupId);
}
