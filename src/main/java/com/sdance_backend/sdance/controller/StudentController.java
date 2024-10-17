package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.model.dto.student.StudentDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.entity.Student;
import com.sdance_backend.sdance.model.payload.ResponseMessage;
import com.sdance_backend.sdance.model.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("student")
    public void getAll() {
        studentService.getAllStudents();
    }

    @GetMapping("student/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try{
            Student student = studentService.getStudentById(id);
            if(student == null) {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("The student of id " + id + " was not found")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("")
                    .object(student)
                    .build(),
                    HttpStatus.OK);

        }catch (DataAccessException exDT) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("student")
    public ResponseEntity<?> create (@RequestBody StudentDto studentDto){
        try{
            Student studentSave = studentService.createUpdateStudent(studentDto);
           StudentDto.builder()
                    .id(studentSave.getId())
                    .name(studentSave.getName())
                    .lastName(studentSave.getLastName())
                    .document(studentSave.getDocument())
                    .phoneNumber(studentSave.getPhoneNumber())
                    .danceClassesId(studentSave.getDanceClasses()
                    .stream()
                    .map(DanceClass::getId)
                    .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Student save")
                    .object(studentDto)
                    .build(),
                    HttpStatus.CREATED);
        }catch (DataAccessException exDT) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("student/{id}")
    public ResponseEntity<?> update (@RequestBody StudentDto studentDto, @PathVariable Integer id){
        try{
            if(studentService.existsById(id)){
                studentDto.setId(id);
                Student studentUpdate = studentService.createUpdateStudent(studentDto);
                StudentDto.builder()
                        .id(studentUpdate.getId())
                        .name(studentUpdate.getName())
                        .lastName(studentUpdate.getLastName())
                        .document(studentUpdate.getDocument())
                        .phoneNumber(studentUpdate.getPhoneNumber())
                        .build();
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("Student Update")
                        .object(studentDto)
                        .build(),
                        HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("The student who wants to update is not found")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDT) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id){
        try {
            Student studentDelete = studentService.getStudentById(id);
            studentService.deleteStudent(studentDelete);
            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("The student was deleted")
                    .object(studentDelete)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
