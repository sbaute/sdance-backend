package com.sdance_backend.sdance.model.service.impl;

import com.sdance_backend.sdance.model.dto.DanceClassDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.entity.Instructor;
import com.sdance_backend.sdance.model.entity.Student;
import com.sdance_backend.sdance.model.repository.DanceClassRepository;
import com.sdance_backend.sdance.model.repository.InstructorRepository;
import com.sdance_backend.sdance.model.repository.StudentRepository;
import com.sdance_backend.sdance.model.service.IDanceClassService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DanceClassServiceImpl implements IDanceClassService {

    @Autowired
    private DanceClassRepository danceClassRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<DanceClass> getAllDanceClass() {
        return (List<DanceClass>) danceClassRepository.findAll();
    }

    @Override
    public DanceClass getDanceClassById(Integer id) {
        return danceClassRepository.findById(id).orElse(null);
    }

    @Override
    public DanceClass createUpdateDanceClass(DanceClassDto danceClassDto) {
        // Crear la entidad DanceClass
        DanceClass danceClass = DanceClass.builder()
                .id(danceClassDto.getId())
                .className(danceClassDto.getClassName())
                .daysOfWeek(danceClassDto.getDayOfWeek())
                .classTime(danceClassDto.getClassTime())
                .instructor(instructorRepository.findById(danceClassDto.getInstructorId())
                        .orElseThrow(() -> new EntityNotFoundException("Instructor not found")))
                .students(new ArrayList<>())
                .build();

        // Agregar los estudiantes a la lista de la clase de danza (opcional)
        if(danceClassDto.getStudentsId() != null){
            for (Integer studentId : danceClassDto.getStudentsId()) {
                Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new EntityNotFoundException("Student not found"));
                danceClass.getStudents().add(student);
            }
        }


        // Guardar la clase de danza con los estudiantes
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
}
