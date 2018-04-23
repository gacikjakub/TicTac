package tictac;

public enum Sign {
    NOUGHT('O'),
    CROSS('X');

    private char sign;

    Sign(char sign) {
        this.sign = sign;
    }
}
