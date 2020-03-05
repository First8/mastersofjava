import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static int[][] readFile(int fileId) throws IOException{
		List<String> lines = new ArrayList<String>();
		
		InputStreamReader r = new InputStreamReader(resource("pyramid" + fileId + ".dat"));
		BufferedReader br = new BufferedReader(r);
		String line = null;
		do {
			line = br.readLine();
			if (line!=null) {
				lines.add(line);
			}
		} while(line!=null);
		br.close();
		
		int[][] pyramid = new int[lines.size()][lines.size()];
		for (int i=0; i<lines.size(); i++) {
			String[] numbers = lines.get(i).split(" ");
			for( int j=0; j<i+1; j++) {
				pyramid[i][j] = Integer.parseInt(numbers[j]);
			}
		}
		return pyramid;
	}
	
    private static InputStream resource(String name) throws IOException {
    	return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }

	
}
