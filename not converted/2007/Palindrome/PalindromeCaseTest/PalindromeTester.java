import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import nl.moj.model.Tester;

public class PalindromeTester implements Tester.Testable {

	private Object[][] VALUES=new Object[][] {
			null,
			{ },
			{ "anna otto" },
			{ "sums are not set as a test on erasmus" },
			{ "never odd or even"},
			{ "oozy rat in a sanitary zoo"},
			{ "a toyota race fast.. safe car a toyota"},
			{ 0,1,2,1,0 },
			{ 0,1,2,2,1,0 },
			{ 0,10,21,21,10,0 },
			{ 1,2,4,8,16,32,64,128,256 },
	};
	
	private boolean[] EXPECTED=new boolean[] {
		false,false,false,true,true,true,false,true,true,true,false	
	};
	
	private static final String[] NAMES=new String[] {
			"NULL Test",
			"Empty Test",
			"Obvious",
			"Positive#1",
			"Positive#2",
			"Positive#3",
			"Not so obvious",
			"Odd a Number",
			"Even a Number",
			"More Numbers",
			"Wrong Number"
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("Determine if the following dataset is a Palindrome :\n");
		Deque d=toDeque(nr);
		if (d!=null) {
			Object[] o=d.toArray();
			sb.append("{ ");
			for (int t=0;t<o.length;t++) {
				if (t>0) sb.append(",");
				Object v=o[t];
				if (v instanceof Character) {
					sb.append("'");
					sb.append(String.valueOf(v));
					sb.append("'");
				} else {
					sb.append(String.valueOf(v));
				}
			}
			sb.append(" }\n");
		} else {
			sb.append("NULL array.\n");
		}
		sb.append("\n");
		sb.append("Expected output : "+EXPECTED[nr]);
		//
		return sb.toString();
	}
	
	private ArrayDeque toDeque(int nr) {
		List l=new ArrayList();
		Object[] v=VALUES[nr];
		if (v==null) return null;
		for (int t=0;t<v.length;t++) {
			if (v[t] instanceof String) {
				String s=(String)v[t];
				for (int y=0;y<s.length();y++) {
					if (s.charAt(y)!=' ') {
						l.add(s.charAt(y));
					}
				}
			} else {
				l.add(v[t]);
			}
		}
		return new ArrayDeque(l);
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		try {
			PalindromeImpl instance=new PalindromeImpl();
			boolean out=instance.isPalindrome(toDeque(nr));
			//
			boolean result = (out==EXPECTED[nr]);
			if (!result) { 
				System.out.println("Your result was '"+out+"' but the expected result was '"+EXPECTED[nr]+"'");
			}
			return result;
		} catch (Exception ex) {
			System.out.println("Exception occurred : "+ex);
			ex.printStackTrace();
			return false;
		}
	}
	
}
