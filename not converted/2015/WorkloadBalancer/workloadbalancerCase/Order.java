

public class Order {
	
	private int value;
	private String processor;
	
	public Order(Integer value, String processor) {
		this.value = value;
		this.processor = processor;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((processor == null) ? 0 : processor.hashCode());
		result = prime * result + value;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (processor == null) {
			if (other.processor != null)
				return false;
		} else if (!processor.equals(other.processor))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [value=" + value + ", processor=" + processor + "]";
	}
	
	

}
