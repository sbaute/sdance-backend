package com.sdance_backend.sdance.controller;

import com.sdance_backend.sdance.dto.PageResponseDTO;
import com.sdance_backend.sdance.dto.SearchTermDTO;
import com.sdance_backend.sdance.dto.StudentRequest;
import com.sdance_backend.sdance.dto.StudentResponse;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.enums.status.StudentStatus;
import com.sdance_backend.sdance.payload.ResponseMessage;
import com.sdance_backend.sdance.service.IStudentService;
import com.sdance_backend.sdance.messages.Actions;
import com.sdance_backend.sdance.messages.ResponseBuilderMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/students")
@Slf4j
@AllArgsConstructor
public class StudentController {

    private final IStudentService studentService;
    private final ResponseBuilderMessage responseBuilderMessage;

    @PreAuthorize("hasAuthority('STUDENT_READ_ALL')")
    @GetMapping
    public ResponseEntity<ResponseMessage<PageResponseDTO<StudentResponse>>> getAll(@RequestParam(defaultValue = "0") int page) {
        return responseBuilderMessage.success(Student.class, Actions.LIST_RETRIEVED, studentService.getAllStudents(page));
    }

    @PreAuthorize("hasAuthority('STUDENT_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<StudentResponse>> getById(@PathVariable UUID id) {
        return responseBuilderMessage.success(Student.class, Actions.RETRIEVED, studentService.getStudentById(id));
    }

    @PreAuthorize("hasAuthority('STUDENT_CREATE')")
    @PostMapping
    public ResponseEntity<ResponseMessage<StudentResponse>> create (@Valid @RequestBody StudentRequest studentRequestDto){
        return responseBuilderMessage.success(Student.class, Actions.CREATED, studentService.createStudent(studentRequestDto));
    }

    @PreAuthorize("hasAuthority('STUDENT_SEARCH')")
    @PostMapping("/search")
    public ResponseEntity<PageResponseDTO<StudentResponse>> search (@RequestBody(required = false) SearchTermDTO description,
                                                                   @RequestParam(defaultValue = "0") int pag,
                                                                   @RequestParam int size){
        int validSize = Math.min(Math.max(1, size), 10);
        return ResponseEntity.ok(studentService.searchStudents(description,pag, validSize));
    }

    @PreAuthorize("hasAuthority('STUDENT_UPDATE_STATUS')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<StudentStatus> updateStudentStatus(
            @PathVariable UUID id,
            @RequestParam StudentStatus status) {

        StudentStatus updatedStatus = studentService.updateStatus(id, status);

        return ResponseEntity.ok(updatedStatus);
    }

    @PreAuthorize("hasAuthority('STUDENT_MODIFY')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<StudentResponse>> update (@Valid @RequestBody StudentRequest studentRequestDto, @PathVariable UUID id){
        return responseBuilderMessage.success(Student.class, Actions.UPDATED, studentService.updateStudent(studentRequestDto,id));
    }

    @PreAuthorize("hasAuthority('STUDENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.deactivateStudent(id);
        //studentService.deleteStudent(id); no elimino doy baja administrativa
        return ResponseEntity.noContent().build();
    }

}
