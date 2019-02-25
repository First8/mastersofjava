import java.util.ArrayList;
import java.util.List;



public class RecursiveAntHillImpl implements AntHill {
	
	private int generation = 1;
	private List<Ant> colony = new ArrayList<Ant>();
	private AntQueen queen = new AntQueen(1); 
	private int[][] heights;  
	@Override
	public int calculate(int x, int y) {
		// TODO: implement this method. 
		
		return 0; 
	}
	
	public RecursiveAntHillImpl nextGeneration() {
		RecursiveAntHillImpl pascalAntHillImpl = new RecursiveAntHillImpl();
		pascalAntHillImpl.generation++;
		pascalAntHillImpl.queen = this.queen;
		pascalAntHillImpl.heights = new int[pascalAntHillImpl.generation][pascalAntHillImpl.generation];
		for (int x=1;x<generation;x++) {
			for (int y=1;y<generation;y++) {
				pascalAntHillImpl.heights[x][y] = calculate(x,y);
			}	
		}
		return pascalAntHillImpl;
	}
}
