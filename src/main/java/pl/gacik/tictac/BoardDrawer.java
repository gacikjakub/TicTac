package pl.gacik.tictac;

import pl.gacik.coordinates.Coordinates2DInterface;
import pl.gacik.coordinates.SimpleCoordinates2D;

import java.util.List;

public class BoardDrawer {

    CrossBoard board;

    public BoardDrawer(CrossBoard board) {
        this.board = board;
    }

    public void draw() {
        StringBuilder builder = new StringBuilder();
        List<Integer> border = board.getBorderValues();
        List<Coordinates2DInterface> signs = board.getAddedCoordinates();
        Coordinates2DInterface cord = signs.remove(0);
        builder.append(" ");
        for(int x = border.get(2); x<=border.get(1); x++) {
            if(x>-10 && x<100) {
                builder.append(" ");
            }
            builder.append(x);
            if(x<0 || x>10) {
                builder.append(" ");
            } else {
                builder.append("  ");
            }
        }
        builder.append("\n");
        for(int y = border.get(0); y>=border.get(3); y--) {

            for(int x = border.get(2); x<=border.get(1); x++) {
                builder.append("+---");
            }
            builder.append("+\n");
            int x = border.get(2);
            while (x <= border.get(1)) {
                if(cord.getY() == y) {
                    for (x = x; x <= cord.getX() -1; x++) {
                        builder.append("|   ");
                    }
                    builder.append("| ").append(board.getSign(cord).get().getChar()).append(" ");
                    x++;
                    if(cord.getX().equals(border.get(1))) {
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
                    for (x = x; x <= border.get(1); x++) {
                        builder.append("|   ");
                    }
                    x++;
                    builder.append("|  ").append(y).append("\n");
                }
            }
        }
        for(int nx = border.get(2); nx<=border.get(1); nx++) {
            builder.append("+---");
        }
        builder.append("+\n");
        System.out.println(builder);
    }


}
