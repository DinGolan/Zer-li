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
		CASH,CREDITCARD;	
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
