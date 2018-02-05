package unittests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import boundery.UserUI;

import java.io.IOException;
import java.util.ArrayList;
import controller.CancelOrderController;
import entity.Message;
import entity.Order;
import mypackage.ClientConsole;


public class CancelOrderControllerJUnitTest{

	/* Connection To Client */
	public static ClientConsole client ;

	/* Customer One */
	public static boolean Flag_Check_Order_One = false;                  	
	
	/* Customer Two */                   	
	public static boolean Flag_Check_Order_Two = false;					 	 
	   
	/* Customer Three */                    	
	public static boolean Flag_Check_Order_Three = false;				 	
	
	/* Customer Four */                               
	public static boolean Flag_Check_Order_Four = false;                    
	
	/* ArrayList For The Size Of The Order Table - For Each Test */
	public static ArrayList<Integer> Size_Of_Order_Table_For_Test = new ArrayList<Integer>();
	
	/* ArrayList For The Order Of Each Test */
	public static ArrayList<Order> Orders_For_Test = new ArrayList<Order>();
	
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		client = new ClientConsole("localhost", UserUI.DEFAULT_PORT_For_Client); 
		
		Message Message_One = new Message(Size_Of_Order_Table_For_Test,"Test - Give - Me The Size Of Order_Table");
		client.accept(Message_One);
	    
	    while(Size_Of_Order_Table_For_Test.size() == 0)
	    {
	    	try 
	    	{
	    		Thread.sleep(200);
	    	} 
	    	catch (InterruptedException e) 
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    
		/* -------------------------- We Insert - To The DB ---> Four New Order ---------------------------------------------------- */
		
	    Message Message_Two = new Message(Size_Of_Order_Table_For_Test,"Test - Update - The Order Table , Product In Order Table , Account Table"); 
		client.accept(Message_Two);
		while(Size_Of_Order_Table_For_Test.size() == 0)
		{
			try 
	    	{
	    		Thread.sleep(200);
	    	} 
	    	catch (InterruptedException e) 
	    	{
	    		e.printStackTrace();
	    	}
		}
	}
	

/*************************************************************************************************************************/
	
	/**
	 * I Want That This Check Will Be The Check Between 1 Hour To Three Hour .
	 * Account Balance Of Adi Madker = 1000 .
	 * After He Make New Order its Becoming To 950 . 
	 * After He Cancel The Order Its Will Be 975 . 
	 * Note - בגלל שההשוואה במטודה של ביטול - הזמנה זה על פי הזמן הנוכחי , אז כל פעם צריך לשנות ידנית את השעה שלה אני מצפה לבצע את המשלוח
	 */
	@Test
	public void testCancelOrder_For_Test_Between_One_Hour_To_Three_Hour() throws Exception 
	{
		Flag_Check_Order_One = true;
		
		Order Order_One = new Order();
		
		/* What I Expected */
		double Expected = 25.0; 
		double Delta = 0;
			
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_One);
		
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		System.out.println("--------------------------------------------------------------------------------");
		
		Flag_Check_Order_One = false;
		
		/* The Comparing */
		assertEquals(Expected,Actual,Delta);
	}
	
/*************************************************************************************************************************/
	
	/**
	 * I Want That This Check Will Be The Check Between 1 Hour To The Time Of Delivery .
	 * Account Balance Of Yoni Sphund = 500 .
	 * After He Make New Order its Becoming To 458 . 
	 * After He Cancel The Order Its Will Be 458 . 
	 * Note - בגלל שההשוואה במטודה של ביטול - הזמנה זה על פי הזמן הנוכחי , אז כל פעם צריך לשנות ידנית את השעה שלה אני מצפה לבצע את המשלוח
	 */
	@Test
	public void testCancelOrder_For_Test_Between_One_Hour_To_Time_Of_Delivery() 
	{
		
		Flag_Check_Order_Two = true;
	
		Order Order_Two = new Order();
		
		/* What I Expected */
		double Expected = 0.0;
		double Delta = 0;
		
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Two);
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		System.out.println("--------------------------------------------------------------------------------");
		
		Flag_Check_Order_Two = false;
		
		/* The Comparing */
		assertEquals(Expected,Actual,Delta);
	}
	
/*************************************************************************************************************************/
	
	/**
	 * I Want That This Check Will Be The Check More Than 3 Hours From Delivery .
	 * Account Balance Of Dolev Gal = 1000 .
	 * After He Make New Order its Becoming To 982 . 
	 * After He Cancel The Order Its Will Be 991 . 
	 * Note - בגלל שההשוואה במטודה של ביטול - הזמנה זה על פי הזמן הנוכחי , אז כל פעם צריך לשנות ידנית את השעה שלה אני מצפה לבצע את המשלוח
	 */
	@Test
	public void testCancelOrder_For_Test_After_Three_Hours() 
	{
		Flag_Check_Order_Three = true;
		
		Order Order_Three = new Order();
		
		/* What I Expected */
		double Expected = 18.0; 
		double Delta = 0;
			
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Three);
		
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		System.out.println("--------------------------------------------------------------------------------");
		
		Flag_Check_Order_Three = false;
		
		/* The Comparing */
		assertEquals(Expected,Actual,Delta);
	}
	
/*************************************************************************************************************************/
	
	/**
	 * I Want That This Check Will Be The Check More Than 3 Hours From Delivery .
	 * Account Balance Of Tomer Alon = 500 .
	 * After He Make New Order its Becoming To 440 . 
	 * After He Cancel The Order Its Will Be 470 . 
	 * Note - בגלל שההשוואה במטודה של ביטול - הזמנה זה על פי הזמן הנוכחי , אז כל פעם צריך לשנות ידנית את השעה שלה אני מצפה לבצע את המשלוח
	 */
	@Test
	public void testCancelOrder_For_Test_After_Three_Hours_Second_Check() 
	{
		Flag_Check_Order_Four = true;

		Order Order_Four = new Order();
		
		/* What I Expected */
		double Expected = 60.0; 
		double Delta = 0;
			
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Four);
		
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		System.out.println("--------------------------------------------------------------------------------");
		
		Flag_Check_Order_Four = false;
		
		/* The Comparing */
		assertEquals(Expected,Actual,Delta);
	}
	
	
	  
	@AfterClass
	public static void tearDownAfterClass() throws IOException
	{
		client.close();// method that inside clientConsole
	}
	
	
}
