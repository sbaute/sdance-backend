package com.sdance_backend.sdance.model.dto.danceClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.model.dto.instructor.InstructorNameDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DanceClassNameDto {

    private Integer id;
    private String className;
    private InstructorNameDto instructor;
}
