package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.DanceClassDTO;
import com.sdance_backend.sdance.dto.DanceClassRequestDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.messages.errors.DanceClassError;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.service.IDanceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class DanceClassServiceImpl implements IDanceClassService {

    private final DanceClassRepository danceClassRepository;
    private final InstructorServiceImpl instructorService;

    private final DanceClassMapper danceClassMapper;

    @Override
    public List<DanceClassDTO> getAllDanceClass() {
        List<DanceClass> danceClassDTOList = (List<DanceClass>) danceClassRepository.findAll();

        if (danceClassDTOList.isEmpty()) {
            throw new CustomException(DanceClassError.DANCE_CLASS_LIST_EMPTY);
        }

        return danceClassMapper.toDTOList(danceClassDTOList);
    }

    @Override
    public DanceClassDTO getDanceClassById(UUID id) {
        return  danceClassMapper.toDTO(danceClassRepository.findById(id).get());
    }

    @Override
    public DanceClassDTO createDanceClass(DanceClassRequestDTO danceClassRequestDTO) {
       try{
            DanceClass danceClass = danceClassMapper.toEntity(danceClassRequestDTO);
            danceClass.setInstructor(instructorService.getInstructor(danceClassRequestDTO.getInstructorId()));
            danceClass.setStudents(new ArrayList<>());

           danceClassRepository.save(danceClass);

           return danceClassMapper.toDTO(danceClass);

       } catch (Exception ex) {
           throw new CustomException(DanceClassError.DANCE_CLASS_CREATE_ERROR,
                   ex.getMessage());
       }
    }

    @Override
    public DanceClassDTO updateDanceClass(DanceClassRequestDTO danceClassRequestDTO, UUID id) {
        try{
            DanceClass danceClass = getDanceClass(id);

            danceClass.setInstructor(instructorService.getInstructor(danceClassRequestDTO.getInstructorId()));

            danceClassMapper.updateFromDTO(danceClassRequestDTO, danceClass);
            danceClassRepository.save(danceClass);

            return danceClassMapper.toDTO(danceClass);

        } catch (Exception ex) {
            throw new CustomException(DanceClassError.DANCE_CLASS_UPDATE_ERROR,
                    ex.getMessage());
        }
    }

    @Override
    public void deleteDanceClass(UUID id) {
        try {
            DanceClass danceClass = getDanceClass(id);
            danceClassRepository.delete(danceClass);
        } catch (Exception ex) {
            throw new CustomException(DanceClassError.DANCE_CLASS_DELETE_ERROR,
                    ex.getMessage());
        }
    }

    public DanceClass getDanceClass(UUID id) {
        return danceClassRepository.findById(id).orElseThrow(() -> new CustomException(DanceClassError.DANCE_CLASS_NOT_FOUND));
    }
}
