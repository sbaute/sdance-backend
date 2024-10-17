package com.sdance_backend.sdance.model.dto.student;


import com.sdance_backend.sdance.model.entity.DanceClass;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Data
@ToString
@Builder
public class StudentDto {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private List<Integer> danceClassesId;

}
