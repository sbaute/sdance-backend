package com.sdance_backend.sdance.security.controller;


import com.sdance_backend.sdance.security.dto.AuthRequestDTO;
import com.sdance_backend.sdance.security.dto.AuthResponseDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.security.service.AuthService;
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

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
