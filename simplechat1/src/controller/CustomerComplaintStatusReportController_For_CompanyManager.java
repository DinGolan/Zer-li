package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/* For Company Manager */

public class CustomerComplaintStatusReportController_For_CompanyManager implements Initializable {

	private Store store;
	private Message msg;
	private static String[] Month_Of_Quarter_One = {"January","Febuary","March"};
	private static String[] Month_Of_Quarter_Two = {"April","May","June"};
	private static String[] Month_Of_Quarter_Three = {"July","August","September"};
	private static String[] Month_Of_Quarter_Four = {"October","November","December"};
	
	 @FXML
	 private TextField txtStoreID;
	
	 @FXML
	 private TextField txtYear;

	    @FXML
	 private TextField txtNumberOfQuarter;  
	    
	 @FXML
	 private BarChart<String, Integer> Complaint_BarChart;
	 
	 @FXML
	 private CategoryAxis X_Month;

	 @FXML
	 private NumberAxis Y_Complaint;
	 
	 @FXML
	 private Button btnClose;
	
	public void loadStore(Store s) 					/* To load the User details to the text fields */
	{ 
		if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
		{
				if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true)
				{
					this.store = s;
					this.txtStoreID.setText(String.valueOf(store.getStoreId()));
				}
				else
				{
					this.txtStoreID.setText(String.valueOf(1));
				}
		}
		else if(CompanyManagerReportController.Integer_The_Option_You_Choose == 2)
		{
			if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else
			{
				this.txtStoreID.setText(String.valueOf(1));
			}
		}
	}
	
	public void closeCustomerComplaintStatusReportWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
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
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Store_Id);
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
				}
				else
				{
					temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(1);                             /* The Date */
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
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Store_Id);
					CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
				}
				else
				{
					temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(1);                             /* The Date */
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
			this.txtNumberOfQuarter.setText(String.valueOf(1));
		}
		if(Month_Integer == 4 || Month_Integer == 5 || Month_Integer == 6)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(2));
		}
		if(Month_Integer == 7 || Month_Integer == 8 || Month_Integer == 9)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(3));
		}
		if(Month_Integer == 10 || Month_Integer == 11 || Month_Integer == 12)   /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(4));
		}
		
		ArrayList<Object> StoreID_And_Date_Of_Report = new ArrayList<Object>();
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(0));    /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(1));    /* The Date Of the Report */
		msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take The Complaints Of Specific Store"); 		/* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.complaints_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.complaints_For_Company_Manager.size() == 0)
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
		Put_At_The_Chart_All_The_Complaints();
	}

/* --------------------------------- Initialize The Customer Complaint And The Month Of the Complaint At the Bar Chart ------------------------------------------------- */	 			
	
	public void Put_At_The_Chart_All_The_Complaints()
	{
		int [] Count_In_Chart;
		ArrayList<String> Months_Of_Complaint = new ArrayList<String>();   /* All the Product That We Order On Specific Store */
		ArrayList<String> Month_Of_Complaint_Without_Duplicate = new ArrayList<String>();
		Date date_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(1);
		String String_Date_Report = String.valueOf(date_Report);
		String Month = String_Date_Report.substring(5 , 7);
		int Integer_Month = Integer.parseInt(Month);
		                       						  
		for(int i = 0 ; i < CompanyManagerUI.complaints_For_Company_Manager.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			Months_Of_Complaint.add(CompanyManagerUI.complaints_For_Company_Manager.get(i).getComplaintMonth());
		}
		
		for(int i = 0 ; i < Months_Of_Complaint.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			if((Month_Of_Complaint_Without_Duplicate.contains(Months_Of_Complaint.get(i))) == false) /* If Month_Of_Complaint_Without_Duplicate Not Contain */
				Month_Of_Complaint_Without_Duplicate.add(Months_Of_Complaint.get(i));
		}
		
		Count_In_Chart = new int[3];   /* 3 = Three Month In Each Quarter */
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		
		if(Integer_Month == 1 || Integer_Month == 2 || Integer_Month == 3)
		{
			for(int i = 0 ; i < Month_Of_Quarter_One.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_One[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 4 || Integer_Month == 5 || Integer_Month == 6)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Two.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Two[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 7 || Integer_Month == 8 || Integer_Month == 9)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Three.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Three[i]);
				setChart.add(Chart);
			}
		}
		else if(Integer_Month == 10 || Integer_Month == 11 || Integer_Month == 12)
		{
			for(int i = 0 ; i < Month_Of_Quarter_Four.length ; i++)
			{
				XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
				Chart.setName(Month_Of_Quarter_Four[i]);
				setChart.add(Chart);
			}
		}
		
		
		
		for(int i = 0 ; i < Months_Of_Complaint.size() ; i++)
		{
			for(int WithOut_Duplicate_Index = 0 ; WithOut_Duplicate_Index < Month_Of_Complaint_Without_Duplicate.size() ; WithOut_Duplicate_Index++)
			{
				if(Months_Of_Complaint.get(i).compareTo(Month_Of_Complaint_Without_Duplicate.get(WithOut_Duplicate_Index)) == 0) /* If Equals Than get In Into The 'If' Statement */
				{
					for(int SetChart_Index = 0 ; SetChart_Index < setChart.size() ; SetChart_Index++)
					{
						if(setChart.get(SetChart_Index).getName().compareTo(Month_Of_Complaint_Without_Duplicate.get(WithOut_Duplicate_Index)) == 0)
							setChart.get(SetChart_Index).getData().add(new XYChart.Data<String, Integer>(setChart.get(SetChart_Index).getName() , ++(Count_In_Chart[SetChart_Index])));
					}
				}
			}
		}
		
	
		Complaint_BarChart.getData().addAll(setChart);
	}
}
