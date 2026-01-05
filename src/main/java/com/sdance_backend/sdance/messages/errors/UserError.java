package com.sdance_backend.sdance.messages.errors;


import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserError implements MessageType {
    USER_CREATE_ERROR("Failed to create user", 500),
    USER_UPDATE_ERROR("Failed to update user", 500),
    USER_DELETE_ERROR("Failed to delete user", 500),
    USER_NOT_FOUND("The user was not found", 404),
    USER_ALREADY_EXISTS("The user already exists", 409),
    USER_LIST_EMPTY("The user list is empty", 404);


    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
