package com.sdance_backend.sdance.model.dto;


import com.sdance_backend.sdance.model.entity.DanceClass;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class InstructorDto {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private List<DanceClass> danceClasses;
}
