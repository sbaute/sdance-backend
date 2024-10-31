package com.sdance_backend.sdance.service.impl;
import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.DanceClass;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.model.Student;
import com.sdance_backend.sdance.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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
        Student student;

        if(studentDto.getId() != null && studentRepository.existsById(studentDto.getId())){
            student = studentRepository.findById(studentDto.getId()).get();
        }else{
            student= new Student();
        }
        student.setName(studentDto.getName());
        student.setLastName(studentDto.getLastName());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setDocument(studentDto.getDocument());

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Student student) {
            studentRepository.delete(student);
    }

    @Override
    public void deleteDanceClass (Integer studentId, List<Integer> danceClassesId){
        if(studentRepository.existsById(studentId)){
            Student student = studentRepository.findById(studentId).get();
            danceClassesId.stream().forEach(danceClassId -> {
                if(danceClassRepository.existsById(danceClassId)){
                    DanceClass danceClass =  danceClassRepository.findById(danceClassId).get();
                    student.getDanceClasses().remove(danceClass);
                    danceClass.getStudents().remove(student);
                } else {
                    throw new NoSuchElementException("DanceClass whit id " + danceClassId + "not found");
                }
            });
            studentRepository.save(student);
            danceClassesId.forEach(danceClassId -> {
                DanceClass danceClass = danceClassRepository.findById(danceClassId).get();
                danceClassRepository.save(danceClass);
            });
        }
    }



    @Override
    public boolean existsById(Integer id) {
        return studentRepository.existsById(id);
    }

    @Override
    public  List<StudentNameDto> mapToStudentNameDto(List<Student> students){
        return students.stream()
                .map(student -> StudentNameDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .lastName(student.getLastName())
                        .build()).collect(Collectors.toList());
    }
}
