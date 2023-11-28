import java.util.List;

public class TestRunner {
    private final TestService server;
    public TestRunner(TestService server) {
        this.server = server;
    }

    public void runTests(Team team, List<TestInstance> testInstances) {
        for (int i = 0; i < testInstances.size(); i++) {
            testInstances.get(i).run();
            team.getTestResults()[i] = testInstances.get(i).getSuccess();
        }
        notifyServerThatTestsHaveBeenRan(team);
    }

    private void notifyServerThatTestsHaveBeenRan(Team team) {
        server.testsRan(team);
    }
}
