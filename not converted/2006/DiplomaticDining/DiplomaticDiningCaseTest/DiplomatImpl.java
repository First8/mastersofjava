import java.util.ArrayList;
import java.util.List;


public class DiplomatImpl implements Diplomat {

	private String country;
	private List<String> languages;
	private List<String> relations;
	private boolean leader;
	
	public DiplomatImpl(String s,boolean leader) {
		this.leader=leader;
		String[] values=s.split(" ");
		country=values[0];
		languages=new ArrayList<String>();
		for (int t=0;t<values[1].length();t++) languages.add(String.valueOf(values[1].charAt(t)));
		relations=new ArrayList<String>();
		for (int t=0;t<values.length-2;t++) {
			relations.add(values[t+2]);
		}
	}
	
	public String getCountry() { return country; }
	public String[] getLanguages() { return languages.toArray(new String[languages.size()]); }
	public String[] getRelations() { return relations.toArray(new String[relations.size()]); }

	public boolean hasDiplomaticRelationsWith(Diplomat d) {
		if (d==null) throw new NullPointerException("NULL Diplomat.");
		if (getCountry().equals(d.getCountry())) return true;
		for (String r:relations) {
			if (d.getCountry().equals(r)) return true;
		}
		return false;
	}

	public boolean understands(Diplomat d) {
		if (d==null) throw new NullPointerException("NULL Diplomat.");
		String[] dl=d.getLanguages();
		for (String l:languages) {
			for (int t=0;t<dl.length;t++) {
				if (l.equals(dl[t])) return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isLeader() {
		return leader;
	}
	
	public String toString() {
		return "Diplomat of "+country+", speaking "+languages+", having diplomatic relations with "+relations+". "+(leader?"(leader)":"");
	}

}
