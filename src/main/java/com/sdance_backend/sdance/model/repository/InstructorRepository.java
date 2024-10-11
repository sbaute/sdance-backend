package com.sdance_backend.sdance.model.repository;

import com.sdance_backend.sdance.model.entity.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

}
