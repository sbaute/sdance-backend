package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.StudentDanceClassResponseDTO;
import com.sdance_backend.sdance.service.IStudentDanceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentDanceClassImpl implements IStudentDanceClass {


    @Override
    public List<DanceClassNameDTO> getClassesByStudentId(UUID studentId) {
        return List.of();
    }

    @Override
    public StudentDanceClassResponseDTO addDanceClassToStudent(UUID studentId, UUID classId) {
        return null;
    }

    @Override
    public StudentDanceClassResponseDTO deleteDanceClassToStudent(UUID studentId, UUID classId) {
        return null;
    }
}
