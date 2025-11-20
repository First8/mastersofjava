import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

public class CarbonIntensityZoneCalculator {
    private final List<CarbonData> carbonIntensityData = CarbonIntensityDataFetcher.fetchData();

    public CarbonData getZoneWithLowestCarbonIntensityAt(Instant timestamp) {
        // Calculate the "greenest" carbonIntensityZone to run the job
        return null;
    }

    public CarbonData getLowestCarbonIntensityBetween(Instant start, Instant end, ZoneId expectedZoneId) {
        // Calculate the "greenest" moment and carbonIntensityZone to run the job
        return null;
    }
}
