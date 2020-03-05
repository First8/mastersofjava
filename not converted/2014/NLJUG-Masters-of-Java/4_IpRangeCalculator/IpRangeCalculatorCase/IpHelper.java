public final class IpHelper {
	private IpHelper() {
	};

	public static int ipToInt(String address) {
		String[] nibbles = address.split("\\.");

		int result = Integer.parseInt(nibbles[0]) << 24;
		result |= Integer.parseInt(nibbles[1]) << 16;
		result |= Integer.parseInt(nibbles[2]) << 8;
		result |= Integer.parseInt(nibbles[3]);

		return result;
	}

	public static String intToIp(int address) {
		StringBuilder result = new StringBuilder();

		result.append((address & 0xFF000000) >>> 24).append(".");
		result.append((address & 0x00FF0000) >>> 16).append(".");
		result.append((address & 0x0000FF00) >>> 8).append(".");
		result.append(address & 0x000000FF);

		return result.toString();
	}
}
