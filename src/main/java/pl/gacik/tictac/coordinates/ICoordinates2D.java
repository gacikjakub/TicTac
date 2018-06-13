package pl.gacik.tictac.coordinates;

public interface ICoordinates2D extends Comparable {

    Integer getX();

    Integer getY();

    /**
     * Return new instance with coordinates located one left from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getLeft();

    /**
     * Return new instance with coordinates located one right from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getRight();

    /**
     * Return new instance with coordinates located one up from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getTop();

    /**
     * Return new instance with coordinates located one down from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getBottom();

    /**
     * Return new instance with coordinates located one left and one up from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getTopLeft();

    /**
     * Return new instance with coordinates located one right and one up from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getTopRight();

    /**
     * Return new instance with coordinates located one left and one down from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getBottomLeft();

    /**
     * Return new instance with coordinates located one right and one down from this.
     *
     * @return ICoordinates2D
     */
    ICoordinates2D getBottomRight();
}
