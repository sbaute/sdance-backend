package com.sdance_backend.sdance.dto.instructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdance_backend.sdance.dto.danceClass.DanceClassNameDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private List<DanceClassNameDTO> danceClasses;
}
