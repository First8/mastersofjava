import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestFormatting {

	@Test
	public void testSimple() {

		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("FR8559", airports.get("SXF"), "28-02-2019 23:55", airports.get("DUB"), "01-03-2019 03:25")				);

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);

		List<String> expected = List.of("FR8559 SXF->DUB 04:30h");

		assertEquals(expected, sortedFlightEvents);
	}
	
	@Test
	public void testLineoutFlight() {

		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("FR85", airports.get("SXF"), "28-02-2019 15:55", airports.get("DUB"), "01-03-2019 03:25")				);

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);

		List<String> expected = List.of("FR85   SXF->DUB 12:30h");

		assertEquals(expected, sortedFlightEvents);
	}


}
