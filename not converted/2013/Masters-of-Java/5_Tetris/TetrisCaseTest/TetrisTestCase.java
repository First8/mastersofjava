class TetrisTestCase {

    private String name;
    private String description;
    private Game game;
    private Block block;
    private int rows;

    public TetrisTestCase() {
        super();
    }

    public Block getBlock() {
        return block;
    }

    public String getDescription() {
        return description;
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

}
