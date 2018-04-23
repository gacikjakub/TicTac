package coordinates;

public class SimpleCoordinates2D implements Coordinates2DInterface {

    private final int x;

    private final int y;


    public SimpleCoordinates2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o) {
        SimpleCoordinates2D casted;
        try {
            casted = (SimpleCoordinates2D) o;
        } catch (ClassCastException e) {
            return false;
        }
        if (this.x == casted.x && this.y == casted.y) {
            return true;
        }
        return false;
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
