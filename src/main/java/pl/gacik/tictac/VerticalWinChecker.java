package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.Optional;

public class VerticalWinChecker extends WinChecker {

    public VerticalWinChecker(IBoard board) {
        super(board);
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("There is no sign under given coordinates");
        }
        int counter = 1;
        counter += seriesGoingLeft(lastMove.getLeft(), lastSign.get());
        counter += seriesGoingRight(lastMove.getRight(), lastSign.get());
        return (counter >= requiredSeriesLength);
    }

    private int seriesGoingLeft(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingLeft(coordinates.getLeft(), sign);
    }

    private int seriesGoingRight(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingRight(coordinates.getRight(), sign);
    }

}
