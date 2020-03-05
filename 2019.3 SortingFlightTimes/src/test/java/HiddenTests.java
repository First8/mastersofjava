import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class HiddenTests {

	@Test
	public void justABunchMoreFlights() {
		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("5ANA38", airports.get("HNL"), "26-10-2019 01:25", airports.get("KIX"), "26-10-2019 22:55"),
				new Flight("KL049", airports.get("CCU"), "16-08-2019 14:12", airports.get("SXF"), "16-08-2019 21:25"),
				new Flight("5URD8", airports.get("NHV"), "01-05-2019 10:05", airports.get("KIX"), "02-05-2019 10:55"),
				new Flight("FIRST8", airports.get("DUB"), "21-05-2019 18:55", airports.get("HNL"), "21-05-2019 20:25"),
				new Flight("KL04B", airports.get("CGK"), "31-12-2019 18:55", airports.get("AMS"), "01-01-2020 01:07"));

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);
		List<String> expected = List.of("5ANA38 HNL->KIX 02:30h",  "5URD8  NHV->KIX 06:20h",
				"KL049  CCU->SXF 10:43h", "KL04B  CGK->AMS 12:12h", "FIRST8 DUB->HNL 12:30h");

		assertEquals(expected, sortedFlightEvents);
	}
}
