package com.sdance_backend.sdance.dto.instructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.dto.danceClass.DanceClassDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorDto {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private List<DanceClassDto> danceClasses;
}
