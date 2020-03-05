import java.util.Arrays;
import java.util.function.BiConsumer;

public class AnagramCheckerImpl implements AnagramChecker {

    @Override
    public boolean isAnagram(String subject, String possibility) {
        return Arrays.equals(anagramize(subject), anagramize(possibility));
    }
    
    public static BiConsumer<Long[], Long[]> combiner(){
        return (map1, map2) -> {  };
    }

    private static Long[] anagramize(String input) {
        return input.chars().collect(AnagramCheckerHelper.supplier(), AnagramCheckerHelper.accumulator(), combiner());
    }

}