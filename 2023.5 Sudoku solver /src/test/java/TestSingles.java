import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestSingles {

    /**
     * In this test, we check if the group {1} can be reduced from the given region. It should remove the 1 from the 6th cell.
     */
    @Test
    public void testCheckNaked1() {
        RegionReducer regionReducer = new RegionReducer();
    	
    	List<Integer> group = Arrays.asList(1);
		List<List<Integer>> region 			= toList(new int[][]{{1}, {2}, {3}, {4}, {5}, {1,2,3,4,5,6,7,8,9}, {7}, {8}, {9}});
		List<List<Integer>> expectedRegion 	= toList(new int[][]{{1}, {2}, {3}, {4}, {5}, {  2,3,4,5,6,7,8,9}, {7}, {8}, {9}});
    	
    	regionReducer.checkNaked(group, region);
    	assertEquals(expectedRegion, region);
    	
    }
    
    /**
     * In this test, we check if the group {2} can be reduced from the given region. It should remove the 2, from the 6th and 7th cell.
     */
    @Test
    public void testCheckNaked2() {
        RegionReducer regionReducer = new RegionReducer();
    	
    	List<Integer> group = Arrays.asList(2);
		List<List<Integer>> region 			= toList(new int[][]{{1}, {2}, {3}, {4}, {5}, {1,2,3,4,5,6,7,8,9}, {2,7}, {8}, {9}});
		List<List<Integer>> expectedRegion 	= toList(new int[][]{{1}, {2}, {3}, {4}, {5}, {1,  3,4,5,6,7,8,9}, {  7}, {8}, {9}});
    	
    	regionReducer.checkNaked(group, region);
    	assertEquals(expectedRegion, region);
    	
    }
    
    

    /* make a mutable list */
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
