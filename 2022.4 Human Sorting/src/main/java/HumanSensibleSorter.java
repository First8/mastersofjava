import java.util.Comparator;

public class HumanSensibleSorter implements Comparator<String> {

	public int compare(String s1, String s2) {
		// TODO: implement the human-sensible sorter here
		return s1.compareTo(s2);
	}

}
