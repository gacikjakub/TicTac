package pl.gacik.tictac;

import pl.gacik.coordinates.SimpleCoordinates2D;

import java.nio.file.FileAlreadyExistsException;

public class Main {

    static CrossBoard board = new CrossBoard();


    public static void main(String[] args) throws CrossBoard.FieldCheckException {
        board.addPair(new SimpleCoordinates2D(-3,0), Sign.NOUGHT);
        board.addPair(new SimpleCoordinates2D(3,2), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(7,-10), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(4,5), Sign.NOUGHT);
        board.addPair(new SimpleCoordinates2D(20,30), Sign.NOUGHT);
        board.addPair(new SimpleCoordinates2D(25,31), Sign.NOUGHT);
        board.addPair(new SimpleCoordinates2D(-10,32), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(15,32), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(5,32), Sign.CROSS);
        new BoardDrawer(board).draw();
    }
}
