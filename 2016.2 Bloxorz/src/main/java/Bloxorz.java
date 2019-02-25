
public class Bloxorz {

    public static void main(String[] args) {
    	String[] boardRows = new String[]{
    			"BXXXX",
       			" X X ",
       			" X X ",
       			" X XX",
       			"    X",
       			"OXXXX"
    	};

        Board game = Board.fromStringArray(boardRows);
        GameEngine calculator = new GameEngineImpl();
        calculator.loadGame(game);
        calculator.print();
        // shortest path should be 26 moves
    }

    

}
