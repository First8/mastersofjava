import java.lang.StackWalker.StackFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;

/**
 * Implement the secret code generator here
 */    
public class RisingStackWalker {
    private final StackWalker stackWalker = StackWalker.getInstance();
    private List<Integer> codeList = new ArrayList<>();

    public RisingStackWalker() {
        for (int index = 1 ;index <= 15; index++) {
            codeList.add(getSecretCodeUsingStreams(index));
        }

    }
    public boolean isValidCode(Integer code) {
        return code > 0 && code.equals(codeList.get(14));
    }

    /*
       What is return value when n = 15 ? Will the Dark Force outsmart us?
     */
    public int getSecretCodeUsingStreams(int n) {
        int code = 0; // TODO: use the stackwalker API here

        return code;
    }

}

