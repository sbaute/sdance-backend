package com.sdance_backend.sdance.mapper;


import com.sdance_backend.sdance.dto.UserDTO;
import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;

import java.util.List;

@Slf4j
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    // -------------------- DTO -> Entity (CREATE) --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToRole")
    public abstract User toEntity(UserDTO dto);

    // -------------------- Entity -> DTO (RESPONSE) --------------------
    @Mapping(target = "role", source = "role", qualifiedByName = "roleToString")
    public abstract UserDTO toDTO(User user);

    public abstract List<UserDTO> toDTOList(List<User> users);

    // -------------------- UPDATE (PATCH / PUT) --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "stringToRole")
    public abstract void updateFromDTO(UserDTO dto, @MappingTarget User user);

    // -------------------- HELPERS --------------------
    @Named("stringToRole")
    protected Role stringToRole(String role) {
        return role == null ? null : Role.valueOf(role);
    }

    @Named("roleToString")
    protected String roleToString(Role role) {
        return role == null ? null : role.name();
    }

}
