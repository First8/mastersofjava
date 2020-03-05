import nl.moj.model.Tester;

public class VirtualCPUTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
		"Program 1",
		"Program 2",
		"Program 3",
		"Program 4"
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuilder result = new StringBuilder();
		
		switch (nr) {
			case 0:
				addLine(result, "LD 0, 100");
				addLine(result, "LD 1, 50");
				addLine(result, "ADD 0, 1");
				addLine(result, "OUT 0, 12");
				break;
			case 1:
				addLine(result, "LD, 0, 75");
				addLine(result, "MV 1, 0");
				addLine(result, "MV 2, 0");
				addLine(result, "MV 3, 0");
				addLine(result, "ADD 0, 1");
				addLine(result, "ADD 2, 3");
				break;
			case 2:
				addLine(result, "LD 0, 255");
				addLine(result, "LD 1, 1");
				addLine(result, "ADD 0, 1");
				break;
			case 3:
				addLine(result, "LD 0, 10");
				addLine(result, "MV 1, 0");
				addLine(result, "ADD 1, 0");
				addLine(result, "MV 2, 1");
				addLine(result, "MV 1, 0");
				addLine(result, "ADD 1, 0");
				addLine(result, "ADD 2, 0");
				addLine(result, "MV 3, 2");
				addLine(result, "MV 2, 1");
				addLine(result, "MV 1, 0");
				addLine(result, "ADD 1, 0");
				addLine(result, "ADD 2, 0");
				addLine(result, "ADD 3, 0");
				addLine(result, "MV 4, 3");
				addLine(result, "MV 3, 2");
				addLine(result, "MV 2, 1");
				addLine(result, "MV 1, 0");
				addLine(result, "ADD 1, 0");
				addLine(result, "ADD 2, 0");
				addLine(result, "ADD 3, 0");
				addLine(result, "ADD 4, 0");
				break;
		}
		
		return result.toString();
	}

	private static void addLine(StringBuilder builder, String value) {
		builder.append(value);
		builder.append(System.getProperty("line.separator"));
	}
	
	public boolean performTest(int nr) throws Throwable {
		boolean result = false;

		try {
			switch(nr) {
				case 0:
					result = test1();
					break;
				case 1:
					result = test2();
					break;
				case 2:
					result = test3();
					break;
				case 3:
					result = test4();
					break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		return result;
	}

	private boolean test1() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 100);
		VirtualCPU.LD.exec(state, 1, 50);
		VirtualCPU.ADD.exec(state, 0, 1);
		VirtualCPU.OUT.exec(state, 0, 12);

		return 
		checkPort(state, 0, 0) & 
		checkPort(state, 1, 0) & 
		checkPort(state, 2, 0) & 
		checkPort(state, 3, 0) & 
		checkPort(state, 4, 0) & 
		checkPort(state, 5, 0) & 
		checkPort(state, 6, 0) & 
		checkPort(state, 7, 0) & 
		checkPort(state, 8, 0) & 
		checkPort(state, 9, 0) & 
		checkPort(state, 10, 0) & 
		checkPort(state, 11, 0) & 
		checkPort(state, 12, 150) & 
		checkPort(state, 13, 0) & 
		checkPort(state, 14, 0) & 
		checkPort(state, 15, 0) & 
		checkRegister(state, 0, 150) &
		checkRegister(state, 1, 50) &
		checkRegister(state, 2, 0) &
		checkRegister(state, 3, 0) &
		checkRegister(state, 4, 0) &
		checkRegister(state, 5, 0) &
		checkRegister(state, 6, 0) &
		checkRegister(state, 7, 0);
	}

	private boolean test2() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 75);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.MV.exec(state, 2, 0);
		VirtualCPU.MV.exec(state, 3, 0);
		VirtualCPU.ADD.exec(state, 0, 1);
		VirtualCPU.ADD.exec(state, 2, 3);

		return 
		checkPort(state, 0, 0) & 
		checkPort(state, 1, 0) & 
		checkPort(state, 2, 0) & 
		checkPort(state, 3, 0) & 
		checkPort(state, 4, 0) & 
		checkPort(state, 5, 0) & 
		checkPort(state, 6, 0) & 
		checkPort(state, 7, 0) & 
		checkPort(state, 8, 0) & 
		checkPort(state, 9, 0) & 
		checkPort(state, 10, 0) & 
		checkPort(state, 11, 0) & 
		checkPort(state, 12, 0) & 
		checkPort(state, 13, 0) & 
		checkPort(state, 14, 0) & 
		checkPort(state, 15, 0) & 
		checkRegister(state, 0, 150) &
		checkRegister(state, 1, 75) &
		checkRegister(state, 2, 150) &
		checkRegister(state, 3, 75) &
		checkRegister(state, 4, 0) &
		checkRegister(state, 5, 0) &
		checkRegister(state, 6, 0) &
		checkRegister(state, 7, 0);
	}

	private boolean test3() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state, 0, 255);
		VirtualCPU.LD.exec(state, 1, 1);
		VirtualCPU.ADD.exec(state, 0, 1);

		return 
		checkPort(state, 0, 0) & 
		checkPort(state, 1, 0) & 
		checkPort(state, 2, 0) & 
		checkPort(state, 3, 0) & 
		checkPort(state, 4, 0) & 
		checkPort(state, 5, 0) & 
		checkPort(state, 6, 0) & 
		checkPort(state, 7, 0) & 
		checkPort(state, 8, 0) & 
		checkPort(state, 9, 0) & 
		checkPort(state, 10, 0) & 
		checkPort(state, 11, 0) & 
		checkPort(state, 12, 0) & 
		checkPort(state, 13, 0) & 
		checkPort(state, 14, 0) & 
		checkPort(state, 15, 0) & 
		checkRegister(state, 0, 0) &
		checkRegister(state, 1, 1) &
		checkRegister(state, 2, 0) &
		checkRegister(state, 3, 0) &
		checkRegister(state, 4, 0) &
		checkRegister(state, 5, 0) &
		checkRegister(state, 6, 0) &
		checkRegister(state, 7, 0);
	}

	private boolean test4() {
		CPUState state = new CPUState();
		VirtualCPU.LD.exec(state,  0, 10);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state,  1, 0);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state,  1, 0);
		VirtualCPU.ADD.exec(state,  2, 0);
		VirtualCPU.MV.exec(state, 3, 2);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state,  1, 0);
		VirtualCPU.ADD.exec(state,  2, 0);
		VirtualCPU.ADD.exec(state,  3, 0);
		VirtualCPU.MV.exec(state, 4, 3);
		VirtualCPU.MV.exec(state, 3, 2);
		VirtualCPU.MV.exec(state, 2, 1);
		VirtualCPU.MV.exec(state, 1, 0);
		VirtualCPU.ADD.exec(state,  1, 0);
		VirtualCPU.ADD.exec(state,  2, 0);
		VirtualCPU.ADD.exec(state,  3, 0);
		VirtualCPU.ADD.exec(state,  4, 0);

		return 
		checkPort(state, 0, 0) & 
		checkPort(state, 1, 0) & 
		checkPort(state, 2, 0) & 
		checkPort(state, 3, 0) & 
		checkPort(state, 4, 0) & 
		checkPort(state, 5, 0) & 
		checkPort(state, 6, 0) & 
		checkPort(state, 7, 0) & 
		checkPort(state, 8, 0) & 
		checkPort(state, 9, 0) & 
		checkPort(state, 10, 0) & 
		checkPort(state, 11, 0) & 
		checkPort(state, 12, 0) & 
		checkPort(state, 13, 0) & 
		checkPort(state, 14, 0) & 
		checkPort(state, 15, 0) & 
		checkRegister(state, 0, 10) &
		checkRegister(state, 1, 20) &
		checkRegister(state, 2, 30) &
		checkRegister(state, 3, 40) &
		checkRegister(state, 4, 50) &
		checkRegister(state, 5, 0) &
		checkRegister(state, 6, 0) &
		checkRegister(state, 7, 0);
	}


	private boolean checkPort(CPUState state, int port, int value)
	{
		if (state.getPort(port) != value) {
			System.err.println("Port " + port + " heeft waarde " + state.getPort(port) + " terwijl " + value + " werd verwacht.");
			return false;
		}
		
		return true;
	}

	private boolean checkRegister(CPUState state, int register, int value)
	{
		if (state.getRegister(register) != value) {
			System.err.println("Register " + register + " heeft waarde " + state.getRegister(register) + " terwijl " + value + " werd verwacht.");
			return false;
		}
		
		return true;
	}
}
