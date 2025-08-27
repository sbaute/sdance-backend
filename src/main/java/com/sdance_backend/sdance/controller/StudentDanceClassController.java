package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.StudentDanceClassResponseDTO;
import com.sdance_backend.sdance.payload.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students/{studentId}/danceClasses")
@Slf4j
@AllArgsConstructor
public class StudentDanceClassController {

    @GetMapping
    public ResponseMessage<StudentDanceClassResponseDTO> getDanceClassByStudentId(@PathVariable UUID studentId, @PathVariable UUID classId) {

        return null;

    }
}
