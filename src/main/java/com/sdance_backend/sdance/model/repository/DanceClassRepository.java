package com.sdance_backend.sdance.model.repository;

import com.sdance_backend.sdance.model.entity.DanceClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DanceClassRepository extends CrudRepository<DanceClass, Integer> {
}
