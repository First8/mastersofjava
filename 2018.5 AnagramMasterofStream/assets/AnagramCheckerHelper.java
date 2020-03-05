import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/**
 * Based on:  
 * @see <a href=
 *      "https://thoughtfulsoftware.wordpress.com/2013/10/13/code-puzzle-anagram-detector-with-lambdas-in-java-8/">Assignment
 *      inspiration</a>
 
 */
public final class AnagramCheckerHelper {

    private AnagramCheckerHelper() {

    }

    public static Supplier<Long[]> supplier() {
        return () -> {
            Long[] a = new Long[Short.MAX_VALUE*2]; Arrays.fill(a, 0L);
            return a;
        };
    }

    public static ObjIntConsumer<Long[]> accumulator(){
        return (map, number) -> {
            final int lowerCased = Character.toLowerCase(number);

            if(!Character.isSpaceChar(lowerCased)) {
                map[lowerCased]++;
            }
        };
    }

    public static BiConsumer<Long[], Long[]> combiner(){
        return (map1, map2) -> {  };
    }
}
