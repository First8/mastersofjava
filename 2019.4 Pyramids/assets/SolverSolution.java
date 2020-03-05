/**
 * Solve a set of pyramids.
 * @author arjanl
 */
public class Solver {

	public int solve(int[][] pyramid )  {
		int[] max = new int[pyramid.length+1];
		for (int i=pyramid.length; i>0; i--) {
			for (int j=0; j<i; j++) {
				max[j] = pyramid[i-1][j] + Math.max(max[j], max[j+1]);  
			}
		}
		return max[0];
	}

}