package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassRequestDTO {

    private UUID id;

    @NotBlank(message = "ClassName cannot be empty")
    private String className;

    private Days daysOfWeek;
    private Hour classTime;
    private UUID instructorId;
}

