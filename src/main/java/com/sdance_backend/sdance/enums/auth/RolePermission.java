package com.sdance_backend.sdance.enums.auth;

public enum RolePermission {

    // ===== PROFILE =====
    PROFILE_VIEW,
    PROFILE_READ_MY,

    // ===== USER =====
    USER_VIEW,
    USER_READ_ALL,
    USER_CREATE,
    USER_MODIFY,
    USER_DELETE,
    USER_SEARCH,

    // ===== CLASS =====
    CLASS_VIEW,
    CLASS_READ_ALL,
    CLASS_CREATE,
    CLASS_MODIFY,
    CLASS_DELETE,
    CLASS_SEARCH,

    // ===== STUDENT =====
    STUDENT_VIEW,
    STUDENT_READ_ALL,
    STUDENT_CREATE,
    STUDENT_MODIFY,
    STUDENT_DELETE,
    STUDENT_SEARCH,

    // ===== INSTRUCTOR =====
    INSTRUCTOR_VIEW,
    INSTRUCTOR_READ_ALL,
    INSTRUCTOR_CREATE,
    INSTRUCTOR_MODIFY,
    INSTRUCTOR_DELETE
}
