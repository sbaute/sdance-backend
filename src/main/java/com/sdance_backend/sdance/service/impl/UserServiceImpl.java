package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.StudentDto;
import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.mapper.StudentMapper;
import com.sdance_backend.sdance.mapper.UserMapper;
import com.sdance_backend.sdance.messages.errors.AuthError;
import com.sdance_backend.sdance.messages.errors.UserError;
import com.sdance_backend.sdance.security.dto.UserRegisterRequestDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.enums.Role;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.messages.errors.PasswordError;
import com.sdance_backend.sdance.repository.UserRepository;
import com.sdance_backend.sdance.service.IStudentService;
import com.sdance_backend.sdance.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final IStudentService studentService;
    private final StudentMapper studentMapper;

    @Override
    public User createUser(UserRegisterRequestDTO newUser, Role role) {
        try{
            validatePassword(newUser.getPassword(), newUser.getRepeatedPassword());
            User user = new User();
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setRole(role);

            return userRepository.save(user);
        } catch (Exception ex) {
            throw new CustomException(
                    UserError.USER_REGISTER_ERROR,
                    ex.getMessage()
            );
        }
    }


    public User registerAdminUser(UserRegisterRequestDTO newUser){
        return createUser(newUser, Role.ADMIN);
    }

    public User registerStudetUser(UserRegisterRequestDTO newUser){
        return createUser(newUser, Role.STUDENT);
    }

    public User registerInstructorUser(UserRegisterRequestDTO newUser){
        return createUser(newUser, Role.INSTRUCTOR);
    }


    public void assignStudentToUser(UUID studentId, UUID userId){
        Student student = studentMapper.toEntity(studentService.getStudentById(studentId));
        student.setUser(userRepository.findById(userId).get());
    }


    @Override
    public List<UserDTO> getAll() {
        List<User> users = (List<User>) userRepository.findAll();
        if(users.isEmpty()){
            throw new CustomException(UserError.USER_LIST_EMPTY);
        }
        return userMapper.toDTOList(users);
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return userMapper.toDTO(getUser(id));
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO, UUID id){
        try{
            User user = getUser(id);
            userMapper.updateFromDTO(userDTO, user);
            userRepository.save(user);

            return userMapper.toDTO(user);
        } catch (Exception ex) {
            throw new CustomException(
                    UserError.USER_UPDATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    public void deleteUser(UUID id) {
        try {
            User user = getUser(id);
            userRepository.delete(user);
        } catch (Exception ex){
            throw new CustomException(
                    UserError.USER_DELETE_ERROR,
                    ex.getMessage()
            );
        }
    }


    public User getUser(UUID id){
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException(UserError.USER_NOT_FOUND));
        return user;
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
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
