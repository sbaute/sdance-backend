package com.sdance_backend.sdance.utils;


import org.springframework.stereotype.Component;

@Component
public class MessageBuilder {
    public String buildSuccessMessage(Class<?> entityClass, String action) {
        return entityClass.getSimpleName() + " " + action + " successfully";
    }
}
