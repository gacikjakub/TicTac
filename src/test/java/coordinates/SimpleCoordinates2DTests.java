package coordinates;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Random;

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
        Coordinates2DInterface first = new SimpleCoordinates2D(x,y);
        Coordinates2DInterface second = new SimpleCoordinates2D(x,y);
        Assert.assertEquals(first, second);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getXReturnInitializedXValueCorrectlyTest(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getX(), x);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getYReturnInitializedYValueCorrectlyTest(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getY(), y);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getLeftReturnsObjectWithXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getLeft(), new SimpleCoordinates2D(x-1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getRightReturnsObjectWithXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getRight(), new SimpleCoordinates2D(x+1, y));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopReturnsObjectWithYCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getTop(), new SimpleCoordinates2D(x, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomReturnsObjectWithYCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getBottom(), new SimpleCoordinates2D(x, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopLeftReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getTopLeft(), new SimpleCoordinates2D(x-1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getTopRightReturnsObjectWithYCoordinateOneMoreAndXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getTopRight(), new SimpleCoordinates2D(x+1, y+1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomLeftReturnsObjectWithYCoordinateOneLessAndXCoordinateOneLessThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getBottomLeft(), new SimpleCoordinates2D(x-1, y-1));
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getBottomRightReturnsObjectWithYCoordinateOneLessAndXCoordinateOneMoreThenExecutingObject(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getBottomRight(), new SimpleCoordinates2D(x+1, y-1));
    }

}
