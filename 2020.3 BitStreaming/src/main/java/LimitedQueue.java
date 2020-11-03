import java.util.LinkedList;

class LimitedQueue<E> extends LinkedList<E> {
	private int limit;
	private static final long serialVersionUID = 1L;

	LimitedQueue(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean add(E o) {
		super.add(o);
		// remove first item when above limit
		while (size() > limit) { super.remove(); }
		return true;
	}
}