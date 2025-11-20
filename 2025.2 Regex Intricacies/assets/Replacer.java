

public class Replacer {

    private Replacer() {}

    public static String createRegex(String stringToReplace) {
        return "(?Ui)\\b" + stringToReplace + "\\b";
    }

}