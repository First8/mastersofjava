
public class InternalSolver {

	public boolean seatDiplomats(Table table,Diplomat[] dip) {
		Diplomat leader=null;
		for (int t=0;t<dip.length;t++) {
			if (dip[t].isLeader()) {
				leader=dip[t];
				dip[t]=null;
				table.place(0,leader);
			}
		}
		if (leader==null) throw new NullPointerException("No leader");
		//
		return find(1,table,dip);
	}
	
	private boolean find(int spos,Table table,Diplomat[] dip) {
		if (spos==table.getNumberOfSeats()) return true;
		//
		for (int t=spos;t<dip.length;t++) {
			Diplomat prev=table.getDiplomatAt(t-1);
			Diplomat next=table.getDiplomatAt(t+1>=table.getNumberOfSeats()?0:t+1);
			if (prev==null) continue;
			for (int y=0;y<dip.length;y++) {
				Diplomat curr=dip[y];
				if (curr==null) continue;
				if (curr.hasDiplomaticRelationsWith(prev)) {
					if (curr.understands(prev)) {
						if (next==null||curr.understands(next)) {
							if (next==null||curr.hasDiplomaticRelationsWith(next)) {
								table.place(t,curr);
								dip[y]=null;
								//
								Diplomat[] copy=new Diplomat[dip.length];
								System.arraycopy(dip,0,copy, 0,dip.length);
								if (find(t+1,table,copy)) return true;
								//
								dip[y]=curr;
								table.place(t,null);
							}
						}
					}
				}
			}
		}		
		return false;
	}
	
}
