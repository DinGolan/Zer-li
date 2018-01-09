package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerReportUI;
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

public class CompanyManagerController_With_Two_Store implements Initializable{

	private Message msg;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 1 By Default */
	private int temp_Store_Id_1;
	private int temp_Store_Id_2;
	private Date temp_Date_Quarter_Report_1;
	private Date temp_Date_Quarter_Report_2;
	
	ObservableList<String> storeList_One;
	ObservableList<String> storeList_Two;
	ObservableList<Date> DateList_One;
	ObservableList<Date> DateList_Two;
	
/* -------------------------  For The Window Of Company Manager Report - To See One Store ----------------------------------- */		
	
	 @FXML
	 private ComboBox<String> cmbFirstStores;

	 @FXML
	 private ComboBox<String> cmbSecondStores;
	 
	 @FXML
	 private ComboBox<Date> cmbReportsFirstStore;
	 
	 @FXML
	 private ComboBox<Date> cmbReportsSecondStore;

	 @FXML
	 private Button btnClickFirstStore;

	 @FXML
	 private Button btnClickFirstStoreReport;

	 @FXML
	 private Button btnCustomerComplaintStatusReport_Store_1;

	 @FXML
	 private Button btnQuarterlyRevenueReport_Store_1;

	 @FXML
	 private Button btnSatisfactionReport_Store_1;

	 @FXML
	 private Button btnOrderReport_Store_1;

	 @FXML
	 private Button btnClose;

	 @FXML
	 private Button btnClickSecondStore;

	 @FXML
	 private Button btnClickSecondStoreReport;

	 @FXML
	 private Button btnCustomerComplaintStatusReport_Store_2;

	 @FXML
	 private Button btnQuarterlyRevenueReport_Store_2;

	 @FXML
	 private Button btnSatisfactionReport_Store_2;

	 @FXML
	 private Button btnOrderReport_Store_2;
	
/* --------------------------------- Close the Order Report Window ------------------------------------------------- */	 	
	
	public void closeTwoStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}	
	
/* --------------------------------  The Report About Quarterly Revenue ----------------------------------- */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void QuarterlyRevenueReport_Store_One(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm.fxml").openStream());
		
		QuarterlyRevenueReportController quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store())); 
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void QuarterlyRevenueReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm.fxml").openStream());
		
		QuarterlyRevenueReportController quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store())); 
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Order ----------------------------------- */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void OrderReport_Store_One(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm.fxml").openStream());
		
		OrderReportController orderReportController = loader.getController();
		orderReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void OrderReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm.fxml").openStream());
		
		OrderReportController orderReportController = loader.getController();
		orderReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void CustomerComplaintStatusReport_Store_One(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm.fxml").openStream());
		
		CustomerComplaintStatusReportController customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void CustomerComplaintStatusReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm.fxml").openStream());
		
		CustomerComplaintStatusReportController customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Satisfaction ----------------------------------- */
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void SatisfactionReport_Store_One(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm.fxml").openStream());
		
		SatisfactionReportController satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void SatisfactionReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm.fxml").openStream());
		
		SatisfactionReportController satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public int getItemIndex_First_Store()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbFirstStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbFirstStores.getSelectionModel().getSelectedIndex();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public int getItemIndex_Second_Store()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbFirstStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbFirstStores.getSelectionModel().getSelectedIndex();
		
	}
/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void set_Dates_Of_Report_Of_Store_One_At_ComboBox()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerReportUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerReportUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList_One = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsFirstStore.setItems(DateList_One);
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void set_Dates_Of_Report_Of_Store_Two_At_ComboBox()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerReportUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerReportUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList_Two = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsSecondStore.setItems(DateList_Two);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void setStores_Two_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerReportUI.stores_For_Company_Manager)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_One = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbFirstStores.setItems(storeList_One);
	}	
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void setStores_One_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerReportUI.stores_For_Company_Manager)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_Two = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbSecondStores.setItems(storeList_Two);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Button_Of_Your_First_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_1 = CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()).getStoreId();
		msg = new Message(temp_Store_Id_1, "Company Manager - Take The Date Of All the Report Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Dates_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_One_At_ComboBox();
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Button_Of_Your_Second_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_2 = CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()).getStoreId();
		msg = new Message(temp_Store_Id_2, "Company Manager - Take The Date Of All the Report Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Dates_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_Two_At_ComboBox();
	}
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Button_Of_Your_Quarter_Report_Store_One(ActionEvent event) throws Exception
	{
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report_1 = CompanyManagerReportUI.Dates_For_Company_Manager.get(getItemIndex_First_Store());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_1);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_1);
		msg = new Message(Store_Id_And_Date_Of_Report, "Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(0));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(1));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report_1);
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}	
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Button_Of_Your_Quarter_Report_Store_Two(ActionEvent event) throws Exception
	{
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report_2 = CompanyManagerReportUI.Dates_For_Company_Manager.get(getItemIndex_First_Store());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_2);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_2);
		msg = new Message(Store_Id_And_Date_Of_Report, "Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(0));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(1));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report_2);
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Store> stores = new ArrayList<Store>();           /* For the First Connection With The DB the ArrayList Of stores Is Empty */
		msg = new Message(stores, "Company Manager - Add Store To Combo Box From DB");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.stores_For_Company_Manager.size() == 0);
		setStores_One_ComboBox();
		setStores_Two_ComboBox();
	}

}
