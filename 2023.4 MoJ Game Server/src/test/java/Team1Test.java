import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Team1Test {


    @Test
    public void play() {
        Controller controller = new Controller();

        String teamName = "The terminators";
        controller.receiveMessageFromTeam(new Team(2L, teamName, 2), List.of(
                new TestInstance("AssignmentTest", () -> true),
                new TestInstance("SanityCheck", () -> true))
        );

        assertEquals(controller.latestMessagePerTeam.get(teamName), "The result of the tests from team The terminators is:\n" +
                "Test AssignmentTest: true\n" +
                "Test SanityCheck: true\n");
    }

}
