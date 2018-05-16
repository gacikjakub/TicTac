package pl.gacik.tictac.winCheckers;

import pl.gacik.tictac.coordinates.ICoordinates2D;
import pl.gacik.tictac.Sign;
import pl.gacik.tictac.boards.IBoard;

import java.util.Optional;

public class DiagonalBottomLeftToTopRightIWinChecker extends WinChecker {

    public DiagonalBottomLeftToTopRightIWinChecker(IBoard board) {
        super(board);
    }

    @Override
    public boolean victoryAchieved(ICoordinates2D lastMove) {
        Optional<Sign> lastSign = board.getSign(lastMove);
        if (!lastSign.isPresent()) {
            throw new IllegalArgumentException("There is no sign under given coordinates");
        }
        int counter = 1;
        counter += seriesGoingTopRight(lastMove.getTopRight(), lastSign.get());
        counter += seriesGoingBottomLeft(lastMove.getBottomLeft(), lastSign.get());
        return (counter >= requiredSeriesLength);
    }

    private int seriesGoingTopRight(ICoordinates2D coordinates, Sign sign) {
        if (!board.getSign(coordinates).isPresent()) return 0;
        if (board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingTopRight(coordinates.getTopRight(), sign);
    }

    private int seriesGoingBottomLeft(ICoordinates2D coordinates, Sign sign) {
        if (!board.getSign(coordinates).isPresent()) return 0;
        if (board.getSign(coordinates).get() != sign) return 0;
        return 1 + seriesGoingBottomLeft(coordinates.getBottomLeft(), sign);
    }

    @Override
    public void setRequiredSeriesLength(long requiredSeriesLength) {
//        if(requiredSeriesLength > Math.sqrt(Math.pow(Math.abs(board.getBordersKeeper().getBorder(BorderDirection.TOP) - board.getBordersKeeper().getBorder(BorderDirection.DOWN)), 2) +
//                Math.pow(Math.abs(board.getBordersKeeper().getBorder(BorderDirection.RIGHT) - board.getBordersKeeper().getBorder(BorderDirection.LEFT)), 2))) {
//            System.out.println(board.getBordersKeeper().getBorder(BorderDirection.TOP));
//            System.out.println(board.getBordersKeeper().getBorder(BorderDirection.DOWN));
//            System.out.println(board.getBordersKeeper().getBorder(BorderDirection.LEFT));
//            System.out.println(board.getBordersKeeper().getBorder(BorderDirection.RIGHT));
//            System.out.println(Math.sqrt(Math.pow(Math.abs(board.getBordersKeeper().getBorder(BorderDirection.TOP) - board.getBordersKeeper().getBorder(BorderDirection.DOWN)), 2) +
//                    Math.pow(Math.abs(board.getBordersKeeper().getBorder(BorderDirection.RIGHT) - board.getBordersKeeper().getBorder(BorderDirection.LEFT)), 2)));
//            throw new IllegalArgumentException("Given series length do not allow to win");
//        } // TODO: pow operation with MAX_INT
        super.setRequiredSeriesLength(requiredSeriesLength);
    }
}
