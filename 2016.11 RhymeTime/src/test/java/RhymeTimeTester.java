import java.util.Arrays;
import java.util.List;

import nl.moj.model.Tester;

public class RhymeTimeTester implements Tester.Testable,Tester.HiddenTestable  {
	
	private static final String[] INPUT_POEM=new String[] {
			"This is what I'm testing\nIt's no special thing\nHopefully it is good\notherwise I'll get a bad mood",
			"The Sint was thinking\nthese presents are blinking\nplease to watch\na computer match",
			"Duke was doing Java,\nThen Groovy\nThen PHP!\nAnd forgot to rhyme",
			"Excel in Java\n with confidence\n win Masters of Java\n enjoy the conference",
			"When programming\n please prefer clean code above rhyming\nLove coding\nMake it as poetry",
			"When programming\n Love coding\nMake it as poetry"
	};
	
	private static final String[] OUTPUT_RHYME=new String[] {
		"AABB",
		"AABB",
		"ABCD",
		"ABAB",
		"AAAB",
		"AAB"
	};
	
	public int getTestCount() {
		return INPUT_POEM.length-2;
	}
	
	@Override
	public int getTestCountIncludeHidden() {
		return INPUT_POEM.length;
	}

	public String getTestName(int nr) {
		return INPUT_POEM[nr].split("\n")[0];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("Use the given String to produce rhyme: \n'"+INPUT_POEM[nr]+"'\n");
		return sb.toString();
	}
	private RhymeAssistantImpl solution = new RhymeAssistantImpl(); 
	
	public void setSolution(RhymeAssistantImpl solution) {
		this.solution = solution;
	}
	
	public boolean performTest(int nr) throws Throwable {
		
		boolean result=false;
		
		try {
			List<String> rhymeScheme = solution.rhyme(INPUT_POEM[nr]);
			String checkString = rhymeScheme.toString().replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
			
			if (checkString.equalsIgnoreCase(OUTPUT_RHYME[nr])) {
			 
				result=true;
			} else {
				System.out.println("Failed: There seems to be problem with the rhyming.");
				result=false;
			}
			if (!result) {
				System.out.println("Result   : '"+checkString + "'");
				System.out.println("Expected : '"+OUTPUT_RHYME[nr]+"'");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
