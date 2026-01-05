package com.sdance_backend.sdance.messages.errors;

import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PasswordError implements MessageType {

    PASSWORDS_EMPTY("Password fields cannot be empty", 400),
    PASSWORDS_DO_NOT_MATCH("Passwords do not match", 400),
    PASSWORD_TOO_SHORT("Password must be at least 8 characters long", 400),
    PASSWORD_TOO_LONG("Password must not exceed 12 characters", 400);

    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }

}
