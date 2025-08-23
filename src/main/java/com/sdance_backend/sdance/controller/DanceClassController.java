package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.DanceClassDTO;
import com.sdance_backend.sdance.dto.DanceClassRequestDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IDanceClassService;
import com.sdance_backend.sdance.utils.Actions;
import com.sdance_backend.sdance.utils.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/dance-classes")
@Slf4j
@AllArgsConstructor
public class DanceClassController {

    private final IDanceClassService danceClassService;
    private final ResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<DanceClassDTO>>> getAll() {
       return responseBuilder.success(DanceClass.class, Actions.LIST_RETRIEVED, danceClassService.getAllDanceClass());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<DanceClassDTO>> getById(@PathVariable UUID id) {
        return responseBuilder.success(DanceClass.class, Actions.RETRIEVED, danceClassService.getDanceClassById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<DanceClassDTO>> create (@Valid @RequestBody DanceClassRequestDTO danceClassRequestDTO) {
        return responseBuilder.success(DanceClass.class, Actions.CREATED, danceClassService.createDanceClass(danceClassRequestDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<DanceClassDTO>> update(@Valid @RequestBody DanceClassRequestDTO danceClassRequestDTO, @PathVariable UUID id) {
        return responseBuilder.success(DanceClass.class, Actions.UPDATED, danceClassService.updateDanceClass(danceClassRequestDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        danceClassService.deleteDanceClass(id);
        return ResponseEntity.noContent().build();
    }
}
