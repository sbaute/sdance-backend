package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.mapper.UserMapper;
import com.sdance_backend.sdance.messages.errors.UserError;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.enums.Role;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.messages.errors.PasswordError;
import com.sdance_backend.sdance.repository.UserRepository;
import com.sdance_backend.sdance.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;



    @Override
    public User registerUser(UserRegisterRequestDTO newUser) {

        validatePassword(newUser.getPassword(), newUser.getRepeatedPassword());

        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }



    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(getUser(id));
    }

    public User getUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException(UserError.USER_NOT_FOUND));
        return user;
    }

    private void validatePassword(String password1, String password2) {

        if (!StringUtils.hasText(password1) || !StringUtils.hasText(password2)) {
            throw new CustomException(PasswordError.PASSWORDS_EMPTY);
        }

        if (!password1.equals(password2)) {
            throw new CustomException(PasswordError.PASSWORDS_DO_NOT_MATCH);
        }

        if(password1.length() < 8 || password2.length() < 8 ){
            throw new CustomException(PasswordError.PASSWORD_TOO_SHORT);
        }

        if(password1.length() > 12 || password2.length() > 12 ){
            throw new CustomException(PasswordError.PASSWORD_TOO_LONG);
        }

    }
}
