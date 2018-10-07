/*
 *  Class to hold the state of the virtual CPU
 *  
 */
public class CPUState {
	private int[] ports = new int[16];
	private int[] registers = new int[8];

	/*
	 * Get a ports value
	 */
	public int getPort(int port) {
		validatePortRange(port);
		return this.ports[port];
	}

	/*
	 * Set the value of a port
	 */
	public void setPort(int port, int value) {
		validatePortRange(port);
		validateValueRange(value);
		this.ports[port] = value;
	}
	
	/*
	 * Get a registers value
	 */
	public int getRegister(int register) {
		validateRegisterRange(register);
		return this.registers[register];
	}

	/*
	 * Set the value of a register
	 */
	public void setRegister(int register, int value) {
		validateRegisterRange(register);
		validateValueRange(value);
		this.registers[register] = value;
	}

	private void validatePortRange(int port) {
		if ((port < 0) || (port > 15)) {
			throw new IllegalArgumentException("Port must be in range [0 - 15]");
		}
	}

	private void validateValueRange(int value) {
		if ((value < 0) || (value > 255)) {
			throw new IllegalArgumentException("Value must be in range [0 - 255]");
		}
	}

	private void validateRegisterRange(int register) {
		if ((register < 0) || (register > 15)) {
			throw new IllegalArgumentException("Register must be in range [0 - 7]");
		}
	}
}
