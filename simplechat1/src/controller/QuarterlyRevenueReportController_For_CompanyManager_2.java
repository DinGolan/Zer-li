package controller;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class QuarterlyRevenueReportController_For_CompanyManager_2 implements Initializable {

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	 
	/* -------------------------  For The Window Of Quarterly Revenue Report - For The Second Store - For Company Manager ----------------------------------- */	
	
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
   
	
/* --------------------------------- Loading Store To the Text Fields --------------------------------------------------------------------------------------- */
 
    /**
	 * In This Function We Load The ComboBox Of Store's .
	 * @param s
	 */
	public void loadStore(Store s) 					
	{ 
		
		/* For This Code We Will Alaways Get In ! */
		
		if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{	
			this.store = s;
			this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			this.txtStoreAddress.setText((store.getStore_Address()));
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* This Code Is For Defult Value - Its Mean If The Company Manager Not Press On One Of The ComboBox .
		 * But On Our Code - We Will Not Get Into To This Code Because We Will See Error Prompt Instead To See Defult Value */
		
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
		{
			this.store = s;
			this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			this.txtStoreAddress.setText((store.getStore_Address()));
		}
		else if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			this.txtStoreID.setText(String.valueOf(2));
			this.txtStoreAddress.setText("Haifa");
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
	}

/* --------------------------------- Loading Quarter Number To the Text Fields ------------------------------------------------------------------------------ */	
	
	/**
	 * In This Function We Load The ComboBox Of Date's .
	 * @param s
	 */
	public void loadQuarter(String string) 					/* To load the Store details to the text fields */
	{ 
		
		/* For This Code We Will Alaways Get In ! */
		
		if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{	
			this.txtQuarterNum.setText(string);
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* This Code Is For Defult Value - Its Mean If The Company Manager Not Press On One Of The ComboBox .
		 * But On Our Code - We Will Not Get Into To This Code Because We Will See Error Prompt Instead To See Defult Value */
		
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
		{
			this.txtQuarterNum.setText(String.valueOf(3));
		}
		else if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
				||(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			this.txtQuarterNum.setText(String.valueOf(3));
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
	}
 
/* --------------------------------- Close the Quarterly Revenue Report Window ------------------------------------------------------------------------------ */		
	
	/**
	 * In This Function I close The GUI Of Revenue Report Of the Second Store .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception
	{
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
	}

/* --------------------------------- Initialize The Quarterly Revenue Report GUI ---------------------------------------------------------------------------- */	 		
	
	/** 
	 * In This Function We Initialize The GUI Of Revenue Report Of the Second Store .
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
		
		/* This Code Is For Defult Value - Its Mean If The Company Manager Not Press On One Of The ComboBox .
		 * But On Our Code - We Will Not Get Into To This Code Because We Will See Error Prompt Instead To See Defult Value */
		
		if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false) 
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false))
		{
			int temp_Store_Id = 2;
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Store_Id);
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
		{
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		
		/* ---------------------------------------------------------------------------------------------------------------- */
		
		/* For This Code We Will Alaways Get In ! */
		
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{
			temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.get(1); /* The Date */
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
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.get(1)); /* The Date Of the Report */
		
		Message msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.size() == 0)
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
		
		String Revenue_Of_Specific_Quarter = String.valueOf(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.get(0));         /* The Revenue */
		String Amount_Of_Order_Of_Specific_Quarter = String.valueOf(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.get(1)); /* The Amount Of Order */
		
		this.txtQuantityOfOrder.setText(String.valueOf(Amount_Of_Order_Of_Specific_Quarter));
		double double_Revenue_Of_Specific_Quarter = Double.parseDouble(Revenue_Of_Specific_Quarter);
		Revenue_Of_Specific_Quarter = String.valueOf(Double.parseDouble(new DecimalFormat("##.####").format(double_Revenue_Of_Specific_Quarter)));
		this.txtRevenueOfSpecificQuarter.setText(Revenue_Of_Specific_Quarter);  
	} 
	
/* ---------------------------------------------------------------------------------------------------------------------------------------------------------- */
	
}
