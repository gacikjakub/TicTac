package coordinates;

import org.testng.annotations.Test;
import org.testng.Assert;

public class SimpleCoordinates2DTests {

    @Test
    public void objectCanBeInitializedCorrectlyTest() {
        Coordinates2DInterface coordinates2D = new SimpleCoordinates2D(10, 20);
    }

    @Test
    public void twoObjectsWithSameCoordinatesIsTheSameTest() {
        Coordinates2DInterface first = new SimpleCoordinates2D(10,20);
        Coordinates2DInterface second = new SimpleCoordinates2D(10,20);
        Assert.assertEquals(first, second);
    }

}
