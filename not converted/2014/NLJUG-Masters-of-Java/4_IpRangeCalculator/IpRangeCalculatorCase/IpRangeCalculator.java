public interface IpRangeCalculator {
	/**
	 * Get the IP address from the given CIDR notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The IP address, e.g. 192.168.0.1
	 */
	public String getAddress(String cidr);

	/**
	 * Get the network address of the subnet of the given CIDR notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The network address, e.g. 192.168.0.0
	 */
	public String getNetworkAddress(String cidr);

	/**
	 * Get the subnet mask from the given CIDR notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The subnet mask, e.g. 255.255.255.0
	 */
	public String getSubnetMask(String cidr);

	/**
	 * Get the broadcast address of the subnet from the given CIDR notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The broadcast address, e.g. 192.168.0.255
	 */
	public String getBroadcastAddress(String cidr);

	/**
	 * Get the address of the first host in the subnet from the given CIDR
	 * notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The address of the first host, e.g. 192.168.0.1
	 */
	public String getFirstHost(String cidr);

	/**
	 * Get the address of the last host in the subnet from the given CIDR
	 * notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The address of the last host, e.g. 192.168.0.254
	 */
	public String getLastHost(String cidr);

	/**
	 * Get maximum number of hosts in the subnet from the given CIDR notation.
	 * 
	 * @param cidr
	 *          The CIDR
	 * @return The number of hosts
	 */
	public int getHostCount(String cidr);

	/**
	 * Determines whether the given ipAddress is in the range of the subnet
	 * described by the given CIDR.
	 * 
	 * @param ipAddress
	 *          The IP address
	 * @param cidr
	 *          CIDR notation of the subnet
	 * @return true if the ipAddress is in range, false otherwise.
	 */
	public boolean isIpInRange(String ipAddress, String cidr);
}
