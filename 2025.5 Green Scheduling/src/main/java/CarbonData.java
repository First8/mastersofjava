import java.time.ZoneId;
import java.time.ZonedDateTime;

public record CarbonData(
        ZonedDateTime zonedDateTime,
        double carbonIntensityValue,
        ZoneId carbonIntensityZone
) {
}
