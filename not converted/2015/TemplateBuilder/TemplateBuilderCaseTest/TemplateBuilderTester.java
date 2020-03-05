import nl.moj.model.Tester;

public class TemplateBuilderTester implements Tester.Testable {
	
	private static final String[] NAMES=new String[] {
			"TestCase #1 - Check local part validity",
			"TestCase #2 - Check domain part validity",
			"TestCase #3 - Check complete string validity"
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
		// TODO: Generate useful test description.
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
		StringBuilder errorMessage = new StringBuilder();
		try {
			DukemailValidator instance=new DukemailValidatorImpl();
			switch (nr) {
			case 0:
				errorMessage.append(instance.isValid(".asgdfe@example.dukemail")?"Check the dot's\n":"");
				errorMessage.append(instance.isValid("asgd..fe@example.dukemail")?"Check the dot's\n":"");
				errorMessage.append(instance.isValid("asgdfe.@example.dukemail")?"Check the dot's\n":"");
				errorMessage.append(instance.isValid("asg.dfe@example.dukemail")?"":"Check the dot's\n");
				
				errorMessage.append(instance.isValid("10dfe@example.dukemail")?"":"This should not fail\n");
				errorMessage.append(instance.isValid("as.g.d#fe@example.dukemail")?"Non alphanumeric test fails? \n":"");
				errorMessage.append(instance.isValid("aWg.dfW@example.dukemail")?"":"upper lower case");
				break;
			case 1:
				errorMessage.append(instance.isValid("1sg.dfe@exaaample.dukemail")?"":"Looks valid to me\n");
				errorMessage.append(instance.isValid("1sg.dfe@exaaample.test.dukemail")?"Check the dot's\n":"");
				errorMessage.append(instance.isValid("1sg.dfe@exaaample1723test.dukemail")?"":"Looks valid to me\n");
				break;
			case 2:
				errorMessage.append(instance.isValid("1sg.dfe@exaaample1723test.dukemailtje")?"What was the domain name again? \n":"");
				errorMessage.append(instance.isValid("1sg.dfe@exaaample1723test.dukemaiL")?"What was the domain name again? \n":"");
				errorMessage.append(instance.isValid("1sg.dfe@exa@aample1723test.dukemail")?"A monkey has how many tails? \n":"");
				errorMessage.append(instance.isValid("1sg.dfe@exa@aample1723test.dukemail")?"your regex is missing something \n":"");
				errorMessage.append(instance.isValid("A.BCDEFGHIJK.LMNOPQRST.UVWXYZabcdefghijklmno.pqrstuvwxyz0123.456789@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.dukemail")?"":"Strange, this should pass, missing some chars?");
				errorMessage.append(instance.isValid("1sg.dfeaample1723test.dukemail")?"Shoulden there be at least one special character? \n":"");
				errorMessage.append(instance.isValid("")?"Are you kidding me? it's a empty string \n":"");
				
			default:
				break;
			}
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			return false;
		}
		if (errorMessage.toString().length()>0){
			result = false;
			System.out.print(errorMessage.toString());
		} else {
			result = true;
		}
		return result;
	}
	
}
