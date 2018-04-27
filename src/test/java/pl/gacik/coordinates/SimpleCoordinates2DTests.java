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
    public void objectCanBeInitializedCorrectly(Integer x, Integer y) {
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void twoObjectsWithSameCoordinatesIsTheSame(Integer x, Integer y) {
        // given
        ICoordinates2D first = new SimpleICoordinates2D(x,y);
        ICoordinates2D second = new SimpleICoordinates2D(x,y);
        // when - then
        Assert.assertEquals(first, second);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void shouldGetInitializedXYValuesCorrectly(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getX(), x);
        Assert.assertEquals(coordinates2D.getY(), y);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getLeftReturnsObjectWithXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getLeft(), new SimpleICoordinates2D(x-1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getRightReturnsObjectWithXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getRight(), new SimpleICoordinates2D(x+1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopReturnsObjectWithYCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTop(), new SimpleICoordinates2D(x, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomReturnsObjectWithYCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottom(), new SimpleICoordinates2D(x, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopLeftReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTopLeft(), new SimpleICoordinates2D(x-1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopRightReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getTopRight(), new SimpleICoordinates2D(x+1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomLeftReturnsObjectWithYCoordinateOneLessAndXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottomLeft(), new SimpleICoordinates2D(x-1, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomRightReturnsObjectWithYCoordinateOneLessAndXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        // given
        ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
        // when - then
        Assert.assertEquals(coordinates2D.getBottomRight(), new SimpleICoordinates2D(x+1, y-1));
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
    public void compareToReturnOneWhenYIsLowerThenInSpecifiedObject(Integer y, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleICoordinates2D(0,y).compareTo(new SimpleICoordinates2D(0,y+delta)), 1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnMinusOneWhenYIsHigherThenInSpecifiedObject(Integer y, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleICoordinates2D(0,y).compareTo(new SimpleICoordinates2D(0,y-delta)), -1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnMinusOneWhenXIsLowerThenInSpecifiedObjectAndYIsTheSame(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleICoordinates2D(x,0).compareTo(new SimpleICoordinates2D(x+delta,0)), -1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnOneWhenXIsHigherThenInSpecifiedObjectAndYIsTheSame(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleICoordinates2D(x,0).compareTo(new SimpleICoordinates2D(x-delta,0)), 1);
    }

    @Test(dataProvider = "10cordAndDelta")
    public void compareToReturnZeroWhenXAndYAreTheSameLikeInSpecifiedObject(Integer x, Integer delta) {
        // when - then
        Assert.assertEquals(new SimpleICoordinates2D(x,delta).compareTo(new SimpleICoordinates2D(x,delta)), 0);
    }

}
