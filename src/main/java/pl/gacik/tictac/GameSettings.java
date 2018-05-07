package pl.gacik.tictac;

import pl.gacik.tictac.languages.MessagesProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameSettings {

    public IBoard getBoard() {
        return board;
    }

    public void setBoard(IBoard board) {
        if(board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
    }

    public TurnHolder getTurnHolder() {
        return turnHolder;
    }

    public void setTurnHolder(TurnHolder turnHolder) {
        if(turnHolder == null) {
            throw new IllegalArgumentException("turnHolder cannot be null");
        }
        this.turnHolder = turnHolder;
    }

    private IBoard board;

    private TurnHolder turnHolder;

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

    public MessagesProvider getMessagesProvider() {
        return messagesProvider;
    }

    public void setMessagesProvider(MessagesProvider messagesProvider) {
        this.messagesProvider = messagesProvider;
    }

    private MessagesProvider messagesProvider;

}
