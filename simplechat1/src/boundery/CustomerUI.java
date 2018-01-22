package boundery;

import java.util.Vector;

import entity.Account;
import entity.Complaint;
import entity.Order;
import javafx.application.Application;
import javafx.stage.Stage;

public class CustomerUI extends Application /* With This Class We Show the Product GUI */
{
	public static Account account = new Account();
	public static Vector<Order> Order_Of_Specific_Customer = new Vector<Order>();
	public static Vector<Complaint> Complaint_Of_Specific_Customer = new Vector<Complaint>();
	public static Vector<Account> Account_Of_Specific_Customer = new Vector<Account>();
	
	public static void main( String args[] ) throws Exception
	{ 

	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		

	}
	
}

