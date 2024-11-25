import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HiddenTest {
    private final PrioritizedTaskListGenerator generator = new PrioritizedTaskListGenerator();

    @Test
    public void testTwoInHeatTasks() {
        Cow cow1 = new Cow("Annie1", true, false, false);
        Cow cow2 = new Cow("Bertha2", true, false, false);

        var tasks = generator.generate(List.of(cow1, cow2));

        var expectedTasks = Set.of(
                new Task<>(cow1, "Check how Annie1 is doing", 7, now().plusDays(3), false),
                new Task<>(cow2, "Check how Bertha2 is doing", 7, now().plusDays(3), false)
        );

        assertEquals(expectedTasks, tasks);
    }

    @Test
    public void testTwoPregnantIllTasks() {
        Cow cow1 = new Cow("Annie1", false, true, true);
        Cow cow2 = new Cow("Bertha2", false, true, true);

        var tasks = generator.generate(List.of(cow1, cow2));

        var expectedTasks = Set.of(
                new Task<>(cow1, "Check how Annie1 is doing", 15, null, false),
                new Task<>(cow2, "Check how Bertha2 is doing", 15, null, false)
        );

        assertEquals(expectedTasks, tasks);
    }
}
