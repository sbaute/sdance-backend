package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.danceClass.ClassLevel;
import com.sdance_backend.sdance.enums.danceClass.Days;
import com.sdance_backend.sdance.enums.danceClass.Hour;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassRequest {

    private UUID id; // opcional para update

    @NotBlank(message = "Class name cannot be empty")
    private String className;

    @NotNull(message = "Day of week is required")
    private Days daysOfWeek;

    @NotNull(message = "Class time is required")
    private Hour classTime;

    @NotNull(message = "Level is required")
    private ClassLevel level;

    @NotNull(message = "Max capacity is required")
    @Min(value = 1, message = "Max capacity must be at least 1")
    private Integer maxCapacity;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private String room;

    @NotNull(message = "Instructor is required")
    private UUID instructorId;
}

