package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import boundery.StoreManagerReportUI;
import entity.Message;
import entity.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StoreManagerReportController implements Initializable {

	private QuarterlyRevenueReportController quarterlyRevenueReportController;
	private OrderReportController orderReportController;
	private CustomerComplaintStatusReportController customerComplaintStatusReportController;
	private SatisfactionReportController satisfactionReportController;

	private Message msg;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default */
	private int temp_Store_Id;
	private Date temp_Date_Quarter_Report;
	
	ObservableList<String> storeList;
	ObservableList<Date> DateList;
	
/* -------------------------  For The First Window Of Report ----------------------------------- */	
	
	@FXML
	private ComboBox<String> cmbStores;  				    /* ComboBox With List Of Stores */
	
	@FXML
    private ComboBox<Date> cmbReports;
	
	@FXML
	private Button btnEnter_Your_Store_Choise = null;
	
	@FXML
    private Button btn_Your_Choise_Of_Quarter_Report;
	
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
	
	
	
/* ----------------------- Method's For the First Window GUI - Report ------------------------ */
	
	public void start(Stage primaryStage) throws Exception          			  /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Quarterly Report - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
/* --------------------------------  The Report About Quarterly Revenue ----------------------------------- */	
	
	public void QuarterlyRevenueReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm.fxml").openStream());
		
		QuarterlyRevenueReportController quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(StoreManagerReportUI.stores.get(getItemIndex())); 
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Order ----------------------------------- */	
	
	public void OrderReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm.fxml").openStream());
		
		OrderReportController orderReportController = loader.getController();
		orderReportController.loadStore(StoreManagerReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	public void CustomerComplaintStatusReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm.fxml").openStream());
		
		CustomerComplaintStatusReportController customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(StoreManagerReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Satisfaction ----------------------------------- */
	
	public void SatisfactionReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm.fxml").openStream());
		
		SatisfactionReportController satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(StoreManagerReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndex()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbStores.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndexFromDateComboBox()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReports.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReports.getSelectionModel().getSelectedIndex();
	}
	
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */	
	
	public void loadStoreToRevenueReport(Store store) 					/* Loading Store */
	{
		this.quarterlyRevenueReportController.loadStore(store);
	}
	
	public void loadStoreToOrderReport(Store store) 					/* Loading Store */
	{
		this.orderReportController.loadStore(store);
	}
	
	public void loadStoreToSaticfactionReport(Store store) 				/* Loading Store */
	{
		this.satisfactionReportController.loadStore(store);
	}
	
	public void loadStoreToCustomerComplaintStatusReport(Store store) 	/* Loading Store */
	{
		this.customerComplaintStatusReportController.loadStore(store);
	}

/* ----------------------------------------- Exit Button --------------------------------------------------- */
	
	public void getExitBtn(ActionEvent event) throws Exception      	/* With this Method we Exit from the Report UI */ 
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}

/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	public void setStoresComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: StoreManagerReportUI.stores)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbStores.setItems(storeList);
	}

/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	public void set_Dates_Of_Report_At_ComboBox()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < StoreManagerReportUI.Dates.size() ; i++)
		{
			Date_Of_Report.add(StoreManagerReportUI.Dates.get(i));
		}
		
		DateList = FXCollections.observableArrayList(Date_Of_Report);
		cmbReports.setItems(DateList);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Button_Of_Your_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id = StoreManagerReportUI.stores.get(getItemIndex()).getStoreId();
		msg = new Message(temp_Store_Id, "Take The Date Of All the Report Of Specific Store");
		StoreManagerReportUI.myClient.accept(msg);
		while(StoreManagerReportUI.Dates.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_At_ComboBox();
	}
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */			
	
	public void Button_Of_Your_Quarter_Report(ActionEvent event) throws Exception
	{
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report = StoreManagerReportUI.Dates.get(getItemIndexFromDateComboBox());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report);
		msg = new Message(Store_Id_And_Date_Of_Report, "Take the Revenue Of Specific Quarter Of Specific Store");
		StoreManagerReportUI.myClient.accept(msg);
		while(StoreManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.size() == 0);
		Thread.sleep(200);
		StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.clear();
		StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.add(StoreManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.get(0));
		StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.add(StoreManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.get(1));
		StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.add(temp_Date_Quarter_Report);
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		StoreManagerReportUI.Help_To_Transfer_Object_At_Order_Report.clear();
		StoreManagerReportUI.Help_To_Transfer_Object_At_Order_Report.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		StoreManagerReportUI.Help_To_Transfer_Object_At_Order_Report.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		StoreManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report.clear();
		StoreManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		StoreManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		StoreManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report.clear();
		StoreManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		StoreManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Store> stores = new ArrayList<Store>();           /* For the First Connection With The DB the ArrayList Of stores Is Empty */
		msg = new Message(stores, "Add Store To Combo Box From DB");
		StoreManagerReportUI.myClient.accept(msg);
		while(StoreManagerReportUI.stores.size() == 0);
		setStoresComboBox();
		
		/* ----------------- Update All the Revenue Of All The Store At The DB ------------------*/
		
		for(int i = 0 ; i < StoreManagerReportUI.stores.size() ; i++)
		{
			stores.add(StoreManagerReportUI.stores.get(i));
		}
		
		msg = new Message(stores, "Update The Total Revenue Of All the Store");
		StoreManagerReportUI.myClient.accept(msg);	
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
