package coordinates;

public class SimpleCoordinates2D implements Coordinates2DInterface {

    private final Integer x;

    private final Integer y;


    public SimpleCoordinates2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public boolean equals(Object o) {
        SimpleCoordinates2D casted;
        try {
            casted = (SimpleCoordinates2D) o;
        } catch (ClassCastException e) {
            return false;
        }
        return this.x.equals(casted.x) && this.y.equals(casted.y);
    }

    public Coordinates2DInterface getLeft() {
        return null;
    }

    public Coordinates2DInterface getRight() {
        return null;
    }

    public Coordinates2DInterface getTop() {
        return null;
    }

    public Coordinates2DInterface getBottom() {
        return null;
    }

    public Coordinates2DInterface getTopLeft() {
        return null;
    }

    public Coordinates2DInterface getTopRight() {
        return null;
    }

    public Coordinates2DInterface getBottomLeft() {
        return null;
    }

    public Coordinates2DInterface getBottomRight() {
        return null;
    }
}
