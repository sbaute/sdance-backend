package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.dto.instructor.InstructorDTO;
import com.sdance_backend.sdance.dto.instructor.InstructorRequestDTO;
import com.sdance_backend.sdance.service.IInstructorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/instructors")
@Slf4j
@AllArgsConstructor
public class InstructorController {

    private final IInstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAll() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getById(@PathVariable UUID id) {
     return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @PostMapping
    public ResponseEntity<InstructorDTO> create (@RequestBody InstructorRequestDTO instructorRequestDto){
       return ResponseEntity.ok(instructorService.createInstructor(instructorRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> update (@RequestBody InstructorRequestDTO instructorRequestDto, @PathVariable UUID id){
       return ResponseEntity.ok(instructorService.updateInstructor(instructorRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){
      instructorService.deleteInstructor(id);
      return ResponseEntity.noContent().build();
    }

}
