import java.util.List;
import java.util.Objects;
import java.util.Set;

public record Card(
        int count,
        Shape shape,
        Shading shading,
        Color color
) {
    public boolean equals(Object object) {
        Card card = (Card) object;
        return count == card.count && shape == card.shape && shading == card.shading && color == card.color;
    }

    public int hashCode() {
        return Objects.hash(count, shape, shading, color);
    }

    enum Shape {
        DIAMOND, SQUIGGLE, OVAL
    }

    enum Shading {
        SOLID, STRIPED, OPEN
    }

    enum Color {
        RED, GREEN, PURPLE
    }

}


