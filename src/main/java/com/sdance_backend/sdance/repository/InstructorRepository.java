package com.sdance_backend.sdance.repository;

import com.sdance_backend.sdance.entity.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, UUID> {

}
