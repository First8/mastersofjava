import java.util.Collections;
import java.util.LinkedList;

public class Deck {
	private final LinkedList<Card> cards = new LinkedList<>();

	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				this.cards.add(Card.getCard(suit, rank));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	public Card draw() {
		return this.cards.poll();
	}

	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
}
