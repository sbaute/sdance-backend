package com.sdance_backend.sdance.mapper;

import com.sdance_backend.sdance.dto.instructor.InstructorDTO;
import com.sdance_backend.sdance.dto.instructor.InstructorNameDTO;
import com.sdance_backend.sdance.dto.instructor.InstructorRequestDTO;
import com.sdance_backend.sdance.entity.Instructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {DanceClassMapper.class})
public abstract class InstructorMapper {

    // -------------------- Entity -> DTO reducido --------------------
    public abstract InstructorNameDTO toDTO(Instructor instructor);

    public abstract List<InstructorNameDTO> toDTONameList(List<Instructor> instructors);

    // -------------------- Request DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract Instructor toEntity(InstructorRequestDTO dto);

    // -------------------- Entity -> Response DTO --------------------
    public abstract InstructorDTO toResponseDTO(Instructor instructor);

    public abstract List<InstructorDTO> toResponseDTOList(List<Instructor> instructors);

    // -------------------- Update --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(InstructorRequestDTO dto, @MappingTarget Instructor instructors);
}
