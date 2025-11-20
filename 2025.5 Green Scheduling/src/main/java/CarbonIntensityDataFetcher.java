import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarbonIntensityDataFetcher {

    private CarbonIntensityDataFetcher() {
    }

    public static List<CarbonData> fetchData() {
        List<CarbonData> carbonIntensityData = new ArrayList<>();

        for (int i = 6; i <= 8; i++) { // 6-8 november
            String date = String.format("2025-11-%02d", i); // Adjust date based on i
            for (int hour = 0; hour < 24; hour++) {
                // Generate data for Europe/Paris, America/New_York, etc.
                carbonIntensityData.add(new CarbonData(
                        ZonedDateTime.parse(String.format("%sT%02d:00:00%+03d:00[Europe/Paris]", date, hour, 1)),
                        100 + (hour % 5) * 5.0,
                        ZoneId.of("Europe/Paris")
                ));

                carbonIntensityData.add(new CarbonData(
                        ZonedDateTime.parse(String.format("%sT%02d:00:00%+03d:00[America/New_York]", date, hour, -5)),
                        110 + (hour % 6) * 4.5,
                        ZoneId.of("America/New_York")
                ));

                carbonIntensityData.add(new CarbonData(
                        ZonedDateTime.parse(String.format("%sT%02d:00:00%+03d:00[America/Los_Angeles]", date, hour, -8)),
                        90 + (hour % 4) * 6.5,
                        ZoneId.of("America/Los_Angeles")
                ));

                carbonIntensityData.add(new CarbonData(
                        ZonedDateTime.parse(String.format("%sT%02d:00:00%+03d:00[Africa/Johannesburg]", date, hour, 2)),
                        105 + (hour % 7) * 3.5,
                        ZoneId.of("Africa/Johannesburg")
                ));

                carbonIntensityData.add(new CarbonData(
                        ZonedDateTime.parse(String.format("%sT%02d:00:00%+03d:00[Asia/Dubai]", date, hour, 4)),
                        115 + (hour % 5) * 4.0,
                        ZoneId.of("Asia/Dubai")
                ));
            }
        }

        return carbonIntensityData;
    }
}