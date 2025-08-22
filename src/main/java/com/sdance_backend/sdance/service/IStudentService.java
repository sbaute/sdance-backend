package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.StudentDto;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<StudentDto> getAllStudents();
    StudentDto getStudentById(UUID id);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto, UUID id);
    void deleteStudent(UUID id);
}
