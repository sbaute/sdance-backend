package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.instructor.InstructorDto;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.model.Instructor;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.repository.InstructorRepository;
import com.sdance_backend.sdance.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DanceClassRepository danceClassRepository;

    @Override
    public List<Instructor> getAllInstructors() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public Instructor createAndUpdateInstructor(InstructorDto instructorDto) {
        Instructor instructor;
        if (instructorDto.getId() != null && instructorRepository.existsById(instructorDto.getId())) {
            instructor = instructorRepository.findById(instructorDto.getId()).get();
        } else {
            instructor = new Instructor();
        }
        instructor.setName(instructorDto.getName());
        instructor.setLastName(instructorDto.getLastName());
        instructor.setPhoneNumber(instructorDto.getPhoneNumber());
        instructor.setDocument(instructorDto.getDocument());

        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(Instructor instructor) {
        instructorRepository.delete(instructor);
    }

    @Override
    public Boolean existById(Integer id) {
        return instructorRepository.existsById(id);
    }

    @Override
    public InstructorNameDto mapToInstructorNameDto(Instructor instructor){
        return InstructorNameDto.builder()
                        .id(instructor.getId())
                        .name(instructor.getName())
                        .lastName(instructor.getLastName())
                        .build();
    }
}
