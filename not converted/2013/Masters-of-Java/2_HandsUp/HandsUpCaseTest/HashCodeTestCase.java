public class HashCodeTestCase extends AbstractTestCase {
	protected HashCodeTestCase() {
		super("hashCode()");
	}

	@Override
	public String getDescription() {
		return "Tests the hashCode contract of the Card class";
	}

	@Override
	public boolean execute(HandsUpImpl implementation) {
		Card x = Card.getCard(Suit.HEARTS, Rank.QUEEN);
		Card y = Card.getCard(Suit.HEARTS, Rank.QUEEN);
		Card a = Card.getCard(Suit.CLUBS, Rank.SEVEN);

		// Test Consistency
		System.out.println("Is it consistent? [Multiple invocations of x.hashCode() return the same result]");
		int resultX = x.hashCode();
		int resultA = a.hashCode();
		for (int i = 0; i < 10; i++) {
			if (resultX != x.hashCode() || resultA != a.hashCode()) {
				System.out.println("No");
				return false;
			}
		}
		System.out.println("Yes");

		// Test equals
		System.out.println("Is it consistent with equals? [When x.equals(y), then x.hashCode() == y.hashCode()]");
		if (x.hashCode() == y.hashCode()) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
			return false;
		}

		return true;
	}
}
