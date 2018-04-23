package tictac;

import java.util.HashMap;
import java.util.Map;
import coordinates.*;

public class CrossBoard {

    /**
     * 0: TOP - highest Y
     * 1: RIGHT - highest X
     * 2: LEFT - lowest X
     * 3: BOTTOM - lowest Y
     */
    private Integer[] borderValues = new Integer[4];

    private final Map<Coordinates2DInterface, Sign> boardMap = new HashMap<>();

    public void addPair(Coordinates2DInterface coordinates, Sign sign) {

    }


}
