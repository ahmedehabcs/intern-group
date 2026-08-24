package com.talabaty.backend.service.impl;

import lombok.RequiredArgsConstructor;

import com.talabaty.backend.dto.request.CreateDeliveryFeedbackRequest;
import com.talabaty.backend.dto.response.DeliveryFeedbackResponse;
import com.talabaty.backend.model.DeliveryFeedback;
import com.talabaty.backend.model.Order;
import com.talabaty.backend.model.OrderStatus;
import com.talabaty.backend.repository.DeliveryFeedbackRepository;
import com.talabaty.backend.repository.OrderRepository;
import com.talabaty.backend.service.DeliveryFeedbackService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryFeedbackServiceImpl implements DeliveryFeedbackService {

    private final DeliveryFeedbackRepository feedbackRepository;
    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public DeliveryFeedbackResponse createFeedback(
            Long orderId,
            Long customerId,
            CreateDeliveryFeedbackRequest request
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found"
                ));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot rate another customer's order"
            );
        }

        if (order.getStatus() != OrderStatus.DELIVERED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only delivered orders can be rated"
            );
        }

        if (order.getRider() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This order has no assigned rider"
            );
        }

        if (feedbackRepository.existsByOrderId(orderId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This delivery has already been rated"
            );
        }

        DeliveryFeedback feedback = new DeliveryFeedback();
        feedback.setRating(request.rating());
        feedback.setComment(request.comment());
        feedback.setOrder(order);
        feedback.setCustomer(order.getCustomer());
        feedback.setRider(order.getRider());

        DeliveryFeedback savedFeedback = feedbackRepository.save(feedback);

        return toResponse(savedFeedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryFeedbackResponse> getMyFeedback(Long riderId) {
        return feedbackRepository.findByRiderIdOrderByCreatedAtDesc(riderId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryFeedbackResponse> getAllFeedback() {
        return feedbackRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private DeliveryFeedbackResponse toResponse(DeliveryFeedback feedback) {
        return new DeliveryFeedbackResponse(
                feedback.getOrder().getId(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getCreatedAt()
        );
    }
}
