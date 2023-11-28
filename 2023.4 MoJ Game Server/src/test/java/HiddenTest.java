import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HiddenTest {

    @Test
    public void play() {
        Controller controller = new Controller();

        controller.receiveMessageFromTeam(new Team(21324L, "First8", 2), List.of(
                new TestInstance("DifferentTest", () -> true),
                new TestInstance("HiddenTest", () -> true))
        );

        assertEquals(controller.latestMessagePerTeam.get("First8"), "The result of the tests from team First8 is:\n" +
                "Test DifferentTest: true\n" +
                "Test HiddenTest: true\n");
    }
}
