package pl.gacik.coordinates;

public class SimpleUnsignedICoordinates2D extends SimpleICoordinates2D {

    /**
     * @param x - set X coordinate
     * @param y - set Y coordinate
     * @throws IllegalArgumentException - when at least one of given coordinates is negative.
     */
    public SimpleUnsignedICoordinates2D(Integer x, Integer y) {
        super(x, y);
        if (x<0 || y<0) {
            throw new IllegalArgumentException("X and Y values must be non-negative");
        }
    }
}
