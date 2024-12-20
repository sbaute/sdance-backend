package com.sdance_backend.sdance.service;

import com.sdance_backend.sdance.dto.danceClass.AddStudentsToDanceClassDto;
import com.sdance_backend.sdance.dto.danceClass.DanceClassDto;
import com.sdance_backend.sdance.dto.danceClass.DanceClassNameDto;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.model.DanceClass;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;

import java.util.List;

public interface IDanceClassService {
    List<DanceClass> getAllDanceClass();
    DanceClass getDanceClassById(Integer id);
    DanceClass createDanceClass(DanceClassDto danceClassDto);
    DanceClass updateDanceClass(DanceClassDto danceClassDto);
    void deleteDanceClass(DanceClass danceClass);
    boolean existsById(Integer id);
    DanceClassDto mapToDanceClassDtoWhitStudents(DanceClass danceClass);
    List<DanceClassNameDto> mapToDanceClassDtoWhitInstructor(List<DanceClass> danceClasses);
    boolean isClassExists(InstructorNameDto instructorDto, Days daysOfWeek, Hour classTime);

    DanceClass addStudentsToDanceClass(AddStudentsToDanceClassDto studentAndDanceClassDto);
}
