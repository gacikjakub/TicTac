package pl.gacik.tictac;

import pl.gacik.coordinates.SimpleICoordinates2D;

import java.util.Scanner;


public class Main {

//    static CrossIBoard board = new CrossIBoard();
    static CrossIBoard board = new LimitedCrossIBoard(9,12);

    static GameSettings gameSettings = new GameSettings();
    static GameInitializer gameInitializer = new GameInitializer(gameSettings);


    public static void main(String[] args) throws IBoard.FieldCheckException {
        /*
//        board.addPair(new SimpleICoordinates2D(20,30), Sign.NOUGHT);
//        board.addPair(new SimpleICoordinates2D(25,31), Sign.NOUGHT);
//        board.addPair(new SimpleICoordinates2D(-10,32), Sign.CROSS);
//        board.addPair(new SimpleICoordinates2D(15,32), Sign.CROSS);
//        board.addPair(new SimpleICoordinates2D(5,32), Sign.CROSS);
        board.addPair(new SimpleICoordinates2D(3,5), Sign.CROSS);
        board.addPair(new SimpleICoordinates2D(1,4), Sign.CROSS);
        board.addPair(new SimpleICoordinates2D(7,4), Sign.NOUGHT);
        board.addPair(new SimpleICoordinates2D(8,2), Sign.NOUGHT);
        new BoardDrawer(board).draw();
        */

        gameInitializer.setLanguage(System.out::println, () -> new Scanner(System.in).next());
    }
}
