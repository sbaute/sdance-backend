package com.sdance_backend.sdance.security.service;

import com.sdance_backend.sdance.security.dto.AuthRequestDTO;
import com.sdance_backend.sdance.security.dto.AuthResponseDTO;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.security.dto.UserRegisterResponseDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.messages.errors.UserError;
import com.sdance_backend.sdance.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserRegisterResponseDTO registerUser(@Valid UserRegisterRequestDTO newUser) {

        User user = userService.registerUser(newUser);

        UserRegisterResponseDTO userDto = new UserRegisterResponseDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName()); //aca si es student o instructor deberia asignarlo
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;

    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthResponseDTO login(AuthRequestDTO authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((User)user));
        //String role = ((User) user).getRole().name();

        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setJwt(jwt);
        //authResponse.set(role);

        return authResponse;
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser() {
        Authentication auth =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        return userService.findOneByUsername(username)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));
    }
}
