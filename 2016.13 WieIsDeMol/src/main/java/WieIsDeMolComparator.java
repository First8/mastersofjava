import java.util.Comparator;

/**
 * Comparator based on the mole rating of each contestant.
 */
public class WieIsDeMolComparator implements Comparator<Contestant> {
	@Override
	public int compare(Contestant aContestant, Contestant anotherContestant) {
		return Double.compare(anotherContestant.getMoleRating(), aContestant.getMoleRating());
	}
}