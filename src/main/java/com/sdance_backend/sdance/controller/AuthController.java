package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.dto.AuthRequestDTO;
import com.sdance_backend.sdance.dto.AuthResponseDTO;
import com.sdance_backend.sdance.service.auth.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authenticationRequest) {
        AuthResponseDTO response = authService.login(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
