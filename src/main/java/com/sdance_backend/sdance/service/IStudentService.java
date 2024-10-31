package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student createUpdateStudent(StudentDto student);
    void deleteStudent(Student student);

    void deleteDanceClass (Integer studentId, List<Integer> danceClassesId);
    boolean existsById(Integer id);
    List<StudentNameDto> mapToStudentNameDto(List<Student> students);

}
