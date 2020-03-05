
public class Print {
	
	public static void print(String name, boolean plot[][]) {
		System.out.println("\n"+ name);
		
		int w = plot.length;
		int h = plot[0].length;
		
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				if (plot[x][y]) {
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

}
