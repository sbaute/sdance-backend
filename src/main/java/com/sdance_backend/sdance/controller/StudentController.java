package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.StudentDto;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IStudentService;
import com.sdance_backend.sdance.utils.Actions;
import com.sdance_backend.sdance.utils.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@Slf4j
@AllArgsConstructor
public class StudentController {

    private final IStudentService studentService;
    private final ResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<StudentDto>>> getAll() {
        return responseBuilder.success(Student.class, Actions.LIST_RETRIEVED, studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<StudentDto>> getById(@PathVariable UUID id) {
        return responseBuilder.success(Student.class, Actions.RETRIEVED, studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<StudentDto>> create (@Valid @RequestBody StudentDto studentRequestDto){
        return responseBuilder.success(Student.class, Actions.CREATED, studentService.createStudent(studentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<StudentDto>> update (@Valid @RequestBody StudentDto studentRequestDto, @PathVariable UUID id){
        return responseBuilder.success(Student.class, Actions.UPDATED, studentService.updateStudent(studentRequestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }



}
