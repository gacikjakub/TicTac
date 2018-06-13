package pl.gacik.tictac;

import pl.gacik.tictac.languages.MessagesProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

public class PointsSumUpDrawer {

    private PointsHolder pointsHolder;
    private MessagesProvider messagesProvider;


    public PointsSumUpDrawer(PointsHolder pointsHolder, MessagesProvider messagesProvider) {
        if (messagesProvider == null) {
            throw new IllegalArgumentException("Message provider object cannot be null");
        }
        if (pointsHolder == null) {
            throw new IllegalArgumentException("Points holder object cannot be null");
        }
        this.pointsHolder = pointsHolder;
        this.messagesProvider = messagesProvider;
    }


    public void draw(Consumer<String> out) {
        out.accept("===========================================================================");
        out.accept("| \t" + messagesProvider.pointsSummary().toUpperCase());
        out.accept("===========================================================================");
        pointsHolder.getAddedPlayers().stream()
                .sorted((player2, player1) ->
                        Integer.compare(Points.sum(pointsHolder.getPlayerPoints(player1)).points, Points.sum(pointsHolder.getPlayerPoints(player2)).points)).
                forEach(player -> {
                    out.accept(player + ": \t" + Points.sum(pointsHolder.getPlayerPoints(player)).points);
                    out.accept("_______________________________________________________________________");
                });
    }
}
