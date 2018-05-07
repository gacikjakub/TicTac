package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

import java.util.Optional;

public class VerticalWinChecker implements WinChecker {

    private int requiredSeries;

    private IBoard board;

    public VerticalWinChecker(IBoard board) {
        this.board = board;
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("Under Given Coordinates is no any sign");
        }
        int counter = 1;
        counter += seriesGoingLeft(lastMove.getLeft(), lastSign.get());
        counter += seriesGoingRight(lastMove.getRight(), lastSign.get());
        return (counter >= requiredSeries);
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

    @Override
    public void setRequiredSeries(int requiredSeries) {
        this.requiredSeries = requiredSeries;
    }
}
