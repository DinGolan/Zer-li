package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Entity class for the Account with the enum, get,set, enum
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


	public String getAccountUserId() { //accountUserId getter
		return accountUserId;
	}

	public void setAccountUserId(String accountUserId) { //accountUserId setter
		this.accountUserId = accountUserId;
	}

	public PaymentArrangement getAccountPaymentArrangement() { //accountPaymentArrangement getter
		return accountPaymentArrangement;
	}

	public void setAccountPaymentArrangement(PaymentArrangement accountPaymentArrangement) { //accountPaymentArrangement setter
		this.accountPaymentArrangement = accountPaymentArrangement;
	}

	public double getAccountBalanceCard() { //accountBalanceCard getter
		return accountBalanceCard;
	}

	public void setAccountBalanceCard (double accountBalanceCard) { //accountBalanceCard setter
		this.accountBalanceCard = accountBalanceCard;
	}

	public String getAccountCreditCardNum() { //accountCreditCardNum getter
		return accountCreditCardNum;
	}

	public void setAccountCreditCardNum(String accountCreditCardNum) { //accountCreditCardNum setter
		this.accountCreditCardNum = accountCreditCardNum;
	}
	

	public Date getAccountSubscriptionEndDate() { //accountSubscriptionEndDate getter
		return accountSubscriptionEndDate;
	}

	public void setAccountSubscriptionEndDate(Date date) { //accountSubscriptionEndDate setter
		this.accountSubscriptionEndDate = date;
	}

	public int getAccountStoreNum() {
		return accountStoreNum;
	}

	public void setAccountStoreNum(int accountStoreNum) {
		this.accountStoreNum = accountStoreNum;
	}

	@Override
	public String toString() {
		return "Account [accountUserId=" + accountUserId + ", accountPaymentArrangement=" + accountPaymentArrangement
				+ ", accountBalanceCard=" + accountBalanceCard + ", accountCreditCardNum=" + accountCreditCardNum
				+ ", accountSubscriptionEndDate=" + accountSubscriptionEndDate + ", accountStoreNum=" + accountStoreNum
				+ "]";
	}
	
}
