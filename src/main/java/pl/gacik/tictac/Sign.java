package pl.gacik.tictac;

/**
 * Contains characters, which can be book by Players.
 */
public enum Sign {
    NOUGHT('O'),
    CROSS('X'),
    TRIANGLE('\u0394'),
    SQUARE('\u25A0'),
    GAMMA('\u0194');

    private final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    /**
     * @return char to print
     */
    public char getChar() {
        return sign;
    }

}
