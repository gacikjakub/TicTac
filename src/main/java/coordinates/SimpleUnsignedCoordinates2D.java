package coordinates;

public class SimpleUnsignedCoordinates2D extends SimpleCoordinates2D {

    /**
     * Constructor for Class. Throw Exception when at least one of given coordinates is negative.
     * @param x
     * @param y
     * @throws ConstructionError
     */
    public SimpleUnsignedCoordinates2D(Integer x, Integer y) throws ConstructionError {
        super(x, y);
        if (x<0 || y<0) {
            throw new ConstructionError("X and Y values must be non-negative");
        }
    }

    /**
     * This exception is throw when creation of object failed
     */
    class ConstructionError extends Throwable {
        public static final long serialVersionUID = 273642634236426394L;
        public ConstructionError(String s) {
            super(s);
        }
    }
}
