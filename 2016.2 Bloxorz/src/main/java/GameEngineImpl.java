

import java.util.List;

public class GameEngineImpl implements GameEngine {

	private Board board; 
	private List<MovableBlock> historyList; 
	
	@Override
	public int calculate() {
		//TODO: Implement a method to find the shortest possible route 		
		return 0; 
	}
	
	@Override
	public void loadGame(Board board) {
		this.board = board; 
	}
	
	@Override
	public void print() {
		System.out.println("From " + board.getBlock() + " to " + board.getGoal()+ ", takes " + calculate() + " steps."); 
	}
	
	public List<MovableBlock> getHistoryList() {
		return historyList;
	}
}
