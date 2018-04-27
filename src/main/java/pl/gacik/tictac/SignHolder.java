package pl.gacik.tictac;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignHolder {

    private Map<Sign, Player> signPlayerMap = new HashMap<>();

    public void attachPlayer(Player player, Sign sign) throws IllegalArgumentException {
        if(signPlayerMap.containsKey(sign)) {
            throw new IllegalArgumentException("This sign has already booked by another player");
        }
        signPlayerMap.put(sign,player);
    }

    public Optional<Player> getSignOwner(Sign sign){
        return Optional.ofNullable(signPlayerMap.get(sign));
    }

}
