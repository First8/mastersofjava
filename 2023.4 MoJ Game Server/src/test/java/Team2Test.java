import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Team2Test {


    @Test
    public void play() {
        Controller controller = new Controller();

        String teamName = "Go hard or go home";
        controller.receiveMessageFromTeam(new Team(2L, teamName, 2), List.of(
                new TestInstance("AssignmentTest", () -> false),
                new TestInstance("SanityCheck", () -> false))
        );

        assertTrue(controller.latestMessagePerTeam.get(teamName).contains("The result of the tests from team Go hard or go home is:\n" +
                "Test AssignmentTest: false\n" +
                "Test SanityCheck: false\n"));
    }

}
