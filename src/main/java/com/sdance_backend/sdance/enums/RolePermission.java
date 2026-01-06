package com.sdance_backend.sdance.enums;

public enum RolePermission {

    // ===== PROFILE =====
    PROFILE_VIEW,
    PROFILE_READ_MY,

    // ===== CLASS =====
    CLASS_VIEW,
    CLASS_READ_ALL,
    CLASS_CREATE,
    CLASS_MODIFY,
    CLASS_DELETE,

    // ===== STUDENT =====
    STUDENT_VIEW,
    STUDENT_READ_ALL,
    STUDENT_CREATE,
    STUDENT_MODIFY,
    STUDENT_DELETE,

    // ===== INSTRUCTOR =====
    INSTRUCTOR_VIEW,
    INSTRUCTOR_READ_ALL,
    INSTRUCTOR_CREATE,
    INSTRUCTOR_MODIFY,
    INSTRUCTOR_DELETE
}
