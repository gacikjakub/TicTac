package pl.gacik.tictac;

import pl.gacik.tictac.boards.LimitedCrossIBoard;
import pl.gacik.tictac.languages.MessagesProvider;
import pl.gacik.tictac.winCheckers.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameInitializer {

    private GameSettings gameSettings;

    public GameInitializer(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void setBoard(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        int width = -1;
        int height = -2;
        boolean notCorrect = false;
        do {
            notCorrect = false;
            try {
                out.accept(gameSettings.getMessagesProvider().askForBoardWidth());
                width = Integer.parseInt(in.get());
                if (width < 1 || width > 50) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
        do {
            notCorrect = false;
            try {
                out.accept(gameSettings.getMessagesProvider().askForBoardHeight());
                height = Integer.parseInt(in.get());
                if (height < 1 || height > 20) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
        gameSettings.setBoard(new LimitedCrossIBoard(width, height));
    }

    public void setLanguage(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        boolean notCorrect = false;
        do {
            notCorrect = false;
            try {
                out.accept("CHOOSE LANGUAGE [NUMBER]");
                int i = 0;
                Map<Integer, MessagesProvider.AVAILABLE_LANGUAGE> tempLanguageMap = new HashMap<>();
                for (MessagesProvider.AVAILABLE_LANGUAGE language : MessagesProvider.AVAILABLE_LANGUAGE.values()) {
                    out.accept(i + ". " + language.getLocale());
                    tempLanguageMap.put(i, language);
                    i++;
                }
                int choose = Integer.parseInt(in.get());
                if (choose < 0 || choose >= i) {
                    throw new IndexOutOfBoundsException("Your choice is not in available range");
                }
                gameSettings.setMessagesProvider(new MessagesProvider(tempLanguageMap.get(choose)));
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("You have to give number in available range");
                notCorrect = true;
            }
        }
        while (notCorrect);
    }

    public void setPlayer(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        boolean notCorrect = false;
        notCorrect = false;
        String playerName = "";
        Pattern noEmptyPattern = Pattern.compile("[\\s]*");
        do {
            notCorrect = false;
            out.accept(gameSettings.getMessagesProvider().askForPlayerName() + " " + (gameSettings.getSignHolder().getAttachedPlayers().size() + 1));
            playerName = in.get();
            Matcher matcher = noEmptyPattern.matcher(playerName);
            if (matcher.matches()) {
                notCorrect = true;
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                continue;
            }
        } while (notCorrect);
        Player player = new Player(playerName);
        do {
            try {
                notCorrect = false;
                out.accept(gameSettings.getMessagesProvider().askForPlayerSign());
                int i = 0;
                Map<Integer, Sign> tempSignMap = new HashMap<>();
                List<Sign> availableSigns = new LinkedList<>(Arrays.asList(Sign.values()));
                availableSigns.removeAll(gameSettings.getSignHolder().getUsedSigns());
                for (Sign sign : availableSigns) {
                    out.accept(i + ". " + sign.getChar());
                    tempSignMap.put(i, sign);
                    i++;
                }
                int choose = Integer.parseInt(in.get());
                if (choose < 0 || choose >= i) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
                gameSettings.getSignHolder().attachPlayer(player, tempSignMap.get(choose));
                gameSettings.getPointsHolder().addPlayer(player);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);

    }

    public void setPlayersAmount(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        boolean notCorrect = false;
        do {
            notCorrect = false;
            try {
                out.accept(gameSettings.getMessagesProvider().askForPlayerAmount() + "     min 2,   max " + Sign.values().length);
                int choose = Integer.parseInt(in.get());
                if (choose < 2 || choose > Sign.values().length) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
                gameSettings.setPlayersAmount(choose);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
    }

    public void setWinCheckers(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        boolean notCorrect = false;
        int choose = 3;
        do {
            notCorrect = false;
            try {
                out.accept(gameSettings.getMessagesProvider().askForWinCheckerMode());
                out.accept("0. " + gameSettings.getMessagesProvider().yes());
                out.accept("1. " + gameSettings.getMessagesProvider().no());
                choose = Integer.parseInt(in.get());
                if (choose < 0 || choose >= 2) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
        gameSettings.clearWinCheckers();
        switch (choose) {
            case 0:
                do {
                    try {
                        notCorrect = false;
                        out.accept(gameSettings.getMessagesProvider().askForRequiredSeriesLengthToWin());
                        int seriesLength = Integer.parseInt(in.get());
                        gameSettings.addWinChecker(new VerticalWinChecker(gameSettings.getBoard()));
                        gameSettings.addWinChecker(new HorizontalWinChecker(gameSettings.getBoard()));
                        gameSettings.addWinChecker(new DiagonalBottomLeftToTopRightIWinChecker(gameSettings.getBoard()));
                        gameSettings.addWinChecker(new DiagonalTopLeftToBottomRightIWinChecker(gameSettings.getBoard()));
                        for (IWinChecker winChecker : gameSettings.getWinCheckers()) {
                            winChecker.setRequiredSeriesLength(seriesLength);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                        notCorrect = true;
                    }
                } while (notCorrect);
                break;
            case 1:
                setCustomWinCheckers(out, in);
                break;
        }
    }

    public void setTournamentAmount(Consumer<String> out, Supplier<String> in) {
        if (in == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        boolean notCorrect = false;
        do {
            notCorrect = false;
            try {
                out.accept(gameSettings.getMessagesProvider().askForTournamentAmount());
                int choose = Integer.parseInt(in.get());
                if (choose < 0 || choose >= (Integer.MAX_VALUE / gameSettings.getWinPoints().points)) {
                    throw new IndexOutOfBoundsException(gameSettings.getMessagesProvider().choiceNotInRange());
                }
                gameSettings.setMaxRoundAmount(choose);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
    }

    private void setCustomWinCheckers(Consumer<String> out, Supplier<String> in) {
        boolean notCorrect = false;
        int choose;
        WinChecker winChecker;
        do {
            try {
                out.accept(gameSettings.getMessagesProvider().askForHorizontalSeriesLengthToWin());
                choose = Integer.parseInt(in.get());
                winChecker = new HorizontalWinChecker(gameSettings.getBoard());
                winChecker.setRequiredSeriesLength(choose);
                gameSettings.addWinChecker(winChecker);
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
                out.accept(gameSettings.getMessagesProvider().askForVerticalSeriesLengthToWin());
                choose = Integer.parseInt(in.get());
                winChecker = new VerticalWinChecker(gameSettings.getBoard());
                winChecker.setRequiredSeriesLength(choose);
                gameSettings.addWinChecker(winChecker);
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
                out.accept(gameSettings.getMessagesProvider().askForDecreasingDiagonalSeriesLengthToWin());
                choose = Integer.parseInt(in.get());
                winChecker = new DiagonalTopLeftToBottomRightIWinChecker(gameSettings.getBoard());
                winChecker.setRequiredSeriesLength(choose);
                gameSettings.addWinChecker(winChecker);
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
                out.accept(gameSettings.getMessagesProvider().askForIncreasingDiagonalSeriesLengthToWin());
                choose = Integer.parseInt(in.get());
                winChecker = new DiagonalBottomLeftToTopRightIWinChecker(gameSettings.getBoard());
                winChecker.setRequiredSeriesLength(choose);
                gameSettings.addWinChecker(winChecker);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                notCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println(gameSettings.getMessagesProvider().shouldBeNumber());
                notCorrect = true;
            }
        } while (notCorrect);
    }
}
