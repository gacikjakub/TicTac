package pl.gacik.tictac;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import pl.gacik.coordinates.ICoordinates2D;
import pl.gacik.coordinates.SimpleICoordinates2D;
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

    private CrossIBoard board;

    @BeforeMethod
    public void setUp() {
        board = new CrossIBoard();
    }


    @Mock
    private ICoordinates2D coordinates = Mockito.mock(SimpleICoordinates2D.class);

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
        CrossIBoard board = new CrossIBoard();
    }

    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void coordinatesSignPairCanBeAddedToBoardProperly(Integer x, Integer y, Sign sign) {
        // given
        try {
            board.addPair(coordinates,sign);
        } catch (CrossIBoard.FieldCheckException e) {
            Assert.fail("Field Check Exception has been handled");
        }
        // when - then
        Assert.assertEquals(board.getSign(coordinates), Optional.of(sign));
    }

    @Test(dataProvider = "10randomUniqueCoordinatesAndSign", expectedExceptions = CrossIBoard.FieldCheckException.class)
    public void addPairThrowFieldCheckExceptionWhenGivenKeyAlreadyExist(Integer x, Integer y, Sign sign) throws CrossIBoard.FieldCheckException {
        board.addPair(coordinates,sign);
        board.addPair(coordinates,sign);
    }


    @Test(dataProvider = "10randomUniqueCoordinatesAndSign")
    public void getSignReturnEmptyOptionalWhenGivenCoordinatesHasNotBeenAddedToBoard(Integer x, Integer y, Sign sign) {
        Assert.assertEquals(board.getSign(coordinates), Optional.empty());
    }

    private void assertIsGreaterOrTheSame(Integer a, Integer b) {
        if(a < b) {
            Assert.fail();
        }
    }

    private void assertIsLowerOrTheSame(Integer a, Integer b) {
        if(a > b) {
            Assert.fail();
        }
    }

    @Test(dataProvider = "10listWith4RandomInteger")
    public void givenPairsAreSavedInOrderByKeyComparingTest(List<Integer> list) throws CrossIBoard.FieldCheckException {
        // given
        board.addPair(new SimpleICoordinates2D(list.get(0), list.get(1)), Sign.CROSS);
        board.addPair(new SimpleICoordinates2D(list.get(2), list.get(3)), Sign.CROSS);
        // when - then
        ICoordinates2D first = board.getAddedCoordinates().get(0);
        ICoordinates2D second = board.getAddedCoordinates().get(1);
        assertIsGreaterOrTheSame(first.getY(), second.getY());
        if(first.getY().equals(second.getY())) {
         assertIsLowerOrTheSame(first.getX(), second.getY());
        }
    }

}
