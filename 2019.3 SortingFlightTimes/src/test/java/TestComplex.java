import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestComplex {

	@Test
	public void flightsDuringDST() {
		var flightSorter = new FlightSorter();
		AirportRepository repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(		
				new Flight("ANA71", airports.get("AMS"), "26-10-2019 20:50", airports.get("KIX"), "27-10-2019 15:24"),
				new Flight("ANA72", airports.get("AMS"), "27-10-2019 20:50", airports.get("KIX"), "28-10-2019 15:24"),
				new Flight("KL421", airports.get("KIX"), "28-03-2020 23:52", airports.get("AMS"), "29-03-2020 03:20"),
				new Flight("KL422", airports.get("KIX"), "27-03-2020 23:52", airports.get("AMS"), "28-03-2020 03:20"),
				new Flight("AU19", airports.get("LHR"), "04-10-2019 13:52", airports.get("SYD"), "05-10-2019 13:02"),
				new Flight("AU20", airports.get("LHR"), "05-10-2019 13:52", airports.get("SYD"), "06-10-2019 13:02")
				);

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);
		List<String> expected = List.of("KL421  KIX->AMS 10:28h","ANA72  AMS->KIX 10:34h","KL422  KIX->AMS 11:28h","ANA71  AMS->KIX 11:34h","AU20   LHR->SYD 13:10h","AU19   LHR->SYD 14:10h");

		assertEquals(expected, sortedFlightEvents);
	}
	
	@Test
	public void sameFlightTimes() {
		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("5ANA38", airports.get("HNL"), "22-10-2019 01:25", airports.get("KIX"), "22-10-2019 22:55"),
				new Flight("FR8559", airports.get("SXF"), "03-05-2019 18:55", airports.get("DUB"), "03-05-2019 20:25"),
				new Flight("KL049", airports.get("SXF"), "28-02-2019 18:55", airports.get("AMS"), "28-02-2019 21:25"));

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);
		List<String> expected = List.of("5ANA38 HNL->KIX 02:30h", "FR8559 SXF->DUB 02:30h",
				"KL049  SXF->AMS 02:30h");

		assertEquals(expected, sortedFlightEvents);
	}

}
