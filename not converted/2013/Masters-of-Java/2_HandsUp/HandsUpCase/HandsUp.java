public interface HandsUp {
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
	public String determineWinner(Card[] table, Player player1, Player player2);
}
