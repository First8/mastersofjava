public class EqualsTestCase extends AbstractTestCase {
	protected EqualsTestCase() {
		super("equals()");
	}

	@Override
	public String getDescription() {
		return "Tests the equals contract of the Card class";
	}

	@Override
	public boolean execute(HandsUpImpl implementation) {
		// The set of equal cards
		Card x = Card.getCard(Suit.HEARTS, Rank.QUEEN);
		Card y = Card.getCard(Suit.HEARTS, Rank.QUEEN);
		Card z = Card.getCard(Suit.HEARTS, Rank.QUEEN);

		// The set of non-equal cards
		Card a = Card.getCard(Suit.DIAMONDS, Rank.KING);
		Card b = Card.getCard(Suit.CLUBS, Rank.TWO);

		boolean result = true;

		// Test reflexive
		System.out.println("Is it reflexive? [x.equals(x) == true]");
		if (x.equals(x)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
			return false;
		}

		// Test symmetric
		System.out.println("Is it symmetric? [When x.equals(y), then y.equals(x)]");
		if ((x.equals(y) == y.equals(x)) && (a.equals(b) == b.equals(a))) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
			result = false;
		}

		// Test transitive
		System.out.println("Is it transitive? [When x.equals(y) and then y.equals(z), then x.equals(z)]");
		if (x.equals(y) && y.equals(z) && x.equals(z)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
			result = false;
		}

		// Test consistent
		System.out.println("Is it consistent? [Multiple invocation of x.equals(y) return the same result]");
		boolean resultXY = x.equals(y);
		boolean resultAB = a.equals(b);
		for (int i = 0; i < 10; i++) {
			if (resultXY != x.equals(y) || resultAB != a.equals(b)) {
				System.out.println("No");
				result = false;
			}
		}
		System.out.println("Yes");

		// Test null
		System.out.println("Does it return false when passing null?");
		try {
			if (x.equals(null)) {
				System.out.println("No");
				result = false;
			} else {
				System.out.println("Yes");
			}
		} catch (NullPointerException npe) {
			System.out.println("No");
			result = false;
		}

		// Test if it does not
		System.out.println("Does it correctly reject non-equal Cards?");
		Deck deck = new Deck();
		Card reference = deck.draw();
		while (!deck.isEmpty()) {
			Card other = deck.draw();
			if (reference.equals(other)) {
				result = false;
				System.out.println("No, it says " + reference + " and " + other + "are equal, while they are not.");
				break;
			}
		}

		return result;
	}
}
