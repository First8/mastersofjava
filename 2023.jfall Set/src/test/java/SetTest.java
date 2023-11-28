import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetTest {
    @Test
    public void play() {
        // Arrange
        Set<Card> cardsOnTheTable = new HashSet<>();
        while (cardsOnTheTable.size() < 12) {
            cardsOnTheTable.add(
                    new Card(
                            either(1,2,3),
                            either(Card.Shape.values()),
                            either(Card.Shading.values()),
                            either(Card.Color.values())
                    )
            );
        }
        cardsOnTheTable = Collections.unmodifiableSet(cardsOnTheTable);
        System.out.println("Cards on the table: \n" + formattedTable(cardsOnTheTable));

        // Act
        Set<Card> setFoundByPlayer = SetFinder.findSet(cardsOnTheTable);
        System.out.println("Set found by player: \n" + formattedTable(setFoundByPlayer));

        // Assert
        if (findSet(cardsOnTheTable).isEmpty()) {
            assertEquals(0, setFoundByPlayer.size());
        } else {
            assertEquals(3, setFoundByPlayer.size());
            assertTrue(cardsOnTheTable.containsAll(setFoundByPlayer));
            assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::count).toList());
            assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::shape).toList());
            assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::shading).toList());
            assertEqualsOrDifferent(setFoundByPlayer.stream().map(Card::color).toList());
        }

    }

    private static <T> void assertEqualsOrDifferent(List<T> properties) {
        assertTrue(
                (properties.get(0).equals(properties.get(1)) && properties.get(1).equals(properties.get(2)))
                || (!properties.get(0).equals(properties.get(1)) && !properties.get(1).equals(properties.get(2)) && !properties.get(0).equals(properties.get(2)))
        );
    }

    @SafeVarargs
    private static <T> T either(T... options) {
        return options[(int) (Math.random() * options.length)];
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
    
    public static Set<Card> findSet(Set<Card> cardsOnTheTable) {
        for (Card card1 : cardsOnTheTable) {
            for (Card card2 : cardsOnTheTable) {
                for (Card card3 : cardsOnTheTable) {
                    if (card1 == card2 || card2 == card3 || card1 == card3) {
                        continue;
                    }
                    if (equalsOrDifferent(List.of(card1.count(), card2.count(), card3.count()))
                            && equalsOrDifferent(List.of(card1.shading(), card2.shading(), card3.shading()))
                            && equalsOrDifferent(List.of(card1.shape(), card2.shape(), card3.shape()))
                            && equalsOrDifferent(List.of(card1.color(), card2.color(), card3.color()))
                    ) {
                        return Set.of(card1, card2, card3);
                    }

                }
            }
        }
        return Collections.emptySet();
    }

    private static <T> boolean equalsOrDifferent(List<T> properties) {
        return (properties.get(0).equals(properties.get(1)) && properties.get(1).equals(properties.get(2)))
                || (!properties.get(0).equals(properties.get(1)) && !properties.get(1).equals(properties.get(2)) && !properties.get(0).equals(properties.get(2)));
    }
    
}
