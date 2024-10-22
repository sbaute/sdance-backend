package com.sdance_backend.sdance.model.entity.enums;

public enum Days {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    MONDAY_WEDNESDAY("Monday & Wednesday"),
    TUESDAY_THURSDAY("Tuesday & Thursday"),
    TUESDAY_FRIDAY("Tuesday & Friday"),
    MONDAY_THURSDAY("Monday & Thursday");

    private final String displayName;

    Days(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
