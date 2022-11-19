package com.example.epamfinalproject.Entities.Enums;

public enum Status {
    PENDING("pending"),
    PAID("paid");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    public static Status fromString(String text) {
        for (Status b : Status.values()) {
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
