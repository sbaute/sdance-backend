package com.sdance_backend.sdance.messages.errors;


import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InstructorError implements MessageType {

    INSTRUCTOR_CREATE_ERROR("Failed to create instructor", 500),
    INSTRUCTOR_UPDATE_ERROR("Failed to update instructor", 500),
    INSTRUCTOR_DELETE_ERROR("Failed to delete instructor", 500),
    INSTRUCTOR_NOT_FOUND("The instructor was not found", 404),
    INSTRUCTOR_ALREADY_EXISTS("The instructor already exists", 409),
    INSTRUCTOR_LIST_EMPTY("The instructor list is empty", 404);

    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
