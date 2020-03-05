
public class IpRangeTestCase {
	private final String description;
	private final String cidr;
	private final String ipInRange;
	private final String ipNotInRange;

	public IpRangeTestCase(String description, String cidr, String ipInRange, String ipNotInRange) {
		this.description = description;
		this.cidr = cidr;
		this.ipInRange = ipInRange;
		this.ipNotInRange = ipNotInRange;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCidr() {
		return this.cidr;
	}

	public final boolean performTest(IpRangeCalculator testable) {
		IpRangeCalculator reference = new IpRangeCalculatorRI();

		System.out.println("Test                         | Expected        | Actual");
		System.out.println("-----------------------------+-----------------+-----------------");

		boolean isOk = true;
		if (!this.areEqual("IP address", reference.getAddress(this.cidr), testable.getAddress(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("Subnet mask", reference.getSubnetMask(this.cidr), testable.getSubnetMask(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("Network address", reference.getNetworkAddress(this.cidr), testable.getNetworkAddress(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("Broadcast address", reference.getBroadcastAddress(this.cidr), testable.getBroadcastAddress(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("First host", reference.getFirstHost(this.cidr), testable.getFirstHost(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("Last host", reference.getLastHost(this.cidr), testable.getLastHost(this.cidr))) {
			isOk = false;
		}
		if (!this.areEqual("Host count", reference.getHostCount(this.cidr), testable.getHostCount(this.cidr))) {
			isOk = false;
		}

		if (!this.areEqual("Is " + this.ipInRange + " in range?", reference.isIpInRange(this.ipInRange, this.cidr), testable.isIpInRange(this.ipInRange, this.cidr))) {
			isOk = false;
		}

		if (!this.areEqual("Is " + this.ipNotInRange + " in range?", reference.isIpInRange(this.ipNotInRange, this.cidr), testable.isIpInRange(this.ipNotInRange, this.cidr))) {
			isOk = false;
		}

		return isOk;
	}

	private boolean areEqual(String label, String expected, String actual) {
		System.out.println(addWhiteSpace(label, 28) + " | " + addWhiteSpace(expected, 15) + " | " + actual);
		return expected.equals(actual);
	}

	private boolean areEqual(String label, int expected, int actual) {
		System.out.println(addWhiteSpace(label, 28) + " | " + addWhiteSpace(Integer.toString(expected), 15) + " | " + Integer.toString(actual));
		return expected == actual;
	}

	private boolean areEqual(String label, boolean expected, boolean actual) {
		System.out.println(addWhiteSpace(label, 28) + " | " + addWhiteSpace(Boolean.toString(expected), 15) + " | " + Boolean.toString(actual));
		return expected == actual;
	}

	private static String addWhiteSpace(String original, int length) {
		if (original.length() < length) {
			StringBuilder result = new StringBuilder(original);
			while (result.length() < length) {
				result.append(" ");
			}
			return result.toString();
		} else {
			return original;
		}
	}
}
