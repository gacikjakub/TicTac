package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.Optional;

public class HorizontalWinChecker implements WinChecker {


    private int requiredSeries;

    private IBoard board;

    public HorizontalWinChecker(IBoard board) {
        this.board = board;
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("Under Given Coordinates is no any sign");
        }
        int counter = 1;
        counter += seriesGoingDown(lastMove.getBottom(), lastSign.get());
        counter += seriesGoingUp(lastMove.getTop(), lastSign.get());
        return (counter >= requiredSeries);
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

    @Override
    public void setRequiredSeries(int requiredSeries) {
        this.requiredSeries = requiredSeries;
    }
}
