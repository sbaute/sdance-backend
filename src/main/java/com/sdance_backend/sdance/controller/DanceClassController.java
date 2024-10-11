package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.model.dto.DanceClassDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.entity.Student;
import com.sdance_backend.sdance.model.payload.ResponseMessage;
import com.sdance_backend.sdance.model.service.IDanceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class DanceClassController {

    @Autowired
    private IDanceClassService danceClassService;

    @GetMapping("danceClass")
    public void getAll() {
        danceClassService.getAllDanceClass();
    }

    @GetMapping("danceClass/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try{
            DanceClass danceClass = danceClassService.getDanceClassById(id);
            if(danceClass == null) {
                return new ResponseEntity<>(ResponseMessage
                        .builder()
                        .message("The DanceClass of id " + id + " was not found" )
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("" )
                    .object(danceClass)
                    .build(),
                    HttpStatus.OK);
            } catch (DataAccessException exDT){
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PostMapping("danceClass")
    public ResponseEntity<?> create (@RequestBody DanceClassDto danceClassDto){
        try{
            DanceClass danceClassSave = danceClassService.createUpdateDanceClass(danceClassDto);
            DanceClassDto.builder()
                    .id(danceClassSave.getId())
                    .className(danceClassSave.getClassName())
                    .dayOfWeek(danceClassSave.getDaysOfWeek())
                    .classTime(danceClassSave.getClassTime())
                    .instructorId(danceClassSave.getInstructor().getId())
                    .studentsId(danceClassSave.getStudents().stream()
                            .map(Student::getId)
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Dance class save")
                    .object(danceClassSave)
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

    @PutMapping("danceClass/{id}")
    public ResponseEntity<?> update (@RequestBody DanceClassDto danceClassDto, @PathVariable Integer id){
        try{
            if(danceClassService.existsById(id)){
                danceClassDto.setId(id);
                DanceClass danceClassUpdate = danceClassService.createUpdateDanceClass(danceClassDto);
                DanceClassDto.builder()
                        .id(danceClassUpdate.getId())
                        .className(danceClassUpdate.getClassName())
                        .dayOfWeek(danceClassUpdate.getDaysOfWeek())
                        .classTime(danceClassUpdate.getClassTime())
                        .instructorId(danceClassUpdate.getInstructor().getId()) // Solo el ID del instructor
                        .studentsId(danceClassUpdate.getStudents().stream()
                                .map(Student::getId)
                                .collect(Collectors.toList())) // Lista de IDs de estudiantes
                        .build();
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("Dance class Update")
                        .object(danceClassDto)
                        .build(),
                        HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("The Dance class who wants to update is not found")
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
    @DeleteMapping("danceClass/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id){
        try {
            DanceClass danceClassDelete = danceClassService.getDanceClassById(id);
            danceClassService.deleteDanceClass(danceClassDelete);
            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("The Dance class was deleted")
                    .object(danceClassDelete)
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
