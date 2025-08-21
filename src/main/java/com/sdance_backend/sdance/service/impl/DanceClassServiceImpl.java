package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.danceClass.DanceClassDTO;
import com.sdance_backend.sdance.dto.danceClass.DanceClassRequestDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.entity.Instructor;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.exceptions.ErrorType;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.service.IDanceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


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
            throw new CustomException(ErrorType.DANCE_CLASS_LIST_EMPTY);
        }

        return danceClassMapper.toResponseDTOList(danceClassDTOList);
    }

    @Override
    public DanceClassDTO getDanceClassById(UUID id) {
        return  danceClassMapper.toResponseDTO(danceClassRepository.findById(id).get());
    }

    @Override
    public DanceClassDTO createDanceClass(DanceClassRequestDTO danceClassRequestDTO) {
       try{
            DanceClass danceClass = danceClassMapper.toEntity(danceClassRequestDTO);
            danceClass.setInstructor(instructorService.getInstructor(danceClassRequestDTO.getInstructorId()));

           danceClassRepository.save(danceClass);

           return danceClassMapper.toResponseDTO(danceClass);

       } catch (Exception ex) {
           throw new CustomException(ErrorType.DANCE_CLASS_CREATE_ERROR,
                   ex.getMessage());
       }
    }

    @Override
    public DanceClassDTO updateDanceClass(DanceClassRequestDTO danceClassRequestDTO, UUID id) {
        try{
            DanceClass danceClass = getDanceClass(id);

            Instructor instructor = instructorService.getInstructor(danceClassRequestDTO.getInstructorId());
            danceClass.setInstructor(instructor);

            danceClassMapper.updateFromDTO(danceClassRequestDTO, danceClass);
            danceClassRepository.save(danceClass);

            return danceClassMapper.toResponseDTO(danceClass);

        } catch (Exception ex) {
            throw new CustomException(ErrorType.DANCE_CLASS_UPDATE_ERROR,
                    ex.getMessage());
        }
    }

    @Override
    public void deleteDanceClass(UUID id) {
        try {
            DanceClass danceClass = getDanceClass(id);
            danceClassRepository.delete(danceClass);
        } catch (Exception ex) {
            throw new CustomException(ErrorType.DANCE_CLASS_DELETE_ERROR,
                    ex.getMessage());
        }
    }


    private DanceClass getDanceClass(UUID id) {
        return danceClassRepository.findById(id).orElseThrow(() -> new CustomException(ErrorType.DANCE_CLASS_NOT_FOUND));
    }
}
