package com.sdance_backend.sdance.security.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequestDTO {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Password repeat cannot be empty")
    private String repeatedPassword;
}
