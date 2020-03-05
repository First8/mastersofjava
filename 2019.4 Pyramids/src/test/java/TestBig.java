import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestBig {

	   @Test
	    public void test3() throws IOException {
		   test(3, 13);
	   }

	   @Test
	    public void test4() throws IOException {
		   test(4, 5643);
	   }

	   @Test
	    public void test5() throws IOException {
		   test(5, 1730);
	   }

	   private void test(int file, int solution) throws IOException {
		   int[][] pyramid = Util.readFile(file);
		   Solver solver = new Solver();
		   int s = solver.solve(pyramid);
		   assertEquals(solution, s);
	    }
	   
}
