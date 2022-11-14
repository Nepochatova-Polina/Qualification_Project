package com.example.epamfinalproject.Entities;

public enum UserRole {
    PASSENGER("Passenger"),
    STAFF("Staff");

    private final String text;

    UserRole(String text) {
        this.text = text;
    }

    public static UserRole fromString(String text) {
        for (UserRole b : UserRole.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public String toString() {
        return text;
    }
}
