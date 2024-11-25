import org.junit.Test;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Collections.emptySet;
import static org.junit.Assert.assertEquals;

public class TestPrioritizedTaskListGenerator {

    private final PrioritizedTaskListGenerator generator = new PrioritizedTaskListGenerator();

    /**
     * Test the case where no cows have any tasks.
     */
    @Test
    public void testNoTasks() {
        Cow cow1 = new Cow("Annie1", false, false, false);
        Cow cow2 = new Cow("Bertha2", false, false, false);

        var tasks = generator.generate(List.of(cow1, cow2));

        assertEquals(emptySet(), tasks);
    }

    /**
     * Test if the tasks are sorted correctly.
     */
    @Test
    public void testTaskSorting() {
        Cow cow1 = new Cow("Annie1", true, false, false);
        Cow cow2 = new Cow("Bertha2", false, false, true);
        Cow cow3 = new Cow("Carla3", false, true, false);

        var tasks = generator.generate(List.of(cow1, cow2, cow3));

        var iterate = tasks.iterator();
        assertEquals(iterate.next(), new Task<>(cow2, "Check how Bertha2 is doing", 10, null, false));
        assertEquals(iterate.next(), new Task<>(cow1, "Check how Annie1 is doing", 7, now().plusDays(3), false));
        assertEquals(iterate.next(), new Task<>(cow3, "Check how Carla3 is doing", 5, null, false));
    }

    /**
     *  What happens here!? Why are there only 5 tasks instead of 6?
     */
    @Test
    public void testMoreTasks() {
        Cow cow1 = new Cow("Annie1", true, false, false);
        Cow cow2 = new Cow("Bertha2", true, false, true);
        Cow cow3 = new Cow("Carla3", false, true, false);
        Cow cow4 = new Cow("Dory4", false, true, true);
        Cow cow5 = new Cow("Evelien5", false, false, false);
        Cow cow6 = new Cow("Fiona6", false, false, true);
        Cow cow7 = new Cow("Gertie7", true, false, false);

        var tasks = generator.generate(List.of(cow1, cow2, cow3, cow4, cow5, cow6, cow7));

        // Evelien5 is doing fine, does not have any tasks. So we are left with 6 tasks.
        assertEquals(6, tasks.size());

        assertEquals(tasks.first(), new Task<>(cow2, "Check how Bertha2 is doing", 17, now().plusDays(3), false));
        assertEquals(tasks.last(), new Task<>(cow3, "Check how Carla3 is doing", 5, null, false));
    }
}
