package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.DanceClassDTO;
import com.sdance_backend.sdance.dto.DanceClassRequestDTO;
import com.sdance_backend.sdance.service.IDanceClassService;
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

    @GetMapping
    public ResponseEntity<List<DanceClassDTO>> getAll() {
       return ResponseEntity.ok(danceClassService.getAllDanceClass());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DanceClassDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(danceClassService.getDanceClassById(id));
    }
    @PostMapping
    public ResponseEntity<DanceClassDTO> create (@Valid @RequestBody DanceClassRequestDTO danceClassRequestDTO) {
        return ResponseEntity.ok(danceClassService.createDanceClass(danceClassRequestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DanceClassDTO> update(@Valid @RequestBody DanceClassRequestDTO danceClassRequestDTO, @PathVariable UUID id) {
        return ResponseEntity.ok(danceClassService.updateDanceClass(danceClassRequestDTO,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        danceClassService.deleteDanceClass(id);
        return ResponseEntity.noContent().build();
    }
}
