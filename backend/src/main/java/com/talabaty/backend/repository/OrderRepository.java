package com.talabaty.backend.repository;


import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface OrderRepository extends JpaRepository<Order, Long> {


    // Active order: rider owns it, still in progress (not yet delivered/cancelled)
    Optional<Order> findFirstByRiderIdAndStatusIn(Long riderId, List<OrderStatus> statuses);

    // Completed deliveries for history, most recent first
    List<Order> findByRiderIdAndStatusOrderByUpdatedAtDesc(Long riderId, OrderStatus status);
    // Orders ready for pickup, not yet assigned to a rider
    List<Order> findByStatusAndRiderIsNullOrderByIdAsc(OrderStatus status);

    // A rider's currently active orders (used later in Feature 2)
    List<Order> findByRiderIdAndStatus(Long riderId, OrderStatus status);

    // Atomic accept: only succeeds if the order is still unassigned and READY.
    // Prevents two riders from grabbing the same order at the same time.
    @Modifying
    @Query("UPDATE Order o SET o.rider.id = :riderId, o.status = :newStatus " +
            "WHERE o.id = :orderId AND o.rider IS NULL AND o.status = :expectedStatus")
    int acceptOrderIfAvailable(@Param("orderId") Long orderId,
                               @Param("riderId") Long riderId,
                               @Param("newStatus") OrderStatus newStatus,
                               @Param("expectedStatus") OrderStatus expectedStatus);

    // Atomic pickup: only succeeds if THIS rider owns the order and it's still ACCEPTED
    @Modifying
    @Query("UPDATE Order o SET o.status = :newStatus " +
            "WHERE o.id = :orderId AND o.rider.id = :riderId AND o.status = :expectedStatus")
    int markPickedUpIfOwned(@Param("orderId") Long orderId,
                            @Param("riderId") Long riderId,
                            @Param("newStatus") OrderStatus newStatus,
                            @Param("expectedStatus") OrderStatus expectedStatus);


    // Atomic deliver: only succeeds if THIS rider owns the order and it's still PICKED_UP
    @Modifying
    @Query("UPDATE Order o SET o.status = :newStatus " +
            "WHERE o.id = :orderId AND o.rider.id = :riderId AND o.status = :expectedStatus")
    int markDeliveredIfOwned(@Param("orderId") Long orderId,
                             @Param("riderId") Long riderId,
                             @Param("newStatus") OrderStatus newStatus,
                             @Param("expectedStatus") OrderStatus expectedStatus);

    // Atomic cancel: releases order back to pool (no rider, back to READY)
// Only succeeds if THIS rider owns it and it's still ACCEPTED (not yet picked up)
    @Modifying
    @Query("UPDATE Order o SET o.rider = NULL, o.status = :releasedStatus " +
            "WHERE o.id = :orderId AND o.rider.id = :riderId AND o.status = :expectedStatus")
    int cancelOrderIfOwned(@Param("orderId") Long orderId,
                           @Param("riderId") Long riderId,
                           @Param("releasedStatus") OrderStatus releasedStatus,
                           @Param("expectedStatus") OrderStatus expectedStatus);





    // Customer order history, ordered from newest to oldest
    // Order items are fetched with the orders because the summary mapper
    // Customer order history, ordered from newest to oldest.
    Page<Order> findByCustomerIdOrderByCreatedAtDesc(Long customerId, Pageable pageable);
    Optional<Order> findByIdAndCustomerId(Long orderId, Long customerId);

    List<Order> findByRestaurantIdAndStatusInOrderByCreatedAtAsc(
            Long restaurantId,
            Collection<OrderStatus> statuses
    );

    Optional<Order> findByIdAndRestaurantId(Long orderId, Long restaurantId);
}


