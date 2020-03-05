import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestSmall {

	   @Test
	    public void test0() throws IOException {
		   test(0, 75);
	   }

	   @Test
	    public void test1() throws IOException {
		   test(1, 29);
	   }

	   @Test
	    public void test2() throws IOException {
		   test(2, 10);
	   }

	   private void test(int file, int solution) throws IOException {
		   int[][] pyramid = Util.readFile(file);
		   Solver solver = new Solver();
		   int s = solver.solve(pyramid);
		   assertEquals(solution, s);
	    }
	   
}
