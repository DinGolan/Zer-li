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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CustomerComplaintStatusReportController_For_CompanyManager_2 implements Initializable{

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * Variabel's That Help Me To Show The Month In The BarChart . 
	 */
	private static String[] Month_Of_Quarter_One = {"January","Febuary","March"};
	private static String[] Month_Of_Quarter_Two = {"April","May","June"};
	private static String[] Month_Of_Quarter_Three = {"July","August","September"};
	private static String[] Month_Of_Quarter_Four = {"October","November","December"};
	
	/* -------------------------  For The Window Of Second Store - Customer Complaint Report - For The Company Manager ----------------------------------- */		
	
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
	 
	 
	/**
	 * In This Function We Load The ComboBox Of Store's .
	 * @param s
	 */
	public void loadStore(Store s) 					
	{ 
		if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{
			this.store = s;
			this.txtStoreID.setText(String.valueOf(store.getStoreId()));
		}
		else /* I Get Into This 'else' Statement Only If I Not Press On One of the Combo Box Of Store Or Date */
		{
			this.txtStoreID.setText(String.valueOf(2));
		}
	}

	/**
	 * In This Function I close The GUI Of Customer Complaint Report Of the Second Store .
	 * @param event - When Client Press On the Button This Parameter Start To Work . 
	 * @throws Exception
	 */
	public void closeCustomerComplaintStatusReportWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
	}
	
	/** 
	 * In This Function We Initialize The GUI Of Customer Complaint Report Of the Second Store .
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer;
		int Month_Integer;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		/* This If Statement Is For Defult Value */
		if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			int temp_Store_Id = 2;
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(temp_Store_Id);
			CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		else /* This else Statement Is Not For Defult Value */
		{
			temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.get(1); /* The Date */
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
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
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.get(0));    /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.get(1));    /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take The Complaints Of Specific Store"); 					  /* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.complaints_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.complaints_For_Company_Manager_2.size() == 0)
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
	
	/**
	 * In This Function I Initialize The BarChart . 
	 */
	public void Put_At_The_Chart_All_The_Complaints()
	{
		int [] Count_In_Chart;
		ArrayList<String> Months_Of_Complaint = new ArrayList<String>();   								/* All the Product That We Order On Specific Store */
		ArrayList<String> Month_Of_Complaint_Without_Duplicate = new ArrayList<String>();
		Date date_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.get(1);
		String String_Date_Report = String.valueOf(date_Report);
		String Month = String_Date_Report.substring(5 , 7);
		int Integer_Month = Integer.parseInt(Month);
		                       						  
		for(int i = 0 ; i < CompanyManagerUI.complaints_For_Company_Manager_2.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			Months_Of_Complaint.add(CompanyManagerUI.complaints_For_Company_Manager_2.get(i).getComplaintMonth());
		}
		
		for(int i = 0 ; i < Months_Of_Complaint.size() ; i++)             								/* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			if((Month_Of_Complaint_Without_Duplicate.contains(Months_Of_Complaint.get(i))) == false) 	/* If Month_Of_Complaint_Without_Duplicate Not Contain */
				Month_Of_Complaint_Without_Duplicate.add(Months_Of_Complaint.get(i));
		}
		
		Count_In_Chart = new int[3];   																	/* 3 = Three Month In Each Quarter */
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
