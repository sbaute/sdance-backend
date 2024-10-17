package com.sdance_backend.sdance.model.service.impl;
import com.sdance_backend.sdance.model.dto.student.StudentDto;
import com.sdance_backend.sdance.model.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.repository.DanceClassRepository;
import com.sdance_backend.sdance.model.repository.StudentRepository;
import com.sdance_backend.sdance.model.entity.Student;
import com.sdance_backend.sdance.model.service.IStudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DanceClassRepository danceClassRepository;


    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Student createUpdateStudent(StudentDto studentDto) {
        Student student = Student.builder()
                            .id(studentDto.getId())
                            .name(studentDto.getName())
                            .lastName(studentDto.getLastName())
                            .document(studentDto.getDocument())
                            .phoneNumber(studentDto.getPhoneNumber())
                            .danceClasses(new ArrayList<>())
                            .build();

        // Agregar las clases de danza a la lista de estudiantes (opcional)
        if((!studentDto.getDanceClassesId().isEmpty()) && (studentDto.getDanceClassesId() != null)){
            for (Integer danceClassId : studentDto.getDanceClassesId()) {
                DanceClass danceClass = danceClassRepository.findById(danceClassId)
                        .orElseThrow(() -> new EntityNotFoundException("ClassDance not found"));
                student.getDanceClasses().add(danceClass);
            }
        }
        return studentRepository.save(student);
    }

   /* @Override
    @Transactional
    public Student updateStudent(Integer id, Student student) {
        Optional<Student> studentIsExist = studentRepository.findById(id);
        if (studentIsExist.isPresent()) {
            Student existingStudent = studentIsExist.get();
            existingStudent.setName(student.getName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setDocument(student.getDocument());
            existingStudent.setPhoneNumber(student.getPhoneNumber());
            return studentRepository.save(existingStudent);
        } else {
            return null;
        }
    } */

    @Override
    @Transactional
    public void deleteStudent(Student student) {
            studentRepository.delete(student);
    }

    @Override
    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    @Override
    public  List<StudentNameDto> mapToStudentNameDtos(List<Student> students){
        return students.stream()
                .map(student -> StudentNameDto.builder()
                        .name(student.getName())
                        .lastName(student.getLastName())
                        .build()).collect(Collectors.toList());
    }
}
