package pl.gacik.coordinates;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

//@Test(dependsOnGroups = {"SimpleCoordinates2DTests"})
public class SimpleUnsignedCoordinates2DTests {

    @DataProvider(name = "10randomNegativeCoordinates")
    public static Object[][] randomNegativeCoordinates() {
        Random generator = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] = generator.nextInt(Integer.MAX_VALUE -3) - Integer.MIN_VALUE;
            result[i][1] = generator.nextInt(Integer.MAX_VALUE -3) - Integer.MIN_VALUE;
        }
        return result;
    }

    @DataProvider(name = "10randomNonNegativeCoordinates")
    public static Object[][] randomNonNegativeCoordinates() {
        Random generator = new Random();
        Object[][] result = new Object[10][2];
        for(int i=0 ; i<10; i++) {
            result[i][0] = generator.nextInt(Integer.MAX_VALUE);
            result[i][1] = generator.nextInt(Integer.MAX_VALUE);
        }
        return result;
    }


    @Test(dataProvider = "10randomNegativeCoordinates", expectedExceptions = IllegalArgumentException.class)
    public void coordinatesWithNegativeXYCannotBeCreated(Integer x, Integer y) {
        new SimpleUnsignedCoordinates2D(x,y);
    }

    @Test(dataProvider = "10randomNonNegativeCoordinates")
    public void coordinatesWithNonNegativeXTStillCanBeCreated(Integer x, Integer y) {
        try {
            new SimpleUnsignedCoordinates2D(x, y);
        } catch (IllegalArgumentException e) {
            Assert.fail("ConstructionError has been handled");
        }
    }

}
