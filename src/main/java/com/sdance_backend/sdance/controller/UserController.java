package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.security.dto.UserRegisterResponseDTO;
import com.sdance_backend.sdance.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> registerUser (@RequestBody @Valid UserRegisterRequestDTO newUser){
            UserRegisterResponseDTO userRegister = authService.registerUser(newUser);
            return ResponseEntity.ok(userRegister);
    }
}
