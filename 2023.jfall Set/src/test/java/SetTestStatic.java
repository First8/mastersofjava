import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetTestStatic {

	@Test
    public void play() {
        // Arrange
    Set<Card> cardsOnTheTable = new HashSet<>();
        cardsOnTheTable.addAll(Set.of(
                new Card(
                        2,
                        Card.Shape.OVAL,
                        Card.Shading.OPEN,
                        Card.Color.RED
                ),
                new Card(
                        1,
                        Card.Shape.DIAMOND,
                        Card.Shading.OPEN,
                        Card.Color.GREEN
                ),
                new Card(
                        2,
                        Card.Shape.OVAL,
                        Card.Shading.STRIPED,
                        Card.Color.GREEN
                ),
                new Card(
                        1,
                        Card.Shape.SQUIGGLE,
                        Card.Shading.STRIPED,
                        Card.Color.GREEN
                ),
                new Card(
                        2,
                        Card.Shape.DIAMOND,
                        Card.Shading.SOLID,
                        Card.Color.GREEN
                ),
                new Card(
                        1,
                        Card.Shape.SQUIGGLE,
                        Card.Shading.SOLID,
                        Card.Color.PURPLE
                ),
                new Card(
                        1,
                        Card.Shape.DIAMOND,
                        Card.Shading.OPEN,
                        Card.Color.RED
                ),
                new Card(
                        1,
                        Card.Shape.DIAMOND,
                        Card.Shading.SOLID,
                        Card.Color.RED
                ),
                new Card(
                        2,
                        Card.Shape.SQUIGGLE,
                        Card.Shading.OPEN,
                        Card.Color.RED
                ),
                new Card(
                        3,
                        Card.Shape.SQUIGGLE,
                        Card.Shading.OPEN,
                        Card.Color.RED
                ),
                new Card(
                        1,
                        Card.Shape.SQUIGGLE,
                        Card.Shading.OPEN,
                        Card.Color.PURPLE
                ),
                new Card(
                        3,
                        Card.Shape.OVAL,
                        Card.Shading.SOLID,
                        Card.Color.GREEN
                ))
        );
            
            
        cardsOnTheTable = Collections.unmodifiableSet(cardsOnTheTable);
        System.out.println("Cards on the table: \n" + formattedTable(cardsOnTheTable));

        // Act
        Set<Card> setFoundByPlayer = SetFinder.findSet(cardsOnTheTable);
        System.out.println("Set found by player: \n" + formattedTable(setFoundByPlayer));

        // Assert
        assertEquals(3, setFoundByPlayer.size());
        assertTrue(cardsOnTheTable.containsAll(setFoundByPlayer));
        assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::count).toList());
        assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::shape).toList());
        assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::shading).toList());
        assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::color).toList());
    }

    private static <T> void assertEqualsOrDifferent(List<T> properties) {
        assertTrue(
                (properties.get(0).equals(properties.get(1)) && properties.get(1).equals(properties.get(2)))
                        || (!properties.get(0).equals(properties.get(1)) && !properties.get(1).equals(properties.get(2)) && !properties.get(0).equals(properties.get(2)))
        );
    }

    public static String formattedTable(Set<Card> cards) {
        List<Card> list = cards.stream().toList();
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < (list.size() / 4) + 1; i++) {
            output.append(getLine(i, list));
        }

        return output.toString();
    }

    private static String getLine(int lineNumber, List<Card> list) {
        StringBuilder line = new StringBuilder();
        int maxIndex = lineNumber * 4 + 4;
        if (lineNumber * 4 == list.size()) {
            return "";
        }

        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("┌────────┐");
        }
        line.append("\n");
        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("|").append(list.get(i).count() + "       ").append("|");
        }
        line.append("\n");
        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("|").append(list.get(i).shape().name() + " ".repeat(8 - list.get(i).shape().name().length())).append("|");
        }
        line.append("\n");
        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("|").append(list.get(i).shading().name() + " ".repeat(8 - list.get(i).shading().name().length())).append("|");
        }
        line.append("\n");
        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("|").append(list.get(i).color().name() + " ".repeat(8 - list.get(i).color().name().length())).append("|");
        }
        line.append("\n");
        for (int i = lineNumber * 4; i < Math.min(list.size(), maxIndex); i++) {
            line.append("└────────┘");
        }
        line.append("\n");

        return line.toString();
    }

}
