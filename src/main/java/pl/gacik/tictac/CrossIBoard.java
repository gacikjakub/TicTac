package pl.gacik.tictac;

import java.util.*;

import pl.gacik.coordinates.*;

/**
 * Keeping sign added by Player.
 */
public class CrossIBoard implements IBoard {


    protected final BordersKeeper bordersKeeper = new BordersKeeper();

    protected final Map<ICoordinates2D, Sign> boardMap = new TreeMap<>();

    private void updateBorder(ICoordinates2D coordinates) {
        int y = coordinates.getY();
        int x = coordinates.getX();
        if (y > bordersKeeper.getBorder(BorderDirection.TOP)) {
            bordersKeeper.updateBorder(BorderDirection.TOP, y);
        }
        if (y < bordersKeeper.getBorder(BorderDirection.DOWN)) {
            bordersKeeper.updateBorder(BorderDirection.DOWN, y);
        }
        if (x > bordersKeeper.getBorder(BorderDirection.RIGHT)) {
            bordersKeeper.updateBorder(BorderDirection.RIGHT, x);
        }
        if (x < bordersKeeper.getBorder(BorderDirection.LEFT)) {
            bordersKeeper.updateBorder(BorderDirection.LEFT, x);
        }
    }

    /**
     * Allow to add sign under given coordinates.
     * @param coordinates - define location of sign
     * @param sign - define character to print
     * @throws FieldCheckException - when sign is already added under given coordinates
     */
    @Override
    public void addPair(ICoordinates2D coordinates, Sign sign) throws FieldCheckException {
        if(boardMap.containsKey(coordinates)) {
            throw new AlreadyUsedCoordinates("Pair with given key already has been added");
        }
        updateBorder(coordinates);
        boardMap.put(coordinates, sign);
    }

    @Override
    public BordersKeeper getBordersKeeper() {
        return new BordersKeeper(bordersKeeper);
    }

    /**
     * Allow to get sign located under given coordinates.
     * It return Optional.
     * @param coordinates
     * @return Optional<Sign>
     */
    @Override
    public Optional<Sign> getSign(ICoordinates2D coordinates) {
        return Optional.ofNullable(boardMap.get(coordinates));
    }

    @Override
    public List<ICoordinates2D> getAddedCoordinates() {
        return new LinkedList<>(boardMap.keySet());
    }


    class AlreadyUsedCoordinates extends FieldCheckException {

        public AlreadyUsedCoordinates(String s) {
            super(s);
        }
    }

}
