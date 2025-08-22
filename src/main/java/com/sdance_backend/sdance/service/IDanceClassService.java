package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.DanceClassDTO;
import com.sdance_backend.sdance.dto.DanceClassRequestDTO;

import java.util.List;
import java.util.UUID;

public interface IDanceClassService {
    List<DanceClassDTO> getAllDanceClass();
    DanceClassDTO getDanceClassById(UUID id);
    DanceClassDTO createDanceClass(DanceClassRequestDTO danceClassRequestDTO);
    DanceClassDTO updateDanceClass(DanceClassRequestDTO danceClassRequestDTO, UUID id);
    void deleteDanceClass(UUID id);

}
