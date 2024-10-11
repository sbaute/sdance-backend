package com.sdance_backend.sdance.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
public class ResponseMessage implements Serializable {

    private String message;
    private Object object;
}
