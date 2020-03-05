import java.util.Arrays;

public class HighVolumeTester {
	public static void main(String[] args) {
		int runs = 500;
		HandsUpReferenceImplementation huRef = new HandsUpReferenceImplementation();

		for (int i = 0; i < runs; i++) {
			System.out.println("====== Run " + i + "=======");
			Deck deck = new Deck();
			deck.shuffle();

			Card[] table = new Card[] { deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw() };
			System.out.println("Table: " + Arrays.toString(table));

			Player player1 = new Player("player 1", deck.draw(), deck.draw());
			System.out.println(player1);
			Player player2 = new Player("player 2", deck.draw(), deck.draw());
			System.out.println(player2);

			String winner = huRef.determineWinner(table, player1, player2);
			System.out.println("Winner: " + winner);
		}
	}
}
