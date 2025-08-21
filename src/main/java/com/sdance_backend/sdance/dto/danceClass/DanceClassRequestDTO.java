package com.sdance_backend.sdance.dto.danceClass;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassRequestDTO {
    private Integer id;
    private String className;
    private Days daysOfWeek;
    private Hour classTime;
    private UUID instructorId;
}
