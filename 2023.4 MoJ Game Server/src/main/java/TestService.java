import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This service manages the running of tests and administrating them.
 */
public class TestService {

    private Controller controller;
    private Map<Team, List<TestInstance>> testResults = new HashMap<>();
    private TestRunner testRunner = new TestRunner(this);

    public TestService(Controller controller) {
        this.controller = controller;
    }

    public void runTest(Team team, List<TestInstance> testInstances) {
        testResults.put(team, testInstances);
        testRunner.runTests(team, testInstances);
    }

    public void testsRan(Team team) {
            this.controller.sendMessageToTeam(
                    team,
                    "The result of the tests from team "+ team.getName() +" is:\n" +
                            getTestResultsString(testResults.get(team)));
    }

    public String getTestResultsString(List<TestInstance> testInstances) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < testInstances.size(); i++) {
            output.append("Test ")
                    .append(testInstances.get(i).getTestName())
                    .append(": ")
                    .append(testInstances.get(i).getSuccess())
                    .append('\n');
        }

        return output.toString();
    }

}
