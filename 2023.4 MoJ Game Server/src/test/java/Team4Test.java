import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Team4Test {


    @Test
    public void play() {
        Controller controller = new Controller();

        String teamName = "Humble Stumble";
        controller.receiveMessageFromTeam(new Team(2L, teamName, 2), List.of(
                new TestInstance("AssignmentTest", () -> false),
                new TestInstance("SanityCheck", () -> true))
        );

        assertTrue(controller.latestMessagePerTeam.get(teamName).contains("The result of the tests from team Humble Stumble is:\n" +
                "Test AssignmentTest: false\n" +
                "Test SanityCheck: true\n"));
    }

}
