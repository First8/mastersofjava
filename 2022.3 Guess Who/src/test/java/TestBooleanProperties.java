import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBooleanProperties {

    private GameService gameService = new GameService();

    @Test
    public void testBlackHairBrownEyes() {
        var testPersons = asList(Persons.ALEX, Persons.ANNE, Persons.MAX, Persons.PHILIP);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        appliedQuestion.values().forEach(list -> assertEquals(2, list.size()));
    }

    @Test
    public void testOrangeHairBrownEyes() {
        var testPersons = asList(Persons.BILL, Persons.CLAIRE, Persons.FRANS, Persons.HERMAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 3));
    }
}
