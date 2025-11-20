import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestLowestCarbonIntensityBetweenTimepointsCalculator {
    private final CarbonIntensityZoneCalculator carbonIntensityZoneCalculator = new CarbonIntensityZoneCalculator();

    @Test
    void testGetLowestCarbonIntensityBetweenTimestamps() {
        Instant start = Instant.parse("2025-11-06T23:00:00Z");
        Instant end = Instant.parse("2025-11-07T23:00:00Z");


        ZoneId expectedZone = ZoneId.of("America/Los_Angeles");
        CarbonData result = carbonIntensityZoneCalculator.getLowestCarbonIntensityBetween(start, end, expectedZone);

        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedZone, result.carbonIntensityZone(), "The zone should be " + expectedZone);
        assertEquals(90.0, result.carbonIntensityValue(), "The lowest carbon intensity between the start and end should be 90.0.");
        assertEquals(expectedZone, result.zonedDateTime().getZone(), "The time zone in the result should match the input time zone.");
    }

    @Test
    void testGetLowestCarbonIntensityBetweenTimestamps2() {
        Instant start = Instant.parse("2025-11-07T21:00:00Z");
        Instant end = Instant.parse("2025-11-07T23:00:00Z");

        ZoneId expectedZone = ZoneId.of("Europe/Paris");
        CarbonData result = carbonIntensityZoneCalculator.getLowestCarbonIntensityBetween(start, end, expectedZone);

        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedZone, result.carbonIntensityZone(), "The zone should be " + expectedZone);
        assertEquals(100.0, result.carbonIntensityValue(), "The lowest carbon intensity between the start and end should be 100.0.");
        assertEquals(expectedZone, result.zonedDateTime().getZone(), "The time zone in the result should match the input time zone.");
    }
}