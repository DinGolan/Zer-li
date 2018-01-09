package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerReportUI;
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

public class SatisfactionReportController_For_CompanyManager implements Initializable  {

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
	 
	public void loadStore(Store s) 			 /* To load the Store details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
/* --------------------------------- Close the Satisfaction Report Window ------------------------------------------------- */			
	
	public void closeSatisfactionReportWindow(ActionEvent event) throws Exception  
	{ 
		CompanyManagerReportUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   
	}
	
/* --------------------------------- Initialize The Satisfaction Report GUI ------------------------------------------------- */	 			
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer;
		int Month_Integer;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		temp_Date_Quarter_Report = (Date)CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.get(1);                             /* The Date */
		Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
		Year = Full_Date_String.substring(0 , 4);
		Month = Full_Date_String.substring(5 , 7);
		Year_Integer = Integer.parseInt(Year);
		Month_Integer = Integer.parseInt(Month);
		
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
		StoreID_And_Date_Of_Report.add(CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.get(1)); /* The Date Of the Report */
		msg = new Message(StoreID_And_Date_Of_Report , "Store Manager - Take The Surveys Of Specific Store In Specific Quarter"); 		/* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.size() == 0);
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
		                       						  
		for(int i = 0 ; i < CompanyManagerReportUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			The_Average_Result_Of_Each_Question.add(CompanyManagerReportUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.get(i));
		}
		
		Total_Average = The_Average_Result_Of_Each_Question.get(6);       		   /* In The 6 Cell There Have The Total Average Of The Survey */
		Number_Of_Client = The_Average_Result_Of_Each_Question.get(7).intValue();  /* In The 7 Cell There Have The Number Of Client */
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
