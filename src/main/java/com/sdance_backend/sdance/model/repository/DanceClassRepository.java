package com.sdance_backend.sdance.model.repository;

import com.sdance_backend.sdance.model.dto.instructor.InstructorNameDto;
import com.sdance_backend.sdance.model.entity.DanceClass;
import com.sdance_backend.sdance.model.entity.Instructor;
import com.sdance_backend.sdance.model.entity.enums.Days;
import com.sdance_backend.sdance.model.entity.enums.Hour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DanceClassRepository extends CrudRepository<DanceClass, Integer> {

    boolean existsByInstructorAndDaysOfWeekAndClassTime(Instructor instructor, Days daysOfWeek, Hour classTime);

}
