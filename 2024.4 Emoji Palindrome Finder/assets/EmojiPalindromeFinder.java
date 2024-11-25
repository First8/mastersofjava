import java.util.*;

public class EmojiPalindromeFinder {
    private List<String> splitString = new ArrayList<>();
    private StringBuilder longestPalindrome = new StringBuilder();

    public String findLongestPalindrome(String input) {
        splitString = List.of(input.split("\\b{g}"));

        List<List<Integer>> splitStringCodePoints = splitString.stream()
                .map(this::convertToCodePoints)
                .toList();

        return longestPalindrome(splitStringCodePoints);
    }

    private String longestPalindrome(List<List<Integer>> input) {
        int len = input.size();
        if(len == 1){
            return splitString.getFirst();
        }
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(input, i, i);
            extendPalindrome(input, i, i+1);
        }
        return longestPalindrome.toString();
    }

    private void extendPalindrome(List<List<Integer>> input, int left, int right) {
        while (left >= 0 && right < input.size() && checkIfLeftEqualsRight(input.get(left), input.get(right))) {
            left--;
            right++;
        }
        List<String> palindromeCharList = splitString.subList(left + 1, right);
        String trimmedPalindrome = String.join("", palindromeCharList).trim();
        if(!palindromeCharList.isEmpty() && trimmedPalindrome.length() > longestPalindrome.length()){
            longestPalindrome = new StringBuilder(trimmedPalindrome);
        }
    }

    private List<Integer> convertToCodePoints(String s) {
        List<Integer> codePointsList = s.codePoints().boxed().toList();
        if (codePointsList.size() > 1 && codePointsList.stream().anyMatch(Character::isEmojiModifierBase)) {
            codePointsList = codePointsList.stream().filter(Character::isEmojiModifierBase).toList();
        }
        return codePointsList;
    }

    private boolean checkIfLeftEqualsRight(List<Integer> leftInput, List<Integer> rightInput){
        if(leftInput.stream().anyMatch(Character::isEmojiModifierBase) || rightInput.stream().anyMatch(Character::isEmojiModifierBase)){
            return leftInput.stream().anyMatch(rightInput::contains);
        }
        return leftInput.stream().allMatch(rightInput::contains);
    }
}