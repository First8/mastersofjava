/**
 * A block is a small set of rows and cells.
 */
public class Block extends Table {

	/**
	 * Creates a block.
	 * 
	 * @param rows
	 *            The rows of this block.
	 */
	public Block(Row[] rows) {
		super(rows);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Row row : getRows()) {
			for (Cell cell : row.getCells()) {
				sb.append(cell.getFilling().getChar());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
