package pl.gacik.coordinates;

public interface Coordinates2DInterface extends Comparable {

    Integer getX();

    Integer getY();

    /**
     * Return new instance with coordinates located one left from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getLeft();

    /**
     * Return new instance with coordinates located one right from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getRight();

    /**
     * Return new instance with coordinates located one up from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getTop();

    /**
     * Return new instance with coordinates located one down from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getBottom();

    /**
     * Return new instance with coordinates located one left and one up from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getTopLeft();

    /**
     * Return new instance with coordinates located one right and one up from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getTopRight();

    /**
     * Return new instance with coordinates located one left and one down from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getBottomLeft();

    /**
     * Return new instance with coordinates located one right and one down from this.
     * @return Coordinates2DInterface
     */
    Coordinates2DInterface getBottomRight();
}
