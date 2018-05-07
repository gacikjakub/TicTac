package pl.gacik.tictac;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.gacik.coordinates.ICoordinates2D;
import pl.gacik.coordinates.SimpleICoordinates2D;

import java.util.Random;

public class VerticalWinCheckerTests {

    @DataProvider(name = "signs")
    private Object[][] signs() {
        int signsAmount = Sign.values().length;
        Object[][] result = new Object[signsAmount][1];
        int counter = 0;
        for(Sign sign: Sign.values()) {
            result[counter][0] = sign;
            counter++;
        }
        return result;
    }

    @Test(dataProvider = "signs")
    public void shouldReturnTrueWhenAchievedRequiredSignAmount(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        for(int i=0; i < 5; i ++) {
            lastCoordinates = new SimpleICoordinates2D(i,1);
            board.addPair(lastCoordinates, sign);
        }
        WinChecker winChecker = new VerticalWinChecker(board);
        winChecker.setRequiredSeries(5);
        // when - then
        Assert.assertTrue(winChecker.victoryAchieved(lastCoordinates));
        Assert.assertTrue(winChecker.victoryAchieved(lastCoordinates.getLeft().getLeft()));
    }

    @Test(dataProvider = "signs")
    public void shouldReturnFalseWhenDoNotAchievedRequiredSignAmount(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        for(int i=0; i < 8; i ++) {
            lastCoordinates = new SimpleICoordinates2D(i,1);
            board.addPair(lastCoordinates, sign);
        }
        WinChecker winChecker = new VerticalWinChecker(board);
        winChecker.setRequiredSeries(10);
        // when - then
        Assert.assertFalse(winChecker.victoryAchieved(lastCoordinates));
        Assert.assertFalse(winChecker.victoryAchieved(lastCoordinates.getLeft().getLeft()));
    }

    @Test(dataProvider = "signs")
    public void shouldReturnFalseWhenRequiredSignAmountInColumnIsOkButSeparatedOtherSign(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        int i = 0;
        for(;i < 4; i ++) {
            lastCoordinates = new SimpleICoordinates2D(i,1);
            board.addPair(lastCoordinates, sign);
        }
        board.addPair(lastCoordinates.getRight(), randomDifferentSign(sign));
        i++;
        for(; i < 10; i++) {
            lastCoordinates = new SimpleICoordinates2D(i,1);
            board.addPair(lastCoordinates, sign);
        }
        WinChecker winChecker = new VerticalWinChecker(board);
        winChecker.setRequiredSeries(8);
        // when - then
        Assert.assertFalse(winChecker.victoryAchieved(lastCoordinates));
        Assert.assertFalse(winChecker.victoryAchieved(lastCoordinates.getLeft().getLeft()));
    }

    @Test(dataProvider = "signs", expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUnderGivenCoordinateIsNoSign(Sign sign) throws IBoard.FieldCheckException {
        // given
        IBoard board = new CrossIBoard();
        ICoordinates2D lastCoordinates = null;
        int i = 0;
        for(;i < 4; i ++) {
            lastCoordinates = new SimpleICoordinates2D(i,1);
            board.addPair(lastCoordinates, sign);
        }
        WinChecker winChecker = new VerticalWinChecker(board);
        winChecker.setRequiredSeries(8);
        // when - then
     winChecker.victoryAchieved(lastCoordinates.getTop());
    }

    private Sign randomDifferentSign(Sign s) {
        Sign random = s;
        Random random1 = new Random();
        while(random == s) {
            random = Sign.values()[random1.nextInt(Sign.values().length)];
        }
        return random;
    }
}
