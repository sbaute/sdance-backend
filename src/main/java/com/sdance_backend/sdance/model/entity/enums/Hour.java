package com.sdance_backend.sdance.model.entity.enums;

public enum Hour {
    HOUR_9_10("09:00-10:00"),
    HOUR_10_11("10:00 - 11:00"),
    HOUR_11_12("11:00 - 12:00"),
    HOUR_12_13("12:00 - 13:00"),
    HOUR_13_14("13:00 - 14:00"),
    HOUR_14_15("14:00 - 15:00"),
    HOUR_15_16("15:00 - 16:00"),
    HOUR_16_17("16:00 - 17:00"),
    HOUR_17_18("17:00 - 18:00"),
    HOUR_18_19("18:00 - 19:00"),
    HOUR_19_20("19:00 - 20:00"),
    HOUR_20_21("20:00 - 21:00");

    private final String displayName;

    Hour(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
