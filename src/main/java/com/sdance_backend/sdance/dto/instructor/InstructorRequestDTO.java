package com.sdance_backend.sdance.dto.instructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorRequestDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
}
