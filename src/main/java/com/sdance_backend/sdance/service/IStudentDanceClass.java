package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.StudentDanceClassResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IStudentDanceClass {
    List<DanceClassNameDTO> getClassesByStudentId (UUID studentId);
    StudentDanceClassResponseDTO addDanceClassToStudent (UUID studentId, UUID classId);
    void deleteDanceClassToStudent (UUID studentId, UUID classId);



}
