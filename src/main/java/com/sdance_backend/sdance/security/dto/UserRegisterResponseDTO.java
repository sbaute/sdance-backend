package com.sdance_backend.sdance.security.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterResponseDTO {

    private UUID id;
    private String name;
    private String username;
    private String role;
    private String jwt;
}
