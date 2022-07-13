public class LedMatrix {

	int width;
	int height;

	public LedMatrix(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int xyToLed(int x, int y) {
		// Insert your code here
		return 0;
	}

	public boolean[] getStrip(boolean[][] image) {
		// Insert your code here
		return new boolean[] {};
	}

	public void plot(boolean[][] image) {
		System.out.println();
		System.out.println();

		for (boolean[] booleans : image) {
			for (boolean aBoolean : booleans) {
				if (aBoolean) {
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
		for (boolean b : ledStrip) {
			if (b) {
				System.out.print("[*]");
			} else {
				System.out.print("[ ]");
			}
		}
	}
}
