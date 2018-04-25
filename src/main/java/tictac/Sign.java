package tictac;

/**
 * Enum class contains characters, which can be book by Players.
 */
public enum Sign {
    NOUGHT('O'),
    CROSS('X');

    private final char sign;

    Sign(char sign) {
        this.sign = sign;
    }

    /**
     * Return char to print
     * @return
     */
    public char getChar() {
        return sign;
    }

}
