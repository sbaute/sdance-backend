package com.sdance_backend.sdance.enums;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ADMIN(Arrays.asList(
            RolePermission.READ_MY_PROFILE,
            // ===== CLASS =====
            RolePermission.READ_ALL_CLASS,
            RolePermission.CREATE_CLASS,
            RolePermission.UPDATE_CLASS,
            RolePermission.DELETE_CLASS,

            // ===== STUDENT =====
            RolePermission.READ_ALL_STUDENT,
            RolePermission.CREATE_STUDENT,
            RolePermission.UPDATE_STUDENT,
            RolePermission.DELETE_STUDENT,

            // ===== INSTRUCTOR =====
            RolePermission.READ_ALL_INSTRUCTOR,
            RolePermission.CREATE_INSTRUCTOR,
            RolePermission.UPDATE_INSTRUCTOR,
            RolePermission.DELETE_INSTRUCTOR
    )),

    INSTRUCTOR(Arrays.asList(
            RolePermission.READ_ALL_CLASS,
            RolePermission.READ_ALL_STUDENT,
            RolePermission.READ_MY_PROFILE
    )),

    STUDENT(Arrays.asList(
            RolePermission.READ_ALL_CLASS,
            RolePermission.READ_ALL_INSTRUCTOR,
            RolePermission.READ_MY_PROFILE
    ));

    private final List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }
}

