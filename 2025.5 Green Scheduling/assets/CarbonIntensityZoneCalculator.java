import java.time.Instant;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

public class CarbonIntensityZoneCalculator {
    private final List<CarbonData> carbonIntensityData = CarbonIntensityDataFetcher.fetchData();

    public CarbonData getZoneWithLowestCarbonIntensityAt(Instant timestamp) {
        return carbonIntensityData.stream()
                .filter(data -> data.zonedDateTime().toInstant().equals(timestamp))
                .min(Comparator.comparingDouble(CarbonData::carbonIntensityValue))
                .orElse(null);
    }

    public CarbonData getLowestCarbonIntensityBetween(Instant start, Instant end, ZoneId expectedZoneId) {
        return carbonIntensityData.stream()
                .filter(data -> !data.zonedDateTime().toInstant().isBefore(start) && !data.zonedDateTime().toInstant().isAfter(end))
                .filter(carbonData -> carbonData.carbonIntensityZone().equals(expectedZoneId))
                .min(Comparator.comparing(CarbonData::carbonIntensityValue))
                .orElse(null);
    }
}
