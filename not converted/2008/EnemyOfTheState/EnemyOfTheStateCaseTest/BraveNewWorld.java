import java.util.ArrayList;
import java.util.List;


public class BraveNewWorld implements FugitiveAction, GovernmentAction {
	
	public static enum FugitiveState {
		UNAWARE,RUNNING,HIDING,SEARCHING,CAPTURED,INNOCENT
	}
	
	public static enum GovernmentState {
		SEARCHING,PURSUIT,CAPTURED,CONVICED;
	}
	
	private static final String[] EVIDENCE=new String[] {
		"MemoryCard","Photo","Report"
	};
	
	private Government g;
	private Fugitive e;
	private List<EvidenceObject> foundEvidence=new ArrayList<EvidenceObject>();
	private List<EvidenceObject> usedEvidence=new ArrayList<EvidenceObject>();
	private GovernmentState gState=GovernmentState.SEARCHING;
	private FugitiveState fState=FugitiveState.INNOCENT;
	private boolean finished;
	private int distance=4;
	private int distanceAfterHide=7;
	private int runSpeed=2;
	private boolean playerMoved;
	
	public BraveNewWorld(int distance,int hideDistance,int runSpeed) {
		super();
		this.distance=distance;
		this.distanceAfterHide=hideDistance;
		this.runSpeed=runSpeed;
	}
	
	private void checkPlayerMoved() {
	    if (playerMoved) throw new RuntimeException("Can only move once per Government action.");
	    playerMoved=true;
	}
	
	private void resetPlayerMoved() {
	    playerMoved=false;
	}
	
	
	public void set(Government g,Fugitive e) {
		this.g=g;
		this.e=e;
	}
	
	//
	// Fugitive Actions
	//
	
	@Override
	public void doGoToEvidenceLocation() {
		if (!FugitiveState.HIDING.equals(fState)) throw new RuntimeException("Invalid state ("+fState+") for operation 'doGoToEvidenceLocation'");
		checkPlayerMoved();
		System.out.println("F: Goes to an Evidence Location.");
		fState=FugitiveState.SEARCHING;		
	}
	
	@Override
	public void doHide() {
		if (!FugitiveState.RUNNING.equals(fState)) throw new RuntimeException("Invalid state ("+fState+") for operation 'doHide'");
        checkPlayerMoved();
		System.out.println("F: Attempts to hide.");
		if (distance>=3) {
			distance=distanceAfterHide;
			fState=FugitiveState.HIDING;
			gState=GovernmentState.SEARCHING;
			g.onLost();
			//e.onSearching();
		} else {
			throw new RuntimeException("Can't hide at distance ("+distance+")");
		}
	}
	@Override
	public void doRun() {
		if (!FugitiveState.RUNNING.equals(fState)) throw new RuntimeException("Invalid state ("+fState+") for operation 'doRun'");
        checkPlayerMoved();
		System.out.println("F: Attempts to escape.");
		distance+=runSpeed;		
	}
	
	@Override
	public void doUse(EvidenceObject evidenceObject) {
		if ((FugitiveState.SEARCHING.equals(fState))||(FugitiveState.HIDING.equals(fState))) {
			//
	        checkPlayerMoved();
			System.out.println("F: Uses the "+evidenceObject+" as evidence.");
			//			
			if (foundEvidence.contains(evidenceObject)) {
				if (!usedEvidence.contains(evidenceObject)) {
					usedEvidence.add(evidenceObject);
					if (usedEvidence.size()==3) {
						System.out.println("F: The Fugutive proves his innocence!");
						gState=GovernmentState.CONVICED;
						fState=FugitiveState.INNOCENT;
						g.onConvinced();
						e.onIsInnocent();
						finished=true;
					}
				} else {
					throw new RuntimeException("'"+evidenceObject+"' was already used.");
				}
			}
			//
		} else throw new RuntimeException("Invalid state ("+fState+") for operation 'doUse'");
		
	}

	@Override
	public void doSearch() {
		if ((FugitiveState.SEARCHING.equals(fState))) {
			//
	        checkPlayerMoved();
			if (foundEvidence.size()<3) {
				EvidenceObject obj=new EvidenceObjectImpl(EVIDENCE[foundEvidence.size()]);
				System.out.println("F: Searches for evidence and finds an "+obj+".");
				foundEvidence.add(obj);
				e.onObjectFound(obj);
				//
			}
			//
		} else throw new RuntimeException("Invalid state ("+fState+") for operation 'doSearch'");
			
	}

	//
	// Government Actions
	//
	
	@Override
	public void doSearchForFugitive() {
		if (!GovernmentState.SEARCHING.equals(gState)) throw new RuntimeException("Bad government action.");
		//
        resetPlayerMoved();
		distance--;
		if (distance<=3) {
			System.out.println("G: The government searches and finds the Fugitive!");
			gState=GovernmentState.PURSUIT;
			fState=FugitiveState.RUNNING;
			g.onFound();
			distance=1;
			e.onFound(distance);
		} else {
			System.out.println("G: The government searches...");
			e.onSearching();
		}
		//
	}
	@Override
	public void doPersuit() {
		if (!GovernmentState.PURSUIT.equals(gState)) throw new RuntimeException("Bad government action.");
		//
        resetPlayerMoved();
        //
		distance--;
		//
		if (distance==0) {
			System.out.println("G: The government catches the Fugitive!");
			gState=GovernmentState.CAPTURED;
			fState=FugitiveState.CAPTURED;
			e.onCaptured();
			g.onCaptured();
			finished=true;
 		} else {
			System.out.println("G: The government decreases the distance to the Fugitive.");
			e.onPursuit(distance);
 		}
		//
	}
	
	public boolean isFinished () {
		return finished;
	}
	
	public boolean isWonByGovernment() {
		return GovernmentState.CAPTURED.equals(gState);
	}
	public boolean isWonByFugitive() {
		return FugitiveState.INNOCENT.equals(fState);
	}

}
