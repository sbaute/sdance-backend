package com.sdance_backend.sdance.exceptions;

import lombok.Getter;

@Getter
public enum ErrorType {

    //--------------------GENERIC
    NOT_FOUND("The requested resource does not exist", 404),
    BAD_REQUEST("The request is invalid", 400),
    NOT_RELATED("The resources are not related", 422),
    REQUIRED_FIELDS_MISSING("Required fields are missing or empty", 400),

    //-------------------- STUDENT
    STUDENT_CREATE_ERROR("Failed to create student", 500),
    STUDENT_UPDATE_ERROR("Failed to update student", 500),
    STUDENT_DELETE_ERROR("Failed to delete student", 500),
    STUDENT_NOT_FOUND("The student was not found", 404),
    STUDENT_ALREADY_EXISTS("The student already exists", 409),
    STUDENT_LIST_EMPTY("The student list is empty", 404),

    //-------------------- INSTRUCTOR
    INSTRUCTOR_CREATE_ERROR("Failed to create instructor", 500),
    INSTRUCTOR_UPDATE_ERROR("Failed to update instructor", 500),
    INSTRUCTOR_DELETE_ERROR("Failed to delete instructor", 500),
    INSTRUCTOR_NOT_FOUND("The instructor was not found", 404),
    INSTRUCTOR_ALREADY_EXISTS("The instructor already exists", 409),
    INSTRUCTOR_LIST_EMPTY("The instructor list is empty", 404),

    // -------------------- DANCE CLASS
    DANCE_CLASS_CREATE_ERROR("Failed to create dance class", 500),
    DANCE_CLASS_UPDATE_ERROR("Failed to update dance class", 500),
    DANCE_CLASS_DELETE_ERROR("Failed to delete dance class", 500),
    DANCE_CLASS_NOT_FOUND("The dance class was not found", 404),
    DANCE_CLASS_ALREADY_EXISTS("The dance class already exists", 409),
    DANCE_CLASS_LIST_EMPTY("The dance class list is empty", 404);


    private final String message;
    private final int status;

    ErrorType(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
