package com.sdance_backend.sdance.dto.instructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorNameDto {

    private  Integer id;
    private String name;
    private String lastName;
}
