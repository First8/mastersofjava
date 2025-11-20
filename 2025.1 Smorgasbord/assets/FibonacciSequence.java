import java.util.ArrayList;
import java.util.List;

public class FibonacciSequence {

    public static List<Integer> fibonacciSequenceMethod(int n) {
        List<Integer> fibonacciList = new ArrayList<>();
        if (n <= 0) return fibonacciList;

        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            fibonacciList.add(a);
            int temp = a;
            a = b;
            b = temp + b;
        }

        return fibonacciList;

    }
}