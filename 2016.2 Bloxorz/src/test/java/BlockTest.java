

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlockTest {

    @Test
    public void testMoveRight() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedRight = block.moveRight();

        assertEquals(new Position(2, 1), movedRight.getPos());
        assertEquals(new Position(2, 1), movedRight.getPos2());
    }

    @Test
    public void testMoveRightWhenLyingVertically() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 2);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedRight = block.moveRight();

        assertEquals(new Position(2, 1), movedRight.getPos());
        assertEquals(new Position(2, 2), movedRight.getPos2());
    }

    @Test
    public void testMoveRightWhenStanding() {
        Position pos1 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos1);

        MovableBlock movedRight = block.moveRight();

        assertEquals(new Position(1, 1), movedRight.getPos());
        assertEquals(new Position(2, 1), movedRight.getPos2());
    }

    @Test
    public void testMoveLeft() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedLeft = block.moveLeft();

        assertEquals(new Position(1, 1), movedLeft.getPos());
        assertEquals(new Position(1, 1), movedLeft.getPos2());
    }

    @Test
    public void testMoveLeftWhenLyingVertically() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 2);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedLeft = block.moveLeft();

        assertEquals(new Position(0, 1), movedLeft.getPos());
        assertEquals(new Position(0, 2), movedLeft.getPos2());
    }

    @Test
    public void testMoveLeftWhenStanding() {
        Position pos1 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos1);

        MovableBlock movedLeft = block.moveLeft();

        assertEquals(new Position(0, 1), movedLeft.getPos());
        assertEquals(new Position(1, 1), movedLeft.getPos2());
    }
    
     @Test
    public void testMoveUp() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 2);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedUp = block.moveUp();

        assertEquals(new Position(1, 1), movedUp.getPos());
        assertEquals(new Position(1, 1), movedUp.getPos2());
    }

    @Test
    public void testMoveUpWhenLyingHorizontally() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedUp = block.moveUp();

        assertEquals(new Position(1, 0), movedUp.getPos());
        assertEquals(new Position(2, 0), movedUp.getPos2());
    }

    @Test
    public void testMoveUpWhenStanding() {
        Position pos1 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos1);

        MovableBlock movedUp = block.moveUp();

        assertEquals(new Position(1, 0), movedUp.getPos());
        assertEquals(new Position(1, 1), movedUp.getPos2());
    }

    @Test
    public void testMoveDown() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 2);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedDown = block.moveDown();

        assertEquals(new Position(1, 2), movedDown.getPos());
        assertEquals(new Position(1, 2), movedDown.getPos2());
    }

    @Test
    public void testMoveDownWhenLyingHorizontally() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        MovableBlock movedDown = block.moveDown();

        assertEquals(new Position(1, 2), movedDown.getPos());
        assertEquals(new Position(2, 2), movedDown.getPos2());
    }

    @Test
    public void testMoveDownWhenStanding() {
        Position pos1 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos1);

        MovableBlock movedDown = block.moveDown();

        assertEquals(new Position(1, 1), movedDown.getPos());
        assertEquals(new Position(1, 2), movedDown.getPos2());
    }
    
    @Test
    public void testIsStandingPositionsEqual() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        assertTrue(block.isStanding());
    }

    @Test
    public void testIsStandingPositionDifferentXAxis() {
        Position pos1 = new Position(1, 1);
        Position pos2 = new Position(2, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        assertFalse(block.isStanding());
    }

    @Test
    public void testIsStandingPositionDifferentYAxis() {
        Position pos1 = new Position(1, 2);
        Position pos2 = new Position(1, 1);

        MovableBlock block = new MovableBlock(pos1, pos2);

        assertFalse(block.isStanding());
    }

    @Test
    public void testEqualsAndHashCode() {
        final MovableBlock block = new MovableBlock(new Position(1, 1), new Position(1, 1));

        //Reflexive && consistency
        assertEquals(block, block);
        assertEquals(block, block);
        assertEquals(block.hashCode(), block.hashCode());

        //Symmetric
        final MovableBlock block2 = new MovableBlock(new Position(1, 1), new Position(1, 1));
        assertEquals(block, block2);
        assertEquals(block2, block2);
        assertEquals(block.hashCode(), block2.hashCode());

        //Transitive
        final MovableBlock block3 = new MovableBlock(new Position(1, 1), new Position(1, 1));
        assertEquals(block2, block3);
        assertEquals(block2.hashCode(), block3.hashCode());

        //Unequality
        final MovableBlock blockWithDifferentPos1 = new MovableBlock(new Position(2, 1), new Position(1, 1));
        final MovableBlock blockWithDifferentPos2 = new MovableBlock(new Position(1, 1), new Position(2, 1));

        assertNotEquals(block, blockWithDifferentPos1);
        assertNotEquals(block.hashCode(), blockWithDifferentPos1.hashCode());

        assertNotEquals(block, blockWithDifferentPos2);
        assertNotEquals(block.hashCode(), blockWithDifferentPos2.hashCode());

        assertNotEquals(blockWithDifferentPos1, blockWithDifferentPos2);
        assertNotEquals(blockWithDifferentPos1.hashCode(), blockWithDifferentPos2.hashCode());
    }
}
