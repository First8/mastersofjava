import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * This class makes the super awesome list of names
 */
public class SuperAwesomeNameService {
    public static List<String> doTheThings(List<String> names) {
        names.sort(comparing(String::toLowerCase));
        for (int i = 1; i < names.size(); i++) {
            if (names.get(i - 1).contains("a")) {
                names.set(i, reverseAndCapitalize(names.get(i)));
            }
        }
        return names;
    }

    private static String reverseAndCapitalize(String name) {
        var reversedLowerCase = new StringBuilder(name).reverse().toString().toLowerCase();
        return reversedLowerCase.substring(0,1).toUpperCase() + reversedLowerCase.substring(1).toLowerCase();
    }

//    private static final String VOWELS = "aeiouAEIOU";
//
//    public static List<String> doTheThings(List<String> names) {
//        Comparator<String> specialComparator =
//                comparing(SuperAwesomeNameService::hasNoConsecutiveVowels)
//                        .thenComparing(comparing(String::toLowerCase));
//
//        return names.stream()
//                .sorted(specialComparator)
//                .filter(s -> !s.contains("e"))
//                .toList();
//    }
//
//    private static boolean hasNoConsecutiveVowels(String name) {
//        char[] charArray = name.toCharArray();
//        for (int i = 1; i < charArray.length; i++) {
//            if (isVowel(charArray[i]) && isVowel(charArray[i - 1])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean isVowel(char c) {
//        return VOWELS.indexOf(c) != -1;
//    }
}
