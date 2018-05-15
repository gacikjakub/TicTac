package pl.gacik.tictac;

import pl.gacik.tictac.Player;
import pl.gacik.tictac.Points;

import java.util.*;

public class PointsHolder {

    private Map<Player, List<Points>> playerPointsMap = new HashMap<>();

    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (playerPointsMap.containsKey(player)) {
            throw new IllegalArgumentException("Player already exist in this holder");
        }
        playerPointsMap.put(player, new LinkedList<>());
    }

    public void addPoints(Player player, Points points) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!playerPointsMap.containsKey(player)) {
            throw new IllegalArgumentException("Player not found in PointsHolder");
        }
        playerPointsMap.get(player).add(points);
    }

    public List<Points> getPlayerPoints(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!playerPointsMap.containsKey(player)) {
            throw new IllegalArgumentException("Player not found in PointsHolder");
        }
        return playerPointsMap.get(player);
    }

    public Set<Player> getAddedPlayers() {
        return new HashSet<>(playerPointsMap.keySet());
    }
}