package com.sdance_backend.sdance.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResponseMessage implements Serializable {

    private String message;
    private Object object;
}
