package tictac;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import coordinates.*;

/**
 * This class keeping sign added by Player.
 */
public class CrossBoard {

    private final Map<Coordinates2DInterface, Sign> boardMap = new TreeMap<>();

    /**
     * This method allow to add sign under given coordinates.
     * @param coordinates - define location of sign
     * @param sign - define character to print
     * @throws FieldCheckException - when sign already added under given coordinates
     */
    public void addPair(Coordinates2DInterface coordinates, Sign sign) throws FieldCheckException {
        if(boardMap.containsKey(coordinates)) {
            throw new FieldCheckException("Pair with given key already has been added");
        }
        boardMap.put(coordinates, sign);
    }

    /**
     * Allow to get sign located under given coordinates.
     * It return Optional.
     * @param coordinates
     * @return Optional<Sign>
     */
    public Optional<Sign> getSign(Coordinates2DInterface coordinates) {
        if(boardMap.containsKey(coordinates)) {
            return Optional.of(boardMap.get(coordinates));
        }
        else return Optional.empty();
    }

    /**
     * This exception is throw when operation on kept content has failed.
     */
    class FieldCheckException extends Exception {
        public static final long serialVersionUID = 19273126L;
        public FieldCheckException(String s) {
            super(s);
        }
    }
}
