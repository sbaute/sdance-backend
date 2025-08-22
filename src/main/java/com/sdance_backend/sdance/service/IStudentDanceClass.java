package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;

import java.util.List;
import java.util.UUID;

public interface IStudentDanceClass {
    List<DanceClassNameDTO> getClassesByStudentId (UUID studentId);
}
