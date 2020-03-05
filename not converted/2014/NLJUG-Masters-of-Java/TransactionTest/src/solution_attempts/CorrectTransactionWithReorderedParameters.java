package solution_attempts;

import java.math.BigDecimal;
import java.util.Calendar;

public final class CorrectTransactionWithReorderedParameters implements Comparable<CorrectTransactionWithReorderedParameters> {
	private final BigDecimal amount;
	private final Calendar date;
	private final String description;
	
	public CorrectTransactionWithReorderedParameters(Calendar date, String description, BigDecimal amount) {
		if (amount == null) throw new NullPointerException("amount");
		if (date == null) throw new NullPointerException("date");
		if (description == null) throw new NullPointerException("description");
		
		this.amount = amount;
		this.date = (Calendar)date.clone();
		this.description = description;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Calendar getDate() {
		return (Calendar)date.clone();
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CorrectTransactionWithReorderedParameters)) {
			return false;
		}
		CorrectTransactionWithReorderedParameters other = (CorrectTransactionWithReorderedParameters)obj;
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
		String dateString = "" + date.get(Calendar.DAY_OF_MONTH) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.YEAR);
		return "Transaction: [amount=" + amount + ", date=" + dateString + ", description=" + description + "]";
	}
	
	@Override
	public int compareTo(CorrectTransactionWithReorderedParameters obj) {
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
