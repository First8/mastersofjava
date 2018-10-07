import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;


public class TestVirtualCPU {

	private static final String[] NAMES = new String[] { "Program 1", "Program 2", "Program 3", "Program 4" };

	@Test(timeout=1000)
	public void test1() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 100);
		VirtualCPU.LD.exec(state, 1, 50);
		VirtualCPU.ADD.exec(state, 0, 1);
		VirtualCPU.OUT.exec(state, 0, 12);
		checkPort(state, 0, 0);
		checkPort(state, 1, 0);
		checkPort(state, 2, 0);
		checkPort(state, 3, 0);
		checkPort(state, 4, 0);
		checkPort(state, 5, 0);
		checkPort(state, 6, 0);
		checkPort(state, 7, 0);
		checkPort(state, 8, 0);
		checkPort(state, 9, 0);
		checkPort(state, 10, 0);
		checkPort(state, 11, 0);
		checkPort(state, 12, 150);
		checkPort(state, 13, 0);
		checkPort(state, 14, 0);
		checkPort(state, 15, 0);
		checkRegister(state, 0, 150);
		checkRegister(state, 1, 50);
		checkRegister(state, 2, 0);
		checkRegister(state, 3, 0);
		checkRegister(state, 4, 0);
		checkRegister(state, 5, 0);
		checkRegister(state, 6, 0);
		checkRegister(state, 7, 0);
	}

	@Test(timeout=1000)
	public void test2() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 75);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.MV.exec(state, 2, 0);
		VirtualCPU.MV.exec(state, 3, 0);
		VirtualCPU.ADD.exec(state, 0, 1);
		VirtualCPU.ADD.exec(state, 2, 3);

		boolean success = checkPort(state, 0, 0) & checkPort(state, 1, 0) & checkPort(state, 2, 0)
				& checkPort(state, 3, 0) & checkPort(state, 4, 0) & checkPort(state, 5, 0) & checkPort(state, 6, 0)
				& checkPort(state, 7, 0) & checkPort(state, 8, 0) & checkPort(state, 9, 0) & checkPort(state, 10, 0)
				& checkPort(state, 11, 0) & checkPort(state, 12, 0) & checkPort(state, 13, 0) & checkPort(state, 14, 0)
				& checkPort(state, 15, 0) & checkRegister(state, 0, 150) & checkRegister(state, 1, 75)
				& checkRegister(state, 2, 150) & checkRegister(state, 3, 75) & checkRegister(state, 4, 0)
				& checkRegister(state, 5, 0) & checkRegister(state, 6, 0) & checkRegister(state, 7, 0);
	}

	@Test(timeout=1000)
	public void test3() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 255);
		VirtualCPU.LD.exec(state, 1, 1);
		VirtualCPU.ADD.exec(state, 0, 1);

		boolean success = checkPort(state, 0, 0) & checkPort(state, 1, 0) & checkPort(state, 2, 0)
				& checkPort(state, 3, 0) & checkPort(state, 4, 0) & checkPort(state, 5, 0) & checkPort(state, 6, 0)
				& checkPort(state, 7, 0) & checkPort(state, 8, 0) & checkPort(state, 9, 0) & checkPort(state, 10, 0)
				& checkPort(state, 11, 0) & checkPort(state, 12, 0) & checkPort(state, 13, 0) & checkPort(state, 14, 0)
				& checkPort(state, 15, 0) & checkRegister(state, 0, 0) & checkRegister(state, 1, 1)
				& checkRegister(state, 2, 0) & checkRegister(state, 3, 0) & checkRegister(state, 4, 0)
				& checkRegister(state, 5, 0) & checkRegister(state, 6, 0) & checkRegister(state, 7, 0);
	}

	@Test(timeout=1000)
	public void test4() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 10);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 1, 0);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 2, 0);
		VirtualCPU.MV.exec(state, 3, 2);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 2, 0);
		VirtualCPU.ADD.exec(state, 3, 0);
		VirtualCPU.MV.exec(state, 4, 3);
		VirtualCPU.MV.exec(state, 3, 2);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state, 2, 0);
		VirtualCPU.ADD.exec(state, 3, 0);
		VirtualCPU.ADD.exec(state, 4, 0);

		boolean success = checkPort(state, 0, 0) & checkPort(state, 1, 0) & checkPort(state, 2, 0)
				& checkPort(state, 3, 0) & checkPort(state, 4, 0) & checkPort(state, 5, 0) & checkPort(state, 6, 0)
				& checkPort(state, 7, 0) & checkPort(state, 8, 0) & checkPort(state, 9, 0) & checkPort(state, 10, 0)
				& checkPort(state, 11, 0) & checkPort(state, 12, 0) & checkPort(state, 13, 0) & checkPort(state, 14, 0)
				& checkPort(state, 15, 0) & checkRegister(state, 0, 10) & checkRegister(state, 1, 20)
				& checkRegister(state, 2, 30) & checkRegister(state, 3, 40) & checkRegister(state, 4, 50)
				& checkRegister(state, 5, 0) & checkRegister(state, 6, 0) & checkRegister(state, 7, 0);
	}

	private boolean checkPort(CPUState state, int port, int value) {
		//collector.checkThat("Port " + port + " heeft waarde " + state.getPort(port) + " terwijl " + value + " werd verwacht.", value, equalTo(state.getPort(port)));
		assertEquals("Port " + port + " heeft waarde " + state.getPort(port) + " terwijl " + value + " werd verwacht.", value, state.getPort(port));
//		if (state.getPort(port) != value) {
//			System.err.println(
//					"Port " + port + " heeft waarde " + state.getPort(port) + " terwijl " + value + " werd verwacht.");
//			return false;
//		}
//
		return true;
	}

	private boolean checkRegister(CPUState state, int register, int value) {
		assertEquals("Register " + register + " heeft waarde " + state.getRegister(register) + " terwijl "
				+ value + " werd verwacht.", value, state.getRegister(register));
		//collector.checkThat("Register " + register + " heeft waarde " + state.getRegister(register) + " terwijl "
		//		+ value + " werd verwacht.", value, equalTo(state.getRegister(register)));
//		if (state.getRegister(register) != value) {
//			System.err.println("Register " + register + " heeft waarde " + state.getRegister(register) + " terwijl "
//					+ value + " werd verwacht.");
//			return false;
//		}

		return true;
	}
}
