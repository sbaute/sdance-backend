package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.messages.Actions;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.impl.StudentDanceClassImpl;
import com.sdance_backend.sdance.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students/{studentId}/danceClasses")
@Slf4j
@AllArgsConstructor
public class StudentDanceClassController {

    private final StudentDanceClassImpl studentDanceClassService;
    private final ResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<DanceClassNameDTO>>> getDanceClassByStudentId(@PathVariable UUID studentId) {
        return responseBuilder.success(Student.class, Actions.GET_DANCE_CLASS, studentDanceClassService.getClassesByStudentId(studentId));
    }


}
