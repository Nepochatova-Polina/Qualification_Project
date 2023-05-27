package org.acme.model.enums;

public enum Level {
    ZERO("zero"),
    FIRST("first"),
    SECOND("second"),
    THIRD("third");

    private final String text;

    Level(String text) {
        this.text = text;
    }

    public static Level fromString(String text) {
        for (Level b : Level.values()) {
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
