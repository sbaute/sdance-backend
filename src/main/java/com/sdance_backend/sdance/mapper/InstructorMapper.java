package com.sdance_backend.sdance.mapper;

import com.sdance_backend.sdance.dto.InstructorRequest;
import com.sdance_backend.sdance.dto.InstructorNameDTO;
import com.sdance_backend.sdance.dto.InstructorResponse;
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

    // -------------------- Request DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract Instructor toEntity(InstructorRequest dto);

    // -------------------- Entity -> DTO --------------------
    public abstract InstructorResponse toDTO(Instructor instructor);
    public abstract List<InstructorResponse> toDTOList(List<Instructor> instructors);

    // -------------------- Update --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "danceClasses", ignore = true)
    public abstract void updateFromDTO(InstructorRequest dto, @MappingTarget Instructor instructor);
}
