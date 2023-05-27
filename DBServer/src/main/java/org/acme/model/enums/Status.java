package org.acme.model.enums;

public enum Status {
    PENDING("pending"),
    COMPLETED("completed");

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

    @Override
    public String toString() {
        return text;
    }
}