package com.sdance_backend.sdance.mapper;

import com.sdance_backend.sdance.dto.danceClass.DanceClassDTO;
import com.sdance_backend.sdance.dto.danceClass.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.danceClass.DanceClassRequestDTO;
import com.sdance_backend.sdance.dto.instructor.InstructorRequestDTO;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.entity.Instructor;
import org.mapstruct.*;

import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {InstructorMapper.class, StudentMapper.class}
)
public abstract class DanceClassMapper {

    // Entity -> DTO reducido
    public abstract DanceClassNameDTO toDTO(DanceClass danceClass);

    public abstract List<DanceClassNameDTO> toDTONameList(List<DanceClass> danceClasses);

    // -------------------- Request DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    public abstract DanceClass toEntity(DanceClassRequestDTO dto);

    // -------------------- Entity -> Response DTO --------------------
    public abstract DanceClassDTO toResponseDTO(DanceClass danceClass);

    public abstract List<DanceClassDTO> toResponseDTOList(List<DanceClass> danceClasses);

    // -------------------- Update DanceClass --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    public abstract void updateFromDTO(DanceClassRequestDTO dto, @MappingTarget DanceClass danceClass);

}
