package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.InstructorDTO;

import java.util.List;
import java.util.UUID;

public interface IInstructorService {

   List<InstructorDTO> getAllInstructors();
   InstructorDTO getInstructorById(UUID id);
   InstructorDTO createInstructor(InstructorDTO instructorRequestDto);
   InstructorDTO updateInstructor(InstructorDTO instructorRequestDto, UUID id);
   void deleteInstructor(UUID iD);
}
