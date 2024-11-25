import java.util.*;

public class EmojiPalindromeFinder {

    public String findLongestPalindrome(String input) {
        return "";
    }

    private List<Integer> convertToCodePoints(String s) {
        List<Integer> codePointsList = s.codePoints().boxed().toList();
        if (codePointsList.size() > 1 && codePointsList.stream().anyMatch(Character::isEmojiModifierBase)) {
            codePointsList = codePointsList.stream().filter(Character::isEmojiModifierBase).toList();
        }
        return codePointsList;
    }

}