import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Gatherers;

@SuppressWarnings("preview")
public class BoardGames {

    /**
     * Suppose there are multiple board game evenings and for every evening you can have a score. Instead of only knowing
     * the total score over several evenings, you want to know the progress. Using the new scan function calculate a List
     * containing the total score with all cumulative values.
     *
     * @param evenings board game evenings with gained score
     * @return cumulative total score
     */
    public List<Integer> scoreHistory(List<BoardGameEvening> evenings) {
        return evenings.stream()
                .sorted(Comparator.comparing(BoardGameEvening::evening))
                .gather(Gatherers.scan(() -> 0, scanner()))
                .toList();
    }

    private BiFunction<Integer, BoardGameEvening, Integer> scanner() {
        // TODO complete this function
        return null;
    }

}
