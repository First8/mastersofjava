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

	public void handleGroup(int size, int start, List<Integer> partialGroup, List<List<Integer>> region) {
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
	
	public int checkNaked(List<Integer> group, List<List<Integer>> region ) {
		// if group size is x, then if we have x cells with only digits in the group, we have a naked group
		
		List<Integer> cellsInGroup = new ArrayList<Integer>();
		int count =0;
		for (int cellIndex =0; cellIndex < region.size(); cellIndex++) {
			List<Integer> cell = region.get(cellIndex);
			if (group.containsAll(cell)) {
				count++;
				cellsInGroup.add(cellIndex);
			}
		}
		
		if (count<group.size()) {
			// no naked group
			return 0;
		} else if (count>group.size()) {
			throw new RuntimeException("broken sudoku");
		} else {
			int reduced= reduceNaked(group, region, cellsInGroup);
			if (reduced>0) {
				System.out.println("      naked group: " + group + " reduced " + reduced + " to " + region);
			}
			return reduced;
		}
	}

	private int reduceNaked(List<Integer> group, List<List<Integer>> region, List<Integer> cellsInGroup) {
		int count = 0;
		// since this is a naked group, all other digits next to the group's digits can be removed
		for (int i=0; i<9; i++) {
			if (cellsInGroup.contains(i)) {
				continue;
			}
			
			List<Integer> cell = region.get(i);
			
			for (int r=1; r<=9; r++) {
				if( group.contains(r)) {
					if (cell.remove((Object)r)) {
						count++;
					}
				}
			}
		}
		return count;
		
	}
	
}
