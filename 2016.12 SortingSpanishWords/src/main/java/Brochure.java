import java.util.List;


public class Brochure {

	private List<String> titles; 
	
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	/**
	 * Sample method to create brochure
	 */
	public void print() {
		
		int index = 0; 
		int pages = 0; 

		System.out.println("Dali foundation brochure");
		System.out.println("------------------------");
		for (String title: titles) {
			if (index%2==0) {
				System.out.println("Page "+pages);
				pages++;
			}
			System.out.println("-"+title);
			index++; 
		}
		
	}
}
