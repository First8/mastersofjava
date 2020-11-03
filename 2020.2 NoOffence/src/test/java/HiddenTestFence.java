import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * hidden tests to validate if the algorithm is implemented completely.
 */
public class HiddenTestFence {

    @Test
    public void testFenceFor_15_houses() throws IOException {
        List<House> houseLocations = loadHouseLocationsFromFile("address_list_with_15_houses.txt");
        FenceGeneratorTest.comparePointsWithExpected("A D H N M I C", houseLocations);
    }

    private List<House> loadHouseLocationsFromFile(String fileName) throws IOException {
        return House.createHouseList(new BufferedReader(new InputStreamReader(readHiddenResourceAsStream(fileName))));
    }
    private static InputStream readHiddenResourceAsStream(String name) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }
}
