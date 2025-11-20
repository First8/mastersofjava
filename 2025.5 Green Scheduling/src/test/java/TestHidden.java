import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestHidden {

    private final CarbonIntensityZoneCalculator carbonIntensityZoneCalculator = new CarbonIntensityZoneCalculator();

    @Test
    void testGetLowestCarbonIntensityBetweenTimestamps() {
        ZonedDateTime start = ZonedDateTime.parse("2025-11-06T10:00:00+01:00[Europe/Paris]");
        ZonedDateTime end = ZonedDateTime.parse("2025-11-06T23:00:00+00:00[Europe/Paris]");

        ZoneId expectedZone = ZoneId.of("Africa/Johannesburg");
        CarbonData result = carbonIntensityZoneCalculator.getLowestCarbonIntensityBetween(start.toInstant(), end.toInstant(), expectedZone);

        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedZone, result.carbonIntensityZone(), "The zone should be " + expectedZone);
        assertEquals(105.0, result.carbonIntensityValue(), "The lowest carbon intensity between the start and end should be 105.0.");
        assertEquals(expectedZone, result.zonedDateTime().getZone(), "The time zone in the result should match the input time zone.");
    }
}