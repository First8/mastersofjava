import java.util.ArrayList;
import java.util.List;

import nl.moj.model.Tester;

public class HandsUpTester implements Tester.Testable {
	private final List<HandsUpTestCase> testCases = new ArrayList<>();

	public HandsUpTester() {
		GameSituationTestCase itsATie = new GameSituationTestCase("It's a tie", new Card[] { Card.getCard(Suit.CLUBS, Rank.FIVE),
				Card.getCard(Suit.DIAMONDS, Rank.TWO), Card.getCard(Suit.CLUBS, Rank.KING), Card.getCard(Suit.DIAMONDS, Rank.NINE),
				Card.getCard(Suit.HEARTS, Rank.SEVEN) }, new Player("Admiral Ackbar", Card.getCard(Suit.CLUBS, Rank.ACE), Card.getCard(
				Suit.DIAMONDS, Rank.ACE)),
				new Player("Alan Alda", Card.getCard(Suit.HEARTS, Rank.ACE), Card.getCard(Suit.SPADES, Rank.ACE)), null);

		GameSituationTestCase itsAlmostATie = new GameSituationTestCase("It's almost a tie", new Card[] {
				Card.getCard(Suit.HEARTS, Rank.NINE), Card.getCard(Suit.CLUBS, Rank.QUEEN), Card.getCard(Suit.CLUBS, Rank.ACE),
				Card.getCard(Suit.SPADES, Rank.ACE), Card.getCard(Suit.SPADES, Rank.FOUR) }, new Player("Ken", Card.getCard(Suit.HEARTS,
				Rank.KING), Card.getCard(Suit.DIAMONDS, Rank.NINE)), new Player("Kuwait", Card.getCard(Suit.HEARTS, Rank.QUEEN),
				Card.getCard(Suit.SPADES, Rank.EIGHT)), "Kuwait");

		GameSituationTestCase fullHouse = new GameSituationTestCase("Full House", new Card[] { Card.getCard(Suit.SPADES, Rank.EIGHT),
				Card.getCard(Suit.CLUBS, Rank.EIGHT), Card.getCard(Suit.HEARTS, Rank.THREE), Card.getCard(Suit.DIAMONDS, Rank.QUEEN),
				Card.getCard(Suit.SPADES, Rank.THREE) }, new Player("Henk", Card.getCard(Suit.HEARTS, Rank.EIGHT), Card.getCard(
				Suit.HEARTS, Rank.SEVEN)),
				new Player("Ingrid", Card.getCard(Suit.HEARTS, Rank.JACK), Card.getCard(Suit.DIAMONDS, Rank.THREE)), "Henk");

		this.testCases.add(new EqualsTestCase());
		// this.testCases.add(new HashCodeTestCase());
		this.testCases.add(itsATie);
		this.testCases.add(itsAlmostATie);
		this.testCases.add(fullHouse);
	}

	@Override
	public int getTestCount() {
		return this.testCases.size();
	}

	@Override
	public String getTestName(int nr) {
		return this.testCases.get(nr).getName();
	}

	@Override
	public String getTestDescription(int nr) {
		return this.testCases.get(nr).getDescription();
	}

	@Override
	public boolean performTest(int nr) throws Throwable {
		HandsUpTestCase testCase = this.testCases.get(nr);

		HandsUpImpl implementation = new HandsUpImpl();

		return testCase.execute(implementation);
	}
}
