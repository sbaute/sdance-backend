package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.danceClass.AddStudentsToDanceClassDto;
import com.sdance_backend.sdance.dto.danceClass.DanceClassDto;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.DanceClass;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IDanceClassService;
import com.sdance_backend.sdance.service.IInstructorService;
import com.sdance_backend.sdance.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class DanceClassController {

    @Autowired
    private IDanceClassService danceClassService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IInstructorService instructorService;

    @GetMapping("danceClass")
    public ResponseEntity<?> getAll() {
        List<DanceClass> danceClasses = danceClassService.getAllDanceClass();

        List<DanceClassDto> danceClassDto = danceClasses.stream()
                .map(danceClass -> {
                    List<StudentNameDto> studentNameDto = studentService.mapToStudentNameDto(danceClass.getStudents());
                    InstructorNameDto instructorNameDto = instructorService.mapToInstructorNameDto(danceClass.getInstructor());

                    return DanceClassDto.builder()
                            .id(danceClass.getId())
                            .className(danceClass.getClassName())
                            .daysOfWeek(danceClass.getDaysOfWeek())
                            .classTime(danceClass.getClassTime())
                            .instructor(instructorNameDto)
                            .students(studentNameDto)
                            .build();
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseMessage
                .builder()
                .message("" )
                .object(danceClassDto)
                .build(),
                HttpStatus.OK);

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
            List<StudentNameDto> studentNameDto = studentService.mapToStudentNameDto(danceClass.getStudents());
            InstructorNameDto instructorNameDto = instructorService.mapToInstructorNameDto(danceClass.getInstructor());

            DanceClassDto danceClassDto = DanceClassDto.builder()
                    .id(danceClass.getId())
                    .className(danceClass.getClassName())
                    .daysOfWeek(danceClass.getDaysOfWeek())
                    .classTime(danceClass.getClassTime())
                    .instructor(instructorNameDto)
                    .students(studentNameDto)
                    .build();

            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("" )
                    .object(danceClassDto)
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
            DanceClass danceClassSave = danceClassService.createDanceClass(danceClassDto);
            DanceClassDto  danceClassSaveDto = DanceClassDto.builder()
                    .id(danceClassSave.getId())
                    .className(danceClassSave.getClassName())
                    .daysOfWeek(danceClassSave.getDaysOfWeek())
                    .classTime(danceClassSave.getClassTime())
                    .instructor(instructorService.mapToInstructorNameDto(danceClassSave.getInstructor()))
                    .students((danceClassSave.getStudents() != null && !danceClassSave.getStudents().isEmpty())
                            ? studentService.mapToStudentNameDto(danceClassSave.getStudents())
                            : Collections.emptyList())
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Dance class save")
                    .object(danceClassSaveDto)
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

    @PostMapping("danceclass/addStudentsToDanceClass/")
    public ResponseEntity<?> addStudentsToDanceClass(@RequestBody AddStudentsToDanceClassDto studentAndDanceClassDto){
        danceClassService.addStudentsToDanceClass(studentAndDanceClassDto);

        return new ResponseEntity<>(ResponseMessage.builder()
                .message("Students add to class " + danceClassService.getDanceClassById(studentAndDanceClassDto.getDanceClassId()).getClassName())
                .object(null)
                .build(),
                HttpStatus.OK);
    }

    @PutMapping("danceClass/{id}")
    public ResponseEntity<?> update (@RequestBody DanceClassDto danceClassDto, @PathVariable Integer id){
        try{
            if(danceClassService.existsById(id)){
                danceClassDto.setId(id);
                DanceClass danceClassUpdate = danceClassService.updateDanceClass(danceClassDto);
                DanceClassDto.builder()
                        .id(danceClassUpdate.getId())
                        .className(danceClassUpdate.getClassName())
                        .daysOfWeek(danceClassUpdate.getDaysOfWeek())
                        .classTime(danceClassUpdate.getClassTime())
                        .instructor(instructorService.mapToInstructorNameDto(danceClassUpdate.getInstructor()))
                        .students(studentService.mapToStudentNameDto(danceClassUpdate.getStudents()))
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
            if (danceClassDelete == null) {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("Dance class not found")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            danceClassService.deleteDanceClass(danceClassDelete);
            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("The Dance class was deleted")
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
