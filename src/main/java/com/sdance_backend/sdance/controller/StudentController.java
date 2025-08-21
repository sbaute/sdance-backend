package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentRequestDto;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IDanceClassService;
import com.sdance_backend.sdance.service.IStudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<StudentDto> create (@RequestBody StudentRequestDto studentRequestDto){
       return ResponseEntity.ok(studentService.createStudent(studentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update (@RequestBody StudentRequestDto studentRequestDto, @PathVariable UUID id){
        return ResponseEntity.ok(studentService.updateStudent(studentRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/student/deleteClass/{studentId}")
    public ResponseEntity<?> deleteDanceClass (@PathVariable Integer studentId, @RequestBody List<Integer> danceClassId) {

            return null;
    }



}
