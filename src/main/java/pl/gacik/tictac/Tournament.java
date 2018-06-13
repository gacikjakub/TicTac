package pl.gacik.tictac;

import pl.gacik.tictac.coordinates.ICoordinates2D;
import pl.gacik.tictac.coordinates.SimpleICoordinates2D;
import pl.gacik.tictac.boards.BoardDrawer;
import pl.gacik.tictac.boards.CrossIBoard;
import pl.gacik.tictac.boards.IBoard;
import pl.gacik.tictac.boards.LimitedCrossIBoard;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Tournament {

    private GameSettings gameSettings;

    public Tournament(GameSettings gameSettings) {
        if (gameSettings == null) {
            throw new IllegalArgumentException("gameSettings cannot be null");
        }
        this.gameSettings = gameSettings;
    }

    public TurnHolder getTurnHolder() {
        return turnHolder;
    }

    public void setTurnHolder(TurnHolder turnHolder) {
        if (turnHolder == null) {
            throw new IllegalArgumentException("turnHolder cannot be null");
        }
        if (turnHolder.getPlayersAmount() < 2) {
            throw new IllegalArgumentException("Not enough players for game");
        }
        this.turnHolder = turnHolder;
    }

    private TurnHolder turnHolder;

    public void startGame(Consumer<String> out, Supplier<String> in) throws IBoard.FieldCheckException {
        boolean isWinner = false;
        boolean notCorrect = false;
        while (gameSettings.getBoard().hasAvailableField() && !isWinner) {
            int x = 0;
            int y = 0;
            Player player = turnHolder.getNextPlayer();
            do {
                new BoardDrawer((CrossIBoard) gameSettings.getBoard()).draw();
                out.accept(gameSettings.getMessagesProvider().introductionToTurn() + " " + player.getName() + " [" + gameSettings.getSignHolder().getBookedSign(player).get().getChar() + "]");
                notCorrect = false;
                do {
                    try {
                        notCorrect = false;
                        out.accept(gameSettings.getMessagesProvider().askForCoordinateX());
                        x = Integer.parseInt(in.get());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        notCorrect = true;
                    } catch (NumberFormatException e) {
                        System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                        notCorrect = true;
                    }
                } while (notCorrect);
                do {
                    try {
                        notCorrect = false;
                        out.accept(gameSettings.getMessagesProvider().askForCoordinateY());
                        y = Integer.parseInt(in.get());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        notCorrect = true;
                    } catch (NumberFormatException e) {
                        System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                        notCorrect = true;
                    }
                } while (notCorrect);
                try {
                    ICoordinates2D coordinates2D = new SimpleICoordinates2D(x, y);
                    gameSettings.getBoard().addPair(coordinates2D, gameSettings.getSignHolder().getBookedSign(player).get());
                    if (victoryAchieved(coordinates2D)) {
                        isWinner = true;
                        new BoardDrawer((CrossIBoard) gameSettings.getBoard()).draw();
                        out.accept(gameSettings.getMessagesProvider().winnerAnnouncing() + " " + player.getName());
                        gameSettings.getPointsHolder().addPoints(player, gameSettings.getWinPoints());
                        gameSettings.getPointsHolder().getAddedPlayers().stream().filter(p -> p != player).forEach(p -> {
                            gameSettings.getPointsHolder().addPoints(p, gameSettings.getLosePoints());
                        });
                        break;
                    }
                } catch (CrossIBoard.AlreadyUsedCoordinates e) {
                    notCorrect = true;
                    System.out.println(gameSettings.getMessagesProvider().bookedCoordinates());
                } catch (LimitedCrossIBoard.CoordinatesOutOfBoundException e) {
                    notCorrect = true;
                    System.out.println(gameSettings.getMessagesProvider().coordinatesNotInRange());
                }
            } while (notCorrect);
        }
        if (!isWinner) {
            new BoardDrawer((CrossIBoard) gameSettings.getBoard()).draw();
            out.accept(gameSettings.getMessagesProvider().draw());
            gameSettings.getSignHolder().getAttachedPlayers().stream().forEach(player -> {
                gameSettings.getPointsHolder().addPoints(player, gameSettings.getDrawPoints());
            });
        }
    }

    private boolean victoryAchieved(ICoordinates2D lastMove) {
        return (gameSettings.getWinCheckers().stream().filter(w -> w.victoryAchieved(lastMove)).count() != 0);
    }


}
