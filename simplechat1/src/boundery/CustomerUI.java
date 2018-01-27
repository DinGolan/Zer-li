package boundery;

import java.util.Vector;

import entity.Account;
import entity.Complaint;
import entity.Order;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * class to save static variables that belong to the customer
 */
public class CustomerUI extends Application /* With This Class We Show the Product GUI */
{
	public static Account account = new Account();
	public static Vector<Order> Order_Of_Specific_Customer = new Vector<Order>();
	public static Vector<Complaint> Complaint_Of_Specific_Customer = new Vector<Complaint>();
	public static Vector<Account> Account_Of_Specific_Customer = new Vector<Account>();
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		

	}
	
}

