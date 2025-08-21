package com.sdance_backend.sdance.repository;

import com.sdance_backend.sdance.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {

    //@Query("SELECT d FROM DanceClass d JOIN d.students s WHERE s.id = :studentId")
    //List<DanceClass> findClassesByStudentId(@Param("studentId") Integer studentId);
}
