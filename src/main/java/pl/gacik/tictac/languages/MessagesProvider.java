package pl.gacik.tictac.languages;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesProvider {

    public enum AVAILABLE_LANGUAGE {
        POL("pl", "PL"),
        ENG("en", "US");

        public String getLocale() {
            return language+"_"+country;
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
        messages = ResourceBundle.getBundle("pl.gacik.tictac.languages.Messages", new Locale(language.language, language.country));
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


}
