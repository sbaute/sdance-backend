package com.sdance_backend.sdance.messages;


import org.springframework.stereotype.Component;

@Component
public class MessageBuilder {
    public String buildSuccessMessage(Class<?> entityClass, String action) {
        return entityClass.getSimpleName() + " " + action + " successfully";
    }
}
