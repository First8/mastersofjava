
public class Draw {

	/**
	 * Draw a line from (x0,y0) to (x1,y1)
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @return a [x][y] array of booleans indicating which pixels are colored with
	 *         the line drawing
	 */
	public static boolean[][] drawLine(int x0, int y0, int x1, int y1) {
		boolean[][] plot = new boolean[Math.max(x0, x1) + 1][Math.max(y0, y1) + 1];

		
		float deltaX = x1 - x0;
		float deltaY = y1 - y0;

		if (Math.abs(deltaX) < Math.abs(deltaY)) {
			// in case of a mostly vertical line, make sure y0 < y1
			// if not, swap (x0,y0) with (x1, y1)
			if (y0>y1) {
				int t = x1;
				x1 = x0;
				x0 = t;
				t = y1;
				y1 = y0;
				y0 = t;
			}
		} else {
			// in case of a mostly horizontal line, make sure x0 < x1
			// if not, swap (x0,y0) with (x1, y1)
			if (x0 > x1) {
				int t = x1;
				x1 = x0;
				x0 = t;
				t = y1;
				y1 = y0;
				y0 = t;
			}
		}

		return drawBresenham(plot, x0, y0, x1, y1);
	}

	private static boolean[][] drawBresenham(boolean[][] plot, int x0, int y0, int x1, int y1) {
		return plot;
	}

}
