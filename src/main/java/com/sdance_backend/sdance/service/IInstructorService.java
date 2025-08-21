package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.instructor.InstructorDTO;
import com.sdance_backend.sdance.dto.instructor.InstructorRequestDTO;

import java.util.List;
import java.util.UUID;

public interface IInstructorService {

   List<InstructorDTO> getAllInstructors();
   InstructorDTO getInstructorById(UUID id);
   InstructorDTO createInstructor(InstructorRequestDTO instructorRequestDto);
   InstructorDTO updateInstructor(InstructorRequestDTO instructorRequestDto, UUID id);
   void deleteInstructor(UUID iD);
}
