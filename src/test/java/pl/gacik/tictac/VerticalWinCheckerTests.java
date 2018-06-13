package pl.gacik.tictac;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.gacik.tictac.coordinates.ICoordinates2D;
import pl.gacik.tictac.coordinates.SimpleICoordinates2D;
import pl.gacik.tictac.boards.CrossIBoard;
import pl.gacik.tictac.boards.IBoard;
import pl.gacik.tictac.winCheckers.IWinChecker;
import pl.gacik.tictac.winCheckers.VerticalWinChecker;

import java.util.Random;

public class VerticalWinCheckerTests {

    @DataProvider(name = "signs")
    private Object[][] signs() {
        int signsAmount = Sign.values().length;
        Object[][] result = new Object[signsAmount][1];
        int counter = 0;
        for (Sign sign : Sign.values()) {
            result[counter][0] = sign;
            counter++;
        }
        return result;
    }

    @Test(dataProvider = "signs")
    public void shouldReturnTrueWhenAchievedRequiredSignsAmount(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        IWinChecker IWinChecker = new VerticalWinChecker(board);
        IWinChecker.setRequiredSeriesLength(5);
        for (int i = 0; i < 5; i++) {
            lastCoordinates = new SimpleICoordinates2D(1, i);
            board.addPair(lastCoordinates, sign);
        }
        // when - then
        Assert.assertTrue(IWinChecker.victoryAchieved(lastCoordinates));
        Assert.assertTrue(IWinChecker.victoryAchieved(lastCoordinates.getBottom().getBottom()));
    }

    @Test(dataProvider = "signs")
    public void shouldReturnFalseWhenDoNotAchievedRequiredSignsAmount(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        IWinChecker IWinChecker = new VerticalWinChecker(board);
        IWinChecker.setRequiredSeriesLength(10);
        for (int i = 0; i < 8; i++) {
            lastCoordinates = new SimpleICoordinates2D(1, i);
            board.addPair(lastCoordinates, sign);
        }
        // when - then
        Assert.assertFalse(IWinChecker.victoryAchieved(lastCoordinates));
        Assert.assertFalse(IWinChecker.victoryAchieved(lastCoordinates.getBottom().getBottom()));
    }

    @Test(dataProvider = "signs")
    public void shouldReturnFalseWhenRequiredSignsAmountInColumnIsOkButSeparatedOtherSign(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        int i = 0;
        for (; i < 4; i++) {
            lastCoordinates = new SimpleICoordinates2D(1, i);
            board.addPair(lastCoordinates, sign);
        }
        board.addPair(lastCoordinates.getTop(), randomDifferentSign(sign));
        i++;
        for (; i < 10; i++) {
            lastCoordinates = new SimpleICoordinates2D(1, i);
            board.addPair(lastCoordinates, sign);
        }
        IWinChecker IWinChecker = new VerticalWinChecker(board);
        IWinChecker.setRequiredSeriesLength(8);
        // when - then
        Assert.assertFalse(IWinChecker.victoryAchieved(lastCoordinates));
        Assert.assertFalse(IWinChecker.victoryAchieved(lastCoordinates.getBottom().getBottom()));
    }

    @Test(dataProvider = "signs", expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUnderGivenCoordinateIsNoSign(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        int i = 0;
        for (; i < 4; i++) {
            lastCoordinates = new SimpleICoordinates2D(1, i);
            board.addPair(lastCoordinates, sign);
        }
        IWinChecker IWinChecker = new VerticalWinChecker(board);
        IWinChecker.setRequiredSeriesLength(8);
        // when - then
        IWinChecker.victoryAchieved(lastCoordinates.getLeft());
    }

    private Sign randomDifferentSign(Sign s) {
        Sign random = s;
        Random random1 = new Random();
        while (random == s) {
            random = Sign.values()[random1.nextInt(Sign.values().length)];
        }
        return random;
    }
}