package com.sdance_backend.sdance.security.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequestDTO {

    private String username;
    private String name;
    private String password;
    private String repeatedPassword;
}
