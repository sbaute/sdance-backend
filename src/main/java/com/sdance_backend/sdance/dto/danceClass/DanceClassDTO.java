package com.sdance_backend.sdance.dto.danceClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDTO;
import com.sdance_backend.sdance.dto.student.StudentNameDto;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassDTO {

    private UUID id;
    private String className;
    private Days daysOfWeek;
    private Hour classTime;
    private InstructorNameDTO instructor;
    private List<StudentNameDto> students = new ArrayList<>();
}
