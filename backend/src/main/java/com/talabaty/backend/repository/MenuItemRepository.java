package com.talabaty.backend.repository;
import com.talabaty.backend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByMenuSectionIdAndIsAvailableTrue(Long menuSectionId);
}
