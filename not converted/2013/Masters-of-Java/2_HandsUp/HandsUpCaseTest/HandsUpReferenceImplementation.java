import java.util.HashSet;
import java.util.Set;

public class HandsUpReferenceImplementation implements HandsUp {
	HandComparator comparator = new HandComparator();

	/**
	 * Determines the winner of a show-down in Texas Hold'm
	 * 
	 * @param table
	 *          The 5 cards that are on the table
	 * @param player1
	 *          The first player contending in the show-down
	 * @param player2
	 *          The second player contending in the show-down
	 * @return The name of the player with the best hand, or null if it is a tie.
	 */
	@Override
	public String determineWinner(Card[] table, Player player1, Player player2) {
		Hand bestHandPlayer1 = this.getBestHand(table, player1.getPocket());
		Hand bestHandPlayer2 = this.getBestHand(table, player2.getPocket());

		int result = this.comparator.compare(bestHandPlayer1, bestHandPlayer2);
		if (result > 0) {
			return player1.getName();
		} else if (result < 0) {
			return player2.getName();
		} else {
			return null;
		}
	}

	/**
	 * Determines the best hand (combination of 5 cards) that a player can make with his pocket cards and the five cards
	 * on the table.
	 * 
	 * @param table
	 *          Array of five Cards on the table.
	 * @param pocket
	 *          Array of two Cards in the player's hands
	 * @return
	 */
	private Hand getBestHand(Card[] table, Card[] pocket) {
		Hand bestHand = null;

		// Loop through all the possible hands by omitting 2 cards every time
		for (int omitOne = 0; omitOne < 7; omitOne++) {
			for (int omitOther = 0; omitOther < 7; omitOther++) {
				// Can't omit the same card twice!
				if (omitOne == omitOther) {
					continue;
				}

				Hand possibleHand = this.composeHand(table, pocket, omitOne, omitOther);
				if (bestHand == null) {
					bestHand = possibleHand;
				} else if (this.comparator.compare(possibleHand, bestHand) > 0) {
					bestHand = possibleHand;
				}
			}
		}

		return bestHand;
	}

	private Hand composeHand(Card[] table, Card[] pocket, int omitOne, int omitOther) {
		Set<Card> cardsForHand = new HashSet<>();

		for (int position = 0; position < 7; position++) {
			if (position == omitOne || position == omitOther) {
				continue;
			} else if (position < 5) {
				cardsForHand.add(table[position]);
			} else {
				cardsForHand.add(pocket[position - 5]);
			}
		}

		return Hand.getHand(cardsForHand);
	}
}