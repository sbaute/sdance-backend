package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.StudentDto;
import com.sdance_backend.sdance.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@Slf4j
@AllArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
            return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> create (@Valid @RequestBody StudentDto studentRequestDto){
       return ResponseEntity.ok(studentService.createStudent(studentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update (@Valid @RequestBody StudentDto studentRequestDto, @PathVariable UUID id){
        return ResponseEntity.ok(studentService.updateStudent(studentRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }



}
