import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Hand implements Iterable<Card> {
	private Combination combination;
	private final List<Card> cards = new ArrayList<>();
	private final Map<Rank, CardGroup> cardGroups = new HashMap<>();

	private Hand(Collection<Card> cards) {
		this.cards.addAll(cards);
		Collections.sort(this.cards);

		for (Card card : cards) {
			if (this.cardGroups.containsKey(card.getRank())) {
				this.cardGroups.get(card.getRank()).add(card);
			} else {
				this.cardGroups.put(card.getRank(), new CardGroup(card));
			}
		}
	}

	/**
	 * Returns a new Hand instance with the five given cards.
	 * 
	 * @param cards
	 *          A Collection of exactly 5 Cards.
	 * @return A new Hand instance
	 */
	public static Hand getHand(Collection<Card> cards) {
		if (cards.size() != 5) {
			throw new IllegalArgumentException("Wrong number of cards in hand (" + cards.size() + "), should be 5!");
		}
		Hand hand = new Hand(cards);
		hand.determineCombination();
		return hand;
	}

	public Card getHighestCard() {
		return this.cards.get(4);
	}

	public Card getLowestCard() {
		return this.cards.get(0);
	}

	public Combination getCombination() {
		return this.combination;
	}

	public void determineCombination() {
		this.combination = CombinationHelper.determineCombination(this);
	}

	@Override
	public Iterator<Card> iterator() {
		return this.cards.iterator();
	}

	public CardGroup getQuads() {
		return this.getGroupOf(4);
	}

	public boolean hasQuads() {
		return this.hasGroupOf(4);
	}

	public CardGroup getTrips() {
		return this.getGroupOf(3);
	}

	public boolean hasTrips() {
		return this.hasGroupOf(3);
	}

	public CardGroup getHighestPair() {
		CardGroup[] pairs = this.getPairs();
		if (pairs.length == 2) {
			if (pairs[0].getRank().compareTo(pairs[1].getRank()) > 0) {
				return pairs[0];
			} else {
				return pairs[1];
			}
		} else if (pairs.length == 1) {
			return pairs[0];
		} else {
			return null;
		}
	}

	public CardGroup getSecondHighestPair() {
		CardGroup[] pairs = this.getPairs();
		if (pairs.length == 2) {
			if (pairs[0].getRank().compareTo(pairs[1].getRank()) > 0) {
				return pairs[1];
			} else {
				return pairs[0];
			}
		} else {
			return null;
		}
	}

	public boolean hasPair() {
		return this.hasGroupOf(2);
	}

	public boolean hasTwoPairs() {
		return this.getPairs().length == 2;
	}

	public List<Card> getSortedSingles() {
		List<Card> cards = new ArrayList<>();
		for (CardGroup group : this.cardGroups.values()) {
			if (group.getSize() == 1) {
				cards.addAll(group.getCards());
			}
		}
		Collections.sort(cards);
		return cards;
	}

	private CardGroup getGroupOf(int number) {
		for (CardGroup group : this.cardGroups.values()) {
			if (group.getSize() == number) {
				return group;
			}
		}
		return null;
	}

	private CardGroup[] getPairs() {
		List<CardGroup> pairs = new ArrayList<>();
		for (CardGroup group : this.cardGroups.values()) {
			if (group.getSize() == 2) {
				pairs.add(group);
			}
		}
		return pairs.toArray(new CardGroup[pairs.size()]);
	}

	private boolean hasGroupOf(int number) {
		for (CardGroup group : this.cardGroups.values()) {
			if (group.getSize() == number) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.cards.toString() + ": " + this.combination;
	}
}
