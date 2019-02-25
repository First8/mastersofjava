

public interface GameEngine {

    /**
     * Calculates the shortest number of steps in order to solve the state of the provided board
     * @param board the board to solve
     * @return the shortest number of steps in order to solve the board
     */
	int calculate();

	void loadGame(Board board); 
	
	void print(); 
	
}