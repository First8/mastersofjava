import java.util.*;

import static java.util.stream.Collectors.toCollection;

/**
 * This class generates a prioritized list of tasks for a list of cows.
 */
public class PrioritizedTaskListGenerator {
    public SortedSet<Task<Cow>> generate(List<Cow> cows) {
        TaskFactory<Cow> factory = new CowTaskFactory();
        return generate(cows, factory);
    }

    public  SortedSet<Task<Cow>> generate(List<Cow> subjectList, TaskFactory<Cow> factory) {
        Comparator<Task<Cow>> scoreComparator = (a, b) -> factory.score(b.subject()) - factory.score(a.subject());
        return subjectList.stream()
                .map(factory::createTask)
                .filter(factory.getAllBusinessRules())
                .collect(toCollection(()-> new TreeSet<>(scoreComparator.thenComparing( t -> t.subject().name()))));
    }
}
