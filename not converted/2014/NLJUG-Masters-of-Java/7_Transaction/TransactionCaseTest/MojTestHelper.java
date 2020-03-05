import java.util.ArrayList;
import java.util.List;

public class MojTestHelper {
	public static List<MojTest> buildListOfTests() {
		List<MojTest> result = new ArrayList<MojTest>();
		result.add(new FieldsCase());
		result.add(new ConstructorsCase());
		result.add(new GettersCase());
		result.add(new SettersCase());
		result.add(new EqualsCase());
		result.add(new HashCodeCase());
		result.add(new ToStringCase());
		result.add(new CompareToCase());
		result.add(new ImmutabilityCase());
		return result;
	}
}
