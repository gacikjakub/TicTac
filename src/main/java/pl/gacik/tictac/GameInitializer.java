package pl.gacik.tictac;

import pl.gacik.tictac.languages.MessagesProvider;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameInitializer {

    private GameSettings gameSettings;

    public GameInitializer(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void setBoard(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            out.accept(gameSettings.getMessagesProvider().askForBoardWidth());
            int width = Integer.parseInt(in.get());
            out.accept(gameSettings.getMessagesProvider().askForBoardHeight());
            int height = Integer.parseInt(in.get());
            gameSettings.setBoard(new LimitedCrossIBoard(width,height));
        } catch (Exception e) {
            throw e; // TODO: Make any handling exceptions
        }
    }

    public void setLanguage(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            out.accept("CHOOSE LANGUAGE [NUMBER] / WYBIERZ JĘZYK [NUMER]");
            int i=0;
            Map<Integer, MessagesProvider.AVAILABLE_LANGUAGE> tempLanguageMap = new HashMap<>();
            for(MessagesProvider.AVAILABLE_LANGUAGE language : MessagesProvider.AVAILABLE_LANGUAGE.values()) {
                out.accept(i + ". " + language.getLocale());
                tempLanguageMap.put(i, language);
                i++;
            }
            int choose = Integer.parseInt(in.get());
            if (choose<0 || choose>=i) {
                throw new IndexOutOfBoundsException("Your choice is not in available range");
            }
            gameSettings.setMessagesProvider(new MessagesProvider(tempLanguageMap.get(choose)));
        } catch (Exception e) {
            throw e; // TODO: Make any handling exceptions
        }
    }

    public void setPlayer(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            out.accept(gameSettings.getMessagesProvider().askForPlayerName() + " " + (gameSettings.getSignHolder().getAttachedPlayers().size() + 1));
            Player player = new Player(in.get());
            out.accept(gameSettings.getMessagesProvider().askForPlayerSign());
            int i=0;
            Map<Integer, Sign> tempSignMap = new HashMap<>();
            List<Sign> availableSigns = new LinkedList<>(Arrays.asList(Sign.values()));
            availableSigns.removeAll(gameSettings.getSignHolder().getUsedSigns());
            for(Sign sign: availableSigns) {
                out.accept(i + ". " + sign.getChar());
                tempSignMap.put(i, sign);
                i++;
            }
            int choose = Integer.parseInt(in.get());
            if (choose<0 || choose>=i) {
                throw new IndexOutOfBoundsException("Your choice is not in available range");
            }
            gameSettings.getSignHolder().attachPlayer(player,tempSignMap.get(choose));
        } catch (Exception e) {
            throw e; // TODO: Make any handling exceptions
        }
    }

    public void setWinCheckers(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        try {
            out.accept(gameSettings.getMessagesProvider().askForWinCheckerMode());
            out.accept("0. " + gameSettings.getMessagesProvider().yes());
            out.accept("1. " + gameSettings.getMessagesProvider().no());
            int choose = Integer.parseInt(in.get());
            if (choose<0 || choose>=2) {
                throw new IndexOutOfBoundsException("Your choice is not in available range");
            }
            switch (choose) {
                case 0 :
                    out.accept(gameSettings.getMessagesProvider().askForRequiredSeriesLengthToWin());
                    int seriesLength = Integer.parseInt(in.get());
                    gameSettings.addWinChecker(new VerticalWinChecker(gameSettings.getBoard()));
                    gameSettings.addWinChecker(new HorizontalWinChecker(gameSettings.getBoard()));
                    gameSettings.addWinChecker(new DiagonalBottomLeftToTopRightIWinChecker(gameSettings.getBoard()));
                    gameSettings.addWinChecker(new DiagonalTopLeftToBottomRightIWinChecker(gameSettings.getBoard()));
                    for(IWinChecker winChecker : gameSettings.getWinCheckers()) {
                        winChecker.setRequiredSeriesLength(seriesLength);
                    }
                    break;
                case 1:
                    setCustomWinCheckers(out, in);
                    break;
            }

        } catch (Exception e) {
            throw e; // TODO: Make any handling exceptions
        }
    }

    private void setCustomWinCheckers(Consumer<String> out, Supplier<String> in) {
        try {
            out.accept(gameSettings.getMessagesProvider().askForHorizontalSeriesLengthToWin());
            int choose = Integer.parseInt(in.get());
            WinChecker winChecker = new HorizontalWinChecker(gameSettings.getBoard());
            winChecker.setRequiredSeriesLength(choose);
            gameSettings.addWinChecker(winChecker);
            out.accept(gameSettings.getMessagesProvider().askForVerticalSeriesLengthToWin());
            choose = Integer.parseInt(in.get());
            winChecker = new VerticalWinChecker(gameSettings.getBoard());
            winChecker.setRequiredSeriesLength(choose);
            gameSettings.addWinChecker(winChecker);
            out.accept(gameSettings.getMessagesProvider().askForDecreasingDiagonalSeriesLengthToWin());
            choose = Integer.parseInt(in.get());
            winChecker = new DiagonalTopLeftToBottomRightIWinChecker(gameSettings.getBoard());
            winChecker.setRequiredSeriesLength(choose);
            gameSettings.addWinChecker(winChecker);
            out.accept(gameSettings.getMessagesProvider().askForIncreasingDiagonalSeriesLengthToWin());
            choose = Integer.parseInt(in.get());
            winChecker = new DiagonalBottomLeftToTopRightIWinChecker(gameSettings.getBoard());
            winChecker.setRequiredSeriesLength(choose);
            gameSettings.addWinChecker(winChecker);
        } catch (Exception e) {
            throw e; // TODO: Make any handling exceptions
        }
    }
}
