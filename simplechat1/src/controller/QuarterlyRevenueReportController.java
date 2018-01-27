package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.StoreManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class QuarterlyRevenueReportController implements Initializable{
	
	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
 
/* -------------------------  For The Window Of Quarterly Revenue Report - For Store Manager ----------------------------------- */	
	
	@FXML
	private TextField txtYear;
	
	@FXML
    private TextField txtQuarterNum;
	
    @FXML
	private TextField txtStoreID;               /* Text Field Of the Store ID */

    @FXML
    private TextField txtStoreAddress;          /* Text Field Of the Store Address */

    @FXML
    private TextField txtQuantityOfOrder;       /* Text Field Of the Quantity Of Order At The Store */
    
    @FXML
    private TextField txtRevenueOfSpecificQuarter;
    
    @FXML
	private Button btnClose;                    /* Button For Close The Window */

   
	
/* --------------------------------- Loading Store To the Text Fields ---------------------------------------------------------- */
 
	/**	
	* In This Function We Load The Number ID Of Specific Store .
	* @param s
	*/
	public void loadStore(Store s) 				
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
		this.txtStoreAddress.setText((store.getStore_Address()));
	}

/* --------------------------------- Loading Quarter Number To the Text Fields ------------------------------------------------- */	
	
	/**	
	* In This Function We Load The The Date's O Report Of Specific Store .
	* @param s
	*/
	public void loadQuarter(String string) 		
	{ 
		this.txtQuarterNum.setText(string);
	}
 
/* --------------------------------- Close the Quarterly Revenue Report Window ------------------------------------------------- */		
	
	/**
	* In This Function I close The GUI Of Revenue Report Of the The Store That The Store Manager Watch .
	* @param event - When The Client Press On the Butten This Parameter Start To Work .
	* @throws Exception - If The FXML Not Work .
	*/
	public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception
	{
		StoreManagerUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();										  
	}

/* --------------------------------- Initialize The Quarterly Revenue Report GUI ------------------------------------------------- */	 		
	
	/** 
	 * In This Function We Initialize The GUI Of Revenue Report Of the Store That The Store Manager Watch .
	 */
	@Override
	 public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer = 0;
		int Month_Integer = 0;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;

		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* This Code Is For Defult Value - Its Mean If The Store Manager Not Press On The ComboBox Of Date .
		 * But On Our Code - We Will Not Get Into To This Code Because We Will See Error Prompt Instead To See Defult Value */
		
		if(StoreManagerReportController.Flag_Press_On_The_Date_ComboBox == false)
		{
			StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.clear();
			StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.add(StoreManagerReportController.temp_Store_Id);
			StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.add(StoreManagerUI.Dates.get(0));
			temp_Date_Quarter_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.get(1);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* For This Code We Will Alaways Get In ! */
		
		else
		{
			temp_Date_Quarter_Report = (Date)StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.get(1); /* The Date */
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		
		this.txtYear.setText(String.valueOf(Year_Integer)); 					/* Set The Year */
		
		if(Month_Integer == 1 || Month_Integer == 2 || Month_Integer == 3)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(1));
		}
		if(Month_Integer == 4 || Month_Integer == 5 || Month_Integer == 6)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(2));
		}
		if(Month_Integer == 7 || Month_Integer == 8 || Month_Integer == 9)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(3));
		}
		if(Month_Integer == 10 || Month_Integer == 11 || Month_Integer == 12)   /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(4));
		}
		
		ArrayList<Object> StoreID_And_Date_Of_Report = new ArrayList<Object>();
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.get(1)); /* The Date Of the Report */
		Message msg = new Message(StoreID_And_Date_Of_Report,"Store Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		
		UserUI.myClient.accept(msg);
		while(StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.size() == 0)
		{
			if(StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.size() == 0)
			{
				break;
			}
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		String Revenue_Of_Specific_Quarter = String.valueOf(StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.get(0));         /* The Revenue */
		String Amount_Of_Order_Of_Specific_Quarter = String.valueOf(StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.get(1)); /* The Amount Of Order */
		
		this.txtQuantityOfOrder.setText(String.valueOf(Amount_Of_Order_Of_Specific_Quarter));
		this.txtRevenueOfSpecificQuarter.setText(Revenue_Of_Specific_Quarter);   
	} 
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
