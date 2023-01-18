import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEnumProperties {

    private GameService gameService = new GameService();

    @Test
    public void testAllWithOnlyMustache() {
        var testPersons = asList(Persons.ALEX, Persons.ALFRED, Persons.CHARLES, Persons.MAX);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        appliedQuestion.values().forEach(list -> assertEquals(2, list.size()));
    }

    @Test
    public void testAllWithOnlyBeard() {
        var testPersons = asList(Persons.BILL, Persons.DAVID, Persons.PHILIP);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 2));
    }
}
