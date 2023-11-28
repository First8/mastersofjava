import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private TestService server = new TestService(this);
    Map<String, String> latestMessagePerTeam = new HashMap<>();

    public void receiveMessageFromTeam(Team team, List<TestInstance> testInstances) {
        server.runTest(team, testInstances);
    }

    public void sendMessageToTeam(Team team, String message) {
        // Code that sends message to the team
        latestMessagePerTeam.put(team.getName(), message);
    }
}
