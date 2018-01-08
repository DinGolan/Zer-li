package entity;

import java.io.Serializable;
import java.sql.Date;

public class Account implements Serializable{

	private String accountUserId; //save the userId for this account
	public PaymentArrangement accountPaymentArrangement;
	public PaymentMethod accountPaymentMethod;
	private double accountBalanceCard;
	private String accountCreditCardNum;
	private Date accountSubscriptionEndDate;
	
	public enum PaymentArrangement{ //enum for the payment arrangement
		FULLPRICE,MONTHLY,ANNUAL
	}
	public enum PaymentMethod{ //enum for the payment method
		CASH,CREDIT;	
	}
	
	/*public Account() { //constructor
		//super();
		this.accountUserId = null;
		this.accountPaymentArrangement = PaymentArrangement.FULLPRICE;
		this.accountPaymentMethod = PaymentMethod.CASH;
		this.accountBalanceCard = 0;
		this.accountCreditCardNum = null;
		this.accountSubscriptionEndDate = null;	
	}*/

	/*public Account(String accountUserId, PaymentArrangement accountPaymentArrangement,
			PaymentMethod accountPaymentMethod, double accountBalanceCard, String accountCreditCardNum,
			Date accountSubscriptionEndDate) {
		super();
		this.accountUserId = accountUserId;
		this.accountPaymentArrangement = accountPaymentArrangement;
		this.accountPaymentMethod = accountPaymentMethod;
		this.accountBalanceCard = accountBalanceCard;
		this.accountCreditCardNum = accountCreditCardNum;
		this.accountSubscriptionEndDate = accountSubscriptionEndDate;
	}*/

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

	public PaymentMethod getAccountPaymentMethod() { //accountPaymentMethod getter
		return accountPaymentMethod;
	}

	public void setAccountPaymentMethod(PaymentMethod accountPaymentMethod) { //accountPaymentMethod setter
		this.accountPaymentMethod = accountPaymentMethod;
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

	@Override
	public String toString() {
		return "Account [accountUserId=" + accountUserId + ", accountPaymentArrangement=" + accountPaymentArrangement
				+ ", accountPaymentMethod=" + accountPaymentMethod + ", accountBalanceCard=" + accountBalanceCard
				+ ", accountCreditCardNum=" + accountCreditCardNum + ", accountSubscriptionEndDate="
				+ accountSubscriptionEndDate + "]";
	}	
	
}
