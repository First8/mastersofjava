import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;


public class TestCount {
	
	@Test
	public void test3() {
		assertEquals( 2, new AreaCounter().count(readImage("image3.txt")));
	}

	@Test
	public void test4() {
		assertEquals( 1, new AreaCounter().count(readImage("image4.txt")));
	}

	@Test
	public void test5() {
		assertEquals( 4, new AreaCounter().count(readImage("image5.txt")));
	}

	@Test
	public void test6() {
		assertEquals( 1, new AreaCounter().count(readImage("image6.txt")));
	}

	public static int[][] readImage(String filename) {
		BufferedReader r = new BufferedReader(new InputStreamReader(TestCount.class.getResourceAsStream(filename)));
		List<String> lines = r.lines().collect(Collectors.toList());

		int width = lines.get(0).length();
		int[][] image = new int[width][lines.size()];

		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < width; x++) {
				if (lines.get(y).charAt(x) == '0') {
					image[x][y] = 0;
				} else if (lines.get(y).charAt(x) == '1') {
					image[x][y] = 1;
				} else {
					throw new IllegalArgumentException("Cannot parse image " + filename + " at (" + x + "," + y
							+ "): invalid char '" + lines.get(y).charAt(x) + "'");
				}
			}
		}

		return image;
	}


}
