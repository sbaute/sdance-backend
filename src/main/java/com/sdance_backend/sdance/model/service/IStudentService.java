package com.sdance_backend.sdance.model.service;

import com.sdance_backend.sdance.model.dto.StudentDto;
import com.sdance_backend.sdance.model.entity.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student createUpdateStudent(StudentDto student);
    void deleteStudent(Student student);

    boolean existsById(Integer id);
}
