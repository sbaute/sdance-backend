package com.sdance_backend.sdance.enums.auth;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ADMIN(Arrays.asList(

            // ===== PROFILE =====
            RolePermission.PROFILE_VIEW,
            RolePermission.PROFILE_READ_MY,

            // ===== USER =====
            RolePermission.USER_VIEW,
            RolePermission.USER_READ_ALL,
            RolePermission.USER_CREATE,
            RolePermission.USER_MODIFY,
            RolePermission.USER_DELETE,
            RolePermission.USER_SEARCH,

            // ===== CLASS =====
            RolePermission.CLASS_VIEW,
            RolePermission.CLASS_READ_ALL,
            RolePermission.CLASS_CREATE,
            RolePermission.CLASS_MODIFY,
            RolePermission.CLASS_DELETE,
            RolePermission.CLASS_SEARCH,

            // ===== STUDENT =====
            RolePermission.STUDENT_VIEW,
            RolePermission.STUDENT_READ_ALL,
            RolePermission.STUDENT_CREATE,
            RolePermission.STUDENT_MODIFY,
            RolePermission.STUDENT_DELETE,
            RolePermission.STUDENT_SEARCH,
            RolePermission.STUDENT_UPDATE_STATUS,

            // ===== INSTRUCTOR =====
            RolePermission.INSTRUCTOR_VIEW,
            RolePermission.INSTRUCTOR_READ_ALL,
            RolePermission.INSTRUCTOR_CREATE,
            RolePermission.INSTRUCTOR_MODIFY,
            RolePermission.INSTRUCTOR_DELETE,
            RolePermission.INSTRUCTOR_SEARCH
    )),

    INSTRUCTOR(Arrays.asList(

            // ===== PROFILE =====
            RolePermission.PROFILE_VIEW,
            RolePermission.PROFILE_READ_MY,

            // ===== CLASS =====
            RolePermission.CLASS_VIEW,
            RolePermission.CLASS_READ_ALL,

            // ===== STUDENT =====
            RolePermission.STUDENT_VIEW,
            RolePermission.STUDENT_READ_ALL
    )),

    STUDENT(Arrays.asList(

            // ===== PROFILE =====
            RolePermission.PROFILE_VIEW,
            RolePermission.PROFILE_READ_MY,

            // ===== CLASS =====
            RolePermission.CLASS_VIEW,
            RolePermission.CLASS_READ_ALL,

            // ===== INSTRUCTOR =====
            RolePermission.INSTRUCTOR_VIEW,
            RolePermission.INSTRUCTOR_READ_ALL
    ));

    private final List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }
}

