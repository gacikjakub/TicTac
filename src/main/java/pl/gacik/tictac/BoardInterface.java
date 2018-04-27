package pl.gacik.tictac;

import pl.gacik.coordinates.Coordinates2DInterface;

import java.util.List;
import java.util.Optional;

public interface BoardInterface {

    void addPair(Coordinates2DInterface coordinates, Sign sign) throws FieldCheckException;
    List<Coordinates2DInterface> getAddedCoordinates();
    Optional<Sign> getSign(Coordinates2DInterface coordinates);
    BordersKeeper getBordersKeeper();

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
