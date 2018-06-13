package pl.gacik.tictac;

import pl.gacik.tictac.boards.IBoard;
import pl.gacik.tictac.languages.MessagesProvider;
import pl.gacik.tictac.winCheckers.IWinChecker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameSettings {

    public int getMaxRoundAmount() {
        return maxRoundAmount;
    }

    public void setMaxRoundAmount(int maxRoundAmount) {
        this.maxRoundAmount = maxRoundAmount;
    }

    private int maxRoundAmount;

    public IBoard getBoard() {
        return board;
    }

    public void setBoard(IBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
    }

    public PointsHolder getPointsHolder() {
        return pointsHolder;
    }

    public Points getWinPoints() {
        return winPoints;
    }

    public Points getDrawPoints() {
        return drawPoints;
    }

    public void setDrawPoints(Points drawPoints) {
        this.drawPoints = drawPoints;
    }

    private Points drawPoints = new Points(1);

    private Points winPoints = new Points(3);

    public Points getLosePoints() {
        return losePoints;
    }

    public void setLosePoints(Points losePoints) {
        this.losePoints = losePoints;
    }

    private Points losePoints = new Points(0);

    public void setWinPoints(Points winPoints) {
        this.winPoints = winPoints;
    }

    private PointsHolder pointsHolder = new PointsHolder();

    private IBoard board;

    private SignHolder signHolder = new SignHolder();

    public SignHolder getSignHolder() {
        return signHolder;
    }

    public List<IWinChecker> getWinCheckers() {
        return new LinkedList<>(winCheckers);
    }

    private List<IWinChecker> winCheckers = new ArrayList<>();

    public boolean addWinChecker(IWinChecker winChecker) {
        return winCheckers.add(winChecker);
    }

    public void clearWinCheckers() {
        winCheckers = new ArrayList<>();
    }

    public MessagesProvider getMessagesProvider() {
        return messagesProvider;
    }

    public void setMessagesProvider(MessagesProvider messagesProvider) {
        this.messagesProvider = messagesProvider;
    }

    private MessagesProvider messagesProvider;

    public int getPlayersAmount() {
        return playersAmount;
    }

    public void setPlayersAmount(int playersAmount) {
        this.playersAmount = playersAmount;
    }

    private int playersAmount = 0;


}
