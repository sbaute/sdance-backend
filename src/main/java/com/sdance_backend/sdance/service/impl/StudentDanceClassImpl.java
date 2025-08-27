package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.StudentDanceClassResponseDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.service.IStudentDanceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentDanceClassImpl implements IStudentDanceClass {

    private final StudentServiceImpl studentService;
    private final DanceClassServiceImpl danceClassService;
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
        student.addDanceClass(danceClass);
        return response(student, danceClass);
    }

    @Override
    public StudentDanceClassResponseDTO deleteDanceClassToStudent(UUID studentId, UUID classId) {
        Student student = studentService.getStudent(studentId);
        DanceClass danceClass = danceClassService.getDanceClass(classId);
        student.removeDanceClass(danceClass);
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
