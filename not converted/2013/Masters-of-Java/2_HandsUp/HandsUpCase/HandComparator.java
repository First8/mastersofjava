import java.util.Comparator;
import java.util.List;

public class HandComparator implements Comparator<Hand> {

	/**
	 * Compares two poker hands (combination of 5 cards).
	 * 
	 * @param theOne
	 *          The one hand
	 * @param theOther
	 *          The other hand
	 * 
	 * @return A negative integer, zero, or a positive integer when the one hand is less than, equal to or greater than
	 *         the other hand.
	 */
	@Override
	public int compare(Hand theOne, Hand theOther) {
		if (theOne.getCombination() != theOther.getCombination()) {
			// If the combinations are different, just compare the combination
			return theOne.getCombination().compareTo(theOther.getCombination());
		}
		switch (theOne.getCombination()) {
			case ROYAL_FLUSH:
				// A royal flush is always equal to another royal flush
				return 0;
			case STRAIGHT_FLUSH:
				return this.compareSingles(theOne, theOther);
			case FOUR_OF_A_KIND:
				return this.compareFourOfAKind(theOne, theOther);
			case FULL_HOUSE:
				return this.compareFullHouse(theOne, theOther);
			case FLUSH:
				return this.compareSingles(theOne, theOther);
			case STRAIGHT:
				// No use in comparing all the singles, since they will be in the same order.
				return theOne.getHighestCard().compareTo(theOther.getHighestCard());
			case THREE_OF_A_KIND:
				return this.compareThreeOfAKind(theOne, theOther);
			case TWO_PAIR:
				return this.compareTwoPair(theOne, theOther);
			case ONE_PAIR:
				return this.compareOnePair(theOne, theOther);
			case HIGH_CARD:
				return this.compareSingles(theOne, theOther);
			default:
				throw new IllegalArgumentException("An unknown Combination was encountered.");
		}
	}

	private int compareFourOfAKind(Hand theOne, Hand theOther) {
		// Four of a kind: Highest ranked four, otherwise the highest ranked single.
		Rank theOneQuads = theOne.getQuads().getRank();
		Rank theOtherQuads = theOther.getQuads().getRank();

		if (theOneQuads != theOtherQuads) {
			return theOneQuads.compareTo(theOtherQuads);
		} else {
			// If the quads are equal, then inspect the single
			return this.compareSingles(theOne, theOther);
		}
	}

	private int compareFullHouse(Hand theOne, Hand theOther) {
		// TODO: Vergelijk twee Hands met een full house

		return 0;
	}

	private int compareThreeOfAKind(Hand theOne, Hand theOther) {
		// Three of a kind: Highest ranked three, otherwise the highest ranked singles.
		CardGroup thisTrips = theOne.getTrips();
		CardGroup otherTrips = theOther.getTrips();

		if (thisTrips.getRank() != otherTrips.getRank()) {
			return thisTrips.getRank().compareTo(otherTrips.getRank());
		} else {
			// If the trips are equal, then inspect the singles
			return this.compareSingles(theOne, theOther);
		}
	}

	private int compareTwoPair(Hand theOne, Hand theOther) {
		// Two pair: Highest ranked pair, otherwise the second ranked pair, otherwise the single.
		CardGroup theOneHighPair = theOne.getHighestPair();
		CardGroup otherHighPair = theOther.getHighestPair();

		if (theOneHighPair.getRank() != otherHighPair.getRank()) {
			return theOneHighPair.getRank().compareTo(otherHighPair.getRank());
		} else {
			// If the highest pairs are equal, then inspect the second pairs
			CardGroup thisLowPair = theOne.getSecondHighestPair();
			CardGroup otherLowPair = theOther.getSecondHighestPair();

			if (thisLowPair.getRank() != otherLowPair.getRank()) {
				return thisLowPair.getRank().compareTo(otherLowPair.getRank());
			} else {
				// If the second pairs are equal as well, then inspect the single
				return this.compareSingles(theOne, theOther);
			}
		}
	}

	private int compareOnePair(Hand theOne, Hand theOther) {
		// Two pair: Highest pair, otherwise the singles
		CardGroup thisHighPair = theOne.getHighestPair();
		CardGroup otherHighPair = theOther.getHighestPair();

		if (thisHighPair.getRank() != otherHighPair.getRank()) {
			return thisHighPair.getRank().compareTo(otherHighPair.getRank());
		} else {
			// If the pairs are equal, then inspect the singles
			return this.compareSingles(theOne, theOther);
		}
	}

	private int compareSingles(Hand theOne, Hand theOther) {
		List<Card> theOneSingles = theOne.getSortedSingles();
		List<Card> theOtherSingles = theOther.getSortedSingles();
		for (int index = theOneSingles.size() - 1; index >= 0; index--) {
			if (theOneSingles.get(index).compareTo(theOtherSingles.get(index)) != 0) {
				return theOneSingles.get(index).compareTo(theOtherSingles.get(index));
			}
		}
		return 0;
	}
}
