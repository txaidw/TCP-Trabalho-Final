package bank.business.domain;

import java.util.Date;

/**
 * @author Ingrid Nunes
 * 
 */
public abstract class Transaction {
	
	public enum State {
		FINALIZED, PENDING, CANCELED;
	}

	private CurrentAccount account;
	private double amount;
	private Date date;
	private OperationLocation location;
	private State state;

	protected Transaction(OperationLocation location, CurrentAccount account,
			double amount) {
		this.location = location;
		this.date = new Date(System.currentTimeMillis());
		this.account = account;
		this.amount = amount;
		this.state = State.FINALIZED;
	}

	/**
	 * @return the account
	 */
	public CurrentAccount getAccount() {
		return account;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the location
	 */
	public OperationLocation getLocation() {
		return location;
	}

	/**
	 * This method is here for initializing the database.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
