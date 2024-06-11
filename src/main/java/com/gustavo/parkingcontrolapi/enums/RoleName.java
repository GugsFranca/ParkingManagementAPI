package com.gustavo.parkingcontrolapi.enums;

public enum RoleName {
    ROLE_USER, ROLE_ADMIN, ROLE_RECRUITER;

    public static RoleName fromString(String roleName) {
        try {
            return valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
