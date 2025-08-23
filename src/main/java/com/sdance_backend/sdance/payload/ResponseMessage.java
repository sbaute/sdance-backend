package com.sdance_backend.sdance.payload;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class ResponseMessage<T> implements Serializable {
    private String message;
    private T data;
}
