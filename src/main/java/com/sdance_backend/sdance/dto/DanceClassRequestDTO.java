package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassRequestDTO {

    private UUID id;

    @NotBlank(message = "Class name cannot be empty")
    private String className;

    @NotBlank(message = "Day of week cannot be empty")
    private Days daysOfWeek;

    @NotBlank(message = "Class time cannot be empty")
    private Hour classTime;

    private UUID instructorId;
}
