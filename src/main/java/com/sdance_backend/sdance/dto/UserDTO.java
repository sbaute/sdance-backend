package com.sdance_backend.sdance.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private UUID id;
    private String name;
    private String username;
    private String role;
}
