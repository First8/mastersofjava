import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestSorting {
	

	@Test
	public void testSimpleFlights() {

		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("FR8559", airports.get("SXF"), "28-02-2019 23:55", airports.get("DUB"), "01-03-2019 03:25"),
				new Flight("KL049", airports.get("SXF"), "26-10-2019 18:55", airports.get("AMS"), "26-10-2019 21:25"),
				new Flight("AU007", airports.get("CHT"), "02-08-2043 18:23", airports.get("SYD"), "02-08-2043 16:03"));

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);

		List<String> expected = List.of("AU007  CHT->SYD 00:25h", "KL049  SXF->AMS 02:30h",
				"FR8559 SXF->DUB 04:30h");

		assertEquals(expected, sortedFlightEvents);
	}

	@Test
	public void testMoreFlights() {

		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("NH7070", airports.get("KIX"), "30-07-2019 16:50", airports.get("NHV"), "31-07-2019 10:15"),
				new Flight("3C591", airports.get("SYD"), "31-07-2019 13:10", airports.get("CHT"), "01-08-2019 17:00"),
				new Flight("DL128", airports.get("KIX"), "31-12-2019 20:45", airports.get("HNL"), "31-12-2019 09:08"),
				new Flight("CH3970", airports.get("LHR"), "26-10-2019 20:25", airports.get("KIX"), "27-10-2019 20:10"),
				new Flight("FR295", airports.get("SXF"), "28-02-2019 23:55", airports.get("DUB"), "01-03-2019 03:25"),
				new Flight("KL192", airports.get("NHV"), "26-10-2019 10:55", airports.get("AMS"), "27-10-2019 10:25"),
				new Flight("F8M0J", airports.get("CHT"), "01-03-2019 13:03", airports.get("HNL"), "28-02-2019 16:03"));

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);

		List<String> expected = List.of("F8M0J  CHT->HNL 02:45h", "FR295  SXF->DUB 04:30h",
				"DL128  KIX->HNL 07:23h", "KL192  NHV->AMS 13:00h", "CH3970 LHR->KIX 15:45h",
				"3C591  SYD->CHT 25:05h", "NH7070 KIX->NHV 35:55h");

		assertEquals(expected, sortedFlightEvents);
	}
	

	@Test
	public void justABunchMoreFlights() {
		var flightSorter = new FlightSorter();
		var repo = new AirportRepository();
		var airports = repo.getAirports();
		List<Flight> flights = List.of(
				new Flight("5ANA38", airports.get("HNL"), "26-10-2019 01:25", airports.get("KIX"), "26-10-2019 22:55"),
				new Flight("FR8559", airports.get("SXF"), "06-07-2019 18:55", airports.get("DUB"), "06-07-2019 20:25"),
				new Flight("KL049", airports.get("CCU"), "16-08-2019 14:12", airports.get("SXF"), "16-08-2019 21:25"),
				new Flight("5URD8", airports.get("NHV"), "01-05-2019 10:05", airports.get("KIX"), "02-05-2019 10:55"),
				new Flight("FIRST8", airports.get("DUB"), "21-05-2019 18:55", airports.get("HNL"), "21-05-2019 20:25"),
				new Flight("KL04B", airports.get("CGK"), "31-12-2019 18:55", airports.get("AMS"), "01-01-2020 01:07"));

		List<String> sortedFlightEvents = flightSorter.sortFlightTimes(flights);
		List<String> expected = List.of("5ANA38 HNL->KIX 02:30h", "FR8559 SXF->DUB 02:30h", "5URD8  NHV->KIX 06:20h",
				"KL049  CCU->SXF 10:43h", "KL04B  CGK->AMS 12:12h", "FIRST8 DUB->HNL 12:30h");

		assertEquals(expected, sortedFlightEvents);
	}

}
