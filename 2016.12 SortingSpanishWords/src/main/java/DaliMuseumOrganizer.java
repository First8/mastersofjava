import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class DaliMuseumOrganizer {


	/** 
	 * Class to generate brochure
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		List<String> toSortedList = new ArrayList<>();
		for (DaliPainting painting: DaliPainting.values()) {
			toSortedList.add(painting.getDescription()); 
		}
				
		Collections.sort(toSortedList, new SortingSpanishWordsImpl());
		
		Brochure brochure = new Brochure(); 
		brochure.setTitles(toSortedList);
		brochure.print();
	}
}
