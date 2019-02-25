import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import nl.moj.model.Tester;

public class ApplesOrangesTester implements Tester.Testable, Tester.HiddenTestable  {

	private static final String[] FILE_SET = new String[] { "test1",
			"test2", "non existing","full"};

	private static final String[] AMOUNT = new String[] { "42", "64",
			"0","510" };

	public int getTestCount() {
		return FILE_SET.length-1;
	}

	public String getTestName(int nr) {
		return FILE_SET[nr];
	}

	public String getTestDescription(int nr) {
		StringBuffer sb = new StringBuffer();
		sb.append("As the given directory is "+FILE_SET[nr]+", compute the totals, this should return "+AMOUNT[nr]+"");
		return sb.toString();
	}

	
	@Override
	public int getTestCountIncludeHidden() {
		return FILE_SET.length;
	}
	
	
	public int getCollectionTotal(String name) throws Exception {
		Path p = Paths.get(new File("./testdirs/" +name).getCanonicalPath());// full path to help the embedded runtime. 
		ApplesOranges ap = solution;
		int total = ap.total(p);
		return total; 
	}
	public static int getCollectionTotalViaSolution(String name) throws Exception {
		Path p = Paths.get("./testdirs/" +name);
		ApplesOranges ap = new Solution();
		int total = ap.total(p);
		return total; 
	}
	
	private ApplesOranges solution = new ApplesOrangesImpl(); 
	
	public void setSolution(ApplesOranges solution) {
		this.solution = solution;
	}
	
	public boolean performTest(int nr) throws Throwable {
		
		boolean result = false;
		
		int total = getCollectionTotal(FILE_SET[nr]);

		result = AMOUNT[nr].equals(""+ total);
		if (!result) {
			System.out.println("Result   : '" + total + "' ");
			System.out.println("Expected : '" + AMOUNT[nr] + "'");
		} else {
			System.out.println("Total: " + total);
		}
		
		return result;
	}
	 
}
