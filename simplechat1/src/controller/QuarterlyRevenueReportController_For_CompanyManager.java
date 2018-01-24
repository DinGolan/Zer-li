package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.StoreManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/* For Company Manager */

public class QuarterlyRevenueReportController_For_CompanyManager implements Initializable {

		private Store store;
	 
		/* -------------------------  For The Window Of Quarterly Revenue Report ----------------------------------- */	
		
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

	   
		
	/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */
	 
		public void loadStore(Store s) 					/* To load the Store details to the text fields */
		{ 
			if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
				this.txtStoreAddress.setText((store.getStore_Address()));
			}
			else
			{
				this.txtStoreID.setText(String.valueOf(1));
				this.txtStoreAddress.setText(("Nahariya"));
			}
		}

	/* --------------------------------- Loading Quarter Number To the Text Fields ------------------------------------------------- */	
		
		public void loadQuarter(String string) 					/* To load the Store details to the text fields */
		{ 
			if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true)
			{
				this.txtQuarterNum.setText(string);
			}
			else
			{
				this.txtQuarterNum.setText(String.valueOf(4));
			}
		}
	 
	/* --------------------------------- Close the Quarterly Revenue Report Window ------------------------------------------------- */		
		
		public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception
		{
			CompanyManagerUI.stores_For_Company_Manager.clear();
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = null;
			if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 1)
			{
				root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_Only_One_Store.fxml").openStream());
			}
			else if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 2)
			{
				root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
			}
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);		
			primaryStage.show();										   /* show catalog frame window */
		}

	/* --------------------------------- Initialize The Quarterly Revenue Report GUI ------------------------------------------------- */	 		
		
		@Override
		 public void initialize(URL location, ResourceBundle resources) 
		{
			int Year_Integer = 0;
			int Month_Integer = 0;
			String Month;
			String Year;
			String Full_Date_String;
			Date temp_Date_Quarter_Report;
			
			if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
			{
			
				if((CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false)
						|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true) 
						|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false))
				{
					int temp_Store_Id = 1;
					temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
					
					CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
					CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Store_Id);
					CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
					
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
				}
				else
				{
					temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.get(1);                             /* The Date */
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
					
				}
			}
			else if(CompanyManagerReportController.Integer_The_Option_You_Choose == 2)
			{
				if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false)
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true) 
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false))
					{
						int temp_Store_Id = 1;
						temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
						CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
						CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Store_Id);
						CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
						Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
						Year = Full_Date_String.substring(0 , 4);
						Month = Full_Date_String.substring(5 , 7);
						Year_Integer = Integer.parseInt(Year);
						Month_Integer = Integer.parseInt(Month);
					}
					else
					{
						temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.get(1);                             /* The Date */
						Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
						Year = Full_Date_String.substring(0 , 4);
						Month = Full_Date_String.substring(5 , 7);
						Year_Integer = Integer.parseInt(Year);
						Month_Integer = Integer.parseInt(Month);
					}
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
			StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.get(0)); /* The Store Id */
			StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.get(1)); /* The Date Of the Report */
			
			Message msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
			UserUI.myClient.accept(msg);
			while(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.size() == 0)
			{
				if(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.size() == 0)
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
			
			String Revenue_Of_Specific_Quarter = String.valueOf(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(0));         /* The Revenue */
			String Amount_Of_Order_Of_Specific_Quarter = String.valueOf(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(1)); /* The Amount Of Order */
			
			this.txtQuantityOfOrder.setText(String.valueOf(Amount_Of_Order_Of_Specific_Quarter));
			this.txtRevenueOfSpecificQuarter.setText(Revenue_Of_Specific_Quarter);   /* Set the Revenue */
		} 
		
	/* ------------------------------------------------------------------------------------------------------------------- */
		
}
