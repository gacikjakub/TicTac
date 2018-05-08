package pl.gacik.tictac.languages;

import pl.gacik.tictac.Main;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesProvider {

    public enum AVAILABLE_LANGUAGE {
        POL("pl", "PL"),
        ENG("en", "US");

        public String getLocale() {
            return language + "_" + country;
        }

        String language;
        String country;

        AVAILABLE_LANGUAGE(String language, String country) {
            this.language = language;
            this.country = country;
        }
    }

    private ResourceBundle messages;

    public MessagesProvider(AVAILABLE_LANGUAGE language) {
        messages = ResourceBundle.getBundle("Messages", new Locale(language.language, language.country));
    }

    public String greetings() {
        return messages.getString("greetings");
    }

    public String askForBoardWidth() {
        return messages.getString("askForBoardWidth");
    }

    public String askForBoardHeight() {
        return messages.getString("askForBoardHeight");
    }

    public String askForPlayerName() {
        return messages.getString("askForPlayerName");
    }

    public String askForPlayerSign() {
        return messages.getString("askForPlayerSign");
    }

    public String askForWinCheckerMode() {
        return messages.getString("askForWinCheckerMode");
    }

    public String askForRequiredSeriesLengthToWin() {
        return messages.getString("askForRequiredSeriesLengthToWin");
    }

    public String yes() {
        return messages.getString("yes");
    }

    public String no() {
        return messages.getString("no");
    }

    public String askForHorizontalSeriesLengthToWin() {
        return messages.getString("askForHorizontalSeriesLengthToWin");
    }

    public String askForVerticalSeriesLengthToWin() {
        return messages.getString("askForVerticalSeriesLengthToWin");
    }

    public String askForDecreasingDiagonalSeriesLengthToWin() {
        return messages.getString("askForDecreasingDiagonalSeriesLengthToWin");
    }

    public String askForIncreasingDiagonalSeriesLengthToWin() {
        return messages.getString("askForIncreasingDiagonalSeriesLengthToWin");
    }

    public String introductionToTurn() {
        return messages.getString("introductionToTurn");
    }

    public String askForCoordinateX() {
        return messages.getString("askForCoordinateX");
    }

    public String askForCoordinateY() {
        return messages.getString("askForCoordinateY");
    }

    public String winnerAnnouncing() {
        return messages.getString("winnerAnnouncing");
    }


    public String draw() {
        return messages.getString("draw");
    }
}