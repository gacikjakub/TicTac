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
        return new SimpleCoordinates2D(this.x-1, this.y);
    }

    public Coordinates2DInterface getRight() {
        return new SimpleCoordinates2D(this.x+1, this.y);
    }

    public Coordinates2DInterface getTop() {
        return new SimpleCoordinates2D(this.x, this.y+1);
    }

    public Coordinates2DInterface getBottom() {
        return new SimpleCoordinates2D(this.x, this.y-1);
    }

    public Coordinates2DInterface getTopLeft() {
        return new SimpleCoordinates2D(this.x-1, this.y+1);
    }

    public Coordinates2DInterface getTopRight() {
        return new SimpleCoordinates2D(this.x+1, this.y+1);
    }

    public Coordinates2DInterface getBottomLeft() {
        return new SimpleCoordinates2D(this.x-1, this.y-1);
    }

    public Coordinates2DInterface getBottomRight() {
        return new SimpleCoordinates2D(this.x+1, this.y-1);
    }
}
