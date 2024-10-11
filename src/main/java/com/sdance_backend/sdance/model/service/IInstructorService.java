package com.sdance_backend.sdance.model.service;

import com.sdance_backend.sdance.model.dto.InstructorDto;
import com.sdance_backend.sdance.model.entity.Instructor;

import java.util.List;

public interface IInstructorService {

   List<Instructor> getAllInstructors();

   Instructor getInstructorById(Integer id);

   Instructor createAndUpdateInstructor(InstructorDto instructorDto);

   void deleteInstructor(Instructor instructor);

   Boolean existById(Integer id);

}
