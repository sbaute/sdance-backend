package com.sdance_backend.sdance.dto.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentRequestDto {
    private UUID id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
}
