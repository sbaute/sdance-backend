package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.dto.InstructorRequest;
import com.sdance_backend.sdance.dto.InstructorResponse;
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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('INSTRUCTOR_READ_ALL')")
    @GetMapping
    public ResponseEntity<ResponseMessage<List<InstructorResponse>>> getAll() {
        return responseBuilderMessage.success(Instructor.class, Actions.LIST_RETRIEVED, instructorService.getAllInstructors());
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<InstructorResponse>> getById(@PathVariable UUID id) {
        return responseBuilderMessage.success(Instructor.class, Actions.RETRIEVED, instructorService.getInstructorById(id));
    }


    @PreAuthorize("hasAuthority('INSTRUCTOR_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseMessage<InstructorResponse>> create (@Valid  @RequestBody InstructorRequest instructorRequestDto){
        return responseBuilderMessage.success(Instructor.class, Actions.CREATED, instructorService.createInstructor(instructorRequestDto));
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR_SEARCH')")
    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<InstructorResponse>> search (@RequestBody(required = false) SearchTermDTO description,
                                                                      @RequestParam(defaultValue = "0") int pag,
                                                                      @RequestParam int size){
        int validSize = Math.min(Math.max(1, size), 100);
        return ResponseEntity.ok(instructorService.searchInstructors(description,pag, validSize));
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR_MODIFY')")
    @PutMapping("/{id}")
    public  ResponseEntity<ResponseMessage<InstructorResponse>> update (@Valid @RequestBody InstructorRequest instructorRequestDto, @PathVariable UUID id){
        return responseBuilderMessage.success(Instructor.class, Actions.UPDATED, instructorService.updateInstructor(instructorRequestDto, id));
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){
      instructorService.deleteInstructor(id);
      return ResponseEntity.noContent().build();
    }

}
