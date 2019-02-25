import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import nl.moj.model.Tester;

public class SortingSpanishWordsTester implements Tester.Testable,Tester.HiddenTestable {
	
	
	private String[] INPUT_LIST = new String[]{
			"noot,mies,aap",
			"piñata,pim,pio,pinza",
			"luz,curioso,llama,chalina",
			"luz,curioso,llama,chalina,noot,mies,aap,cómo,álgebra,educación,almacén,aquí,bús,vergüenza",
			SolutionComparator.toRandomList()
			
	};
	private String[] OUTPUT_SORTED_LIST = new String[]{
			"aap,mies,noot",
			"pim,pinza,piñata,pio",
			"curioso,chalina,luz,llama",
			"aap,álgebra,almacén,aquí,bús,cómo,curioso,chalina,educación,luz,llama,mies,noot,vergüenza",
			SolutionComparator.toSortedList()
	};
	private String[] TEST_DESCRIPTION = new String[]{
			"Sorting works",
			"ñ works",
			"ch,ll,etc works",
			"Vowels work",
			"Hidden solution"
	};
	//private String[] ACCENTED_WORDS = new String[] {  "schön" ,  "cómo" ,  "álgebra" ,  "educación" ,  "almacén" ,  "aquí" ,  "bús" ,  "vergüenza" };
	
	
	public int getTestCount() {
		return INPUT_LIST.length-1;
	}
	@Override
	public int getTestCountIncludeHidden() {
		return INPUT_LIST.length;
	}
	public String getTestName(int nr) {
		return OUTPUT_SORTED_LIST[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append(TEST_DESCRIPTION[nr]);
		return sb.toString();
	}
	
	private SortingSpanishWordsImpl sorter = new SortingSpanishWordsImpl();
	
	public void setSorter(SortingSpanishWordsImpl sorter) {
		this.sorter = sorter;
	}

	public boolean performTest(int nr) throws Throwable {
		
		boolean result=true;
		try {
			List<String> unsortedList = (List<String>) Arrays.asList(INPUT_LIST[nr].split(","));
			
			unsortedList.sort(sorter);
		
			List<String> sortedList = (List<String>) Arrays.asList(OUTPUT_SORTED_LIST[nr].split(","));
	 
			result &= unsortedList.equals(sortedList);
			
			if (getTestCount()>nr) {
				System.out.println("Test: " + (nr+1) + "== "+result+" | unsortedList " +unsortedList + " " + sortedList );; 				
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
		//
		return result;
	}
	
}
