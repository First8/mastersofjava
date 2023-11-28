import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestNakedSets {

    private RegionReducer regionReducer = new RegionReducer();

    /**
     * In this test the number 5 occurs only once, so all other numbers should be removed from that cell
     */
    @Test
    public void testOccursOnce() {
        List<List<Integer>> region = toList(new int[][]{{1, 2, 4, 6, 8, 9}, {1, 2, 4, 6, 8, 9}, {4, 6, 8, 9}, {7}, {1, 6, 8, 9}, {6, 8, 9}, {2, 4, 5, 8, 9}, {2, 4, 8, 9}, {3}});

        List<List<Integer>> reducedRegion = regionReducer.reduce(region);

        List<List<Integer>> expectedRegion = toList(new int[][]{{1, 2, 4, 6, 8, 9}, {1, 2, 4, 6, 8, 9}, {4, 6, 8, 9}, {7}, {1, 6, 8, 9}, {6, 8, 9}, {5}, {2, 4, 8, 9}, {3}});
        assertEquals(expectedRegion, reducedRegion);
    }

    
    @Test
    public void testNakedPair() {
        List<List<Integer>> testRegion = toList(new int[][]{{4}, {1, 6}, {1, 6}, {1, 2, 5}, {1, 2, 5, 6, 7}, {2, 5, 6, 7}, {9}, {3}, {8}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{4}, {1, 6}, {1, 6}, {2, 5}, {2, 5, 7}, {2, 5, 7}, {9}, {3}, {8}});
        assertEquals(expectedResult, result);
    }

    
    @Test
    public void testNakedTriple() {
        List<List<Integer>> testRegion = toList(new int[][]{{4, 5, 6, 7, 9}, {2}, {1, 5, 6, 7, 9}, {5, 8, 9}, {5, 8}, {5, 9}, {3, 5, 8, 9}, {3, 4, 5, 8, 9}, {1, 6}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        // {5, 8, 9} is the naked triple, after the other cells are cleaned, {3} becomes a naked single and {4} subsequently
        List<List<Integer>> expectedResult = toList(new int[][]{{6, 7}, {2}, {1, 6, 7,}, {5, 8, 9}, {5, 8}, {5, 9}, {3}, {4}, {1, 6}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNakedQuad() {
        List<List<Integer>> testRegion = toList(new int[][]{{1, 5}, {1, 2, 4, 5}, {2, 4, 5, 7}, {1, 5, 6, 8}, {1, 5, 6, 8}, {3, 5, 6, 7, 8}, {1, 6}, {9}, {3, 4, 6}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        // {1, 5, 6, 8} is the naked quad
        List<List<Integer>> expectedResult = toList(new int[][]{{1, 5}, {2, 4}, {2, 4, 7}, {1, 5, 6, 8}, {1, 5, 6, 8}, {3, 7}, {1, 6}, {9}, {3, 4}});
        assertEquals(expectedResult, result);
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
