package pl.gacik.tictac;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import pl.gacik.coordinates.Coordinates2DInterface;
import pl.gacik.coordinates.SimpleCoordinates2D;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
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

    @BeforeClass
    public void initClass() {
        MockitoAnnotations.initMocks(this);
    }



    @DataProvider(name = "10randomUniqueCoordinatesAndSign")
    public Object[][] randomUniqueCoordinatesAndSign() {
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

    @DataProvider(name = "10randomTwoSigns")
    public Object[][] randomTwoSigns() {
        Random generator = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] = generator.nextInt(2)==0 ? Sign.NOUGHT: Sign.CROSS;
            result[i][1] = generator.nextInt(2)==0 ? Sign.NOUGHT: Sign.CROSS;
        }
        return result;
    }

    @DataProvider(name = "10listWith4RandomInteger")
    public Object[][] randomCoordinatesAndSign() {
        Random generator = new Random();
        Object[][] result = new Object[10][1];
        List<Integer> list = new LinkedList<>();
        for(int i=0 ; i<10; i++) {
            for (int j=0; j<4;j++) {
                list.add(generator.nextInt());
            }
            result[i][0] = list;
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
    public void objectCanBeInitializedCorrectly() {
        CrossBoard board = new CrossBoard();
    }

    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void coordinatesSignPairCanBeAddedToBoardProperly(Integer x, Integer y, Sign sign) {
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
    public void addPairThrowFieldCheckExceptionWhenGivenKeyAlreadyExist(Integer x, Integer y, Sign sign) throws CrossBoard.FieldCheckException {
        board.addPair(coordinates,sign);
        board.addPair(coordinates,sign);
    }


    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void getSignReturnEmptyOptionalWhenGivenCoordinatesHasNotBeenAddedToBoard(Integer x, Integer y, Sign sign) {
        Assert.assertEquals(board.getSign(coordinates), Optional.empty());
    }

    @Test(dataProvider = "10listWith4RandomInteger")
    public void givenPairsAreSavedInOrderByKeyComparingTest(List<Integer> list) throws CrossBoard.FieldCheckException {
        // given
        board.addPair(new SimpleCoordinates2D(list.get(0), list.get(1)), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(list.get(2), list.get(3)), Sign.CROSS);
        // when - then
        Coordinates2DInterface first = board.getAddedCoordinates().get(0);
        Coordinates2DInterface second = board.getAddedCoordinates().get(1);
        if(first.getY() < second.getY()) {
            Assert.fail();
        }
        if(first.getY().equals(second.getY())) {
            if(first.getX() > second.getX()) {
                Assert.fail();
            }
        }
    }

}
