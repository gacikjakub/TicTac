package coordinates;

import java.util.Objects;

public class SimpleCoordinates2D implements Coordinates2DInterface {

    private final Integer x;

    private final Integer y;

    /**
     * Constructed require give X and Y coordinates, which not will be able to change.
     * @param x
     * @param y
     */
    public SimpleCoordinates2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return coordinate X
     * @return Integer
     */
    public Integer getX() {
        return x;
    }

    /**
     * Return coordinate Y
     * @return Integer
     */
    public Integer getY() {
        return y;
    }

    /**
     * Return true when Y and X coordinates are the same in both objects.
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        SimpleCoordinates2D casted;
        try {
            casted = (SimpleCoordinates2D) o;
        } catch (ClassCastException e) {
            return false;
        }
        return this.x.equals(casted.x) && this.y.equals(casted.y);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    /**
     * Return new instance with one less coordinate X.  x-1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getLeft() {
        return new SimpleCoordinates2D(this.x-1, this.y);
    }

    /**
     * Return new instance with one more coordinate X.  x+1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getRight() {
        return new SimpleCoordinates2D(this.x+1, this.y);
    }

    /**
     * Return new instance with one more coordinate Y.  y+1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getTop() {
        return new SimpleCoordinates2D(this.x, this.y+1);
    }

    /**
     * Return new instance with one less coordinate Y.  y-1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getBottom() {
        return new SimpleCoordinates2D(this.x, this.y-1);
    }

    /**
     * Return new instance with one more coordinate Y and one less coordinate X.  y+1  x-1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getTopLeft() {
        return new SimpleCoordinates2D(this.x-1, this.y+1);
    }

    /**
     * Return new instance with one more coordinate Y and one more coordinate X.  y+1  x+1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getTopRight() {
        return new SimpleCoordinates2D(this.x+1, this.y+1);
    }

    /**
     * Return new instance with one less coordinate Y and one less coordinate X.  y-1  x-1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getBottomLeft() {
        return new SimpleCoordinates2D(this.x-1, this.y-1);
    }

    /**
     * Return new instance with one less coordinate Y and one more coordinate X.  y-1  x+1
     * @return Coordinates2DInterface
     */
    public Coordinates2DInterface getBottomRight() {
        return new SimpleCoordinates2D(this.x+1, this.y-1);
    }

    /**
     * Return 1 when Y coordinate of specified object is HIGHER or if Y is the same, when X coordinate of specified object is LOWER
     * Return -1 when Y coordinate of specified object is LOWER or if Y is the same, when X coordinate of specified object is HIGHER
     * Return 0 when Y and X coordinates are the same in both objects.
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        SimpleCoordinates2D specified = (SimpleCoordinates2D) o;
        if(this.y < specified.y ) {
            return 1;
        }
        if(this.y > specified.y ) {
            return -1;
        }
        if(this.x < specified.x ) {
            return -1;
        }
        if(this.x > specified.x ) {
            return 1;
        }
        return 0;
    }
}
