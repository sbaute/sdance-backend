package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentRequest {

    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Document cannot be empty")
    @Size(min = 6, max = 20, message = "Document must be between 6 and 20 characters")
    private String document;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 9, max = 15, message = "Phone number must be between 9 and 15 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Emergency Contact name cannot be empty")
    @Size(min = 2, max = 50, message = "Emergency contact name must be between 2 and 50 characters")
    private String emergencyContactName;

    @NotBlank(message = "Emergency Contact phone cannot be empty")
    @Size(min = 9, max = 15, message = "Emergency contact phone must be between 9 and 15 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Emergency contact phone must contain only digits")
    private String emergencyContactPhone;

}
