

public class SolutionHill extends Hill{

	@Override
	public RecursiveAntHillImpl createAnts() {
		return new Solution();
	}
	@Override
	public Hill createHill() {
		return new SolutionHill();
	}
	
	public static void main(String[] args)  {
		Hill hill = new SolutionHill(); 
		while (hill.getGeneration()<10) {
			hill.print();
			hill = hill.nextGeneration();
		}
	}
}
