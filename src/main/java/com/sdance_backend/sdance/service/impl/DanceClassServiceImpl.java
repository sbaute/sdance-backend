package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.danceClass.AddStudentsToDanceClassDto;
import com.sdance_backend.sdance.dto.danceClass.DanceClassDto;
import com.sdance_backend.sdance.dto.danceClass.DanceClassNameDto;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.dto.student.StudentNameDto;
import com.sdance_backend.sdance.model.DanceClass;
import com.sdance_backend.sdance.model.Instructor;
import com.sdance_backend.sdance.model.Student;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.repository.InstructorRepository;
import com.sdance_backend.sdance.repository.StudentRepository;
import com.sdance_backend.sdance.service.IDanceClassService;
import com.sdance_backend.sdance.service.IInstructorService;
import com.sdance_backend.sdance.service.IStudentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IInstructorService instructorService;

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

        if(danceClassDto.getStudents().isEmpty()){
            danceClass = DanceClass.builder()
                    .className(danceClassDto.getClassName())
                    .daysOfWeek(danceClassDto.getDaysOfWeek())
                    .classTime(danceClassDto.getClassTime())
                    .instructor(instructorRepository.findById(danceClassDto.getInstructor().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Instructor not found")))
                    .build();
        } else {
            danceClass = DanceClass.builder()
                    .className(danceClassDto.getClassName())
                    .daysOfWeek(danceClassDto.getDaysOfWeek())
                    .classTime(danceClassDto.getClassTime())
                    .instructor(instructorRepository.findById(danceClassDto.getInstructor().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Instructor not found")))
                    .students(danceClassDto.getStudents().stream()
                            .map(studentNameDto -> studentRepository.findById(studentNameDto.getId())
                                    .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                            )
                            .collect(Collectors.toList()))
                    .build();
        }


        return danceClassRepository.save(danceClass);
    }

    @Override
    @Transactional
    public DanceClass updateDanceClass(DanceClassDto danceClassDto){
        DanceClass danceClass = null;
        if((danceClassDto.getId() != null && danceClassRepository.existsById(danceClassDto.getId()))){
            danceClass = danceClassRepository.findById(danceClassDto.getId()).get();
        }
        danceClass.setClassName(danceClassDto.getClassName());
        danceClass.setDaysOfWeek(danceClassDto.getDaysOfWeek());
        danceClass.setClassTime(danceClassDto.getClassTime());
        danceClass.setInstructor(instructorRepository.findById(danceClassDto.getInstructor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found")));
       /* danceClass.setStudents(danceClassDto.getStudent().stream()
                .map(studentNameDto -> studentRepository.findById(studentNameDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                )
                .collect(Collectors.toList()));*/
        return danceClassRepository.save(danceClass);
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

           List<Integer> invalidStudentsId = studentAndDanceClassDto.getStudentId().stream()
                   .filter(studentId -> !studentRepository.existsById(studentId))
                   .collect(Collectors.toList());

           if(!invalidStudentsId.isEmpty()){
                throw new IllegalArgumentException("The student IDs are invalid: " + invalidStudentsId);
           }

           List<Student> students = StreamSupport.stream(studentRepository.findAllById(studentAndDanceClassDto.getStudentId()).spliterator(), false)
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
    public DanceClassDto mapToDanceClassDtoWhitStudents(DanceClass danceClass) {
        List<StudentNameDto> studentNameDtos = studentService.mapToStudentNameDto(danceClass.getStudents());
        return DanceClassDto.builder()
                .className(danceClass.getClassName())
                .daysOfWeek(danceClass.getDaysOfWeek())
                .classTime(danceClass.getClassTime())
                .students(studentNameDtos)
                .build();
    }

    @Override
    public List<DanceClassNameDto> mapToDanceClassDtoWhitInstructor(List<DanceClass> danceClasses) {
        return danceClasses.stream()
                .map(danceClass -> DanceClassNameDto.builder()
                        .id(danceClass.getId())
                        .className(danceClass.getClassName())
                        .instructor(instructorService.mapToInstructorNameDto(danceClass.getInstructor()))
                        .build()).collect(Collectors.toList());
    }
}
