package com.sdance_backend.sdance.mapper;

import com.sdance_backend.sdance.dto.DanceClassResponse;
import com.sdance_backend.sdance.dto.DanceClassNameDTO;
import com.sdance_backend.sdance.dto.DanceClassRequest;
import com.sdance_backend.sdance.entity.DanceClass;
import org.mapstruct.*;

import java.util.List;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {InstructorMapper.class}
)
public abstract class DanceClassMapper {

    // -------------------- Entity -> DTO reducido --------------------
    public abstract DanceClassNameDTO toNameDTO(DanceClass danceClass);
    public abstract List<DanceClassNameDTO> toNameDTOList(List<DanceClass> danceClasses);

    // -------------------- Request DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    public abstract DanceClass toEntity(DanceClassRequest dto);

    // -------------------- Entity -> Response DTO --------------------
    @Mapping(target = "instructor", source = "instructor")
    public abstract DanceClassResponse toDTO(DanceClass danceClass);

    public abstract List<DanceClassResponse> toDTOList(List<DanceClass> danceClasses);

    // -------------------- Update --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    public abstract void updateFromDTO(DanceClassRequest dto, @MappingTarget DanceClass danceClass);
}

