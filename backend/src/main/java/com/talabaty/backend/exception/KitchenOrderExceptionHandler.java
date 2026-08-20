package com.talabaty.backend.exception;

import com.talabaty.backend.controller.KitchenMenuItemController;
import com.talabaty.backend.controller.KitchenOrderController;
import com.talabaty.backend.controller.KitchenDashboardController;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice(assignableTypes = {
        KitchenOrderController.class,
        KitchenMenuItemController.class,
        KitchenDashboardController.class
})
public class KitchenOrderExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatusException(
            ResponseStatusException exception
    ) {
        String detail = exception.getReason() != null
                ? exception.getReason()
                : "Kitchen order request failed";

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                exception.getStatusCode(),
                detail
        );

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(problemDetail);
    }
}
