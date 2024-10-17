package com.sdance_backend.sdance.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.model.entity.DanceClass;
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
