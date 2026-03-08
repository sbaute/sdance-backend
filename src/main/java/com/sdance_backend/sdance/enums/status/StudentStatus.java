package com.sdance_backend.sdance.enums.status;

public enum StudentStatus {
    ACTIVE,        // Puede asistir y operar normalmente
    INACTIVE,      // No está cursando actualmente
    BLOCKED,       // Suspendido por deuda / sanción
    DEACTIVATED    // Dado de baja administrativa
}