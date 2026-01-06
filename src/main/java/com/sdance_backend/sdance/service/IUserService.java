package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.entity.User;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User registerUser(@Valid UserRegisterRequestDTO newUser);
    Optional<User> findOneByUsername(String username);
    UserDTO getUserById(UUID id);

}
