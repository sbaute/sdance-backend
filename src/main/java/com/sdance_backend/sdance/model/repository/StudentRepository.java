package com.sdance_backend.sdance.model.repository;

import com.sdance_backend.sdance.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {

    //@Query("SELECT d FROM DanceClass d JOIN d.students s WHERE s.id = :studentId")
    //List<DanceClass> findClassesByStudentId(@Param("studentId") Integer studentId);
}
