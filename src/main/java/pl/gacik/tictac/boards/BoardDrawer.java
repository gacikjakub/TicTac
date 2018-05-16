package pl.gacik.tictac.boards;

import pl.gacik.tictac.coordinates.ICoordinates2D;
import pl.gacik.tictac.coordinates.SimpleICoordinates2D;

import java.util.List;

public class BoardDrawer {

    private final CrossIBoard board;
    private final BordersKeeper bordersKeeper;

    public BoardDrawer(CrossIBoard board) {
        this.board = board;
        this.bordersKeeper = board.getBordersKeeper();
        this.signs = board.getAddedCoordinates();
    }

    private StringBuilder builder = new StringBuilder();
    private List<ICoordinates2D> signs;


    private ICoordinates2D removeFromSignsList() {
        if (!signs.isEmpty()) {
            return signs.remove(0);
        } else {
            return new SimpleICoordinates2D(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
    }

    private void drawColumnsNumber() {
        builder.append(" ");
        for (int x = bordersKeeper.getBorder(BorderDirection.LEFT); x <= bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
            if (x > -10 && x < 100) {
                builder.append(" ");
            }
            builder.append(x);
            if (x < 0 || x >= 10) {
                builder.append(" ");
            } else {
                builder.append("  ");
            }
        }
        builder.append("\n");
    }

    private void drawHorizontalBreak() {
        for (int x = bordersKeeper.getBorder(BorderDirection.LEFT); x <= bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
            builder.append("+---");
        }
        builder.append("+\n");
    }

    private void drawRows() {

    }

    public void draw() {
        ICoordinates2D cord = removeFromSignsList();
        drawColumnsNumber();
        for (int y = bordersKeeper.getBorder(BorderDirection.TOP); y >= bordersKeeper.getBorder(BorderDirection.DOWN); y--) {
            drawHorizontalBreak();
            int x = bordersKeeper.getBorder(BorderDirection.LEFT);
            while (x <= bordersKeeper.getBorder(BorderDirection.RIGHT)) {
                if (cord.getY() == y) {
                    for (; x <= cord.getX() - 1; x++) {
                        builder.append("|   ");
                    }
                    builder.append("| ").append(board.getSign(cord).get().getChar()).append(" ");
                    x++;
                    if (cord.getX().equals(bordersKeeper.getBorder(BorderDirection.RIGHT))) {
                        builder.append("|  ").append(y).append("\n");
                    }
                    cord = removeFromSignsList();
                } else {
                    for (; x <= bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
                        builder.append("|   ");
                    }
                    x++;
                    builder.append("|  ").append(y).append("\n");
                }
            }
        }
        drawHorizontalBreak();
        System.out.println(builder);
    }


}
