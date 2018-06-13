package pl.gacik.tictac.boards;

import pl.gacik.tictac.coordinates.ICoordinates2D;
import pl.gacik.tictac.Sign;

import java.util.List;
import java.util.Optional;

public interface IBoard {

    void addPair(ICoordinates2D coordinates, Sign sign) throws FieldCheckException;

    List<ICoordinates2D> getAddedCoordinates();

    Optional<Sign> getSign(ICoordinates2D coordinates);

    BordersKeeper getBordersKeeper();

    default boolean hasAvailableField() {
        return true;
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
