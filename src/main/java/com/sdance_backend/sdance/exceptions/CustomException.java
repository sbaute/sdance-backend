package com.sdance_backend.sdance.exceptions;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private final int status;
    private final String code;
    private final String message;

    // Constructor con ErrorType
    public CustomException(ErrorType errorType) {
        super(errorType.name());
        this.code = errorType.name();
        this.message = errorType.getMessage();
        this.status = errorType.getStatus();
    }

    // Constructor para excepcion personalizada con mensaje
    public CustomException(ErrorType errorType, String message) {
        super(errorType.name());
        this.code = errorType.name();
        this.message = message;
        this.status = errorType.getStatus();
    }

    // Constructor para excepcion personalizada
    public CustomException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
