import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGatherer {
    private final BoardGames boardGames = new BoardGames();

    @Test
    public void testSimpleEmpty() {
        List<BoardGameEvening> mutations = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = boardGames.scoreHistory(mutations);
        assertEquals(actual, expected);
    }

    @Test
    public void testNotEmpty() {
        List<BoardGameEvening> evenings = List.of(new BoardGameEvening(LocalDate.of(2024,1,1), 2),
                new BoardGameEvening(LocalDate.of(2024,2,1), 2),
                new BoardGameEvening(LocalDate.of(2024,3,1), 2),
                new BoardGameEvening(LocalDate.of(2024,4,1), 2),
                new BoardGameEvening(LocalDate.of(2024,5,1), 2));
        List<Integer> expected = List.of(2, 4, 6, 8, 10);
        List<Integer> actual = boardGames.scoreHistory(evenings);
        assertEquals(actual, expected);
    }

    @Test
    public void testRandomNumbers() {
        List<BoardGameEvening> evenings = List.of(new BoardGameEvening(LocalDate.of(2024,1,1), 2),
                new BoardGameEvening(LocalDate.of(2024,2,1), 2),
                new BoardGameEvening(LocalDate.of(2024,3,1), 7),
                new BoardGameEvening(LocalDate.of(2024,4,1), 2));
        List<Integer> expected = List.of(2, 4, 11, 13);
        List<Integer> actual = boardGames.scoreHistory(evenings);
        assertEquals(actual, expected);
    }

}
