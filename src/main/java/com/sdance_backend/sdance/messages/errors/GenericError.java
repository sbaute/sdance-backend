package com.sdance_backend.sdance.messages.errors;


import com.sdance_backend.sdance.messages.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericError implements MessageType {

    NOT_FOUND("The requested resource does not exist", 404),
    BAD_REQUEST("The request is invalid", 400),
    NOT_RELATED("The resources are not related", 422),
    REQUIRED_FIELDS_MISSING("Required fields are missing or empty", 400),
    INVALID_PAGE_PARAMETER_ERROR("Invalid page parameter",400),
    INVALID_SIZE_PARAMETER_ERROR("Invalid size parameter", 400),
    SEARCH_ERROR("Error while performing the search", 500);


    private final String message;
    private final int status;

    @Override
    public String getCode() {
        return name();
    }
}
