package com.sdance_backend.sdance.exceptions;

import com.sdance_backend.sdance.payload.ErrorResponseMessage;
import com.sdance_backend.sdance.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseBuilder responseBuilder;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseMessage<Object>> handleApiException(CustomException ex) {
        return responseBuilder.error(ex, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage<Object>> handleException(Exception ex) {
        return responseBuilder.error(500, "INTERNAL_ERROR", ex.getMessage(), null);
    }
}

