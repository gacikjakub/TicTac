package pl.gacik.tictac;

import pl.gacik.coordinates.SimpleCoordinates2D;


public class Main {

    static CrossBoard board = new LimitedCrossBoard(10,10);


    public static void main(String[] args) throws BoardInterface.FieldCheckException {
//        board.addPair(new SimpleCoordinates2D(20,30), Sign.NOUGHT);
//        board.addPair(new SimpleCoordinates2D(25,31), Sign.NOUGHT);
//        board.addPair(new SimpleCoordinates2D(-10,32), Sign.CROSS);
//        board.addPair(new SimpleCoordinates2D(15,32), Sign.CROSS);
//        board.addPair(new SimpleCoordinates2D(5,32), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(3,5), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(1,4), Sign.CROSS);
        board.addPair(new SimpleCoordinates2D(7,4), Sign.NOUGHT);
        board.addPair(new SimpleCoordinates2D(8,2), Sign.NOUGHT);

        new BoardDrawer(board).draw();
    }
}
