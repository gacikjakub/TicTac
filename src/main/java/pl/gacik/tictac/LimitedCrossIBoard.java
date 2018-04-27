package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

public class LimitedCrossIBoard extends CrossIBoard {

    private final Integer width;
    private final Integer height;

    public LimitedCrossIBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.bordersKeeper.updateBorder(BorderDirection.TOP, height);
        this.bordersKeeper.updateBorder(BorderDirection.RIGHT, width);
        this.bordersKeeper.updateBorder(BorderDirection.DOWN, 1);
        this.bordersKeeper.updateBorder(BorderDirection.LEFT, 1);
    }

    private boolean checkIfCoordinatesAreCorrect(ICoordinates2D coordinates) {
        return !(coordinates.getY() > height ||  coordinates.getY() < 1 || coordinates.getX() > width || coordinates.getX() < 1);
    }

    @Override
    public void addPair(ICoordinates2D coordinates, Sign sign) throws FieldCheckException {
        if(!checkIfCoordinatesAreCorrect(coordinates)) {
            throw new FieldCheckException("Given coordinates are out of max size of board");
        }
        if(boardMap.containsKey(coordinates)) {
            throw new FieldCheckException("Pair with given key already has been added");
        }
        boardMap.put(coordinates, sign);
    }

}
