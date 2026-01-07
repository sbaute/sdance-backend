package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.enums.Role;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.entity.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User createUser(@Valid UserRegisterRequestDTO newUser, Role role);
    Optional<User> findOneByUsername(String username);
    UserDTO getUserById(UUID id);
    List<UserDTO> getAll();
    UserDTO updateUser(UserDTO userDTO, UUID id);
    void deleteUser(UUID id);

}
