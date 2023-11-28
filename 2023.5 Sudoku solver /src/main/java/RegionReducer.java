import java.util.List;
import java.util.ArrayList;

/**
 * This class helps to solve a sudoku by reducing the possibilities in each region.
 */
public class RegionReducer {

    /**
     * @param region A region is one row, column or block in a sudoku
     * @return A region with reduced possibilities
     */
    public List<List<Integer>> reduce(List<List<Integer>> region) {
    	for (int size = 1; size<9; size++) {
    		handleGroup( size, 0, new ArrayList<Integer>(), region);
    	}
        return region;
    }

	private void handleGroup(int size, int start, List<Integer> partialGroup, List<List<Integer>> region) {
		if (size==partialGroup.size()) {
			int reduced = checkNaked(partialGroup, region);
			if (reduced!=0) {
				System.out.println("reduced " + reduced + "options");
			}
			
		} else {
			for( int i=start+1; i<=9; i++) {
				List<Integer> group = new ArrayList<>(partialGroup);
				group.add(i);
				handleGroup( size, i, group, region);
			}
		}
	}
	
	/**
	 * Given the group, check if this group is in the region, and reduce the region. 
	 * @param group the group to check for
	 * @param region the region to check in
	 * @return the number of digits reduced (0 if it couldn't be reduced)
	 */
	int checkNaked(List<Integer> group, List<List<Integer>> region ) {
		
		// TODO implement here.

		// If group size is x, then if we have x cells with only digits in the given group, we have a naked group.
		// Example: If the group is {3,4,5} and we have 3 cells containing only the digits 3, 4 or 5 (or less), then it is a naked group. 
		// Those digits can be removed from all other cells in the region.

		// Tip: start with focusing on group size 1 to score at least those points!
		return 0;

	}
	
}
