package pl.gacik.tictac;

import java.util.*;

public class SignHolder {

    private Map<Sign, Player> signPlayerMap = new LinkedHashMap<>();

    public void attachPlayer(Player player, Sign sign) throws IllegalArgumentException {
        if (signPlayerMap.containsKey(sign)) {
            throw new IllegalArgumentException("This sign has been already booked by another player");
        }
        if (signPlayerMap.containsValue(player)) {
            throw new IllegalArgumentException("This player already have booked sign");
        }
        signPlayerMap.put(sign, player);
    }

    public List<Player> getAttachedPlayers() {
        return new LinkedList<>(signPlayerMap.values());
    }

    public List<Sign> getUsedSigns() {
        return new LinkedList<>(signPlayerMap.keySet());
    }

    public Optional<Sign> getBookedSign(Player player) {
        return Optional.ofNullable(signPlayerMap.entrySet().stream().filter(s -> s.getValue() == player).findFirst().
                orElse(new AbstractMap.SimpleEntry<>(null,null)).getKey());
    }

    public Optional<Player> getSignOwner(Sign sign) {
        return Optional.ofNullable(signPlayerMap.get(sign));
    }

}
