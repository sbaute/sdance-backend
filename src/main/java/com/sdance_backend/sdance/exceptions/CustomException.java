package com.sdance_backend.sdance.exceptions;

import com.sdance_backend.sdance.messages.MessageType;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int status;
    private final String code; //nombre del enum EJ: STUDENT_ADD_SUCESSFULL
    private final String message;

    // Constructor con cualquier enum de MessageType
    public CustomException(MessageType errorType) {
        super(errorType.getMessage());
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.status = errorType.getStatus();
    }

    // Constructor con mensaje personalizado
    public CustomException(MessageType errorType, String customMessage) {
        super(customMessage);
        this.code = errorType.getCode();
        this.message = customMessage;
        this.status = errorType.getStatus();
    }

    // Constructor generico
    public CustomException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }
}

