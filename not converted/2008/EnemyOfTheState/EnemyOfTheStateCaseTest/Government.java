
public class Government implements GovernmentNotifications {

	public static enum State {
		SEARCHING,PURSUIT,CAPTURED,CONVICED;
	}
	
	private GovernmentAction action;
	private State state=State.SEARCHING;
	
	public Government(GovernmentAction action) {
		this.action=action;
	}
	
	public void onFound() {
		state=State.PURSUIT;
	}
	
	public void onLost() {
		state=State.SEARCHING;
	}
	
	public void onCaptured() {
		state=State.CAPTURED;
	}

	public void onConvinced() {
		state=State.CONVICED;
	}

	public void onDo() {
		if (State.PURSUIT.equals(state)) {
			action.doPersuit();
		} else if (State.SEARCHING.equals(state)) {
			action.doSearchForFugitive();
		}
	}
	
}
