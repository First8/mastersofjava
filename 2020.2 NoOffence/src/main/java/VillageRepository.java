import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VillageRepository {

    public static List<House> loadHouseLocationsInNouvion() {
        StringBuffer buildingCollection = new StringBuffer();

        buildingCollection.append("A 3 8\n");
        buildingCollection.append("B 5 6\n");
        buildingCollection.append("C 5 12\n");
        buildingCollection.append("D 6 3\n");
        buildingCollection.append("E 6 10\n");
        buildingCollection.append("F 7 7\n");
        buildingCollection.append("G 8 10\n");
        buildingCollection.append("H 9 2\n");
        buildingCollection.append("I 9 14\n");
        buildingCollection.append("J 10 5\n");
        buildingCollection.append("K 11 7\n");
        buildingCollection.append("L 11 9\n");
        buildingCollection.append("M 12 12\n");
        return House.createHouseList(buildingCollection.toString());
    }

    public static List<House> loadHouseLocationsInCastleNearbyNouvion() {
        StringBuffer buildingCollection = new StringBuffer();

        buildingCollection.append("A 21 3\n");
        buildingCollection.append("B 21 2\n");
        buildingCollection.append("C 22 1\n");
        buildingCollection.append("D 23 1\n");
        buildingCollection.append("E 24 2\n");
        buildingCollection.append("F 24 3\n");
        buildingCollection.append("G 23 4\n");
        buildingCollection.append("H 22 4\n");
        buildingCollection.append("I 22 2\n");
        buildingCollection.append("J 23 2\n");
        buildingCollection.append("K 22 3\n");
        buildingCollection.append("L 23 3\n");

        return House.createHouseList(buildingCollection.toString());
    }


    public static List<House> loadWholeAreaIndicationOfParis() {
        return Stream.of(Direction.SOUTH.getDegrees(), Direction.WEST.getDegrees(), Direction.NORTH.getDegrees(), Direction.EAST.getDegrees())
                .map(VillageRepository::getCoordinateSurParis)
                .collect(Collectors.toList());
    }
    public static House getCoordinateSurParis(int degrees) {
        House direction;
        switch (degrees) {
            case 0 : direction= new House("n", 600, 100); break;
            case 90 : direction= new House("w", 100, 100); break;
            case 180 : direction= new House("s", 100, 600); break;
            case 270 : direction= new House("e", 600, 600); break;
            default : direction= new House("n", 600, 100); break;
        };
        return direction;
    }
    public enum Direction {
        NORTH('n', 0), SOUTH('s', 180), WEST('w', 270), EAST('e', 90);

        private char character;
        private int degrees;

        private Direction(char ch, int degrees) {
            this.character = ch;
            this.degrees = degrees;
        }

        public int getDegrees() {
            return degrees;
        }

        public char getCharacter() {
            return character;
        }
    }
}
