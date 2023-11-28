import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CorrectSolution {

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
