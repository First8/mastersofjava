/**
 * Respresents a playing card
 */
public class Card implements Comparable<Card> {
	private final Suit suit;
	private final Rank rank;

	private Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public static Card getCard(Suit suit, Rank rank) {
		return new Card(suit, rank);
	}

	public Suit getSuit() {
		return this.suit;
	}

	public Rank getRank() {
		return this.rank;
	}

	public boolean isNeighbour(Card other) {
		return this.rank.isNeighbour(other.rank);
	}

	@Override
	public String toString() {
		return this.rank.getSymbol() + this.suit.getSymbol();
	}

	/**
	 * Compares this Card to an other Card by comparing their Rank. The Suit does not influence this.
	 * 
	 * @param other
	 *            The Card to compare this instance to.
	 * 
	 * @return a negative integer, zero or a positive integer as this Card is lower, equal to or higher than the other
	 *         Card.
	 */
	@Override
	public int compareTo(Card other) {
		// TODO: Implementeer de compareTo method

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Implementeer de equals method volgens het equals contract

		return super.equals(object);
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.rank.hashCode();
		hash = 31 * hash + this.suit.hashCode();
		return hash;
	}
}
