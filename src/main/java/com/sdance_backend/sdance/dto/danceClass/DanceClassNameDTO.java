package com.sdance_backend.sdance.dto.danceClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassNameDTO {

    private Integer id;
    private String className;
}
