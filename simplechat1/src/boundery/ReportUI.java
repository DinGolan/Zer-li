package boundery;

import java.sql.Date;
import java.util.Scanner;
import java.util.Vector;
import controller.ReportController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Complaint;
import entity.Order;
import entity.Store;

public class ReportUI extends Application 		/* With This Class We Show the Report GUI */{
	
	public static Vector<Store> stores = new Vector<Store>();
	public static Vector<Order> orders = new Vector<Order>(); 
	public static Vector<Complaint> complaints = new Vector<Complaint>(); 
	public static Vector<Date> Dates = new Vector<Date>();
	public static Vector<Double> Total_Revenue_In_Specific_Quarter = new Vector<Double>();
	public static Vector<Object> Help_To_Transfer_Object = new Vector<Object>();
	public static ClientConsole myClient;
	
	public static void main( String args[] ) throws Exception
	{ 
		//System.out.println("Please enter the server IP");
		//Scanner scanner = new Scanner(System.in);
		//String IP = scanner.next(); 			/* Enter Server IP */
		myClient = new ClientConsole("localhost", 5555);
        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		ReportController aFrame = new ReportController(); /* Create Report Frame */				  
		aFrame.start(arg0);
	}
}
