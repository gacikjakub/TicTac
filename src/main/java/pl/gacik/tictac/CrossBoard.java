package pl.gacik.tictac;

import java.nio.ByteOrder;
import java.util.*;

import pl.gacik.coordinates.*;

/**
 * Keeping sign added by Player.
 */
public class CrossBoard implements BoardInterface {


    protected final BordersKeeper bordersKeeper = new BordersKeeper();

    protected final Map<Coordinates2DInterface, Sign> boardMap = new TreeMap<>();

    private void updateBorder(Coordinates2DInterface coordinates) {
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
    public void addPair(Coordinates2DInterface coordinates, Sign sign) throws FieldCheckException {
        if(boardMap.containsKey(coordinates)) {
            throw new FieldCheckException("Pair with given key already has been added");
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
    public Optional<Sign> getSign(Coordinates2DInterface coordinates) {
        if(boardMap.containsKey(coordinates)) {
            return Optional.of(boardMap.get(coordinates));
        }
        else return Optional.empty();
    }

    @Override
    public List<Coordinates2DInterface> getAddedCoordinates() {
        return new LinkedList<>(boardMap.keySet());
    }


}
