import java.time.ZoneId;

public class Airport {

	private final String fullName;
	
	/** Is also the Unique ID for an airport. e.g. AMS for Schiphol, Amsterdam */
	private final String shortName;
	private final String city;
	private final String country;
	
	/** timezone of this airport */
	private final ZoneId zoneId;

	public Airport(String fullName, String shortName, String city, String country, ZoneId zoneId) {
		this.fullName = fullName;
		this.shortName = shortName;
		this.city = city;
		this.country = country;
		this.zoneId = zoneId;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public ZoneId getZoneId() {
		return zoneId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airport [fullName=" + fullName + ", shortName=" + shortName + ", zoneId=" + zoneId + "]";
	}

}
