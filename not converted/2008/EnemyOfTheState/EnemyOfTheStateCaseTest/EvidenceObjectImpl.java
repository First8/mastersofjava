
public class EvidenceObjectImpl implements EvidenceObject {

	private String name;
	
	public EvidenceObjectImpl(String name) {
		this.name=name;
	}
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
