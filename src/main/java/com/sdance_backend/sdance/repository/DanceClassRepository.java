package com.sdance_backend.sdance.repository;

import com.sdance_backend.sdance.model.DanceClass;
import com.sdance_backend.sdance.model.Instructor;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DanceClassRepository extends CrudRepository<DanceClass, Integer> {

    boolean existsByInstructorAndDaysOfWeekAndClassTime(Instructor instructor, Days daysOfWeek, Hour classTime);

}
