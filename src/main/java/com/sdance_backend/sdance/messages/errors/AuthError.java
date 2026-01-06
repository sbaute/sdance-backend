package com.sdance_backend.sdance.messages.errors;

import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum AuthError implements MessageType {

    AUTH_INVALID_CREDENTIALS("Invalid username or password", 401),
    AUTH_UNAUTHORIZED("Unauthorized access", 401),
    AUTH_FORBIDDEN("Access denied", 403),
    AUTH_TOKEN_EXPIRED("Authentication token has expired", 401),
    AUTH_TOKEN_INVALID("Invalid authentication token", 401),
    AUTH_TOKEN_MISSING("Authentication token is missing", 401),
    AUTH_ACCOUNT_DISABLED("User account is disabled", 403),
    AUTH_ACCOUNT_LOCKED("User account is locked", 403),
    AUTH_ACCOUNT_EXPIRED("User account has expired", 403),
    AUTH_CREDENTIALS_EXPIRED("User credentials have expired", 403),
    AUTH_EMAIL_NOT_VERIFIED("Email address is not verified", 403),
    AUTH_ROLE_NOT_ALLOWED("User role is not allowed to perform this action", 403),
    AUTH_LOGIN_ERROR("Failed to authenticate user", 500);

    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
