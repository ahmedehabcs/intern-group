package com.talabaty.backend.exception;

import com.talabaty.backend.controller.OrderController;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice(assignableTypes = OrderController.class)
public class OrderExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatusException(ResponseStatusException exception) {
        String detail = exception.getReason() != null
                ? exception.getReason()
                : "Order request failed";

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getStatusCode(), detail);

        return ResponseEntity.status(exception.getStatusCode()).body(problemDetail);
    }
}
