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

    // -------------------- DTO -> Entity --------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", expression = "java(toRole(dto.getRole()))")
    public abstract User toEntity(UserDTO dto);

    // -------------------- Entity -> DTO --------------------
    @Mapping(target = "role", expression = "java(user.getRole() != null ? user.getRole().name() : null)")
    public abstract UserDTO toDTO(User user);

    public abstract List<UserDTO> toDTOList(List<User> users);

    // -------------------- Update parcial --------------------
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", expression = "java(toRole(dto.getRole()))")
    public abstract void updateFromDTO(UserDTO dto, @MappingTarget User user);

    // -------------------- Helper --------------------
    protected Role toRole(String role) {
        return role == null ? null : Role.valueOf(role);
    }

}
