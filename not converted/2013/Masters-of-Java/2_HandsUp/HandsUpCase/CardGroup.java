import java.util.HashSet;
import java.util.Set;

/**
 * Represents a group of Cards with the same Rank. Can be used to represent pairs, trips or quads.
 */
public final class CardGroup implements Comparable<CardGroup> {
	private final Rank rank;
	private final Set<Card> cards = new HashSet<>();

	public CardGroup(Card card) {
		this.rank = card.getRank();
		this.cards.add(card);
	}

	public void add(Card card) {
		this.cards.add(card);
	}

	public Rank getRank() {
		return this.rank;
	}

	public int getSize() {
		return this.cards.size();
	}

	public Set<Card> getCards() {
		return this.cards;
	}

	@Override
	public int compareTo(CardGroup other) {
		if (this.getSize() == other.getSize()) {
			return this.rank.compareTo(other.rank);
		} else {
			return this.getSize() - other.getSize();
		}
	}

	@Override
	public boolean equals(Object obj) {
		CardGroup other = (CardGroup) obj;
		if (this.getSize() != other.getSize()) {
			return false;
		}
		if (this.getRank() != other.getRank()) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.rank.hashCode();
		hash = 31 * hash + this.getSize();
		return hash;
	}

	@Override
	public String toString() {
		return this.cards.toString();
	}
}