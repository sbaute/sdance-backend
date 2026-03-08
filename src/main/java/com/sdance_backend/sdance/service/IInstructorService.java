package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.InstructorRequest;
import com.sdance_backend.sdance.dto.InstructorResponse;
import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;

import java.util.List;
import java.util.UUID;

public interface IInstructorService {

   List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(UUID id);
    InstructorResponse createInstructor(InstructorRequest instructorRequestDto);
    InstructorResponse updateInstructor(InstructorRequest instructorRequestDto, UUID id);
   void deleteInstructor(UUID iD);
   PageResponseDTO<InstructorResponse> searchInstructors(SearchTermDTO searchRequest, int page, int size);
}
