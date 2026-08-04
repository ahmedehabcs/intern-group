package com.talabaty.backend.restaurant.repository;
import com.talabaty.backend.Entities.catalog.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


}
