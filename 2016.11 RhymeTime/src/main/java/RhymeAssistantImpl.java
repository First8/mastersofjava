import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main class with Rhymer functions. 
 */
public class RhymeAssistantImpl {

    private static final List<Character> VOWELS = Arrays.asList('A', 'E', 'U', 'I', 'O', 'Y');

    private static boolean isVowel(char c) {
        return VOWELS.contains(c);
    }
    
    /**
     * Main function to implement rhyming
     * @param input
     * @return 
     */
    public List<String> rhyme(String input) {
        List<String> list = Arrays.asList(input.split("\n"));

        List<String>  soundexs = toSoundex(list);
        List<String> rhymeScheme = toRhymeScheme(soundexs);
        return rhymeScheme;
    }
    /**
     * creates rhyme scheme from soundex
     * @param input
     * @return 
     */
    private static List<String> toRhymeScheme(List<String> soundexs) {
        List<String> result  = new ArrayList<>(soundexs);
        char rhymeLetter = 'A';
        for (int i = 0; i < result.size(); i++) {
            if (result.contains(soundexs.get(i))) {
                Collections.replaceAll(result, soundexs.get(i), String.valueOf(rhymeLetter));
                rhymeLetter++;
            }
        }
        return result;
    }
  
    private static List<String> toSoundex(List<String> list) {
        List<String> result  = new ArrayList<>();

        //TODO: implement Rhymer function. 
        
        return result;
    }

   
}
