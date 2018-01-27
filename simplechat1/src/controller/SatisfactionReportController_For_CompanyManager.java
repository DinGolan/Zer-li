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

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * Variabel's That Help Me To Show The Question's In The BarChart . 
	 */
	private static String[] Questions = {"Questions 1","Questions 2","Questions 3","Questions 4" , "Questions 5" , "Questions 6"};

/* -------------------------  For The Window Of Satisfaction Report - For The First Store - For Company Manager ----------------------------------- */	
	
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
	
/* --------------------------------- Loading Store To the Text Fields ----------------------------------------------------------------------------- */	 
	 
	/**
	* In This Function We Load The ComboBox Of Store's .
	* @param s
	*/
	public void loadStore(Store s) 					
	{ 
			if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
			{
					if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true)
					{
						this.store = s;
						this.txtStoreID.setText(String.valueOf(store.getStoreId()));
					}
					else if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false)
					{
						this.store = s;
						this.txtStoreID.setText(String.valueOf(store.getStoreId()));
					}
					else if((CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false)
							|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true))
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
				else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false) 	
				{
					this.store = s;
					this.txtStoreID.setText(String.valueOf(store.getStoreId()));
				}
				else if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false)
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true))
				{
					this.txtStoreID.setText(String.valueOf(1));
				}
			}
	}
	
/* --------------------------------- Close the Satisfaction Report Window ------------------------------------------------------------------------- */			
	
	/**
	 * In This Function I close The GUI Of Satisfaction Report Of the First Store .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void closeSatisfactionReportWindow(ActionEvent event) throws Exception  
	{ 
		CompanyManagerUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
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
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 1)
		{
			primaryStage.setTitle("----- Company Manager Report Form - Watch Only One Store -----");
		}
		else if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 2)
		{
			primaryStage.setTitle("----- Company Manager Report Form - Watch Two Store -----");
		}
		primaryStage.show();										   
	}
	
/* --------------------------------- Initialize The Satisfaction Report GUI ----------------------------------------------------------------------- */	 			
	
	/** 
	 * In This Function We Initialize The GUI Of Saticfaction Report Of the First Store .
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 2)
		{
			 btnClose.setTranslateX(315);
		}
		int Year_Integer = 0;
		int Month_Integer = 0;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		
		if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
		{
			if((CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false)
					|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false))
			{
				int temp_Store_Id = 1;
				temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
				CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
				CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Store_Id);
				CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
				Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
				Year = Full_Date_String.substring(0 , 4);
				Month = Full_Date_String.substring(5 , 7);
				Year_Integer = Integer.parseInt(Year);
				Month_Integer = Integer.parseInt(Month);
			}
			else if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false) /* This else Statement Is Not For Defult Value */
			{
				temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
				CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
				Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
				Year = Full_Date_String.substring(0 , 4);
				Month = Full_Date_String.substring(5 , 7);
				Year_Integer = Integer.parseInt(Year);
				Month_Integer = Integer.parseInt(Month);
			}
			else if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true) 
			{
				temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.get(1); /* The Date */
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
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true))
			{
					int temp_Store_Id = 1;
					temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
					CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
					CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Store_Id);
					CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
			}
			else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false) /* This else Statement Is Not For Defult Value */
			{
				temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
				CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
				Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
				Year = Full_Date_String.substring(0 , 4);
				Month = Full_Date_String.substring(5 , 7);
				Year_Integer = Integer.parseInt(Year);
				Month_Integer = Integer.parseInt(Month);
				
			}
			else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true)
			{
				temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.get(1); /* The Date */
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
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.get(1)); /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report ,"Company Manager - Take The Surveys Of Specific Store In Specific Quarter"); 		/* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.size() == 0)
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
	
/* --------------------------------- Initialize The Survey And The Rank On The Bar Chart ---------------------------------------------------------- */	 			
	
	/**
	 * In This Function I Initialize The BarChart . 
	 */
	public void Put_At_The_Chart_All_The_Surveys()
	{
		double Total_Average = 0;
		int Number_Of_Client;
		ArrayList<Double> The_Average_Result_Of_Each_Question = new ArrayList<Double>();   				  							  /* All the Product That We Order On Specific Store */
		                       						  
		for(int i = 0 ; i < CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			The_Average_Result_Of_Each_Question.add(CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.get(i));
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
		The_Average_Result_Of_Each_Question.remove(6);                    		   /* Remove The Almost Last Index With the Total Average - After That The Last Index Is 6 */
		The_Average_Result_Of_Each_Question.remove(6);                    		   /* Remove The Last Index With the Number Of Client */
		
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
	
/* ------------------------------------------------------------------------------------------------------------------------------------------------ */	

}
