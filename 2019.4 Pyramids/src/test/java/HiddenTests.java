import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class HiddenTests {
	   @Test
	    public void test37() throws IOException {
		   test(37, 5802);
	   }

	   @Test
	    public void test38() throws IOException {
		   test(38, 35);
	   }

	   @Test
	    public void test39() throws IOException {
		   test(39, 2720);
	   }

	   private void test(int file, int solution) throws IOException {
		   int[][] pyramid = Util.readFile(file);
		   Solver solver = new Solver();
		   int s = solver.solve(pyramid);
		   assertEquals(solution, s);
	    }
	   

}
