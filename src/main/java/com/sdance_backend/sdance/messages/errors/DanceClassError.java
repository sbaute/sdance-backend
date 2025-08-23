package com.sdance_backend.sdance.messages.errors;


import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DanceClassError implements MessageType {
    DANCE_CLASS_CREATE_ERROR("Failed to create dance class", 500),
    DANCE_CLASS_UPDATE_ERROR("Failed to update dance class", 500),
    DANCE_CLASS_DELETE_ERROR("Failed to delete dance class", 500),
    DANCE_CLASS_NOT_FOUND("The dance class was not found", 404),
    DANCE_CLASS_ALREADY_EXISTS("The dance class already exists", 409),
    DANCE_CLASS_LIST_EMPTY("The dance class list is empty", 404);

    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
