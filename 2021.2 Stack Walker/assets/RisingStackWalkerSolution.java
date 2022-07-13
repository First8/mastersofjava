import java.lang.StackWalker.StackFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;

/**
 * <INTRODUCTION>
 * use reflection and stackwalker to view and trigger server actions. Also uses Java12 API
 * </INTRODUCTION>
 */
public class RisingStackWalker {
    private final StackWalker stackWalker = StackWalker.getInstance();
    private List<Integer> codeList = new ArrayList<>();

    public RisingStackWalker() {
        for (int index = 1 ;index <= 15; index++) {
            codeList.add(getSecretCodeUsingStreams(index));
        }
    }
    public boolean isValidCode(int code) {
        return codeList.get(14)==code;
    }
    /*
    What is return value when n = 15 ? Will the dark force outsmart us?
 	*/
    public int getSecretCodeUsingStreams(int n) {
        int code = 0; // use the force of stackwalker here
        // <SOLUTION>
        code = stackWalker.walk(f -> f.filter(s ->
                s.getClassName().startsWith("org.junit"))
                .limit(n)
                .filter(s -> s.getMethodName().contains("run"))
                .map(StackFrame::getLineNumber)
                .mapToInt(l -> l.intValue())
                .sum());
        // </SOLUTION>
        return code;
    }

}

