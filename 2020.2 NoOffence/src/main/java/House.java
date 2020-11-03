import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class House {
    private int X;
    private int Y;
    private String id;

    public House(String name, int X, int Y) {
        this.id = name;
        this.X = X;
        this.Y = Y;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "[" + id + "(" + X + "," + Y + ")]";
    }

    public static List<House> createHouseList(String multilineString) {
        return createHouseList(new BufferedReader(new StringReader(multilineString)));
    }

    public static List<House> createHouseList(BufferedReader reader) {
        return createHouseList(reader, BufferedReader::lines);
    }

    public static List<House> createHouseList(List<String> strings) {
        return createHouseList(strings, List::stream);
    }

    public static <T> List<House> createHouseList(T stringStreamSource, Function<T, Stream<String>> stringStreamFunction) {
        return stringStreamFunction.apply(stringStreamSource).
                map(line -> line.split(" ")).
                filter(words -> words.length == 3).
                map(words -> new House(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]))).
                collect(Collectors.toList());
    }


}
