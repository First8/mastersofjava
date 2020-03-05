public enum Rank {
	TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE(
			"A");

	private String symbol;

	private Rank(String symbol) {
		this.symbol = symbol;
	}

	public boolean isNeighbour(Rank other) {
		return Math.abs(this.ordinal() - other.ordinal()) == 1;
	}

	public String getSymbol() {
		return this.symbol;
	}
}
