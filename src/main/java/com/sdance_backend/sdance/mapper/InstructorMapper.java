package com.sdance_backend.sdance.mapper;

import com.sdance_backend.sdance.dto.InstructorDTO;
import com.sdance_backend.sdance.dto.InstructorNameDTO;
import com.sdance_backend.sdance.entity.Instructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class InstructorMapper {

    // -------------------- Entity -> DTO reducido --------------------
    public abstract InstructorNameDTO toNameDTO(Instructor instructor);
    public abstract List<InstructorNameDTO> toNameDTOList(List<Instructor> instructors);

    // -------------------- DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract Instructor toEntity(InstructorDTO dto);

    // -------------------- Entity -> DTO --------------------
    public abstract InstructorDTO toDTO(Instructor instructor);
    public abstract List<InstructorDTO> toDTOList(List<Instructor> instructors);

    // -------------------- Update --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(InstructorDTO dto, @MappingTarget Instructor instructor);
}
