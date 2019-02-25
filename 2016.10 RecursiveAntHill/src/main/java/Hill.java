import java.util.ArrayList;
import java.util.List;


public class Hill {
	private int generation = 0;
	private List<Ant> colony = new ArrayList<Ant>();
	private AntQueen queen = new AntQueen(1); 
	private int population = 0;
	private Integer[][] locations=new Integer[0][0]; 
	
	public int getGeneration() {
		return generation;
	}
	public RecursiveAntHillImpl createAnts(){
		return new RecursiveAntHillImpl();
	}
	public Hill createHill(){
		return new Hill();
	}
	public Hill nextGeneration() {
		Hill antHill = createHill();
		antHill.generation = this.generation+1;
		antHill.queen = this.queen;
		antHill.locations=new Integer[antHill.generation][antHill.generation];
		antHill.population = 0; 
		AntHill growMethod = createAnts();
		
		for (int row=1;row<=antHill.generation;row++) {
			for (int col=1;col<=antHill.generation;col++) {
				int height = growMethod.calculate(row, col);

				antHill.locations[row-1][col-1] = height; 
				antHill.population += height;
			}	
		}
		return antHill;
	}
	
	public void print() {
		System.out.println("AntHill " + generation + " - " + population);
		StringBuffer sb = new StringBuffer(); 
		for (int row=0;row<generation;row++) {
			for (int col=0;col<generation;col++) {
				if (locations[row][col]>0) {
					sb.append(locations[row][col] +"\t");
				}
			}	
			sb.append("\n");
		}
		System.out.println(sb); 
	}
	
	public static void main(String[] args)  {
		Hill hill = new Hill(); 
		while (hill.getGeneration()<10) {
			hill.print();
			hill = hill.nextGeneration();
		}
	}
}
