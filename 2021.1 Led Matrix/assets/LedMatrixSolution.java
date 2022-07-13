public class LedMatrix {

	int width;
	int height;

	public LedMatrix(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int xyToLed(int x, int y) {
		if (y % 2 == 0) {
			return y * width + x;
		} else {
			return y * width + (width-x-1);
		}
	}

	public boolean[] getStrip(boolean[][] image) {
		boolean[] ledStrip = new boolean[height * width];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				ledStrip [xyToLed(y,x)] = image[x][y];
			}
		}
		return ledStrip;
	}

	public void plot(boolean[][] image) {
		for(int i=0; i<image.length; ++i) {
			for(int j=0; j<image[i].length; ++j) {
				if(image[i][j]) {
					System.out.print("[*]");
				} else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
		System.out.println();

		printStrip(getStrip(image));

		System.out.println();
		System.out.println();
	}

	public void printStrip(boolean[] ledStrip) {
		for (int i=0; i<ledStrip.length; i++) {
			if(ledStrip[i]) {
				System.out.print("[*]");
			} else {
				System.out.print("[ ]");
			}
		}
	}
}
