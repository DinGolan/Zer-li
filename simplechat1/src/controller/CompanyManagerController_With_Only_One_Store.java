package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import controller.CustomerComplaintStatusReportController_For_CompanyManager;
import entity.Message;
import entity.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerController_With_Only_One_Store implements Initializable {

	private Message msg;
	private static int itemIndex = 0; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default - Store 1 */
	private int temp_Store_Id;
	private Date temp_Date_Quarter_Report;
	
	ObservableList<String> storeList;
	ObservableList<Date> DateList;
	
/* -------------------------  For The Window Of Company Manager Report - To See One Store ----------------------------------- */		
	
	@FXML
	private ComboBox<String> cmbStores;  				    /* ComboBox With List Of Stores */
	
	@FXML
    private ComboBox<Date> cmbReports;
	
	@FXML
	private Button btnQuarterlyRevenueReport = null;        /* Button Of Quarterly Revenue Report */
	
	@FXML
	private Button btnOrderReport = null; 			 	    /* Button Of Order Report */

	@FXML
	private Button btnCustomerComplaintStatusReport = null; /* Button Of Customer Complaint Status Report */
	
	@FXML
	private Button btnSatisfactionReport = null;            /* Button Of Satisfaction Report */
	
	@FXML
	private Button btnExit = null;
	
/* --------------------------------- Close the Order Report Window ------------------------------------------------- */	 	
	
	public void closeOneStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
		CompanyManagerUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);	
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}
	
/* --------------------------------  The Report About Quarterly Revenue ----------------------------------- */	
	
	public void QuarterlyRevenueReport_For_Company_Worker(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager.fxml").openStream());
		
		QuarterlyRevenueReportController_For_CompanyManager quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager())); 
		
		Scene scene = new Scene(root);	
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Order ----------------------------------- */	
	
	public void OrderReport_For_Company_Worker(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager.fxml").openStream());
		
		OrderReportController_For_ComapnyManager orderReportController = loader.getController();
		orderReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	public void CustomerComplaintStatusReport_For_Company_Worker(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager.fxml").openStream());
		
		CustomerComplaintStatusReportController_For_CompanyManager customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Satisfaction ----------------------------------- */
	
	public void SatisfactionReport_For_Company_Worker(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager.fxml").openStream());
		
		SatisfactionReportController_For_CompanyManager satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndex_For_CompanyManager()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbStores.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndexFromDateComboBox_For_CompanyManager()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReports.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReports.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	public void set_Dates_Of_Report_At_ComboBox_For_CompanyManager()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList = FXCollections.observableArrayList(Date_Of_Report);
		cmbReports.setItems(DateList);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	public void setStoresComboBox_ComapnyManager()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbStores.setItems(storeList);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Click_On_Your_Store_Choise_For_CompanyManager(ActionEvent event) throws Exception
	{
		temp_Store_Id = CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()).getStoreId();
		msg = new Message(temp_Store_Id,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Dates_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_At_ComboBox_For_CompanyManager();
	}	
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Click_On_Your_Quarter_Report_For_CompanyManager(ActionEvent event) throws Exception
	{
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report = CompanyManagerUI.Dates_For_Company_Manager.get(getItemIndexFromDateComboBox_For_CompanyManager());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report);
		msg = new Message(Store_Id_And_Date_Of_Report,"Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(0));
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(1));
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}	
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Store> stores = new ArrayList<Store>();           /* For the First Connection With The DB the ArrayList Of stores Is Empty */
		msg = new Message(stores,"Company Manager - Add Store To Combo Box From DB");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.stores_For_Company_Manager.size() == 0);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		setStoresComboBox_ComapnyManager();
	}
}
