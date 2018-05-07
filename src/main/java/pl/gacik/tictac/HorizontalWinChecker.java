package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.Optional;

public class HorizontalWinChecker extends WinChecker {

    public HorizontalWinChecker(IBoard board) {
        super(board);
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("There is no sign under given coordinates");
        }
        int counter = 1;
        counter += seriesGoingDown(lastMove.getBottom(), lastSign.get());
        counter += seriesGoingUp(lastMove.getTop(), lastSign.get());
        return (counter >= requiredSeriesLength);
    }

    private int seriesGoingDown(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingDown(coordinates.getBottom(), sign);
    }

    private int seriesGoingUp(ICoordinates2D coordinates, Sign sign) {
        if(!board.getSign(coordinates).isPresent()) return 0;
        if(board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingUp(coordinates.getTop(), sign);
    }
}
