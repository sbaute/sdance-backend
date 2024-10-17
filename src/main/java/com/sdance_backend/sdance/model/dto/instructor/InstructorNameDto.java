package com.sdance_backend.sdance.model.dto.instructor;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class InstructorNameDto {

    private  Integer id;
    private String name;
    private String lastName;
}
