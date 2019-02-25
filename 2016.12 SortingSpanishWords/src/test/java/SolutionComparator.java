import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;



public class SolutionComparator implements Comparator<String>{
	public int compare(String o1, String o2) {
       try {
           Collator collator = Collator.getInstance(new Locale("es", "ES"));
           String spanishRules = ((RuleBasedCollator) collator).getRules();
  
           String traditionalRules = "& C < ch, cH, Ch, CH & L < ll, lL, Ll, LL";
           RuleBasedCollator traditionalCollator = new RuleBasedCollator(spanishRules + traditionalRules);
           return traditionalCollator.compare(o1.replace(" ", "_"), o2.replace(" ", "_"));
       } catch(ParseException e) {
           throw new RuntimeException(e);
       }
    }
	
	public static String toSortedList() {
		DaliPainting[] paintingList = DaliPainting.values();
		
		List<String> result = new ArrayList<>(); 
		for (DaliPainting item: paintingList) {
			if (item.getDescription().contains(",")) {
				throw new RuntimeException("not allowed");
			}
			result.add(item.getDescription().toLowerCase());
		}
		Collections.sort(result, new SolutionComparator());
		return transformToString(result); 
	}
	public static String toRandomList() {
		DaliPainting[] paintingList = DaliPainting.values();
		
		List<String> result = new ArrayList<>(); 
		for (DaliPainting item: paintingList) {
			result.add(item.getDescription().toLowerCase());
		}
		return transformToString(result); 
	}
	
	private static String transformToString(List<String> result) {
		StringBuffer sb = new StringBuffer(); 
		for (String item: result) {
			if (sb.length()!=0) {
				sb.append(",");
			}
			sb.append(item);
		}
		return sb.toString(); 
	}
	
	public static void main(String[] args) throws Throwable {
		SortingSpanishWordsTester sorter = new SortingSpanishWordsTester(); 
		sorter.setSorter(new Solution2Comparator());
		
		for (int index =0; index< sorter.getTestCountIncludeHidden();index++) {
			System.out.println("check hidden solution " + sorter.performTest(index));
		}
		
	}
}