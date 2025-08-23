package com.sdance_backend.sdance.messages.errors;

import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentError implements MessageType {

    STUDENT_CREATE_ERROR("Failed to create student", 500),
    STUDENT_UPDATE_ERROR("Failed to update student", 500),
    STUDENT_DELETE_ERROR("Failed to delete student", 500),
    STUDENT_NOT_FOUND("The student was not found", 404),
    STUDENT_ALREADY_EXISTS("The student already exists", 409),
    STUDENT_LIST_EMPTY("The student list is empty", 404);

    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
