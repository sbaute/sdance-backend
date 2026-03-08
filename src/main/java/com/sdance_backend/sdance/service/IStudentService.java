package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;
import com.sdance_backend.sdance.dto.StudentRequest;
import com.sdance_backend.sdance.dto.StudentResponse;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    PageResponseDTO<StudentResponse> getAllStudents(int page);
    StudentResponse getStudentById(UUID id);
    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse updateStudent(StudentRequest studentRequest, UUID id);
    void deleteStudent(UUID id);
    PageResponseDTO<StudentResponse> searchStudents(SearchTermDTO searchTermRequestDTO, int page, int size);
}
