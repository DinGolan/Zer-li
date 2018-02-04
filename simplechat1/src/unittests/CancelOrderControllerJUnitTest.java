package unittests;

import org.junit.Before;
import org.junit.Test;

import boundery.UserUI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import controller.CancelOrderController;
import entity.Message;
import entity.Order;
import mypackage.ClientConsole;
import mypackage.EchoServer;
import junit.framework.TestCase;

public class CancelOrderControllerJUnitTest extends TestCase {

	/**
	 * The Server . 
	 */
	public EchoServer Server;
	
	/**
	 * Connection To The Client .
	 */
	public static ClientConsole client;
	
	/**
	 * ArrayList Of Order's . 
	 */
	private ArrayList<String> SQL_Queries_Order = new ArrayList <String>();
	
	/**
	 * ArrayList Of Product In Order . 
	 */
	private ArrayList <String> SQL_Queries_Product_In_Order = new ArrayList <String>();
	
	/**
	 * ArrayList Of Customer ID & Price Of Order . 
	 */
	private ArrayList <String> Customer_ID = new ArrayList<String>();
	private ArrayList <String> Price_Of_Order = new ArrayList<String>();
	private ArrayList <String> Stores_ID = new ArrayList<String>();	
	
	/* The Size Of The Table */
	public  static ArrayList <Integer> Size_Of_Tabels = new ArrayList<Integer>();
	
	/* Vector That Contain All The ArrayList For Update */
	public  static Vector<ArrayList<String>> Update_Tables_For_Test = new Vector<ArrayList<String>>();
	
	/**
	 * The Name Of The Scheme .
	 */
	String  Scheme_Name = "project";
	
	/* Specific Order To Check */
	public static Order Order_For_Test_One;
	public static Order Order_For_Test_Two;
	public static Order Order_For_Test_Three;
	public static Order Order_For_Test_Four;
	
	public static double Customer_Balance_For_Test = 0;
	
	/* Flag's */
	public static boolean Flag_For_Taking_Specific_Order = false;
	public static boolean Flag_For_Taking_Customer_Balance = false;
	public static boolean Flag_Finish_Update = false;
	
	public static boolean Flag_Check_Order_One = false;
	public static boolean Flag_Check_Order_Two = false;
	public static boolean Flag_Check_Order_Three = false;
	public static boolean Flag_Check_Order_Four = false;
	
	
	
	int Number_Of_Rows_In_Order_Table = 0;
	int Number_Of_Rows_In_Product_In_Table_Order = 0;
	
	LocalTime nowTime;
	LocalDate localDate;
	
	@Before
	public void setUp() throws Exception 
	{
//		if(Flag_Finish_Update == false) 
//		{
			super.setUp();
			
			nowTime = LocalTime.parse(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())); /* Get The Current Time */
			localDate = LocalDate.now();
			DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String Formatted_Date_String = localDate.format(Formatter);
			
			/* Server = new EchoServer(UserUI.DEFAULT_PORT_For_Client); */
			/* Server.connectToDB("project" , "root" , "Dingolan203247697"); */
			client = new ClientConsole("localhost", UserUI.DEFAULT_PORT_For_Client); 
			
			Message Message_One = new Message(Size_Of_Tabels,"Test - Give - Me The Size Of Order_Table And Product_In_Order_Table");
			client.accept(Message_One);
			while(Size_Of_Tabels.size() == 0);
			Thread.sleep(200);
			
			Number_Of_Rows_In_Order_Table = Size_Of_Tabels.get(0);            /* The Size Of Order Table */
			Number_Of_Rows_In_Product_In_Table_Order = Size_Of_Tabels.get(1); /* The Size Of Product_In_Order Table */
			
			
			/* We Insert 4 Queries - For Testing ---> All The Possibilities */
			
			/* Between One Hour To Three Hour From The Final Time To Make Delivery */
			String First_New_Order =  "INSERT INTO " + Scheme_Name + ".`order`(`orderID`, `customerID`, `orderSupplyOption`, `orderTotalPrice`, `orderRequiredSupplyDate`, `orderRequiredSupplyTime`, `orderRecipientAddress`, `orderRecipientName`, `orderRecipientPhoneNumber`, `orderPostcard`, `orderDate`, `StoreID`, `paymentMethod`, `orderStatus`, `orderRefund`) VALUES " + "(" + "'" + (++Number_Of_Rows_In_Order_Table) + "'" + ", '308155146', 'DELIVERY', '50', " + "'" + Formatted_Date_String + "'" + ", " + "'" + nowTime.plusHours(2) + "'" + ", 'Haifa', 'Mati Golany', '0545250777', 'Thank You', " + "'" + Formatted_Date_String + "'" + ", '1', 'CASH', 'APPROVED', '0');" ;
			String Product_First_New_Order = "INSERT INTO " + Scheme_Name + ".`productinorder` (`ProductID`, `OrderID`, `QuantityOfProduct`, `ProductType`, `ProductName`, `productPrice`) VALUES ('1234', " + "'" + Number_Of_Rows_In_Order_Table + "'" + ", '1', 'BOUQUET', 'Romantic Drops', '50');" ;
			
			/* Between One Hour To The Final Time To Make Delivery */
			String Second_New_Order = "INSERT INTO " + Scheme_Name + ".`order`(`orderID`, `customerID`, `orderSupplyOption`, `orderTotalPrice`, `orderRequiredSupplyDate`, `orderRequiredSupplyTime`, `orderRecipientAddress`, `orderRecipientName`, `orderRecipientPhoneNumber`, `orderPostcard`, `orderDate`, `StoreID`, `paymentMethod`, `orderStatus`, `orderRefund`) VALUES " + "(" + "'" + (++Number_Of_Rows_In_Order_Table) + "'" + ", '308155127', 'DELIVERY', '42', " + "'" + Formatted_Date_String + "'" + ", " + "'" + nowTime.plusMinutes(30) + "'" + ", 'Tel - Aviv', 'Alon Golany', '0545254564', 'Thank You', " + "'" + Formatted_Date_String + "'" + ", '4', 'CASH', 'APPROVED', '0');" ;
			String Product_Second_New_Order = "INSERT INTO " + Scheme_Name + ".`productinorder` (`ProductID`, `OrderID`, `QuantityOfProduct`, `ProductType`, `ProductName`, `productPrice`) VALUES ('1235', " + "'" + Number_Of_Rows_In_Order_Table + "'" + ", '1', 'BOUQUET', 'Sapphire', '42');" ;
			
			/* More Than Three Hour's From The Final Time To Make Delivery - אני משער שאתם תהיו הרבה לפני שעת שליחת ההזמנה */
			String Thired_New_Order = "INSERT INTO " + Scheme_Name + ".`order` (`orderID`, `customerID`, `orderSupplyOption`, `orderTotalPrice`, `orderRequiredSupplyDate`, `orderRequiredSupplyTime`, `orderRecipientAddress`, `orderRecipientName`, `orderRecipientPhoneNumber`, `orderPostcard`, `orderDate`, `StoreID`, `paymentMethod`, `orderStatus`, `orderRefund`) VALUES " + "(" + "'" + (++Number_Of_Rows_In_Order_Table) + "'" + ", '308155128', 'DELIVERY', '18', " + "'" + Formatted_Date_String + "'" + ", " + "'" + nowTime.plusHours(7) + "'" + ", 'Lod', 'Malki Golany', '0545252343', 'Thank You', " + "'" + Formatted_Date_String + "'" + ", '3', 'CASH', 'APPROVED', '0');" ;
			String Product_Thired_New_Order = "INSERT INTO " + Scheme_Name + ".`productinorder` (`ProductID`, `OrderID`, `QuantityOfProduct`, `ProductType`, `ProductName`, `productPrice`) VALUES ('1236', " + "'" + Number_Of_Rows_In_Order_Table + "'" + ", '1', 'BOUQUET', 'Cotton', '18');" ;
			
			/* More Than Three Hour's From The Final Time To Make Delivery - אני משער שאתם תהיו הרבה לפני שעת שליחת ההזמנה */
			String Fourth_New_Order = "INSERT INTO " + Scheme_Name + ".`order` (`orderID`, `customerID`, `orderSupplyOption`, `orderTotalPrice`, `orderRequiredSupplyDate`, `orderRequiredSupplyTime`, `orderRecipientAddress`, `orderRecipientName`, `orderRecipientPhoneNumber`, `orderPostcard`, `orderDate`, `StoreID`, `paymentMethod`, `orderStatus`, `orderRefund`) VALUES " + "(" + "'" + (++Number_Of_Rows_In_Order_Table) + "'" + ", '308155160', 'DELIVERY', '60', " + "'" + Formatted_Date_String + "'" + ", " + "'" + nowTime.plusHours(5) + "'" + ", 'Jerusalem', 'Itay Golany', '0545255555', 'Thank You', " + "'" + Formatted_Date_String + "'" + ", '2', 'CASH', 'APPROVED', '0');" ;
			String Product_Fourth_New_Order = "INSERT INTO " + Scheme_Name + ".`productinorder` (`ProductID`, `OrderID`, `QuantityOfProduct`, `ProductType`, `ProductName`, `productPrice`) VALUES ('1237', " + "'" + Number_Of_Rows_In_Order_Table + "'" + ", '1', 'BOUQUET', 'Light of the North', '60');" ;
			
			/* SQL Queries - For Insert 4 New Order */
			SQL_Queries_Order.add(First_New_Order);
			SQL_Queries_Order.add(Second_New_Order);
			SQL_Queries_Order.add(Thired_New_Order);
			SQL_Queries_Order.add(Fourth_New_Order);
			
			/* SQL Queries - For Insert New Product_In_Order To The Order That I Insert To Order Table */
			SQL_Queries_Product_In_Order.add(Product_First_New_Order);
			SQL_Queries_Product_In_Order.add(Product_Second_New_Order);
			SQL_Queries_Product_In_Order.add(Product_Thired_New_Order);
			SQL_Queries_Product_In_Order.add(Product_Fourth_New_Order);
			
			/* Customer ID For The Client That Invite Order */
			Customer_ID.add("308155146");
			Customer_ID.add("308155127");
			Customer_ID.add("308155128");
			Customer_ID.add("308155160");
			
			/* The Price Of the Product That The Customer Invite */
			Price_Of_Order.add("50");
			Price_Of_Order.add("42");
			Price_Of_Order.add("18");
			Price_Of_Order.add("60");
			
			/* The Store Number That The Customer Invite */
			Stores_ID.add("1");
			Stores_ID.add("4");
			Stores_ID.add("3");
			Stores_ID.add("2");
			
			/* Inserting All The ArrayList To Vector<String> And Send It to DB */
			Update_Tables_For_Test.add(SQL_Queries_Order);
			Update_Tables_For_Test.add(SQL_Queries_Product_In_Order);
			Update_Tables_For_Test.add(Customer_ID);
			Update_Tables_For_Test.add(Price_Of_Order);
			Update_Tables_For_Test.add(Stores_ID);
			
			/* -------------------------- We Insert - To The DB ---> Four New Order ---------------------------------------------------- */
			
			Message Message_Two = new Message(Update_Tables_For_Test,"Test - Update - The Order Table , Product In Order Table , Account Table"); 
			client.accept(Message_Two);
			
			Flag_Finish_Update = true;
			
//		}
		
		/* ------------------------------------------------------------------------------------------------------------------------- */
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
	public void testCancelOrder_For_Test_Between_One_Hour_To_Three_Hour()  
	{
		Flag_Check_Order_One = true;
		
		Order Order_One = new Order();
		
		/* Take All The Details Of The First Order That I Create In Set Function */
		Order_One.setOrderID(Number_Of_Rows_In_Order_Table - 3); 					
		Message Message_One = new Message(Order_One,"Test - Bring Me The Order That I Add To Table - For Test"); 
		client.accept(Message_One);
		
		Flag_For_Taking_Specific_Order = false ; 
		
		while(Flag_For_Taking_Specific_Order == false);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Flag_For_Taking_Specific_Order = false ; 
		
		Order_One.setCustomerID(Order_For_Test_One.getCustomerID()); 
		Order_One.setOrderDate(Order_For_Test_One.getOrderDate());
		Order_One.setOrderTotalPrice(Order_For_Test_One.getOrderTotalPrice());
		Order_One.setoStatus(Order_For_Test_One.getoStatus());
		Order_One.setPaymentMethod(Order_For_Test_One.getPaymentMethod());
		Order_One.setRefund(Order_For_Test_One.getRefund());
		Order_One.setRequiredSupplyDate(Order_For_Test_One.getRequiredSupplyDate());
		Order_One.setRequiredSupplyTime(Order_For_Test_One.getRequiredSupplyTime());
		
		/* What I Expected */
		double Expected = 1000.0; 
		
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_One);
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		
		Flag_Check_Order_One = false;
		
		/* The Comparing */
		assertEquals(Expected, Actual);
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
		
		/* Take All The Details Of The First Order That I Create In Set Function */
		Order_Two.setOrderID(Number_Of_Rows_In_Order_Table - 2); 					
		Message Message_One = new Message(Order_Two,"Test - Bring Me The Order That I Add To Table - For Test"); 
		client.accept(Message_One);
		
		Flag_For_Taking_Specific_Order = false ; 
		
		while(Flag_For_Taking_Specific_Order == false);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Flag_For_Taking_Specific_Order = false ; 
		
		Order_Two.setCustomerID(Order_For_Test_Two.getCustomerID()); 
		Order_Two.setOrderDate(Order_For_Test_Two.getOrderDate());
		Order_Two.setOrderTotalPrice(Order_For_Test_Two.getOrderTotalPrice());
		Order_Two.setoStatus(Order_For_Test_Two.getoStatus());
		Order_Two.setPaymentMethod(Order_For_Test_Two.getPaymentMethod());
		Order_Two.setRefund(Order_For_Test_Two.getRefund());
		Order_Two.setRequiredSupplyDate(Order_For_Test_Two.getRequiredSupplyDate());
		Order_Two.setRequiredSupplyTime(Order_For_Test_Two.getRequiredSupplyTime());
		
		/* What I Expected */
		double Expected = 458.0; 
		
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Two);
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		
		Flag_Check_Order_Two = false;
		
		/* The Comparing */
		assertEquals(Expected, Actual);
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
		
		/* Take All The Details Of The First Order That I Create In Set Function */
		Order_Three.setOrderID(Number_Of_Rows_In_Order_Table - 1); 					
		Message Message_One = new Message(Order_Three,"Test - Bring Me The Order That I Add To Table - For Test"); 
		client.accept(Message_One);
		
		Flag_For_Taking_Specific_Order = false ; 
		
		while(Flag_For_Taking_Specific_Order == false);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Flag_For_Taking_Specific_Order = false ; 
		
		Order_Three.setCustomerID(Order_For_Test_Three.getCustomerID()); 
		Order_Three.setOrderDate(Order_For_Test_Three.getOrderDate());
		Order_Three.setOrderTotalPrice(Order_For_Test_Three.getOrderTotalPrice());
		Order_Three.setoStatus(Order_For_Test_Three.getoStatus());
		Order_Three.setPaymentMethod(Order_For_Test_Three.getPaymentMethod());
		Order_Three.setRefund(Order_For_Test_Three.getRefund());
		Order_Three.setRequiredSupplyDate(Order_For_Test_Three.getRequiredSupplyDate());
		Order_Three.setRequiredSupplyTime(Order_For_Test_Three.getRequiredSupplyTime());
		
		/* What I Expected */
		double Expected = 991.0; 
		
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Three);
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		
		Flag_Check_Order_Three = false;
		
		/* The Comparing */
		assertEquals(Expected, Actual);
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
		
		/* Take All The Details Of The First Order That I Create In Set Function */
		Order_Four.setOrderID(Number_Of_Rows_In_Order_Table); 					
		Message Message_One = new Message(Order_Four,"Test - Bring Me The Order That I Add To Table - For Test"); 
		client.accept(Message_One);
		
		Flag_For_Taking_Specific_Order = false ; 
		
		while(Flag_For_Taking_Specific_Order == false);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Flag_For_Taking_Specific_Order = false ; 
		
		Order_Four.setCustomerID(Order_For_Test_Four.getCustomerID()); 
		Order_Four.setOrderDate(Order_For_Test_Four.getOrderDate());
		Order_Four.setOrderTotalPrice(Order_For_Test_Four.getOrderTotalPrice());
		Order_Four.setoStatus(Order_For_Test_Four.getoStatus());
		Order_Four.setPaymentMethod(Order_For_Test_Four.getPaymentMethod());
		Order_Four.setRefund(Order_For_Test_Four.getRefund());
		Order_Four.setRequiredSupplyDate(Order_For_Test_Four.getRequiredSupplyDate());
		Order_Four.setRequiredSupplyTime(Order_For_Test_Four.getRequiredSupplyTime());
		
		/* What I Expected */
		double Expected = 470.0; 
		
		/* The Real Result After I Use In CancelOrder */
		double Actual = CancelOrderController.cancelOrder_For_Test(Order_Four);
		System.out.println("\nThe Expected Result Is ---> " + Expected);
		System.out.println("\nThe Actual Result Is ---> " + Actual);
		
		Flag_Check_Order_Four = false;
		
		/* The Comparing */
		assertEquals(Expected, Actual);
	}
}
