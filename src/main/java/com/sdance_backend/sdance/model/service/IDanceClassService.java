package com.sdance_backend.sdance.model.service;

import com.sdance_backend.sdance.model.dto.DanceClassDto;
import com.sdance_backend.sdance.model.entity.DanceClass;

import java.util.List;

public interface IDanceClassService {
    List<DanceClass> getAllDanceClass();
    DanceClass getDanceClassById(Integer id);
    DanceClass createUpdateDanceClass(DanceClassDto danceClassDto);
    void deleteDanceClass(DanceClass danceClass);
    boolean existsById(Integer id);
    DanceClassDto mapToDanceClassDto(DanceClass danceClass);
}
