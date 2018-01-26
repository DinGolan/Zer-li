package entity;

import java.sql.Date;

import entity.Account.PaymentArrangement;

/**
 * This Class For Inserting Details To The TabelView Of Account In The Profile Customer  . 
 * @author dingo
 *
 */
public class CustomerAccountDetailsRow {

	private int Account_Store_ID;
	
	private double Balance_Card;
	
	private Account.PaymentArrangement Payment_Arrangment;
	
	private String Credit_Card_Number;
	
	private Date End_Date;

	public CustomerAccountDetailsRow() 
	{
		
	}
	
	public CustomerAccountDetailsRow(int account_Store_ID, double balance_Card, PaymentArrangement payment_Arrangment,
			String credit_Card_Number, Date end_Date) {
		super();
		Account_Store_ID = account_Store_ID;
		Balance_Card = balance_Card;
		Payment_Arrangment = payment_Arrangment;
		Credit_Card_Number = credit_Card_Number;
		End_Date = end_Date;
	}

	public int getAccount_Store_ID() {
		return Account_Store_ID;
	}

	public void setAccount_Store_ID(int account_Store_ID) {
		Account_Store_ID = account_Store_ID;
	}

	public double getBalance_Card() {
		return Balance_Card;
	}

	public void setBalance_Card(double balance_Card) {
		Balance_Card = balance_Card;
	}

	public Account.PaymentArrangement getPayment_Arrangment() {
		return Payment_Arrangment;
	}

	public void setPayment_Arrangment(Account.PaymentArrangement payment_Arrangment) {
		Payment_Arrangment = payment_Arrangment;
	}

	public String getCredit_Card_Number() {
		return Credit_Card_Number;
	}

	public void setCredit_Card_Number(String credit_Card_Number) {
		Credit_Card_Number = credit_Card_Number;
	}

	public Date getEnd_Date() {
		return End_Date;
	}

	public void setEnd_Date(Date end_Date) {
		End_Date = end_Date;
	}

	@Override
	public String toString() {
		return "CustomerAccountDetailsRow [Account_Store_ID=" + Account_Store_ID + ", Balance_Card=" + Balance_Card
				+ ", Payment_Arrangment=" + Payment_Arrangment + ", Credit_Card_Number=" + Credit_Card_Number
				+ ", End_Date=" + End_Date + "]";
	}
}
