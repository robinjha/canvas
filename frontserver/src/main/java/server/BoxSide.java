package server;

public enum BoxSide {
    TOPLEFT('\u2554'),
    TOPRIGHT('\u2557'),
    VERTICAL('\u2551'),
    HORIZONTAL('\u2550'),
    BOTTOMLEFT('\u255A'),
    BOTTOMRIGHT('\u255D');

    private char c;

    BoxSide(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }
}
