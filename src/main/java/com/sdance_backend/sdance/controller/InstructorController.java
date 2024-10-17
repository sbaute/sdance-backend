package com.sdance_backend.sdance.controller;


import com.sdance_backend.sdance.model.dto.DanceClassDto;
import com.sdance_backend.sdance.model.dto.InstructorDto;
import com.sdance_backend.sdance.model.entity.Instructor;
import com.sdance_backend.sdance.model.payload.ResponseMessage;
import com.sdance_backend.sdance.model.service.IDanceClassService;
import com.sdance_backend.sdance.model.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class InstructorController {

    @Autowired
    IInstructorService instructorService;

    @Autowired
    IDanceClassService danceClassService;

    @GetMapping("instructors")
    public ResponseEntity<?> getAll() {
       List<Instructor> instructors =  instructorService.getAllInstructors();

        List<InstructorDto> instructorDtos = instructors.stream()
                .map(instructor -> {
                    List<DanceClassDto> danceClassDto = instructor.getDanceClasses().stream()
                            .map(danceClass -> danceClassService.mapToDanceClassDto(danceClass))
                            .collect(Collectors.toList());

                    return InstructorDto.builder()
                            .id(instructor.getId())
                            .name(instructor.getName())
                            .lastName(instructor.getLastName())
                            .document(instructor.getDocument())
                            .phoneNumber(instructor.getPhoneNumber())
                            .danceClasses(danceClassDto)
                            .build();
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseMessage.builder()
                .message("")
                .object(instructorDtos)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("instructor/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            Instructor instructor = instructorService.getInstructorById(id);

            List<DanceClassDto> danceClassDto = instructor.getDanceClasses().stream()
                    .map(danceClass -> danceClassService.mapToDanceClassDto(danceClass))
                    .collect(Collectors.toList());

            InstructorDto instructorDtoGet = InstructorDto.builder()
                    .name(instructor.getName())
                    .lastName(instructor.getLastName())
                    .document(instructor.getDocument())
                    .phoneNumber(instructor.getPhoneNumber())
                    .danceClasses(danceClassDto)
                    .build();
            if(instructor == null) {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("The instructor of id " + id + " was not found")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("")
                    .object(instructorDtoGet)
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

    @PostMapping("instructor")
    public ResponseEntity<?> create (@RequestBody InstructorDto instructorDto){
        try{
            Instructor instructorSave = instructorService.createAndUpdateInstructor(instructorDto);
            InstructorDto savedDto = InstructorDto.builder()
                    .name(instructorSave.getName())
                    .lastName(instructorSave.getLastName())
                    .build();
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Instructor save")
                    .object(savedDto)
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

    @PutMapping("instructor/{id}")
    public ResponseEntity<?> update (@RequestBody InstructorDto instructorDto, @PathVariable Integer id){
        try{
            if(instructorService.existById(id)){
                instructorDto.setId(id);
                Instructor instructorUpdate = instructorService.createAndUpdateInstructor(instructorDto);


                InstructorDto updateDto = InstructorDto.builder()
                        .id(instructorUpdate.getId())
                        .name(instructorUpdate.getName())
                        .lastName(instructorUpdate.getLastName())
                        .phoneNumber(instructorUpdate.getPhoneNumber())
                        .document(instructorUpdate.getDocument())
                        .build();
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("Instructor Update")
                        .object(updateDto)
                        .build(),
                        HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(ResponseMessage.builder()
                        .message("The instructor who wants to update is not found")
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

    @DeleteMapping("instructor/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id){
        try {
            Instructor instructorDeleted = instructorService.getInstructorById(id);
            instructorService.deleteInstructor(instructorDeleted);
            return new ResponseEntity<>(ResponseMessage
                    .builder()
                    .message("The Instructor was deleted")
                    .object(instructorDeleted)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException exDT) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message(exDT.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







}
