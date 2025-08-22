package com.sdance_backend.sdance.service.impl;
import com.sdance_backend.sdance.dto.StudentDto;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.exceptions.ErrorType;
import com.sdance_backend.sdance.mapper.StudentMapper;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public List<StudentDto> getAllStudents() {
            List<Student> students = (List<Student>) studentRepository.findAll();

            if(students.isEmpty()) {
                throw new CustomException(ErrorType.STUDENT_LIST_EMPTY);
            }
            return studentMapper.toDTOList(students);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(UUID id) {
        return studentMapper.toDTO(getStudent(id));
    }

    @Override
    @Transactional
    public StudentDto createStudent(StudentDto studentRequestDto) {
        try {
            validateFields(studentRequestDto);

            Student student = studentMapper.toEntity(studentRequestDto);
            student.setDanceClasses(new ArrayList<>());
            studentRepository.save(student);
            return studentMapper.toDTO(student);

        } catch (Exception ex) {
            throw new CustomException(
                   ErrorType.STUDENT_CREATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    @Transactional
    public StudentDto updateStudent(StudentDto studentRequestDto, UUID id) {
        try {
            Student student = getStudent(id);
            studentMapper.updateFromDTO(studentRequestDto, student);
            studentRepository.save(student);

            return studentMapper.toDTO(student);

        } catch (Exception ex) {
            throw new CustomException(
                    ErrorType.STUDENT_UPDATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    @Transactional
    public void deleteStudent(UUID id) {
        try {
            Student student = getStudent(id);
            studentRepository.delete(student);
        } catch (Exception ex) {
            throw new CustomException(
                    ErrorType.STUDENT_DELETE_ERROR,
                    ex.getMessage()
            );
        }
    }

    private void validateFields(StudentDto studentRequestDto){
        if(studentRequestDto.getName() == null || studentRequestDto.getLastName() == null || studentRequestDto.getPhoneNumber().isEmpty() || studentRequestDto.getDocument().isEmpty()){
            throw new CustomException(ErrorType.REQUIRED_FIELDS_MISSING);
        }
    }
    public Student getStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorType.STUDENT_NOT_FOUND));
        return student;
    }


}
