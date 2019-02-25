import java.util.ArrayList;
import java.util.List;

/**
 * Sample usage of rhyme application
 */
public class SublimeRhymeTime {
	List<String> contentList;

    public static void main(String... args) {
    	List<String> contentList = new ArrayList<String>(); 
    	contentList.add("The Sint was thinking\nthese presents are blinking\nplease to watch\na computer match");
    	contentList.add("This is what I'm testing\nIt's no special thing\nHopefully it is good\notherwise I'll get a bad mood");
    	contentList.add("The Programmer was writing Java\nmThen Groovy\nThen Scala!\nAnd forgot to rhyme");

    	for (String content: contentList) {
    		SaintNicholasRhyme rhyme = new SaintNicholasRhyme(content); 
    		
    		
    		if ( !rhyme.isRhymeValid()) {
    			System.out.println("found incorrect rhyme");
    			rhyme.print();
    		} 
    	}
    }
}
