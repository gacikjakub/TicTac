package pl.gacik.tictac.winCheckers;

import pl.gacik.tictac.boards.IBoard;

public abstract class WinChecker implements IWinChecker {

    protected long requiredSeriesLength;

    protected IBoard board;

    public WinChecker(IBoard board) {
        this.board = board;
    }

    @Override
    public void setRequiredSeriesLength(long requiredSeriesLength) {
        this.requiredSeriesLength = requiredSeriesLength;
    }

}
