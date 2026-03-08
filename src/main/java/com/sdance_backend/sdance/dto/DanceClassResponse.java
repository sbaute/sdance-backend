package com.sdance_backend.sdance.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.danceClass.ClassLevel;
import com.sdance_backend.sdance.enums.danceClass.Days;
import com.sdance_backend.sdance.enums.danceClass.Hour;
import com.sdance_backend.sdance.enums.status.DanceClassStatus;
import com.sdance_backend.sdance.enums.status.InstructorStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassResponse {

    private UUID id;

    private String className;
    private Days daysOfWeek;
    private Hour classTime;
    private ClassLevel level;
    private Integer maxCapacity;
    private String description;
    private String room;
    private InstructorNameDTO instructor;

    private DanceClassStatus status;
    private LocalDate registrationDate;
}
