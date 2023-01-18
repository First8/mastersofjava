import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Hidden tests so the participants can't hardcode the results.
 */
public class HiddenTest  {

    private GameService gameService = new GameService();

    @Test
    public void testBooleanYellowHairBrownEyes() {
        var testPersons = asList(Persons.CHARLES, Persons.DAVID, Persons.ERIC, Persons.JOE);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 3));
    }

    @Test
    public void testNoMustache() {
        var testPersons = asList(Persons.ANITA, Persons.ANNE, Persons.BERNARD, Persons.BILL, Persons.CLAIRE,
                Persons.DAVID, Persons.ERIC, Persons.FRANS, Persons.GEORGE, Persons.HERMAN, Persons.JOE, Persons.MARIA,
                Persons.PAUL, Persons.PETER, Persons.PHILIP, Persons.ROBERT, Persons.SAM, Persons.SUSAN, Persons.TOM);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 5));
    }

    @Test
    public void testSplitEqual() {
        var testPersons = asList(Persons.ALEX, Persons.ANNE, Persons.ALFRED, Persons.BILL);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        appliedQuestion.values().forEach(list -> assertEquals(2, list.size()));
    }
}
