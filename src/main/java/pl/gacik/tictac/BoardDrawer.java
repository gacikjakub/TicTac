package pl.gacik.tictac;

import pl.gacik.coordinates.Coordinates2DInterface;
import pl.gacik.coordinates.SimpleCoordinates2D;

import java.util.List;

public class BoardDrawer {

    private final CrossBoard board;
    private final BordersKeeper bordersKeeper;

    public BoardDrawer(CrossBoard board) {
        this.board = board;
        this.bordersKeeper = board.getBordersKeeper();
    }

    public void draw() {
        StringBuilder builder = new StringBuilder();
        List<Coordinates2DInterface> signs = board.getAddedCoordinates();
        Coordinates2DInterface cord;
        if(!signs.isEmpty()) {
            cord = signs.remove(0);
        }
        else {
            cord = new SimpleCoordinates2D(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        builder.append(" ");
        for(int x = bordersKeeper.getBorder(BorderDirection.LEFT); x<=bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
            if(x>-10 && x<100) {
                builder.append(" ");
            }
            builder.append(x);
            if(x<0 || x>=10) {
                builder.append(" ");
            } else {
                builder.append("  ");
            }
        }
        builder.append("\n");
        for(int y = bordersKeeper.getBorder(BorderDirection.TOP); y>=bordersKeeper.getBorder(BorderDirection.DOWN); y--) {

            for(int x = bordersKeeper.getBorder(BorderDirection.LEFT); x<=bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
                builder.append("+---");
            }
            builder.append("+\n");
            int x = bordersKeeper.getBorder(BorderDirection.LEFT);
            while (x <= bordersKeeper.getBorder(BorderDirection.RIGHT)) {
                if(cord.getY() == y) {
                    for (x = x; x <= cord.getX() -1; x++) {
                        builder.append("|   ");
                    }
                    builder.append("| ").append(board.getSign(cord).get().getChar()).append(" ");
                    x++;
                    if(cord.getX().equals(bordersKeeper.getBorder(BorderDirection.RIGHT))) {
                        builder.append("|  ").append(y).append("\n");
                    }
                    if (!signs.isEmpty()) {
                        cord = signs.remove(0);
                    }
                    else {
                        cord = new SimpleCoordinates2D(Integer.MIN_VALUE, Integer.MIN_VALUE);
                    }
                }
                else {
                    for (x = x; x <= bordersKeeper.getBorder(BorderDirection.RIGHT); x++) {
                        builder.append("|   ");
                    }
                    x++;
                    builder.append("|  ").append(y).append("\n");
                }
            }
        }
        for(int nx = bordersKeeper.getBorder(BorderDirection.LEFT); nx<=bordersKeeper.getBorder(BorderDirection.RIGHT); nx++) {
            builder.append("+---");
        }
        builder.append("+\n");
        System.out.println(builder);
    }


}
