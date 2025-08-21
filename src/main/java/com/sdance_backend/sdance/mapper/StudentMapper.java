package com.sdance_backend.sdance.mapper;


import com.sdance_backend.sdance.dto.student.StudentDto;
import com.sdance_backend.sdance.dto.student.StudentRequestDto;
import com.sdance_backend.sdance.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

import com.sdance_backend.sdance.dto.student.StudentNameDto;

@Slf4j
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DanceClassMapper.class}
)
public abstract class StudentMapper {

    // -------------------- Entity -> DTO reducido --------------------
    public abstract StudentNameDto toNameDTO(Student student);

    public abstract List<StudentNameDto> toNameDTOList(List<Student> students);

    // -------------------- Request DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract Student toEntity(StudentRequestDto dto);

    // -------------------- Entity -> Response DTO --------------------
    public abstract StudentDto toResponseDTO(Student student);

    public abstract List<StudentDto> toResponseDTOList(List<Student> students);

    // -------------------- Update --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(StudentRequestDto dto, @MappingTarget Student student);
}


