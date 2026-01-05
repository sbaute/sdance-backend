package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.entity.User;
import jakarta.validation.Valid;

import java.util.Optional;

public interface IUserService {
    User registerUser(@Valid UserRegisterRequestDTO newUser);

    Optional<User> findOneByUsername(String username);
}
