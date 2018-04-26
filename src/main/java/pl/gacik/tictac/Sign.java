package pl.gacik.tictac;

/**
 * Contains characters, which can be book by Players.
 */
public enum Sign {
    NOUGHT('O'),
    CROSS('X');

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
