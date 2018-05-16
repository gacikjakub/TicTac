package pl.gacik.tictac.winCheckers;

import pl.gacik.tictac.coordinates.ICoordinates2D;

public interface IWinChecker {

    boolean victoryAchieved(ICoordinates2D lastMove);

    void setRequiredSeriesLength(long requiredSeriesLength);

}
