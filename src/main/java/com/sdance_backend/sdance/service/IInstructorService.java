package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.instructor.InstructorDto;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.model.Instructor;

import java.util.List;

public interface IInstructorService {

   List<Instructor> getAllInstructors();
   Instructor getInstructorById(Integer id);
   Instructor createAndUpdateInstructor(InstructorDto instructorDto);
   void deleteInstructor(Instructor instructor);
   Boolean existById(Integer id);
   InstructorNameDto mapToInstructorNameDto(Instructor instructor);


}
