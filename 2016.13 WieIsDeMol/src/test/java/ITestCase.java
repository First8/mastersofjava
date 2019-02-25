import java.util.Map;
import java.util.Set;

public interface ITestCase {
	abstract public String getTestCaseName();
	abstract public String getTestCaseDescription();
	abstract public Set<Order> getOrders();
	abstract public Map<Contestant, Route> getLog();
	
	abstract public boolean validateAwnser(Contestant contestant);
}
