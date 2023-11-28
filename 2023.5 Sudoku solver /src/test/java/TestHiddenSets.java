import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestHiddenSets {
    private RegionReducer regionReducer = new RegionReducer();

    @Test
    public void testHiddenPair() {
        List<List<Integer>> testRegion = toList(new int[][]{{1, 2, 5, 8}, {1, 2, 3, 8}, {2, 3}, {1, 2, 9}, {1, 2, 3, 5, 9}, {5, 9}, {4}, {2, 3, 5, 6, 7, 9}, {3, 5, 6, 7, 9}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{1, 2, 5, 8}, {1, 2, 3, 8}, {2, 3}, {1, 2, 9}, {1, 2, 3, 5, 9}, {5, 9}, {4}, {6, 7}, {6, 7}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHiddenPair2() {
        List<List<Integer>> testRegion = toList(new int[][]{{4}, {2, 3, 5, 6, 7, 9}, {3, 5, 6, 7, 9}, {8}, {2, 3, 5}, {3, 5}, {1}, {2, 3, 5, 9}, {3, 5, 9}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{4}, {6, 7}, {6, 7}, {8}, {2, 3, 5}, {3, 5}, {1}, {2, 3, 5, 9}, {3, 5, 9}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHiddenPair3() {
        List<List<Integer>> testRegion = toList(new int[][]{{6, 9}, {3, 7}, {2, 4, 6,}, {8}, {5}, {1}, {3, 6, 7, 9}, {2, 6, 9}, {4, 6, 9}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{6, 9}, {3, 7}, {2, 4, 6,}, {8}, {5}, {1}, {3, 7}, {2, 6, 9}, {4, 6, 9}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHiddenTriple() {
        List<List<Integer>> testRegion = toList(new int[][]{{4, 7, 8, 9}, {4, 8, 9}, {4, 7}, {2, 4, 5, 6, 7, 8}, {4, 7, 8}, {1}, {2, 4, 6, 9}, {3}, {2, 4, 5, 7, 8, 9}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{4, 7, 8, 9}, {4, 8, 9}, {4, 7}, {2, 5, 6}, {4, 7, 8}, {1}, {2, 6}, {3}, {2, 5}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHiddenQuad() {
        // The hidden quad is {3, 4, 5, 7}
        List<List<Integer>> testRegion = toList(new int[][]{{1, 9}, {1, 8}, {1, 6, 8}, {2, 9}, {3, 4, 7}, {4, 7}, {3, 5, 6, 7}, {4, 5, 6, 7}, {2, 6}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{1, 9}, {1, 8}, {1, 6, 8}, {2, 9}, {3, 4, 7}, {4, 7}, {3, 5, 7}, {4, 5, 7}, {2, 6}});
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHiddenQuad2() {
        // The hidden quad is {1, 4, 6, 9}
        List<List<Integer>> testRegion = toList(new int[][]{{1, 3, 4, 6, 7, 8, 9}, {3, 7, 8}, {3, 4, 6, 7, 8, 9}, {2, 3, 7, 8}, {2, 3, 5, 7, 8}, {2, 3, 5, 7, 8}, {1, 3, 4, 7, 8, 9}, {3, 5, 7, 8}, {3, 4, 5, 7, 8, 9}});

        List<List<Integer>> result = regionReducer.reduce(testRegion);

        List<List<Integer>> expectedResult = toList(new int[][]{{1, 4, 6, 9}, {3, 7, 8}, {4, 6, 9}, {2, 3, 7, 8}, {2, 3, 5, 7, 8}, {2, 3, 5, 7, 8}, {1, 4, 9}, {3, 5, 7, 8}, {4, 9}});
        assertEquals(expectedResult, result);
    }

    private List<List<Integer>> toList(int[][] array) {
    	// allow mutable lists since that allows for more implementations
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
