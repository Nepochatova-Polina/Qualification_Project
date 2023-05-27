package org.acme.model.enums;

public enum Role {
    STUDENT("student"),
    PROFESSOR("professor");

    private final String text;

    Role(String text) {
        this.text = text;
    }

    public static Role fromString(String text) {
        for (Role b : Role.values()) {
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
