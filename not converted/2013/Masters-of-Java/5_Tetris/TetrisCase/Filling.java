/**
 * The inside of a cell. Can be, hold on tight: empty of full.
 */
public enum Filling {

	/** The filling of an empty cell. */
	EMPTY(' '),

	/** The filling of a full cell. */
	FULL('X');

	/** The graphical representation of a filling. */
	private char ch;

	/**
	 * Creates a filling.
	 * 
	 * @param ch
	 *            The graphical representation of a filling.
	 */
	private Filling(char ch) {
		this.ch = ch;
	}

	/**
	 * Creates a filling from its graphical representation.
	 * 
	 * @param ch
	 *            graphical representation
	 * @return Filling
	 */
	public static Filling valueOf(char ch) {
		for (Filling filling : values()) {
			if (filling.getChar() == ch) {
				return filling;
			}
		}
		return null;
	}

	/**
	 * @return The graphical representation of a filling.
	 */
	public char getChar() {
		return ch;
	}

}
