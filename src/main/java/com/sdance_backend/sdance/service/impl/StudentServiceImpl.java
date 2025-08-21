package com.sdance_backend.sdance.service.impl;
import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentRequestDto;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.exceptions.ErrorType;
import com.sdance_backend.sdance.mapper.StudentMapper;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return studentMapper.toResponseDTOList(students);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(UUID id) {
        return studentMapper.toResponseDTO(getStudent(id));
    }

    @Override
    @Transactional
    public StudentDto createStudent(StudentRequestDto studentRequestDto) {
        try {
            validateFields(studentRequestDto);

            Student student = studentMapper.toEntity(studentRequestDto);
            studentRepository.save(student);
            return studentMapper.toResponseDTO(student);

        } catch (Exception ex) {
            throw new CustomException(
                   ErrorType.STUDENT_CREATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    @Transactional
    public StudentDto updateStudent(StudentRequestDto studentRequestDto, UUID id) {
        try {
            validateFields(studentRequestDto);

            Student student = getStudent(id);
            studentMapper.updateFromDTO(studentRequestDto, student);
            studentRepository.save(student);

            return studentMapper.toResponseDTO(student);

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


    private void validateFields(StudentRequestDto studentRequestDto){
        if(studentRequestDto.getName() == null || studentRequestDto.getLastName() == null || studentRequestDto.getPhoneNumber().isEmpty() || studentRequestDto.getDocument().isEmpty()){
            throw new CustomException(ErrorType.REQUIRED_FIELDS_MISSING);
        }
    }
    private Student getStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorType.STUDENT_NOT_FOUND));
        return student;
    }


}
