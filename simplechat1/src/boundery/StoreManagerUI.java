package boundery;

import java.sql.Date;
import java.util.Scanner;
import java.util.Vector;

import controller.StoreManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Complaint;
import entity.Order;
import entity.Store;

/**
 * class to save static variables that belong to the store manager
 */
public class StoreManagerUI extends Application 		/* With This Class We Show the Report GUI */{
	
	/* Vector Of ---> { Store , Order , Complaint , Date , Average Of Result At Survey } */
	public static Vector<String> stores = new Vector<String>();
	public static Vector<Order> orders = new Vector<Order>(); 
	public static Vector<Complaint> complaints = new Vector<Complaint>(); 
	public static Vector<Date> Dates = new Vector<Date>();
	public static Vector<Double> Average_Result_Of_Each_Qustions_In_surveys = new Vector<Double>();
	
	/* ArrayList Of Object */
	public static Vector<Object> Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Revenue_Report = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Order_Report = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Complaint_Report = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Satisfaction_Report = new Vector<Object>();
	
	/* Client Console */
	public static ClientConsole myClient;
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		StoreManagerController aFrame = new StoreManagerController(); /* Create Report Frame */				  
		aFrame.start(arg0);
	}
}
