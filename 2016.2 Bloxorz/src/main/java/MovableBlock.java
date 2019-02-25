

import java.util.Objects;

/**
 * Represents the playable rectangular block in Bloxorz. The block consists of
 * two cubes, taking at most 2 tiles on the board. The cubes can also be stacked
 * on top of each other, taking one tile.
 */
public final class MovableBlock {

    private final Position pos, pos2;

    public MovableBlock(final Position pos1, final Position pos2) {
        this.pos = pos1;
        this.pos2 = pos2;
    }

    public MovableBlock moveRight() {
        if (isStanding()) {
            return new MovableBlock(pos, pos2.withX(1));
        } else if (pos.getY() != pos2.getY()) {
            return new MovableBlock(pos.withX(1), pos2.withX(1));
        } else {
            return new MovableBlock(new Position(pos2.getX(), pos.getY()), pos2);
        }
    }

    public MovableBlock moveLeft() {
        if (isStanding()) {
            return new MovableBlock(pos.withX(-1), pos2);
        } else if (pos.getY() != pos2.getY()) {
            return new MovableBlock(pos.withX(-1), pos2.withX(-1));
        } else {
            return new MovableBlock(pos, new Position(pos.getX(), pos2.getY()));
        }
    }

    public MovableBlock moveUp() {
        if (isStanding()) {
            return new MovableBlock(pos.withY(-1), pos2);
        } else if (pos.getX() != pos2.getX()) {
            return new MovableBlock(pos.withY(-1), pos2.withY(-1));
        } else {
            return new MovableBlock(pos, new Position(pos2.getX(), pos.getY()));
        }
    }

    public MovableBlock moveDown() {
        if (isStanding()) {
            return new MovableBlock(pos, pos2.withY(+1));
        } else if (pos.getX() != pos2.getX()) {
            return new MovableBlock(pos.withY(+1), pos2.withY(+1));
        } else {
            return new MovableBlock(new Position(pos.getX(), pos2.getY()), pos2);
        }
    }

    public Position getPos() {
        return pos;
    }

    public Position getPos2() {
        return pos2;
    }

    boolean isStanding() {
        return pos.equals(pos2);
    }

    @Override
    public String toString() {
        return String.format("Block[pos1=%s]", pos);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.pos);
        hash = 61 * hash + Objects.hashCode(this.pos2);
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
        final MovableBlock other = (MovableBlock) obj;
        if (!Objects.equals(this.pos, other.pos)) {
            return false;
        }
        if (!Objects.equals(this.pos2, other.pos2)) {
            return false;
        }
        return true;
    }

}
