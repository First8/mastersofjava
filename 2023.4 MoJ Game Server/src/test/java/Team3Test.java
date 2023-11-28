import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Team3Test {


    @Test
    public void play() {
        Controller controller = new Controller();

        String teamName = "Jaavaa";
        controller.receiveMessageFromTeam(new Team(2L, teamName, 2), List.of(
                new TestInstance("AssignmentTest", () -> true),
                new TestInstance("SanityCheck", () -> false))
        );

        assertTrue(controller.latestMessagePerTeam.get(teamName).contains("The result of the tests from team Jaavaa is:\n" +
                "Test AssignmentTest: true\n" +
                "Test SanityCheck: false\n"));
    }

}
