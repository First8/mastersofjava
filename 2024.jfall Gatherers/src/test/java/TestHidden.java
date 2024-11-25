import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHidden {
    private final BoardGames boardGames = new BoardGames();

    @Test
    public void testRandomNumbers() {
        List<BoardGameEvening> evenings = List.of(new BoardGameEvening(LocalDate.of(2024,1,1), 2),
                new BoardGameEvening(LocalDate.of(2024,3,1), 5),
                new BoardGameEvening(LocalDate.of(2024,4,1), 1));
        List<Integer> expected = List.of(2, 7, 8);
        List<Integer> actual = boardGames.scoreHistory(evenings);
        assertEquals(expected, actual);
    }

}
