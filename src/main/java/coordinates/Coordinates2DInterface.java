package coordinates;

public interface Coordinates2DInterface extends Comparable {

    Integer getX();

    Integer getY();

    Coordinates2DInterface getLeft();

    Coordinates2DInterface getRight();

    Coordinates2DInterface getTop();

    Coordinates2DInterface getBottom();

    Coordinates2DInterface getTopLeft();

    Coordinates2DInterface getTopRight();

    Coordinates2DInterface getBottomLeft();

    Coordinates2DInterface getBottomRight();

}
