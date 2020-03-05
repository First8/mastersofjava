import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Fugitive (Smill With) on the run from the Government.
 * After the Government has moved this object will be notified by the
 * GovernmentResults interface and is allows to make 1 counter move by
 * using the FugitiveAction interface. 
 */
public class Fugitive implements GovernmentResults,FugitiveResults {
    
    /**
     * represents an action to be performed in a certain state. 
     */
    private interface Action {
        /**
         * performs the action
         * @param st the state the action is performed in.
         * @return the new state after performing the action.
         */
        public State perform(State st);
    }
    
	public static enum State {
	    
		UNAWARE,RUNNING,HIDING,SEARCHING,CAPTURED,INNOCENT;
		
		private Map<String,State> transitions=new HashMap<String,State>();
		private Action a;
		/** sets the action for this state. */
		public void setAction(Action a) {
		    this.a=a;
		}
		/** links another state to this state via a named transition. */
		public void link(String transition,State target) {
		    transitions.put(transition,target);
		}
		/**
		 * takes the named transition if available.
		 * @param tr the named transition
		 * @return the state after the transition was taken.
		 */
		public State take(String tr) {
		    return transitions.containsKey(tr)?transitions.get(tr):this;
		}
		/**
		 * executes the action belonging to this state.
		 * @return the resulting state of this action.
		 */
		public State execute() {
		    return a==null?this:a.perform(this);
		}
	}
	
	/** the current state of the Fugitive. */
	private State current;
	/** the possible actions the Fugitive can take. */
	private FugitiveAction action;
	/** the found evidence. */
	private List<EvidenceObject> evidence=new ArrayList<EvidenceObject>();
	
	
	public Fugitive(FugitiveAction fugitiveAction) {
		this.action=fugitiveAction;
		this.current=State.UNAWARE;
	}
	
	//
	// Government actions.
	//
	
	public void onFound(int distance) {
	    //
	    // TODO: Implement or Delegate
	    //
	}
	public void onPursuit(int distance) {
        //
        // TODO: Implement or Delegate
        //
	}
	public void onSearching() {
        //
        // TODO: Implement or Delegate
        //
	}
    public void onIsInnocent() {
        //
        // TODO: Implement or Delegate
        //
    }
    public void onCaptured() {
        //
        // TODO: Implement or Delegate
        //
    }
	
	//
	// Fugitive action results.
	//
	
	public void onObjectFound(EvidenceObject obj) {
		this.evidence.add(obj);
	}
	
}
