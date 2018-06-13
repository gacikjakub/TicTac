package pl.gacik.tictac;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TurnHolder {

    private Queue<Player> turnQueue = new LinkedBlockingQueue<>();

    public TurnHolder(Player... players) {
        for (Player player : players) {
            if (player == null) {
                throw new IllegalArgumentException("Player cannot be null");
            }
            turnQueue.add(player);
        }
    }

    public int getPlayersAmount() {
        return turnQueue.size();
    }

    /**
     * @return Player whose the next turn is
     */
    public Player getNextPlayer() {
        Player current = turnQueue.remove();
        turnQueue.add(current);
        return current;
    }

}
