package com.talabaty.backend.repository;

import com.talabaty.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndCart_Customer_Id(
            Long cartItemId,
            Long customerId
    );
}