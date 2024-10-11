package com.sdance_backend.sdance.model.service.impl;

import com.sdance_backend.sdance.model.dto.InstructorDto;
import com.sdance_backend.sdance.model.entity.Instructor;
import com.sdance_backend.sdance.model.repository.InstructorRepository;
import com.sdance_backend.sdance.model.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAllInstructors() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public Instructor createAndUpdateInstructor(InstructorDto instructordto) {
        Instructor instructor = Instructor
                .builder()
                .id(instructordto.getId())
                .name(instructordto.getName())
                .lastName(instructordto.getLastName())
                .phoneNumber(instructordto.getPhoneNumber())
                .document(instructordto.getDocument())
                .build();
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
}
