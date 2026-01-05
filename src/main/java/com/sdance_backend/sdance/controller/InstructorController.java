package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.dto.InstructorDTO;
import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;
import com.sdance_backend.sdance.entity.Instructor;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IInstructorService;
import com.sdance_backend.sdance.messages.Actions;
import com.sdance_backend.sdance.messages.ResponseBuilderMessage;
import jakarta.validation.Valid;
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
    private final ResponseBuilderMessage responseBuilderMessage;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<InstructorDTO>>> getAll() {
        return responseBuilderMessage.success(Instructor.class, Actions.LIST_RETRIEVED, instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<InstructorDTO>> getById(@PathVariable UUID id) {
        return responseBuilderMessage.success(Instructor.class, Actions.RETRIEVED, instructorService.getInstructorById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<InstructorDTO>> create (@Valid  @RequestBody InstructorDTO instructorRequestDto){
        return responseBuilderMessage.success(Instructor.class, Actions.CREATED, instructorService.createInstructor(instructorRequestDto));
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<InstructorDTO>> search (@RequestBody(required = false) SearchTermDTO description,
                                                               @RequestParam(defaultValue = "0") int pag,
                                                               @RequestParam int size){
        int validSize = Math.min(Math.max(1, size), 100);
        return ResponseEntity.ok(instructorService.searchInstructors(description,pag, validSize));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ResponseMessage<InstructorDTO>> update (@Valid @RequestBody InstructorDTO instructorRequestDto, @PathVariable UUID id){
        return responseBuilderMessage.success(Instructor.class, Actions.UPDATED, instructorService.updateInstructor(instructorRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){
      instructorService.deleteInstructor(id);
      return ResponseEntity.noContent().build();
    }

}
