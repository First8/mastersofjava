import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSorter {

	private static final String outputTemplate = "%-6s %s->%s %02d:%02dh";

	// order by time, and in case of a tie, order by Flight-number
	public List<String> sortFlightTimes(List<Flight> flights) {

		List<String> flightTimes = flights.stream()//
				.map(flight -> new FlightData(flight, flightToDuration(flight)))//
				.sorted((a, b) -> a.flight.getFlightNumber().compareTo(b.flight.getFlightNumber()))//
				.sorted((a, b) -> a.duration.compareTo(b.duration))//
				.map(data -> flightDataStringFormat(data))//
				.collect(Collectors.toList());

		return flightTimes;

	}

	private Duration flightToDuration(Flight flight) {
		var departureDateTime = stringToInstant(flight.getDepartureDateTime(), flight.getOrigin());
		var arrivalDateTime = stringToInstant(flight.getArrivalDateTime(), flight.getDestination());

		return Duration.between(departureDateTime, arrivalDateTime);
	}

	private Instant stringToInstant(String time, Airport airport) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm").withZone(airport.getZoneId());
		ZonedDateTime zonedTime = ZonedDateTime.parse(time, formatter);
		return Instant.ofEpochSecond(zonedTime.toEpochSecond());
	}

	private String flightDataStringFormat(FlightData data) {

		String from = data.flight.getOrigin().getShortName();
		String to = data.flight.getDestination().getShortName();
		Long hours = data.duration.toHours();
		Integer minutes = data.duration.toMinutesPart();
		return String.format(outputTemplate, data.flight.getFlightNumber(), from, to, hours, minutes);

	}

	private class FlightData {

		Flight flight;
		Duration duration;

		FlightData(Flight flight, Duration duration) {
			this.flight = flight;
			this.duration = duration;
		}
	}
}