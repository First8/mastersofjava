import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SaintNicholasRhyme {

	private List<String> rhymeScheme = new ArrayList<>(Arrays.asList("A","A","B","B"));
	
	private String content;
	
	private String childName ="";;
	
	public SaintNicholasRhyme(String content) {
		this.content = content;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public boolean isRhymeValid() {
		return new RhymeAssistantImpl().rhyme(content).toString().equals(rhymeScheme.toString());
	}
	
	public void print() {
		System.out.println("dear " + childName + "\n"+content+ " \n kind regards, Saint Nicolas");  
	}
}
