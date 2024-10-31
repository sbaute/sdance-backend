package com.sdance_backend.sdance.dto.student;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.dto.danceClass.DanceClassNameDto;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private List<DanceClassNameDto> danceClassNameDtos;

}
