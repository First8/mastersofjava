import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

//@Ignore
public class AirportRepository {

	private Map<String, Airport> airports;

	public AirportRepository() {

		airports = new HashMap<>();
		airports.put("AMS",
				new Airport("Schiphol", "AMS", "Amsterdam", "the Netherlands", ZoneId.of("Europe/Amsterdam")));
		airports.put("KIX", new Airport("Kansai", "KIX", "Osaka", "Japan", ZoneId.of("Asia/Tokyo")));
		airports.put("CCU", new Airport("KolKata", "CCU", "Calcutta", "India", ZoneId.of("Asia/Calcutta")));
		airports.put("CHT",
				new Airport("Tuuta", "CHT", "Chatham Islands", "New Zealand", ZoneId.of("Pacific/Chatham")));
		airports.put("NHV", new Airport("Nuku Hiva", "NHV", "Marquesas Islands", "French Polynesia",
				ZoneId.of("Pacific/Marquesas")));
		airports.put("CHC", new Airport("Christchurch", "CHC", "Christchurch", "New Zealand", ZoneId.of("NZ")));
		airports.put("SYD", new Airport("Sydney", "SYD", "Sydney", "Australia", ZoneId.of("Australia/Sydney")));
		airports.put("HNL", new Airport("Honolulu", "HNL", "Honolulu", "USA", ZoneId.of("US/Hawaii")));
		airports.put("LHR", new Airport("Heathrow", "LHR", "London", "UK", ZoneId.of("Europe/London")));
		airports.put("SXF", new Airport("Berlin-Schönefeld", "SXF", "Berlin", "Germany", ZoneId.of("Europe/Berlin")));
		airports.put("DUB", new Airport("Dublin", "DUB", "Dublin", "Ireland", ZoneId.of("Europe/Dublin")));
		airports.put("CGK", new Airport("Soekarno-Hatta", "CGK", "Jakarta", "Indonesia", ZoneId.of("Asia/Jakarta")));	

	}

	public Map<String, Airport> getAirports() {
		return airports;
	}

	public void setAirports(Map<String, Airport> airports) {
		this.airports = airports;
	}
}
