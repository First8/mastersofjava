

final class Tile {

    private final Position position;
    private final boolean isPassable;

    Tile(final Position position, final boolean isPassable) {
        this.position = position;
        this.isPassable = isPassable;
    }

    Position getPosition() {
        return position;
    }

    /**
     * Returns true if this tile can be entered by the Block, otherwise false.
     * @return true if this tile can be entered by the Block, otherwise false
     */
    boolean isPassable() {
        return isPassable;
    }

}
