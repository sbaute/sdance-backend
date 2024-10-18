package com.sdance_backend.sdance.model.service.impl;

import com.sdance_backend.sdance.model.dto.danceClass.AddStudentsToDanceClassDto;
import com.sdance_backend.sdance.model.dto.danceClass.DanceClassDto;
import com.sdance_backend.sdance.model.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.model.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.entity.Instructor;
import com.sdance_backend.sdance.model.entity.Student;
import com.sdance_backend.sdance.model.entity.enums.Days;
import com.sdance_backend.sdance.model.entity.enums.Hour;
import com.sdance_backend.sdance.model.repository.DanceClassRepository;
import com.sdance_backend.sdance.model.repository.InstructorRepository;
import com.sdance_backend.sdance.model.repository.StudentRepository;
import com.sdance_backend.sdance.model.service.IDanceClassService;
import com.sdance_backend.sdance.model.service.IStudentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DanceClassServiceImpl implements IDanceClassService {

    @Autowired
    private DanceClassRepository danceClassRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IStudentService studentService;

    @Override
    public List<DanceClass> getAllDanceClass() {
        return (List<DanceClass>) danceClassRepository.findAll();
    }

    @Override
    public DanceClass getDanceClassById(Integer id) {
        return danceClassRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public DanceClass createDanceClass(DanceClassDto danceClassDto) {
        DanceClass danceClass;

        if((danceClassDto.getId() != null && danceClassRepository.existsById(danceClassDto.getId()))){
            throw new EntityExistsException("A class with id already exist.");
        }

        if (isClassExists(danceClassDto.getInstructor(), danceClassDto.getDaysOfWeek(), danceClassDto.getClassTime())) {
            throw new EntityExistsException("A class with this instructor, day, and time already exists.");
        }

        danceClass = DanceClass.builder()
                .className(danceClassDto.getClassName())
                .daysOfWeek(danceClassDto.getDaysOfWeek())
                .classTime(danceClassDto.getClassTime())
                .instructor(instructorRepository.findById(danceClassDto.getInstructor().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Instructor not found")))
                .students(danceClassDto.getStudent().stream()
                        .map(studentNameDto -> studentRepository.findById(studentNameDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                        )
                        .collect(Collectors.toList()))
                .build();
        return danceClassRepository.save(danceClass);
    }

    @Override
    @Transactional
    public DanceClass updateDanceClass(DanceClassDto danceClassDto){
        /*if((danceClassDto.getId() != null && danceClassRepository.existsById(danceClassDto.getId()))){
            danceClass = danceClassRepository.findById(danceClassDto.getId()).get();
        }
        danceClass.setClassName(danceClassDto.getClassName());
        danceClass.setDaysOfWeek(danceClassDto.getDaysOfWeek());
        danceClass.setClassTime(danceClassDto.getClassTime());
        danceClass.setInstructor(instructorRepository.findById(danceClassDto.getInstructor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found")));
        danceClass.setStudents(danceClassDto.getStudent().stream()
                .map(studentNameDto -> studentRepository.findById(studentNameDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                )
                .collect(Collectors.toList()));*/
        return null;
    }

    @Override
    public boolean isClassExists(InstructorNameDto instructorDto, Days daysOfWeek, Hour classTime) {
        Instructor instructor = instructorRepository.findById(instructorDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

        return danceClassRepository.existsByInstructorAndDaysOfWeekAndClassTime(instructor, daysOfWeek, classTime);
    }

    @Override
    public DanceClass addStudentsToDanceClass(AddStudentsToDanceClassDto studentAndDanceClassDto) {
            DanceClass danceClass = danceClassRepository.findById(studentAndDanceClassDto.getDanceClassId()).orElseThrow( () -> new EntityNotFoundException("Dance class not found"));
            List<Student> students = StreamSupport.stream(studentRepository.findAllById(studentAndDanceClassDto.getStudentIds()).spliterator(), false)
                .collect(Collectors.toList());

            List<Student> newStudents = students.stream()
                .filter(student -> !danceClass.getStudents().contains(student))
                .collect(Collectors.toList());

            danceClass.getStudents().addAll(newStudents);

        return danceClassRepository.save(danceClass);
    }

    @Override
    public void deleteDanceClass(DanceClass danceClass) {
        danceClassRepository.delete(danceClass);
    }

    @Override
    public boolean existsById(Integer id) {
        return danceClassRepository.existsById(id);
    }

    @Override
    public DanceClassDto mapToDanceClassDto(DanceClass danceClass) {
        List<StudentNameDto> studentNameDtos = studentService.mapToStudentNameDto(danceClass.getStudents());
        return DanceClassDto.builder()
                .className(danceClass.getClassName())
                .daysOfWeek(danceClass.getDaysOfWeek())
                .classTime(danceClass.getClassTime())
                .student(studentNameDtos)
                .build();
    }
}
