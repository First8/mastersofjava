
public class AreaCounter {
	
	public int count( int[][] image ) {
		int counter = 0;
		
		int height = image[0].length;
		int width = image.length;

//		System.out.println("width: " + width);
//		System.out.println("height: " + height);
		
		
		for (int x=0; x<width-1; x++) {
			for (int y=0; y<height-1; y++) {
				
				int a = image[x][y];
				int b = image[x+1][y];
				int c = image[x][y+1];
				int d = image[x+1][y+1];
				
				if (a==1 && b==0 && c==0 && d==0) {
					counter++;
				} else if (a ==1 && b==1 && c==1 && d==0) {
					counter--;
				}
				
			}
		}
		
		return counter;
	}

}
