package com.sdance_backend.sdance.mapper;


import com.sdance_backend.sdance.dto.StudentDto;
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

    // -------------------- Response DTO -> Entity --------------------
    public abstract Student toEntity(StudentDto studentDto);

    // -------------------- Entity -> Response DTO --------------------
    public abstract StudentDto toDTO(Student student);

    public abstract List<StudentDto> toDTOList(List<Student> students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(StudentDto dto, @MappingTarget Student student);

}



