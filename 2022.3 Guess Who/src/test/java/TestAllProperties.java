import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.partitioningBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAllProperties {

    private GameService gameService = new GameService();

    @Test
    public void testEveryone() {
        var testPersons = asList(Persons.ALEX, Persons.ALFRED, Persons.ANITA, Persons.ANNE, Persons.BERNARD, Persons.BILL, Persons.CHARLES, Persons.CLAIRE,
                Persons.DAVID, Persons.ERIC, Persons.FRANS, Persons.GEORGE, Persons.HERMAN, Persons.JOE, Persons.MARIA, Persons.MAX,
                Persons.PAUL, Persons.PETER, Persons.PHILIP, Persons.RICHARD, Persons.ROBERT, Persons.SAM, Persons.SUSAN, Persons.TOM);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 5));
    }

    @Test
    public void testNoBlackHair() {
        var testPersons = asList(Persons.ALFRED, Persons.ANITA, Persons.BERNARD, Persons.BILL, Persons.CHARLES, Persons.CLAIRE,
                Persons.DAVID, Persons.ERIC, Persons.FRANS, Persons.GEORGE, Persons.HERMAN, Persons.JOE, Persons.MARIA,
                Persons.PAUL, Persons.PETER, Persons.RICHARD, Persons.ROBERT, Persons.SAM, Persons.SUSAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 5));
    }

    @Test
    public void testAndNoOrangeHair() {
        var testPersons = asList(Persons.ANITA, Persons.BERNARD, Persons.CHARLES,
                Persons.DAVID, Persons.ERIC, Persons.GEORGE, Persons.JOE, Persons.MARIA,
                Persons.PAUL, Persons.PETER, Persons.RICHARD, Persons.ROBERT, Persons.SAM, Persons.SUSAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 5));
    }

    @Test
    public void testAndNoYellowHair() {
        var testPersons = asList(Persons.BERNARD,
                Persons.GEORGE, Persons.MARIA,
                Persons.PAUL, Persons.PETER, Persons.RICHARD, Persons.ROBERT, Persons.SAM, Persons.SUSAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 5));
    }

    @Test
    public void testOnlyWhiteHair() {
        var testPersons = asList(Persons.GEORGE, Persons.PAUL, Persons.PETER, Persons.SAM, Persons.SUSAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 3));
    }

    @Test
    public void testWhiteHairNoGlasses() {
        var testPersons = asList(Persons.GEORGE, Persons.PETER, Persons.SUSAN);

        Question bestQuestion = gameService.determineBestQuestion(testPersons);
        System.out.println("Determined best question: '" + bestQuestion + "'");

        Map<Boolean, List<Person>> appliedQuestion = testPersons.stream().collect(partitioningBy(bestQuestion));
        System.out.println("Question applied to test persons: " + appliedQuestion);
        assertTrue(appliedQuestion.values().stream().anyMatch(list -> list.size() == 2));
    }
}
