package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.*;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IDanceClassService;
import com.sdance_backend.sdance.messages.Actions;
import com.sdance_backend.sdance.messages.ResponseBuilderMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/dance-classes")
@Slf4j
@AllArgsConstructor
public class DanceClassController {

    private final IDanceClassService danceClassService;
    private final ResponseBuilderMessage responseBuilderMessage;

    @PreAuthorize("hasAuthority('CLASS_READ_ALL')")
    @GetMapping
    public ResponseEntity<ResponseMessage<List<DanceClassDTO>>> getAll() {
       return responseBuilderMessage.success(DanceClass.class, Actions.LIST_RETRIEVED, danceClassService.getAllDanceClass());
    }

    @PreAuthorize("hasAuthority('CLASS_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<DanceClassDTO>> getById(@PathVariable UUID id) {
        return responseBuilderMessage.success(DanceClass.class, Actions.RETRIEVED, danceClassService.getDanceClassById(id));
    }

    @PreAuthorize("hasAuthority('CLASS_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseMessage<DanceClassDTO>> create (@RequestBody DanceClassRequestDTO danceClassRequestDTO) {
        return responseBuilderMessage.success(DanceClass.class, Actions.CREATED, danceClassService.createDanceClass(danceClassRequestDTO));

    }

    @PreAuthorize("hasAuthority('CLASS_SEARCH')")
    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<DanceClassDTO>> search (@RequestBody(required = false) SearchTermDTO description,
                                                                  @RequestParam(defaultValue = "0") int pag,
                                                                  @RequestParam int size){
        int validSize = Math.min(Math.max(1, size), 100);
        return ResponseEntity.ok(danceClassService.searchDanceClass(description,pag, validSize));
    }

    @PreAuthorize("hasAuthority('CLASS_MODIFY')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<DanceClassDTO>> update(@RequestBody DanceClassRequestDTO danceClassRequestDTO, @PathVariable UUID id) {
        return responseBuilderMessage.success(DanceClass.class, Actions.UPDATED, danceClassService.updateDanceClass(danceClassRequestDTO,id));
    }

    @PreAuthorize("hasAuthority('CLASS_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        danceClassService.deleteDanceClass(id);
        return ResponseEntity.noContent().build();
    }
}
