package pl.gacik.tictac;

import pl.gacik.coordinates.ICoordinates2D;

public interface IWinChecker {

    boolean victoryAchieved(ICoordinates2D lastMove);

    void setRequiredSeriesLength(long requiredSeriesLength);

}
