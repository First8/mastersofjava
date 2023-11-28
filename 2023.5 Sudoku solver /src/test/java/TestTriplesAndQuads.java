import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * This class tests the triple and quadruple reduction of the RegionReducer
 */
public class TestTriplesAndQuads {

    /**
     * In this test, we check if the group {1,2,3} can be reduced from the given region. It should remove the 1, 2 and 3 from the 6th cell.
     */
    @Test
    public void testCheckNaked123() {
        RegionReducer regionReducer = new RegionReducer();
    	
    	List<Integer> group = Arrays.asList(1,2,3);
		List<List<Integer>> region 			= toList(new int[][]{{1,2,3}, {1,2,3}, {1,2,3}, {4}, {5}, {1,2,3,4,5,6,7,8,9}, {7}, {8}, {9}});
		List<List<Integer>> expectedRegion 	= toList(new int[][]{{1,2,3}, {1,2,3}, {1,2,3}, {4}, {5}, {      4,5,6,7,8,9}, {7}, {8}, {9}});
    	
    	regionReducer.checkNaked(group, region);
    	assertEquals(expectedRegion, region);
    	
    }

    /**
     * In this test, we check if the group {1,2,3} can be reduced from the given region. It should remove the 1, 2 and 3 from the 6th cell.
     */
    @Test
    public void testCheckNaked123_2() {
        RegionReducer regionReducer = new RegionReducer();
    	
    	List<Integer> group = Arrays.asList(1,2,3);
		List<List<Integer>> region 			= toList(new int[][]{{2,3}, {1,2}, {1,3}, {4}, {5}, {1,2,3,4,5,6,7,8,9}, {7}, {8}, {9}});
		List<List<Integer>> expectedRegion 	= toList(new int[][]{{2,3}, {1,2}, {1,3}, {4}, {5}, {      4,5,6,7,8,9}, {7}, {8}, {9}});
    	
    	regionReducer.checkNaked(group, region);
    	assertEquals(expectedRegion, region);
    	
    }

    private List<List<Integer>> toList(int[][] array) {
    	
    	List<List<Integer>> result = new ArrayList<>();
    	for (int i=0; i<array.length; i++) {
    		List<Integer> cell = new ArrayList<>();
    	   	for (int j=0; j<array[i].length; j++) {
	   	    	cell.add(array[i][j]);
    	   	}
    	   	result.add(cell);
    	}
    	return result;
//        return Stream.of(array).map(cell -> Arrays.stream(cell).boxed().toList()).toList();
    }
}
