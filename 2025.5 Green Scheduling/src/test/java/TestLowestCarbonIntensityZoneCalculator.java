import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestLowestCarbonIntensityZoneCalculator {

    private final CarbonIntensityZoneCalculator carbonIntensityZoneCalculator = new CarbonIntensityZoneCalculator();

    @Test
    void testGetZoneWithLowestCarbonIntensityAtTimestamp() {
        Instant testTime = Instant.parse("2025-11-07T10:00:00+01:00");

        CarbonData result = carbonIntensityZoneCalculator.getZoneWithLowestCarbonIntensityAt(testTime);

        ZoneId expectedZone = ZoneId.of("America/Los_Angeles");
        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedZone, result.carbonIntensityZone(), "The zone should be " + expectedZone);
        assertEquals(96.5, result.carbonIntensityValue(), "The carbon intensity value at 10:00 in Paris should be 96.5.");
        assertEquals(expectedZone, result.zonedDateTime().getZone(), "The time zone in the result should match the input time zone.");
    }
}