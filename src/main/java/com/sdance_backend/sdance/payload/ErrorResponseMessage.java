package com.sdance_backend.sdance.payload;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorResponseMessage<T> extends ResponseMessage<T> {
    private int status;
    private String code;
    private long timestamp;
}

