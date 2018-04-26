package pl.gacik.coordinates;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Random;

@Test(groups = {"SimpleCoordinates2DTests"})
public class SimpleCoordinates2DTests {

    @DataProvider(name = "10randomCoordinates")
    public static Object[][] randomCoordinates() {
        Random generator = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] = generator.nextInt();
            result[i][1] = generator.nextInt();
        }
        return result;
    }

    @Test(dataProvider = "10randomCoordinates")
    public void objectCanBeInitializedCorrectlyTest(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void twoObjectsWithSameCoordinatesIsTheSameTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface first = new SimpleCoordinates2D(x,y);
        Coordinates2DInterface second = new SimpleCoordinates2D(x,y);
        // when - then
        Assert.assertEquals(first, second);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getXReturnInitializedXValueCorrectlyTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getX(), x);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getYReturnInitializedYValueCorrectlyTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getY(), y);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getLeftReturnsObjectWithXCoordinateOneLessThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getLeft(), new SimpleCoordinates2D(x-1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getRightReturnsObjectWithXCoordinateOneMoreThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getRight(), new SimpleCoordinates2D(x+1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopReturnsObjectWithYCoordinateOneMoreThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTop(), new SimpleCoordinates2D(x, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomReturnsObjectWithYCoordinateOneLessThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottom(), new SimpleCoordinates2D(x, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopLeftReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneLessThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTopLeft(), new SimpleCoordinates2D(x-1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopRightReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneMoreThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTopRight(), new SimpleCoordinates2D(x+1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomLeftReturnsObjectWithYCoordinateOneLessAndXCoordinateOneLessThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottomLeft(), new SimpleCoordinates2D(x-1, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomRightReturnsObjectWithYCoordinateOneLessAndXCoordinateOneMoreThenExecutingObjectTest(Integer x, Integer y) {
        // given
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottomRight(), new SimpleCoordinates2D(x+1, y-1));
    }

    @DataProvider(name = "10cordAndDelta")
    public static Object[][] cordAndDelta() {
        Random gen = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] = gen.nextInt(9502224) - 71830;
            result[i][1] = gen.nextInt(13579) + 1;
        }
        return result;
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnOneWhenYIsLowerThenInSpecifiedObjectTest(Integer y, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleCoordinates2D(0,y).compareTo(new SimpleCoordinates2D(0,y+delta)), 1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnMinusOneWhenYIsHigherThenInSpecifiedObjectTest(Integer y, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleCoordinates2D(0,y).compareTo(new SimpleCoordinates2D(0,y-delta)), -1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnMinusOneWhenXIsLowerThenInSpecifiedObjectAndYIsTheSameTest(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleCoordinates2D(x,0).compareTo(new SimpleCoordinates2D(x+delta,0)), -1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnOneWhenXIsHigherThenInSpecifiedObjectAndYIsTheSameTest(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleCoordinates2D(x,0).compareTo(new SimpleCoordinates2D(x-delta,0)), 1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnZeroWhenXAndYAreTheSameLikeInSpecifiedObjectTest(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleCoordinates2D(x,delta).compareTo(new SimpleCoordinates2D(x,delta)), 0);
    }

}
