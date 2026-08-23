package com.talabaty.backend.service.impl;

import com.talabaty.backend.dto.request.CreateReviewRequest;
import com.talabaty.backend.dto.response.ReviewResponse;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.model.Review;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.repository.RestaurantRepository;
import com.talabaty.backend.repository.ReviewRepository;
import com.talabaty.backend.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            OrderRepository orderRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public ReviewResponse createReview(
            Long orderId,
            Long customerId,
            CreateReviewRequest request
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found"
                ));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot review another customer's order"
            );
        }

        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only delivered orders can be reviewed"
            );
        }

        if (reviewRepository.existsByOrderId(orderId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This order has already been reviewed"
            );
        }

        Review review = new Review();
        review.setRating(request.rating());
        review.setComment(request.comment());
        review.setCustomer(order.getCustomer());
        review.setRestaurant(order.getRestaurant());
        review.setOrder(order);

        return toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getRestaurantReviews(Long restaurantId) {

        if (!restaurantRepository.existsById(restaurantId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Restaurant not found"
            );
        }

        return reviewRepository.findByRestaurantIdOrderByCreatedAtDesc(restaurantId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getMyReviews(Long customerId) {
        return reviewRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getOrder().getId(),
                review.getRestaurant().getId(),
                review.getRestaurant().getName(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}