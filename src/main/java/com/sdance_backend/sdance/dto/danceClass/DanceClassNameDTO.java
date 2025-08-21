package com.sdance_backend.sdance.dto.danceClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassNameDTO {

    private UUID id;
    private String className;
}
