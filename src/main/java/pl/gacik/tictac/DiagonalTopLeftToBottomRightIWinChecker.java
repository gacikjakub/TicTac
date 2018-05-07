package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.Optional;

public class DiagonalTopLeftToBottomRightIWinChecker extends WinChecker {

    public DiagonalTopLeftToBottomRightIWinChecker(IBoard board) {
        super(board);
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("There is no sign under given coordinates");
        }
        int counter = 1;
        counter += seriesGoingBottomRight(lastMove.getBottomRight(), lastSign.get());
        counter += seriesGoingTopLeft(lastMove.getTopLeft(), lastSign.get());
        return (counter >= requiredSeriesLength);
    }

    private int seriesGoingBottomRight(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingBottomRight(coordinates.getBottomRight(), sign);
    }

    private int seriesGoingTopLeft(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingTopLeft(coordinates.getTopLeft(), sign);
    }

    @Override
    public void setRequiredSeriesLength(int requiredSeriesLength) {
        this.requiredSeriesLength = requiredSeriesLength;
    }

}
