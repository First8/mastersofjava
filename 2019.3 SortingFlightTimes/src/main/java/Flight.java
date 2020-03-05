
public class Flight {

	private final String flightNumber;
	
	/** the departure airport */
	private final Airport origin;
	
	/** in local time of the origin airport in format: "dd-MM-yyyy HH:mm"*/
	private final String departureDateTime;
	
	/** the destination airport */
	private final Airport destination;

	/** in local time of the arrival airport in format: "dd-MM-yyyy HH:mm"*/
	private final String arrivalDateTime;

	public Flight(String flightNumber, Airport origin, String departureDateTime, Airport destination,
			String arrivalDateTime) {
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.departureDateTime = departureDateTime;
		this.destination = destination;
		this.arrivalDateTime = arrivalDateTime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public Airport getOrigin() {
		return origin;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public Airport getDestination() {
		return destination;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", origin=" + origin.getShortName() + ", departureDateTime="
				+ departureDateTime + ", destination=" + destination.getShortName() + ", arrivalDateTime=" + arrivalDateTime + "]";
	}

}
