package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentRequestDto;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<StudentDto> getAllStudents();
    StudentDto getStudentById(UUID id);
    StudentDto createStudent(StudentRequestDto studentRequestDto);
    StudentDto updateStudent(StudentRequestDto studentRequestDto, UUID id);
    void deleteStudent(UUID id);
}
