
public class IpRangeCalculatorRI implements IpRangeCalculator {

	public static void main(String[] args) {
		String cidr = "80.101.149.139/30";

		IpRangeCalculator ipRangeCalculator = new IpRangeCalculatorImpl();

		System.out.println("Address           : " + ipRangeCalculator.getAddress(cidr));
		System.out.println("SubnetMask        : " + ipRangeCalculator.getSubnetMask(cidr));
		System.out.println("Network Address   : " + ipRangeCalculator.getNetworkAddress(cidr));
		System.out.println("Broascast Address : " + ipRangeCalculator.getBroadcastAddress(cidr));
		System.out.println("First host        : " + ipRangeCalculator.getFirstHost(cidr));
		System.out.println("Last host         : " + ipRangeCalculator.getLastHost(cidr));
		System.out.println("Host count        : " + ipRangeCalculator.getHostCount(cidr));
	}

	@Override
	public String getAddress(String cidr) {
		return cidr.split("/")[0];
	}

	@Override
	public String getNetworkAddress(String cidr) {
		int ipAddress = IpHelper.ipToInt(this.getAddress(cidr));
		int subnetMask = IpHelper.ipToInt(this.getSubnetMask(cidr));

		return IpHelper.intToIp(ipAddress & subnetMask);
	}

	@Override
	public String getSubnetMask(String cidr) {
		int prefixSize = Integer.parseInt(cidr.split("/")[1]);

		int subnetMask = 0;
		for (int bit = 0; bit < 32; bit++) {
			subnetMask = subnetMask << 1;
			if (bit < prefixSize) {
				subnetMask |= 1;
			}
		}

		return IpHelper.intToIp(subnetMask);
	}

	@Override
	public String getBroadcastAddress(String cidr) {
		int networkAddress = IpHelper.ipToInt(this.getNetworkAddress(cidr));
		int subnetMask = IpHelper.ipToInt(this.getSubnetMask(cidr));

		return IpHelper.intToIp(networkAddress | (0xFFFFFFFF ^ subnetMask));
	}

	@Override
	public String getFirstHost(String cidr) {
		int networkAddress = IpHelper.ipToInt(this.getNetworkAddress(cidr));

		return IpHelper.intToIp(networkAddress + 1);
	}

	@Override
	public String getLastHost(String cidr) {
		int networkAddress = IpHelper.ipToInt(this.getNetworkAddress(cidr));
		int subnetMask = IpHelper.ipToInt(this.getSubnetMask(cidr));

		return IpHelper.intToIp((networkAddress | (0xFFFFFFFF ^ subnetMask)) - 1);
	}

	@Override
	public int getHostCount(String cidr) {
		int subnetMask = IpHelper.ipToInt(this.getSubnetMask(cidr));

		return (0xFFFFFFFF ^ subnetMask) - 1;
	}

	@Override
	public boolean isIpInRange(String ipAddress, String cidr) {
		int ip = IpHelper.ipToInt(ipAddress);
		int networkAddress = IpHelper.ipToInt(this.getNetworkAddress(cidr));
		int subnetMask = IpHelper.ipToInt(this.getSubnetMask(cidr));

		return networkAddress == (ip & subnetMask);
	}
}
