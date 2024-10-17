package com.sdance_backend.sdance.model.dto.student;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class StudentNameDto {
    private String name;
    private String lastName;
}
