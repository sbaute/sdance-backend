package com.sdance_backend.sdance.dto;

import com.sdance_backend.sdance.enums.status.InstructorStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
public class InstructorResponse {

    private UUID id;
    private String name;
    private String lastName;
    private String document;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private String emergencyContactName;
    private String emergencyContactPhone;

    private InstructorStatus status;
    private LocalDate registrationDate;
}
