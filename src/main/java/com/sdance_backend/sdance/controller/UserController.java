package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.enums.Role;
import com.sdance_backend.sdance.messages.Actions;
import com.sdance_backend.sdance.messages.ResponseBuilderMessage;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.security.dto.UserRegisterResponseDTO;
import com.sdance_backend.sdance.security.service.AuthService;
import com.sdance_backend.sdance.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final AuthService authService;
    private final IUserService userService;
    private final ResponseBuilderMessage responseBuilderMessage;


//    @PostMapping("/register")
//    public ResponseEntity<UserRegisterResponseDTO> registerUser (@RequestBody @Valid UserRegisterRequestDTO newUser){
//            UserRegisterResponseDTO userRegister = authService.registerUser(newUser);
//            return ResponseEntity.ok(userRegister);
//    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<User>> registerAdminUser(@RequestBody @Valid UserRegisterRequestDTO newUser){
        return responseBuilderMessage.success(User.class, Actions.CREATED, userService.createUser(newUser, Role.ADMIN));
    }

    @PostMapping("/register/student")
    public ResponseEntity<ResponseMessage<User>> registerStudentUser(@RequestBody @Valid UserRegisterRequestDTO newUser){
        return responseBuilderMessage.success(User.class, Actions.CREATED, userService.createUser(newUser, Role.STUDENT));
    }
    @PostMapping("/register/instructor")
    public ResponseEntity<ResponseMessage<User>> registerInstructorUser(@RequestBody @Valid UserRegisterRequestDTO newUser){
        return responseBuilderMessage.success(User.class, Actions.CREATED, userService.createUser(newUser, Role.INSTRUCTOR));
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<List<UserDTO>>> getAll (){
        return responseBuilderMessage.success(User.class, Actions.LIST_RETRIEVED, userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> getUserById(@PathVariable UUID id){
        return responseBuilderMessage.success(User.class, Actions.RETRIEVED, userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDTO>> updateUser(@RequestBody UserDTO userDTO, @PathVariable UUID id){
        return responseBuilderMessage.success(User.class, Actions.UPDATED, userService.updateUser(userDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
