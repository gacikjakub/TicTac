package pl.gacik.tictac;

import java.util.Collection;
import java.util.Objects;

public class Points {

    public Points(int points) {
        this.points = points;
    }

    public final int points;

    static Points sum(Collection<Points> collection) {
        return new Points(collection.stream().filter(Objects::nonNull).mapToInt(i -> i.points).sum());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Points)) {
            return false;
        }
        return (this.points == ((Points) o).points);
    }

}
