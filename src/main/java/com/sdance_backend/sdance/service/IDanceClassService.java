package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.DanceClassResponse;
import com.sdance_backend.sdance.dto.DanceClassRequest;
import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;

import java.util.List;
import java.util.UUID;

public interface IDanceClassService {
    List<DanceClassResponse> getAllDanceClass();
    DanceClassResponse getDanceClassById(UUID id);
    DanceClassResponse createDanceClass(DanceClassRequest danceClassRequest);
    DanceClassResponse updateDanceClass(DanceClassRequest danceClassRequest, UUID id);
    void deleteDanceClass(UUID id);
    PageResponseDTO<DanceClassResponse> searchDanceClass(SearchTermDTO searchRequest, int page, int size);

}
