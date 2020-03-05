import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Family {
	private List<Person> persons;

	public Family(List<Person> persons) {
		this.persons = new ArrayList<>(persons);
	}

	public LocalDate getDateWhenFamilyIs(long totalYearsOld) {
		// ...
		return null;
	}
}
