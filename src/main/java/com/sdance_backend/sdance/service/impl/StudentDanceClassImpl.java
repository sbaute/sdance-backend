package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.entity.Student;
import com.sdance_backend.sdance.exceptions.CustomException;
import com.sdance_backend.sdance.exceptions.ErrorType;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.mapper.StudentMapper;
import com.sdance_backend.sdance.service.IStudentDanceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentDanceClassImpl implements IStudentDanceClass {

    private final StudentServiceImpl studentService;

    private final StudentMapper studentMapper;
    private final DanceClassMapper danceClassMapper;


    @Override
    public List<DanceClassNameDTO> getClassesByStudentId(UUID studentId) {
      Student student = studentService.getStudent(studentId);

      if (student.getDanceClasses().isEmpty()) {
          throw new CustomException(ErrorType.DANCE_CLASS_LIST_EMPTY);
      }

        return  danceClassMapper.toNameDTOList(student.getDanceClasses());
    }





}
