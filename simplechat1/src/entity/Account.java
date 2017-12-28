package entity;

public class Account {
	
	private int CustomerId;
	private double BalanceInCustomerAccount;
	private PaymentWay paymentWay;
	private Arrangement arrangement;
	private String NumberOfCreditCard;
	
	public enum PaymentWay {CREDIT , CASH}
	public enum Arrangement {FULLPRICE , MONTHLY , ANNUAL}
	
	/*public Account(int CustomerId , double BalanceInCustomerAccount , PaymentWay paymentWay , Arrangement arrangement , String NumberOfCreditCard)
	{
		this.CustomerId = CustomerId;
		this.BalanceInCustomerAccount = BalanceInCustomerAccount;
		this.paymentWay = paymentWay;
		this.arrangement = arrangement;
		this.NumberOfCreditCard = NumberOfCreditCard;
	}*/

	public Account() {

	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public double getBalanceInCustomerAccount() {
		return BalanceInCustomerAccount;
	}

	public void setBalanceInCustomerAccount(double balanceInCustomerAccount) {
		BalanceInCustomerAccount = balanceInCustomerAccount;
	}

	public PaymentWay getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(PaymentWay paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Arrangement getArrangement() {
		return arrangement;
	}

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

	public String getNumberOfCreditCard() {
		return NumberOfCreditCard;
	}

	public void setNumberOfCreditCard(String numberOfCreditCard) {
		NumberOfCreditCard = numberOfCreditCard;
	}

}
