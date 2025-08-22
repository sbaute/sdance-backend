package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "document cannot be empty")
    private String document;

    @NotBlank(message = "phoneNumber cannot be empty")
    private String phoneNumber;
}
