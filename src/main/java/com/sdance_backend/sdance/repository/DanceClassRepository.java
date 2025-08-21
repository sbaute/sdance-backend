package com.sdance_backend.sdance.repository;

import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.entity.Instructor;
import com.sdance_backend.sdance.enums.Days;
import com.sdance_backend.sdance.enums.Hour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DanceClassRepository extends CrudRepository<DanceClass, UUID> {

    boolean existsByInstructorAndDaysOfWeekAndClassTime(Instructor instructor, Days daysOfWeek, Hour classTime);

}
