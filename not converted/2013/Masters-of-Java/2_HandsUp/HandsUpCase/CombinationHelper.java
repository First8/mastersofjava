import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CombinationHelper {
	public static Combination determineCombination(Hand hand) {
		if (isRoyalFlush(hand)) {
			return Combination.ROYAL_FLUSH;
		} else if (isStraightFlush(hand)) {
			return Combination.STRAIGHT_FLUSH;
		} else if (isFourOfAKind(hand)) {
			return Combination.FOUR_OF_A_KIND;
		} else if (isFullHouse(hand)) {
			return Combination.FULL_HOUSE;
		} else if (isFlush(hand)) {
			return Combination.FLUSH;
		} else if (isStraight(hand)) {
			return Combination.STRAIGHT;
		} else if (isThreeOfAKind(hand)) {
			return Combination.THREE_OF_A_KIND;
		} else if (isTwoPair(hand)) {
			return Combination.TWO_PAIR;
		} else if (isOnePair(hand)) {
			return Combination.ONE_PAIR;
		}
		return Combination.HIGH_CARD;
	}

	public static boolean isRoyalFlush(Hand hand) {
		if (isStraightFlush(hand)) {
			return hand.getLowestCard().getRank() == Rank.TEN;
		} else {
			return false;
		}
	}

	public static boolean isStraightFlush(Hand hand) {
		if (isFlush(hand) && isStraight(hand)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isFlush(Hand hand) {
		Suit suit = hand.getHighestCard().getSuit();
		for (Card card : hand) {
			if (card.getSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	public static boolean isStraight(Hand hand) {
		Card previous = null;
		for (Card card : hand) {
			if (previous != null) {
				if (!previous.isNeighbour(card)) {
					// Check the special case where the ACE is the low card
					if (card.getRank() != Rank.ACE || previous.getRank() != Rank.FIVE) {
						return false;
					}
				}
			}
			previous = card;
		}
		return true;
	}

	public static boolean isFourOfAKind(Hand hand) {
		return hand.hasQuads();
	}

	private static boolean isFullHouse(Hand hand) {
		return hand.hasTrips() && hand.hasPair();
	}

	private static boolean isThreeOfAKind(Hand hand) {
		return hand.hasTrips() && !hand.hasPair();
	}

	private static boolean isTwoPair(Hand hand) {
		Map<Rank, Integer> rankMap = getRankMap(hand);
		if (rankMap.size() == 3) {
			int pairs = 0;
			for (Entry<Rank, Integer> entry : rankMap.entrySet()) {
				if (entry.getValue().intValue() == 2) {
					pairs++;
				}
			}
			return pairs == 2;
		}
		return false;
	}

	private static boolean isOnePair(Hand hand) {
		Map<Rank, Integer> rankMap = getRankMap(hand);
		return rankMap.size() == 4;
	}

	public static Map<Rank, Integer> getRankMap(Hand hand) {
		Map<Rank, Integer> result = new HashMap<>();
		for (Card card : hand) {
			Rank rank = card.getRank();
			if (result.containsKey(rank)) {
				result.put(rank, result.get(rank).intValue() + 1);
			} else {
				result.put(rank, 1);
			}
		}
		return result;
	}
}
