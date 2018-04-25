package coordinates;

public class SimpleUnsignedCoordinates2D extends SimpleCoordinates2D {

    public SimpleUnsignedCoordinates2D(Integer x, Integer y) throws ConstructionError {
        super(x, y);
        if (x<0 || y<0) {
            throw new ConstructionError("X and Y values must be non-negative");
        }
    }

    class ConstructionError extends Throwable {
        public ConstructionError(String s) {
            super(s);
        }
    }
}
