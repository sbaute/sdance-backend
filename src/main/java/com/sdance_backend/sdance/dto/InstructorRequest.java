package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructorRequest {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "lastName cannot be empty")
    private String lastName;

    @NotBlank(message = "document cannot be empty")
    private String document;

    @NotBlank(message = "phoneNumber cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Emergency Contact name cannot be empty")
    private String emergencyContactName;

    @NotBlank(message = "Emergency Contact phone cannot be empty")
    private String emergencyContactPhone;
}
