package pl.gacik.tictac;

import pl.gacik.coordinates.Coordinates2DInterface;
import pl.gacik.coordinates.SimpleCoordinates2D;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Random;


public class CrossBoardTests {

    private CrossBoard board;

    @BeforeMethod
    public void setUp() {
        board = new CrossBoard();
    }


    @Mock
    private Coordinates2DInterface coordinates = Mockito.mock(SimpleCoordinates2D.class);


    @DataProvider(name = "10randomUniqueCoordinatesAndSign")
    public Object[][] randomCoordinatesAndSign() {
        Random generator = new Random();
        Object[][] result = new Object[10][3];
        int x = -100;
        int y = -500;
        for(int i=0 ; i<10; i++) {
            result[i][0] = x += generator.nextInt(50) + 1;
            result[i][1] = y += generator.nextInt(100) + 1;
            result[i][2] = generator.nextInt(2)==0 ? Sign.NOUGHT: Sign.CROSS;
        }
        return result;
    }

    @DataProvider(name = "10binaryRandom")
    public Object[][] binaryRandom() {
        Random generator = new Random();
        Object[][] result = new Object[10][1];
        for(int i=0 ; i<10; i++) {
            result[i][0] = generator.nextInt(2);
        }
        return result;
    }

    @Test
    public void objectCanBeInitializedCorrectlyTest()
    {
        CrossBoard board = new CrossBoard();
    }

    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void coordinatesSignPairCanBeAddedToBoardProperlyTest(Integer x, Integer y, Sign sign) {
        // given
        try {
            board.addPair(coordinates,sign);
        } catch (CrossBoard.FieldCheckException e) {
            Assert.fail("Field Check Exception has been handled");
        }
        // when - then
        Assert.assertEquals(board.getSign(coordinates), Optional.of(sign));
    }

    @Test(dataProvider = "10randomUniqueCoordinatesAndSign", expectedExceptions = CrossBoard.FieldCheckException.class)
    public void addPairThrowFieldCheckExceptionWhenGivenKeyAlreadyExistTest(Integer x, Integer y, Sign sign) throws CrossBoard.FieldCheckException {
        board.addPair(coordinates,sign);
        board.addPair(coordinates,sign);
    }


    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void getSignReturnEmptyOptionalWhenGivenCoordinatesHasNotBeenAddedToBoard(Integer x, Integer y, Sign sign) {
        Assert.assertEquals(board.getSign(coordinates), Optional.empty());
    }


}
