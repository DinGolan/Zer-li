package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity class for the Account with the enum,get,set,variables
 */
public class Account implements Serializable{

	private String accountUserId; //save the userId for this account
	public PaymentArrangement accountPaymentArrangement;
	private double accountBalanceCard;
	private String accountCreditCardNum;
	private Date accountSubscriptionEndDate;
	private int accountStoreNum;
	
	/**
	 * enum to choose the payment arrangement
	 */
	public enum PaymentArrangement{ //enum for the payment arrangement
		FULLPRICE,MONTHLY,ANNUAL
	}
	
	/**
	 * enum to choose the payment Method
	 */
	public enum PaymentMethod{ //enum for the payment method
		CASH,CREDITCARD;	
	}	
	
	/**
	 * auto empty acoount constructor
	 */
	public Account() {
		super();
	}

	/**
	 * auto fiiled acoount constructor
	 */
	public Account(String accountUserId, PaymentArrangement accountPaymentArrangement, double accountBalanceCard,
			String accountCreditCardNum, Date accountSubscriptionEndDate, int accountStoreNum) {
		super();
		this.accountUserId = accountUserId;
		this.accountPaymentArrangement = accountPaymentArrangement;
		this.accountBalanceCard = accountBalanceCard;
		this.accountCreditCardNum = accountCreditCardNum;
		this.accountSubscriptionEndDate = accountSubscriptionEndDate;
		this.accountStoreNum = accountStoreNum;
	}

/**
 * account UserId getter
 * @return int- UserId
 */
	public String getAccountUserId() { //accountUserId getter
		return accountUserId;
	}

	/**
	 * account UserId setter
	 * @param accountUserId- string
	 */
	public void setAccountUserId(String accountUserId) { //accountUserId setter
		this.accountUserId = accountUserId;
	}

	/**
	 * accountPaymentArrangement getter
	 * @return enum- accountPaymentArrangement
	 */
	public PaymentArrangement getAccountPaymentArrangement() { //accountPaymentArrangement getter
		return accountPaymentArrangement;
	}
	
	/**
	 * accountPaymentArrangement setter
	 * @param accountPaymentArrangement- enum
	 */
	public void setAccountPaymentArrangement(PaymentArrangement accountPaymentArrangement) { //accountPaymentArrangement setter
		this.accountPaymentArrangement = accountPaymentArrangement;
	}

	/**
	 * accountBalanceCard getter
	 * @return double- accountBalanceCard
	 */
	public double getAccountBalanceCard() { //accountBalanceCard getter
		return accountBalanceCard;
	}

	/**
	 * accountBalanceCard setter
	 * @param accountBalanceCard- double
	 */
	public void setAccountBalanceCard (double accountBalanceCard) { //accountBalanceCard setter
		this.accountBalanceCard = accountBalanceCard;
	}

	/**
	 * accountCreditCardNum getter
	 * @return String-accountCreditCardNum
	 */
	public String getAccountCreditCardNum() { //accountCreditCardNum getter
		return accountCreditCardNum;
	}

	/**
	 * accountCreditCardNum setter
	 * @param accountCreditCardNum- String
	 */
	public void setAccountCreditCardNum(String accountCreditCardNum) { //accountCreditCardNum setter
		this.accountCreditCardNum = accountCreditCardNum;
	}
	
	/**
	 * accountSubscriptionEndDate getter
	 * @return Date-accountSubscriptionEndDate
	 */
	public Date getAccountSubscriptionEndDate() { //accountSubscriptionEndDate getter
		return accountSubscriptionEndDate;
	}

	/**
	 * accountSubscriptionEndDate setter
	 * @param date- date
	 */
	public void setAccountSubscriptionEndDate(Date date) { //accountSubscriptionEndDate setter
		this.accountSubscriptionEndDate = date;
	}

	/**
	 * AccountStoreNum getter
	 * @return int- accountStoreNum
	 */
	public int getAccountStoreNum() {
		return accountStoreNum;
	}
	
	/**
	 * AccountStoreNum setter
	 * @param accountStoreNum- int
	 */
	public void setAccountStoreNum(int accountStoreNum) {
		this.accountStoreNum = accountStoreNum;
	}

	/**
	 * override toString method for Account entity
	 */
	@Override
	public String toString() {
		return "Account [accountUserId=" + accountUserId + ", accountPaymentArrangement=" + accountPaymentArrangement
				+ ", accountBalanceCard=" + accountBalanceCard + ", accountCreditCardNum=" + accountCreditCardNum
				+ ", accountSubscriptionEndDate=" + accountSubscriptionEndDate + ", accountStoreNum=" + accountStoreNum
				+ "]";
	}
	
}
