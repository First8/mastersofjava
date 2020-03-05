import java.util.Arrays;

/**
 * Represents a poker player.
 */
public class Player {
	private final String name;
	private final Card[] pocket;

	public Player(String name, Card firstCard, Card secondCard) {
		this.name = name;
		this.pocket = new Card[] { firstCard, secondCard };
	}

	public String getName() {
		return this.name;
	}

	public Card[] getPocket() {
		return this.pocket;
	}

	@Override
	public String toString() {
		return String.format("%s: %s", this.name, Arrays.toString(this.pocket));
	}
}
