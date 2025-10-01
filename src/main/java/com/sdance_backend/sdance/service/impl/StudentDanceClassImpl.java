package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.StudentDanceClassResponseDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.messages.errors.StudentError;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.service.IStudentDanceClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentDanceClassImpl implements IStudentDanceClass {

    private final StudentServiceImpl studentService;
    private final DanceClassServiceImpl danceClassService;
    private final DanceClassRepository danceClassRepository;
    private final DanceClassMapper danceClassMapper;

    @Override
    public List<DanceClassNameDTO> getClassesByStudentId(UUID studentId) {
        studentService.getStudent(studentId);
        List<DanceClass> danceClass = studentService.getStudent(studentId).getDanceClasses();
        return danceClassMapper.toNameDTOList(danceClass);
    }

    @Override
    public StudentDanceClassResponseDTO addDanceClassToStudent(UUID studentId, UUID classId) {
        Student student = studentService.getStudent(studentId);
        DanceClass danceClass = danceClassService.getDanceClass(classId);
        if (danceClass.getStudents().contains(student)) {
            throw new CustomException(StudentError.STUDENT_ALREADY_EXISTS);
        }
        danceClass.getStudents().add(student);
        danceClassRepository.save(danceClass);
        return response(student, danceClass);
    }

    @Override
    public StudentDanceClassResponseDTO deleteDanceClassToStudent(UUID studentId, UUID classId) {
        Student student = studentService.getStudent(studentId);
        DanceClass danceClass = danceClassService.getDanceClass(classId);
        if (danceClass.getStudents().contains(student)) {
            danceClass.getStudents().remove(student);
            danceClassRepository.save(danceClass);
        }
        return response(student, danceClass);
    }

    private static StudentDanceClassResponseDTO response(Student student, DanceClass danceClass) {
        return StudentDanceClassResponseDTO.builder()
                .studentId(student.getId())
                .danceClassId(danceClass.getId())
                .danceClassName(danceClass.getClassName())
                .studentName(student.getName() + " " + student.getLastName())
                .build();
    }

}
