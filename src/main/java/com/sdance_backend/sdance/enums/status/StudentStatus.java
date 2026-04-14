package com.sdance_backend.sdance.enums.status;

public enum StudentStatus {
    ACTIVE,        // Puede operar normalmente
    INACTIVE,      // No cursa pero puede volver
    BLOCKED,       // Bloqueado por deuda (temporal)
    DEACTIVATED    // Baja definitiva (no vuelve)
}