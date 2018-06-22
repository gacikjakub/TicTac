package pl.gacik.tictac;

import java.util.*;
import java.util.List;

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

    public Optional<Player> getWinner() {
        return Optional.ofNullable(playerPointsMap.entrySet().stream()
                .max((p1, p2) -> Integer.compare(Points.sum(p1.getValue()).points, Points.sum(p2.getValue()).points))
                .get().getKey());
    }

    public Points getHighestDifference() {
        Iterator<Map.Entry<Player, List<Points>>> iterator = playerPointsMap.entrySet().stream()
                .sorted((player1, player2) -> Integer.compare(Points.sum(player1.getValue()).points, Points.sum(player2.getValue()).points))
                .iterator();
        int maxDiff = 0;
        Points previousPoint = Points.sum(playerPointsMap.get(this.getWinner().get()));
        while (iterator.hasNext()) {
            Points currentPoint = Points.sum(iterator.next().getValue());
            int diff = Math.abs(previousPoint.points - currentPoint.points);
            if (maxDiff < diff) {
                maxDiff = diff;
            }
            previousPoint = currentPoint;
        }
        return new Points(maxDiff);
    }


}