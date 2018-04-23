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
    public void getXReturnInitializedValueCorrectlyTest(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getX(), x);
    }

    @Test(dataProvider = "10randomCoordinates")
    public void getYReturnInitializedValueCorrectlyTest(Integer x, Integer y) {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(x, y);
        Assert.assertEquals(coordinates2D.getY(), y);
    }

}
