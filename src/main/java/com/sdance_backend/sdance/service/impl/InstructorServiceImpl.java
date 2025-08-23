package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.InstructorDTO;
import com.sdance_backend.sdance.entity.Instructor;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.mapper.InstructorMapper;
import com.sdance_backend.sdance.messages.errors.GenericError;
import com.sdance_backend.sdance.messages.errors.InstructorError;
import com.sdance_backend.sdance.repository.InstructorRepository;
import com.sdance_backend.sdance.service.IInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = (List<Instructor>) instructorRepository.findAll();

        if(instructors.isEmpty()) {
            throw new CustomException(InstructorError.INSTRUCTOR_LIST_EMPTY);
        }
        return instructorMapper.toDTOList(instructors);
    }

    @Override
    public InstructorDTO getInstructorById(UUID id) {
        return instructorMapper.toDTO(getInstructor(id));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorRequestDto) {
        try {
            validateFields(instructorRequestDto);

            Instructor instructor = instructorMapper.toEntity(instructorRequestDto);
            instructor.setDanceClasses(new ArrayList<>());
            instructorRepository.save(instructor);
            return instructorMapper.toDTO(instructor);

        } catch (Exception ex) {
            throw new CustomException(
                    InstructorError.INSTRUCTOR_CREATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorRequestDto, UUID id) {
        try {
            validateFields(instructorRequestDto);

            Instructor instructor = getInstructor(id);
           instructorMapper.updateFromDTO(instructorRequestDto, instructor);
            return instructorMapper.toDTO(instructor);

        } catch (Exception ex) {
            throw new CustomException(
                    InstructorError.INSTRUCTOR_UPDATE_ERROR,
                    ex.getMessage()
            );
        }
    }

    @Override
    public void deleteInstructor(UUID id) {
        try {
            Instructor instructor = getInstructor(id);
            instructorRepository.delete(instructor);
        }catch (Exception ex) {
            throw new CustomException(
                    InstructorError.INSTRUCTOR_DELETE_ERROR,
                    ex.getMessage()
            );
        }
    }

    public Instructor getInstructor(UUID id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new CustomException(InstructorError.INSTRUCTOR_NOT_FOUND));
        return instructor;
    }

    private void validateFields(InstructorDTO instructorRequestDto){
        if(instructorRequestDto.getName() == null || instructorRequestDto.getLastName() == null || instructorRequestDto.getPhoneNumber().isEmpty() || instructorRequestDto.getDocument().isEmpty()){
            throw new CustomException(GenericError.REQUIRED_FIELDS_MISSING);
        }
    }

}
