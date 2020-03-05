public enum Combination {
	/**
	 * Five cards, not meeting any of the other requirements to create a combination.
	 */
	HIGH_CARD,

	/**
	 * Two cards of the same rank (plus three unrelated cards).
	 */
	ONE_PAIR,

	/**
	 * Two cards of the same rank, plus two cards of another rank (plus one unrelated card).
	 */
	TWO_PAIR,

	/**
	 * Three cards of the same rank (plus two unrelated cards).
	 */
	THREE_OF_A_KIND,

	/**
	 * Five cards in sequence. The ace can either count as the card before the 2 or the card after the king.
	 */
	STRAIGHT,

	/**
	 * Five cards of the same suit.
	 */
	FLUSH,

	/**
	 * Three matching cards of one rank and two matching cards of another rank.
	 */
	FULL_HOUSE,

	/**
	 * All four cards of the same rank (plus one unrelated card).
	 */
	FOUR_OF_A_KIND,

	/**
	 * Five cards in sequence, all of the same suit.
	 */
	STRAIGHT_FLUSH,

	/**
	 * Five cards in sequence, all of the same suit, with an ace as highest card.
	 */
	ROYAL_FLUSH;
}
