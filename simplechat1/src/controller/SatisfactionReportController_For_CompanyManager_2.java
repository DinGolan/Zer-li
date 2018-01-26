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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SatisfactionReportController_For_CompanyManager_2 implements Initializable {

	private Store store;
	private Message msg;
	private static String[] Questions = {"Questions 1","Questions 2","Questions 3","Questions 4" , "Questions 5" , "Questions 6"};

/* -------------------------  For The Window Of Satisfaction Report ----------------------------------- */	
	
	 @FXML
	 private TextField txtStoreID;           /* Text Field Of the Store ID */
	
	 @FXML
	 private TextField txtYear;

	 @FXML
	 private TextField txtNumberOfQuarter;
	 
	 @FXML
	 private TextField txtTotalAvgRank; 
	
	 @FXML
	 private TextField txtNumberOfClient;
	 
	 @FXML
	 private CategoryAxis X_Questions;

	 @FXML
	 private NumberAxis Y_Average;

	 @FXML
	 private BarChart<String, Double> Chart_SatisfactionReport;
	 
	 @FXML
	 private Button btnClose;                /* Button For Exit from The Window */
	
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */	 
	 
	 public void loadStore(Store s) 					
	 { 
			if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)) 
			{
				this.txtStoreID.setText(String.valueOf(2));
			}
	 }
	
/* --------------------------------- Close the Satisfaction Report Window ------------------------------------------------- */			
	
	public void closeSatisfactionReportWindow(ActionEvent event) throws Exception  
	{ 
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */										   
	}
	
/* --------------------------------- Initialize The Satisfaction Report GUI ------------------------------------------------- */	 			
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer = 0;
		int Month_Integer = 0;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		
		if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			int temp_Store_Id = 2;
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(temp_Store_Id);
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
		{
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		/* This else Statement Is Not For Defult Value */
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{
			temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.get(1); /* The Date */
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
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.get(1)); /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report ,"Company Manager - Take The Surveys Of Specific Store In Specific Quarter"); 		/* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.size() == 0)
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
		Put_At_The_Chart_All_The_Surveys();
	}
	
/* --------------------------------- Initialize The Survey And The Rank On The Bar Chart ------------------------------------------------- */	 			
	
	public void Put_At_The_Chart_All_The_Surveys()
	{
		double Total_Average = 0;
		int Number_Of_Client;
		ArrayList<Double> The_Average_Result_Of_Each_Question = new ArrayList<Double>();   				  /* All the Product That We Order On Specific Store */
		                       						  
		for(int i = 0 ; i < CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			The_Average_Result_Of_Each_Question.add(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.get(i));
		}
		
		Total_Average = The_Average_Result_Of_Each_Question.get(6);       		   /* In The 6 Cell There Have The Total Average Of The Survey */
		Number_Of_Client = The_Average_Result_Of_Each_Question.get(7).intValue();  /* In The 7 Cell There Have The Number Of Client */
		if(String.valueOf(Total_Average).compareTo("NaN") == 0)
		{
			Total_Average = 0;
		}
		Total_Average = Double.parseDouble(new DecimalFormat("##.####").format(Total_Average));
		this.txtTotalAvgRank.setText(String.valueOf(Total_Average));
		this.txtNumberOfClient.setText(String.valueOf(Number_Of_Client));
		The_Average_Result_Of_Each_Question.remove(6);                    /* Remove The Almost Last Index With the Total Average - After That The Last Index Is 6 */
		The_Average_Result_Of_Each_Question.remove(6);                    /* Remove The Last Index With the Number Of Client */
		
		ArrayList<XYChart.Series<String,Double>> setChart = new ArrayList<XYChart.Series<String,Double>>();
		for(int i = 0 ; i < Questions.length ; i++)
		{
			XYChart.Series<String,Double> Chart = new XYChart.Series<String,Double>();
			Chart.setName(Questions[i]);
			setChart.add(Chart);
		}
		
		for(int SetChart_Index = 0 ; SetChart_Index < setChart.size() ; SetChart_Index++)
		{
			setChart.get(SetChart_Index).getData().add(new XYChart.Data<String, Double>(setChart.get(SetChart_Index).getName() , The_Average_Result_Of_Each_Question.get(SetChart_Index)));
		}

		Chart_SatisfactionReport.getData().addAll(setChart);
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */	

}
