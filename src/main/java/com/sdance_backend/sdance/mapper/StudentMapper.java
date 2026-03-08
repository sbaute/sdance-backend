package com.sdance_backend.sdance.mapper;


import com.sdance_backend.sdance.dto.InstructorResponse;
import com.sdance_backend.sdance.dto.StudentRequest;
import com.sdance_backend.sdance.dto.StudentResponse;
import com.sdance_backend.sdance.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

import com.sdance_backend.sdance.dto.StudentNameDto;

@Slf4j
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class StudentMapper {

    // -------------------- Entity -> DTO reducido --------------------
    public abstract StudentNameDto toNameDTO(Student student);
    public abstract List<StudentNameDto> toNameDTOList(List<Student> students);



    // -------------------- Request DTO -> Entity --------------------
    public abstract Student toEntity(StudentRequest studentResponse);

    // -------------------- Response DTO -> Entity --------------------
    public abstract Student toEntity(StudentResponse studentResponse);

    // -------------------- Entity -> Response DTO --------------------
    public abstract StudentResponse toDTO(Student student);
    public abstract List<StudentResponse> toDTOList(List<Student> students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(StudentRequest dto, @MappingTarget Student student);


}



