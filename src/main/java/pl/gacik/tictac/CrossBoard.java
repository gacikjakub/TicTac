package pl.gacik.tictac;

import java.util.*;

import pl.gacik.coordinates.*;

/**
 * Keeping sign added by Player.
 */
public class CrossBoard {

    /**
     * 0 - Top
     * 1 - Right
     * 2 - Left
     * 3 - Bottom
     */
    Integer[] border = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};

    private final Map<Coordinates2DInterface, Sign> boardMap = new TreeMap<>();

    private void updateBorder(Coordinates2DInterface coordinates) {
        int y = coordinates.getY();
        int x = coordinates.getX();
        if (y > border[0]) {
            border[0] = y;
        }
        if (y < border[3]) {
            border[3] = y;
        }
        if (x > border[1]) {
            border[1] = x;
        }
        if (x < border[2]) {
            border[2] = x;
        }
    }

    /**
     * Allow to add sign under given coordinates.
     * @param coordinates - define location of sign
     * @param sign - define character to print
     * @throws FieldCheckException - when sign is already added under given coordinates
     */
    public void addPair(Coordinates2DInterface coordinates, Sign sign) throws FieldCheckException {
        if(boardMap.containsKey(coordinates)) {
            throw new FieldCheckException("Pair with given key already has been added");
        }
        boardMap.put(coordinates, sign);
        updateBorder(coordinates);
    }

    public List<Integer> getBorderValues() {
        return new LinkedList<Integer>(Arrays.asList(border));
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

    public List<Coordinates2DInterface> getAddedCoordinates() {
        return new LinkedList<>(boardMap.keySet());
    }

    /**
     * Is throw when operation on kept content has failed.
     */
    class FieldCheckException extends Exception {
        public static final long serialVersionUID = 19273126L;
        public FieldCheckException(String s) {
            super(s);
        }
    }
}
