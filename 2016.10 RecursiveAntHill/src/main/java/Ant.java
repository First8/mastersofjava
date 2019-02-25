

public abstract class Ant {

	private final int generation;
	
	public Ant(final int generation) {
		this.generation = generation;
	}
	
	public int getGeneration() {
		return generation;
	}
	
	
	public boolean hasWings() {
		return false; 
	}
}
