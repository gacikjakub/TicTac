package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

public interface WinChecker {

    boolean victoryAchieved(ICoordinates2D lastMove);

    void setRequiredSeries(int requiredSeries);

}
