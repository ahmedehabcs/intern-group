package com.talabaty.backend.repository;

import com.talabaty.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category>
    findDistinctByIsActiveTrueAndRestaurants_IsActiveTrueOrderByNameAsc();
}
