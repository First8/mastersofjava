

import java.util.Arrays;
import java.util.Objects;

public final class Board {

    private final MovableBlock block;

    private final Position goal;

    private final Tile[][] level;

    public Board(MovableBlock block, Position goal, Tile[][] level) {
        this.block = block;
        this.goal = goal;
        this.level = level;
    }

    /**
     * Creates and returns a copy of this Board with the provided Block.
     * @param block the block to place on the board
     * @return a copy of this Board with the provided Block
     */
    public Board withBlock(MovableBlock block) {
        return new Board(block, goal, level);
    }

    public MovableBlock getBlock() {
        return block;
    }

    public Position getGoal() {
        return goal;
    }

    /**
     * Determines if the block is standing at the goal of the board.
     * @return  true if the block is standing at the goal of the board, otherwise false
     */
    public boolean isFinished() {
        return block.getPos().equals(goal) && block.getPos2().equals(goal);
    }

    /**
     * Determines if this version of the board is valid. The board is considered valid if the Block is in a legal position.
     * @return true if the board is in a valid state, otherwise false
     */
    public boolean isValid() {
        if (block.getPos().getX() < 0 || block.getPos().getY() < 0) {
            return false;
        }

        if (block.getPos2().getX() < 0 || block.getPos2().getY() < 0) {
            return false;
        }

        //Check Y-axis out of the bounds
        if (block.getPos().getY() >= level.length || block.getPos2().getY() >= level.length) {
            return false;
        }

        //Check X-axis out of the bounds
        if (block.getPos().getX() >= level[0].length || block.getPos2().getX() >= level[0].length) {
            return false;
        }

        return level[block.getPos().getY()][block.getPos().getX()].isPassable() && level[block.getPos2().getY()][block.getPos2().getX()].isPassable();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.block);
        hash = 31 * hash + Objects.hashCode(this.goal);
        hash = 31 * hash + Arrays.deepHashCode(this.level);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        if (!Objects.equals(this.block, other.block)) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        if (!Arrays.deepEquals(this.level, other.level)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        for (Tile[] row : level) {
            for (Tile col : row) {
                if (block.getPos().equals(col.getPosition()) || block.getPos2().equals(col.getPosition())) {
                    buffer.append('B');
                } else if (goal.equals(col.getPosition())) {
                    buffer.append('O');
                } else {
                    buffer = col.isPassable() ? buffer.append('X') : buffer.append('-');
                }
            }

            buffer.append(System.lineSeparator());
        }

        return buffer.toString();
    }

    public static Board fromStringArray(String[] lines) {
    	char[][] result = new char[lines.length][lines[0].length()]; 
    	
    	int row = 0; 
    	for (String line: lines) {
    		
    		for (int index=0;index<line.length();index++) {
    			result[row][index]=line.charAt(index);
    		}
    		row++; 
    	}
    	
    	return fromCharArray(result); 
    }
    /**
     * Instantiates a Board based on the provided level. This method assumes
     * that the provided level is an even matrix (not a jagged array).
     *
     * @param level the level to instantiate
     * @return a Board with the initial
     */
    public static Board fromCharArray(final char[][] level) {
        MovableBlock block = null;
        Position goal = null;
        final Tile[][] tiles = new Tile[level.length][level[0].length];

        for (int rowNr = 0; rowNr < level.length; rowNr++) {
            char[] row = level[rowNr];
            for (int colNr = 0; colNr < row.length; colNr++) {
                final char col = row[colNr];
                final Position position = new Position(colNr, rowNr);
                final boolean isPassable;

                if (col == 'X' || col == 'B' || col == 'O') {
                    isPassable = true;

                    if (col == 'B') {
                        if (block == null) {
                            block = new MovableBlock(position, position);
                        } else {
                            throw new IllegalStateException("Multiple starting positions!");
                        }

                    }

                    if (col == 'O') {
                        if (goal == null) {
                            goal = position;
                        } else {
                            throw new IllegalStateException("Multiple goals!");
                        }
                    }
                } else {
                    isPassable = false;
                }

                tiles[rowNr][colNr] = new Tile(position, isPassable);
            }
        }

        return new Board(block, goal, tiles);
    }
}
