
public class TableImpl implements Table {

	private Diplomat[] table;
	
	public TableImpl(int size) {
		table=new Diplomat[size];
	}
	
	@Override
	public Diplomat getDiplomatAt(int position) {
		if ((position<0)||(position>=table.length)) throw new ArrayIndexOutOfBoundsException("Invalid table position "+position+" for table size "+table.length+".");
		return table[position];
	}
	
	@Override
	public int getNumberOfSeats() {
		return table.length;
	}
	@Override
	public void place(int position, Diplomat d) {
		if ((position<0)||(position>=table.length)) throw new ArrayIndexOutOfBoundsException("Invalid table position "+position+" for table size "+table.length+".");
		table[position]=d;
	}
	
	public boolean isFull() {
		for (Diplomat d:table) {
			if (d==null) return false;
		}
		return true;
	}
	
	@Override
	public boolean isSeatTaken(int position) {
		return getDiplomatAt(position)!=null;
	}
	
	boolean check() {
		return checkFull()&&checkDuplicates()&&checkLeader()&&checkLanguages()&&checkRelations();		
	}
	
	boolean checkLeader() {
		if (!getDiplomatAt(0).isLeader()) {
			System.out.println("The leader is not sitting at the head of the table.");
			return false;
		} 
		return true;
	}
	
	boolean checkFull() {
		if (!isFull()) {
			System.out.println("Not all seats are taken.");
			return false;
		}
		return true;
	}
	
	boolean checkDuplicates() {
		for (int t=0;t<table.length;t++) {
			for (int y=0;y<table.length;y++) {
				if (t==y) continue;
				if (table[t].equals(table[y])) {
					System.out.println("Diplomat is seated twice : #"+t+" #"+y+" : "+table[t]);
					return false;
				}
			}
		}
		return true;
	}
	
	boolean checkLanguages() {
		for (int t=0;t<table.length;t++) {
			Diplomat one=table[t];
			Diplomat two=table[(t+1)%table.length];
			if (!one.understands(two)) {
				System.out.println("Two adjacent diplomats do not understand each other.");
				System.out.println("#"+t+" "+one);
				System.out.println("#"+(t+1%table.length)+" "+two);
				return false;
			}
		}
		return true;
	}
	
	boolean checkRelations() {
		for (int t=0;t<table.length;t++) {
			Diplomat one=table[t];
			Diplomat two=table[(t+1)%table.length];
			if (!one.hasDiplomaticRelationsWith(two)) {
				System.out.println("Two adjacent diplomats countries have no relation with each other.");
				System.out.println("#"+t+" "+one);
				System.out.println("#"+(t+1%table.length)+" "+two);				
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("Nr pR pL nR nL Diplomat\n");
		for (int t=0;t<table.length;t++) {
			Diplomat prev=(t-1<0?table[table.length+t-1]:table[t-1]);
			Diplomat next=table[(t+1)%table.length];
			Diplomat curr=table[t];
			sb.append("#"+t);
			if (table[t]==null) {
				sb.append(" .. .. .. .. Empty");
				sb.append("\n");
			} else {
				if (prev==null) {
					sb.append(" .. ..");
				} else {
					sb.append(prev.hasDiplomaticRelationsWith(curr)?" vv":" XX");
					sb.append(prev.understands(curr)?" vv":" XX");
				}
				if (next==null) {
					sb.append(" .. ..");					
				} else {
					sb.append(next.hasDiplomaticRelationsWith(curr)?" vv":" XX");
					sb.append(next.understands(curr)?" vv":" XX");
				}
				sb.append(" ");
				sb.append(String.valueOf(curr));
				sb.append("\n");
			}
		}
		return sb.toString();		
	}
	
}
