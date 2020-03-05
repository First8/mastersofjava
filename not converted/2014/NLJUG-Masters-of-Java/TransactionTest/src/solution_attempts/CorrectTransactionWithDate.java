package solution_attempts;

import java.math.BigDecimal;
import java.util.Date;

public final class CorrectTransactionWithDate implements Comparable<CorrectTransactionWithDate> {
	private final BigDecimal amount;
	private final Date date;
	private final String description;
	
	public CorrectTransactionWithDate(BigDecimal amount, Date date, String description) {
		if (amount == null) throw new NullPointerException("amount");
		if (date == null) throw new NullPointerException("date");
		if (description == null) throw new NullPointerException("description");
		
		this.amount = amount;
		this.date = (Date)date.clone();
		this.description = description;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Date getDate() {
		return (Date)date.clone();
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CorrectTransactionWithDate)) {
			return false;
		}
		CorrectTransactionWithDate other = (CorrectTransactionWithDate)obj;
		return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		result = (59 * result) + amount.hashCode();
		result = (59 * result) + date.hashCode();
		result = (59 * result) + description.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		return "Transaction: [amount=" + amount + ", date=" + date + ", description=" + description + "]";
	}
	
	@Override
	public int compareTo(CorrectTransactionWithDate obj) {
		int dateDiff = obj.date.compareTo(date);
		if (dateDiff != 0) {
			return dateDiff;
		}
		int amountDiff = amount.compareTo(obj.amount);
		if (amountDiff != 0) {
			return amountDiff;
		}
		return description.compareTo(obj.description);
	}
}
