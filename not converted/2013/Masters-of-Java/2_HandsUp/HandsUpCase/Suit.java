public enum Suit {
	HEARTS("\u2665"), DIAMONDS("\u2666"), CLUBS("\u2663"), SPADES("\u2660");

	private String symbol;

	private Suit(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return this.symbol;
	}
}
