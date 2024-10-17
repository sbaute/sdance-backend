package com.sdance_backend.sdance.model.dto.danceClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.model.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.entity.enums.Days;
import com.sdance_backend.sdance.model.entity.enums.Hour;
import lombok.*;

import java.util.List;


@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassDto {

    private Integer id;
    private String className;
    private Days dayOfWeek;
    private Hour classTime;
    private Integer instructorId;
    private List<StudentNameDto> studentsName;

}
