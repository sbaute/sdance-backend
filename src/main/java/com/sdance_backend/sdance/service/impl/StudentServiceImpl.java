package com.sdance_backend.sdance.service.impl;
import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;
import com.sdance_backend.sdance.dto.StudentDto;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.mapper.StudentMapper;
import com.sdance_backend.sdance.messages.errors.GenericError;
import com.sdance_backend.sdance.messages.errors.StudentError;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

            if(students.isEmpty()) {
                throw new CustomException(StudentError.STUDENT_LIST_EMPTY);
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
                    StudentError.STUDENT_CREATE_ERROR,
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
                    StudentError.STUDENT_UPDATE_ERROR,
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
                    StudentError.STUDENT_DELETE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    public PageResponseDTO<StudentDto> searchStudents(SearchTermDTO searchRequest, int page, int size) {

        // Validacion de pag y tamaño
        if (page < 0) {
            throw new CustomException(GenericError.INVALID_PAGE_PARAMETER_ERROR
            );
        }

        if (size < 1 || size > 100) {
            throw new CustomException(GenericError.INVALID_SIZE_PARAMETER_ERROR
            );
        }

        int pageNumber = Math.max(0, page);

        try {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC, "lastName"));

            //  busco la descripcion en los atributos de student
            Specification<Student> spec = (root, query, cb) -> {
                if (searchRequest != null && searchRequest.getDescription() != null && !searchRequest.getDescription().trim().isEmpty()) {
                    String term = "%" + searchRequest.getDescription().toLowerCase() + "%";
                    return cb.or(
                            cb.like(cb.lower(root.get("name")), term),
                            cb.like(cb.lower(root.get("lastName")), term),
                            cb.like(cb.lower(root.get("document")), term),
                            cb.like(cb.lower(root.get("phoneNumber")), term)
                    );
                }
                return null; // si no hay termino de busqueda, devuelve todo
            };

            Page<Student> studentPage = studentRepository.findAll(spec, pageable);

            List<StudentDto> students = studentPage.getContent().stream()
                    .map(studentMapper::toDTO)
                    .collect(Collectors.toList());

            return new PageResponseDTO<>(
                    students,
                    studentPage.getTotalElements(),
                    page
            );

        } catch (Exception e) {
            throw new CustomException(
                    GenericError.SEARCH_ERROR
            );
        }
    }


    private void validateFields(StudentDto studentRequestDto){
        if(studentRequestDto.getName() == null || studentRequestDto.getLastName() == null || studentRequestDto.getPhoneNumber().isEmpty() || studentRequestDto.getDocument().isEmpty()){
            throw new CustomException(GenericError.REQUIRED_FIELDS_MISSING);
        }
    }
    public Student getStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new CustomException(StudentError.STUDENT_NOT_FOUND));
        return student;
    }


}
