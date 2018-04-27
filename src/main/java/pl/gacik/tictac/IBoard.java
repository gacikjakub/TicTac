package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.List;
import java.util.Optional;

public interface IBoard {

    void addPair(ICoordinates2D coordinates, Sign sign) throws FieldCheckException;

    List<ICoordinates2D> getAddedCoordinates();

    Optional<Sign> getSign(ICoordinates2D coordinates);

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
