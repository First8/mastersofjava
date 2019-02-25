

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class BoardTest {
    private Tile[][] tiles;
    private MovableBlock initialBlock;
    private Position goal;
    private Board board;
    
    @Before
    public void setUp() { 
        tiles = new Tile[1][3];
        
        tiles[0][0] = new Tile(new Position(0, 0), true);
        tiles[0][1] = new Tile(new Position(1, 0), true);
        tiles[0][2] = new Tile(new Position(1, 1), false);
        
        goal = new Position(1,0);
        initialBlock = new MovableBlock(new Position(0,0), new Position(0,0));
        
        board = new Board(initialBlock, goal, tiles);
    }
    
    
    @Test
    public void testWithBlock() {
        final MovableBlock updatedBlock = new MovableBlock(new Position(1,1), new Position(1,1));
        final Board updatedBoard = board.withBlock(updatedBlock);
        
        assertEquals(updatedBlock, updatedBoard.getBlock());
        assertEquals(goal, updatedBoard.getGoal());
    }
    
    @Test
    public void testIsFinishedBlockStandingOnHole() { 
    	final MovableBlock block = new MovableBlock(board.getGoal(), board.getGoal());
    	
    	final Board finishedBoard = board.withBlock(block);
    
    	assertTrue(finishedBoard.isFinished());
    }
    
    @Test
    public void testIsFinishedBlockLyingOnHole() { 
    	final MovableBlock block = new MovableBlock(board.getGoal(), board.getGoal().withX(1));
    	
    	final Board finishedBoard = board.withBlock(block);
    
    	assertFalse(finishedBoard.isFinished());
    }
    
    @Test
    public void testIsFinishedBlockNotOnHole() { 
    	assertFalse(board.isFinished());
    }
    
    @Test
    public void testIsValidBlockOnTheBoard() { 
    	assertTrue(board.isValid());
    }
    
    @Test
    public void testIsValidBlockHalfOnTheBoardYAxis() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(0, 1), new Position(0,0));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testIsValidBlockNotOnTheBoardYAxis() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(0, 1), new Position(0, 1));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testIsValidBlockHalfOnTheBoardXAxis() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(3, 0), new Position(2,0));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testIsValidBlockNotOnTheBoardXAxis() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(3, 0), new Position(3, 0));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testIsValidBlockHalfOnImpassableTile() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(0, 1), new Position(0, 2));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testIsValidBlockOnImpassableTile() { 
    	final MovableBlock invalidBlock = new MovableBlock(new Position(0, 2), new Position(0, 2));
    	final Board invalidBoard = board.withBlock(invalidBlock);
    	
    	assertFalse(invalidBoard.isValid());
    }
    
    @Test
    public void testEqualsAndHashCode() { 
    	
    	//Reflexive && consistent
    	for(int i = 0; i < 2; i++) { 
    		assertEquals(board, board);
        	assertEquals(board.hashCode(), board.hashCode());
    	}
    	
    	//Symmetric
    	final Board secondBoard = new Board(initialBlock, goal, tiles);
		assertEquals(board, secondBoard);
    	assertEquals(board.hashCode(), secondBoard.hashCode());
    	
    	assertEquals(secondBoard, board);
    	assertEquals(secondBoard.hashCode(), board.hashCode());
    	
    	//Transitive
    	final Board thirdBoard = new Board(initialBlock, goal, tiles);
    	assertEquals(secondBoard, thirdBoard);
    	assertEquals(secondBoard.hashCode(), thirdBoard.hashCode());
    	
    	assertNotEquals(board, null);
    	
    	final Board differentBlock = new Board(new MovableBlock(new Position(0, 1), new Position(0, 1)), goal, tiles);
    	assertNotEquals(board, differentBlock);
    	assertNotEquals(board.hashCode(), differentBlock.hashCode());
    	
    	final Board differentGoal = new Board(initialBlock, goal.withX(1), tiles); 
    	assertNotEquals(board, differentGoal);
    	assertNotEquals(board.hashCode(), differentGoal.hashCode());
    	
    	final Board differentTiles = new Board(initialBlock, goal, new Tile[][] {});
      	assertNotEquals(board, differentTiles);
    	assertNotEquals(board.hashCode(), differentTiles.hashCode());
    }
}
