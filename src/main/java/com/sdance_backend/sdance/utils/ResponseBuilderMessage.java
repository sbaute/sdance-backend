package com.sdance_backend.sdance.utils;

import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.messages.MessageBuilder;
import com.sdance_backend.sdance.payload.ErrorResponseMessage;
import com.sdance_backend.sdance.payload.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResponseBuilderMessage {

    private final MessageBuilder messageBuilder;

    //SUCCESFULL
    // Respuesta genérica de éxito
    public <T> ResponseEntity<ResponseMessage<T>> success(Class<?> entityClass, String action, T data) {
        String message = messageBuilder.buildSuccessMessage(entityClass, action);
        return ResponseEntity.ok(
                ResponseMessage.<T>builder()
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    //ERRORS
    // Respuesta generica para handleException
    public <T> ResponseEntity<ErrorResponseMessage<T>> error(int status, String code, String message, T data) {
        ErrorResponseMessage<T> error = ErrorResponseMessage.<T>builder()
                .status(status)
                .code(code)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .data(data)
                .build();
        return ResponseEntity.status(status).body(error);
    }

    // Respuesta generica para handleApiException
    public <T> ResponseEntity<ErrorResponseMessage<T>> error(CustomException ex, T data) {
        return error(ex.getStatus(), ex.getCode(), ex.getMessage(), data);
    }


}
