import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFibonacciSequence {
    @Test
    public void testFibonacciSequence() {
        List<Integer> result = FibonacciSequence.fibonacciSequenceMethod(10);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        assertEquals(expected, result, "Fibonacci sequence up to 10 numbers is incorrect.");
    }

    @Test
    public void testFibonacciSequenceWithNoInput() {
        List<Integer> result = FibonacciSequence.fibonacciSequenceMethod(0);
        List<Integer> expected = List.of();
        assertEquals(expected, result, "Fibonacci sequence with no input is incorrect.");
    }

    @Test
    public void testFibonacciSequenceWithNegativeInput() {
        List<Integer> result = FibonacciSequence.fibonacciSequenceMethod(-5);
        List<Integer> expected = List.of();
        assertEquals(expected, result, "Fibonacci sequence with negative input should be empty.");
    }

    @Test
    public void testFibonacciSequenceWithLimitTwo() {
        List<Integer> result = FibonacciSequence.fibonacciSequenceMethod(2);
        List<Integer> expected = List.of(0, 1);
        assertEquals(expected, result, "Fibonacci sequence with a limit of 2 is incorrect.");
    }
}