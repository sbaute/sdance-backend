package com.sdance_backend.sdance.exceptions;

import com.sdance_backend.sdance.payload.ErrorResponseMessage;
import com.sdance_backend.sdance.utils.ResponseBuilderMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseBuilderMessage responseBuilderMessage;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseMessage<Object>> handleApiException(CustomException ex) {
        return responseBuilderMessage.error(ex, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage<Object>> handleException(Exception ex) {
        return responseBuilderMessage.error(500, "INTERNAL_ERROR", ex.getMessage(), null);
    }
}

