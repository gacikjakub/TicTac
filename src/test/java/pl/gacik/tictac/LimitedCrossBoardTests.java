package pl.gacik.tictac;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.gacik.coordinates.SimpleICoordinates2D;
import java.util.Random;


public class LimitedCrossBoardTests {

    public LimitedCrossBoardTests(IBoard board) {
        this.board = board;
    }

    @BeforeMethod
    public void clearBoard() {
        this.board = new LimitedCrossIBoard(board.getBordersKeeper().getBorder(BorderDirection.RIGHT), board.getBordersKeeper().getBorder(BorderDirection.TOP));
    }

    private IBoard board;

    @DataProvider(name = "10randomPositiveBoardSize")
    public static Object[][] randomPositiveBoardSize() {
        Random generator = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] =  generator.nextInt(50) + 10;
            result[i][1] =  generator.nextInt(100) + 20;
        }
        return result;
    }


    @DataProvider(name = "10biggerThenBoardSizeCoordinatesAndSign")
    public static Object[][] biggerThenBoardSizeCoordinatesAndSign() {
        Random generator = new Random();
        Object[][] result = new Object[10][3];
        for(int i=0 ; i<10; i++) {
            result[i][0] =  generator.nextInt(50) + 51;
            result[i][1] =  generator.nextInt(100) + 101;
            result[i][2] = generator.nextInt(2)==0 ? Sign.NOUGHT: Sign.CROSS;
        }
        return result;
    }

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

    @DataProvider(name = "10lowerThenBoardSizeCoordinatesAndSign")
    public static Object[][] lowerThenBoardSizeCoordinatesAndSign() {
        Random generator = new Random();
        Object[][] result = new Object[10][3];
        for(int i=0 ; i<10; i++) {
            result[i][0] =  generator.nextInt(9) + 1;
            result[i][1] =  generator.nextInt(19) + 1;
            result[i][2] = generator.nextInt(2)==0 ? Sign.NOUGHT: Sign.CROSS;
        }
        return result;
    }

    @Factory(dataProvider = "10randomPositiveBoardSize")
    public LimitedCrossBoardTests(Integer width, Integer height) {
        this.board = new LimitedCrossIBoard(width, height);
    }

    @Test(dataProvider = "10biggerThenBoardSizeCoordinatesAndSign", expectedExceptions = IBoard.FieldCheckException.class)
    public void shouldThrowExceptionWhenGivenCoordinatesAreOutOfMaxSize(Integer x, Integer y, Sign sign) throws IBoard.FieldCheckException {
        // when - then
        board.addPair(new SimpleICoordinates2D(x,y),sign);
    }


    @Test(dataProvider = "10lowerThenBoardSizeCoordinatesAndSign")
    public void shouldProperlyAddPairsWithCorrectCoordinates(Integer x, Integer y, Sign sign) throws IBoard.FieldCheckException {
        // when - then
        board.addPair(new SimpleICoordinates2D(x,y),sign);
    }

    @Test(dataProvider = "signs")
    public void shouldReturnFalseWhenBoardHasNoAvailableField(Sign sign) throws IBoard.FieldCheckException {
        // given
        for(int x = 1; x< this.board.getBordersKeeper().getBorder(BorderDirection.RIGHT); x++) {
            for(int y = 1; y< this.board.getBordersKeeper().getBorder(BorderDirection.TOP); y++) {
                this.board.addPair(new SimpleICoordinates2D(x,y),sign);
            }
        }
        // when - then
        Assert.assertFalse(this.board.hasAvailableField());
    }

    @Test(dataProvider = "signs")
    public void shouldReturnTrueWhenBoardHasAvailableFields(Sign sign) throws IBoard.FieldCheckException {
        // given
        for(int x = 1; x< this.board.getBordersKeeper().getBorder(BorderDirection.RIGHT); x++) {
            for(int y = 1; y< this.board.getBordersKeeper().getBorder(BorderDirection.TOP) -1; y++) {
                this.board.addPair(new SimpleICoordinates2D(x,y),sign);
            }
        }
        // when - then
        Assert.assertTrue(this.board.hasAvailableField());
    }

}
