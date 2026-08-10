package com.talabaty.backend.repository;

import com.talabaty.backend.model.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, Long> {
}
