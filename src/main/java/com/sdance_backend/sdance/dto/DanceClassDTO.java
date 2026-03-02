package com.sdance_backend.sdance.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.danceClass.ClassLevel;
import com.sdance_backend.sdance.enums.danceClass.Days;
import com.sdance_backend.sdance.enums.danceClass.Hour;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassDTO {

    private UUID id;

    @NotBlank(message = "ClassName cannot be empty")
    private String className;

    private Days daysOfWeek;

    private Hour classTime;

    private ClassLevel level;

    private Integer maxCapacity;

    private String description;

    private String room;

    private InstructorNameDTO instructor;
}
