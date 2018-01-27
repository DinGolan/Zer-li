package boundery;

import java.sql.Date;
import java.util.Vector;

import controller.CompanyManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Complaint;
import entity.Order;
import entity.Store;


/**
 * This Class - is The Boundary Of The Company Manager 
 * @author dingo
 *
 */
public class CompanyManagerUI extends Application { 					/* With This Class We Show the Report GUI For the Company Manager */		
	
	/*--------------------------------------- For - Store One ------------------------------------------------------------------------------- */
	
	/* Vector Of ---> { Store , Order , Complaint , Date , Average Of Result At Survey } */
	public static Vector<Store> stores_For_Company_Manager = new Vector<Store>();
	public static Vector<Order> orders_For_Company_Manager = new Vector<Order>(); 
	public static Vector<Complaint> complaints_For_Company_Manager = new Vector<Complaint>(); 
	public static Vector<Date> Dates_For_Company_Manager = new Vector<Date>();
	public static Vector<Double> Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager = new Vector<Double>();
	public static Vector<String> Option_Of_See_One_Store_Or_To_Store_For_Company_Manager = new Vector<String>();
	
	/* Vector Of Object That Help Me To Transfer From the Client to the DB */
	public static Vector<Object> Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Order_Report_For_Company_Manager = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager = new Vector<Object>();
	
	/*--------------------------------------- For - Store Two ------------------------------------------------------------------------------- */
	
	/* Vector Of ---> { Store , Order , Complaint , Date , Average Of Result At Survey } */
	public static Vector<Store> stores_For_Company_Manager_2 = new Vector<Store>();
	public static Vector<Order> orders_For_Company_Manager_2 = new Vector<Order>(); 
	public static Vector<Complaint> complaints_For_Company_Manager_2 = new Vector<Complaint>(); 
	public static Vector<Date> Dates_For_Company_Manager_2 = new Vector<Date>();
	public static Vector<Double> Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2 = new Vector<Double>();
	public static Vector<String> Option_Of_See_One_Store_Or_To_Store_For_Company_Manager_2 = new Vector<String>();
	
	/* Vector Of Object That Help Me To Transfer From the Client to the DB */
	public static Vector<Object> Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2 = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2 = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2 = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2 = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2 = new Vector<Object>();
	
	/*--------------------------------------- For - Compare Between The First Store & Second Store ------------------------------------------ */
	
	public static Vector<Object> Help_To_Transfer_Object_From_Comparing_For_Store_1 = new Vector<Object>();
	public static Vector<Object> Help_To_Transfer_Object_From_Comparing_For_Store_2 = new Vector<Object>();
	public static Vector<Object> Object_From_Comparing_For_Store_1 = new Vector<Object>();
	public static Vector<Object> Object_From_Comparing_For_Store_2 = new Vector<Object>();
	
	/* Client Console */
	public static ClientConsole myClient;
	
	public static void main( String args[] ) throws Exception {}
	
	
/**
 * The Function 'Start' - This Function run the Application Of The Company Manager If I Not Use With UserUI
 */
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		CompanyManagerController aFrame = new CompanyManagerController(); 				  
		aFrame.start(arg0);
	}
}
