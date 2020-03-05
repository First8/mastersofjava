import nl.moj.model.Tester;

public class HelloWorldTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"Hello World!",
			"Hello Klaasjan",
			"Hello Robert",
			"Hello Erik",
			"Hello SUN",
			"Hello NLJUG",
			"Hello null"
	};
	
	private static final String[] HWNAMES=new String[] {
		"World!",
		"Klaasjan",
		"Robert",
		"Erik",
		"SUN",
		"NLJUG",
		null
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		//
		if (HWNAMES[nr]==null) {
			sb.append("As the given String is null, return just 'Hello'\n");
			sb.append("Input: null\n");
			sb.append("Expected output: 'Hello'\n");
		} else {
			sb.append("Use the given String '"+HWNAMES[nr]+"' to produce 'Hello "+HWNAMES[nr]+"'.\n");
			sb.append("Input: '"+HWNAMES[nr]+"'\n");
			sb.append("Expected output: 'Hello "+HWNAMES[nr]+"'\n");
		}
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		HelloWorldImpl instance=new HelloWorldImpl();
		try {
			String r=instance.sayIt(HWNAMES[nr]);
			String exp="Hello"+(HWNAMES[nr]==null?"":" "+HWNAMES[nr]);
			if (exp.equalsIgnoreCase(r)) {
				if (exp.equals(r)) {
					result=true;
				} else {
					System.out.println("Failed: There seems to be a problem with capital an non capital letters.");
					result=false;
				}
			} else if (exp.length()!=r.length()) {
				if (exp.length()>r.length()) System.out.println("Failed: There may be some characters missing.");
				if (exp.length()<r.length()) System.out.println("Failed: There may be too many characters.");
			}
			if (!result) {
				System.out.println("Result   : '"+r+"'");
				System.out.println("Expected : '"+exp+"'");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//
		return result;
	}
	
}
