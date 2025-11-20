import java.util.ArrayList;
import java.util.List;

public class FibonacciSequence {

    public static List<Integer> fibonacciSequenceMethod(int n) {
        List<Integer> fibonacciList = new ArrayList<>();
        if (n < 0) {
            fibonacciList.add(-1);
        }

        int a = 0, b = 1;

        while (a <= n) {
            fibonacciList.add(a);
            int temp = a;
            a = b;
            b = temp + b;
        }

        return fibonacciList;
    }
}