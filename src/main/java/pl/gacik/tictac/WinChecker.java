package pl.gacik.tictac;

public abstract class WinChecker implements IWinChecker {

    protected int requiredSeriesLength;

    protected IBoard board;

    public WinChecker(IBoard board) {
        this.board = board;
    }

    @Override
    public void setRequiredSeriesLength(int requiredSeriesLength) {
        this.requiredSeriesLength = requiredSeriesLength;
    }

}
