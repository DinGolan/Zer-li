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
	private static int itemIndex = 0; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default - Store 1 */
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
	 private Button btnCompare;
	 
	 @FXML
	 private Button btnCustomerComplaintStatusReport_Store;

	 @FXML
	 private Button btnQuarterlyRevenueReport_Store;

	 @FXML
	 private Button btnSatisfactionReport_Store;

	 @FXML
	 private Button btnOrderReport_Store;
	 
	 @FXML
	 private Button btnClose;
	
/* --------------------------------- Close the Order Report Window ------------------------------------------------- */	 	
	
	public void closeTwoStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
		CompanyManagerReportUI.stores_For_Company_Manager.clear();
		CompanyManagerReportUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}	
	
/* --------------------------------  The Report About Quarterly Revenue ----------------------------------- */	
	
	/* -------------------------------- Store 1 + 2 ----------------------------------- */
	
	public void QuarterlyRevenueReport_Store(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{ 
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		Stage primaryStage_2 = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		FXMLLoader loader_2 = new FXMLLoader();
		
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager.fxml").openStream());
		QuarterlyRevenueReportController_For_CompanyManager quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store())); 
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager_2.fxml").openStream());
		QuarterlyRevenueReportController_For_CompanyManager_2 quarterlyRevenueReportController_2 = loader_2.getController();
		quarterlyRevenueReportController_2.loadStore(CompanyManagerReportUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store())); 
		
		Scene scene = new Scene(root);
		Scene scene_2 = new Scene(root_2);
		primaryStage.setScene(scene);	
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
/**	public void QuarterlyRevenueReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
/**	{
/**		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
/**		Stage primaryStage = new Stage();
/**		FXMLLoader loader = new FXMLLoader();
/**		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager_2.fxml").openStream());
/**		
/**		QuarterlyRevenueReportController_For_CompanyManager quarterlyRevenueReportController = loader.getController();
/**		quarterlyRevenueReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store())); 
/**		
/**		Scene scene = new Scene(root);			
/**		primaryStage.setScene(scene);		
/**		primaryStage.show();
/**	}
**/
	
/* --------------------------------  The Report About Order ----------------------------------- */	
	
	/* -------------------------------- Store 1 + 2 ----------------------------------- */
	
	public void OrderReport_Store(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		Stage primaryStage_2 = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		FXMLLoader loader_2 = new FXMLLoader();
		
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager.fxml").openStream());
		OrderReportController_For_ComapnyManager orderReportController = loader.getController();
		orderReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager_2.fxml").openStream());
		OrderReportController_For_ComapnyManager_2 orderReportController_2 = loader_2.getController();
		orderReportController_2.loadStore(CompanyManagerReportUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		Scene scene_2 = new Scene(root_2);
		primaryStage.setScene(scene);	
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
/**	public void OrderReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
/**	{
/**		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
/**		Stage primaryStage = new Stage();
/**		FXMLLoader loader = new FXMLLoader();
/**		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager_2.fxml").openStream());
/**		
/**		OrderReportController_For_ComapnyManager orderReportController = loader.getController();
/**		orderReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
/**		
/**		Scene scene = new Scene(root);			
/**		primaryStage.setScene(scene);		
/**		primaryStage.show();
/**	}
**/
	
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	/* -------------------------------- Store 1 + 2 ----------------------------------- */
	
	public void CustomerComplaintStatusReport_Store(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		Stage primaryStage_2 = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		FXMLLoader loader_2 = new FXMLLoader();
		
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager.fxml").openStream());
		CustomerComplaintStatusReportController_For_CompanyManager customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager_2.fxml").openStream());
		CustomerComplaintStatusReportController_For_CompanyManager_2 customerComplaintStatusReportController_2 = loader_2.getController();
		customerComplaintStatusReportController_2.loadStore(CompanyManagerReportUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		Scene scene_2 = new Scene(root_2);
		primaryStage.setScene(scene);
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();	
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
/**	public void CustomerComplaintStatusReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
/**	{
/**		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
/**		Stage primaryStage = new Stage();
/**		FXMLLoader loader = new FXMLLoader();
/** 	Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager.fxml").openStream());
/**	
/** 	CustomerComplaintStatusReportController_For_CompanyManager customerComplaintStatusReportController = loader.getController();
/** 	customerComplaintStatusReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
/**	
/** 	Scene scene = new Scene(root);			
/** 	primaryStage.setScene(scene);		
/** 	primaryStage.show();
/** }    
**/	
	
/* --------------------------------  The Report About Satisfaction ----------------------------------- */
	
	/* -------------------------------- Store 1 + 2 ----------------------------------- */
	
	public void SatisfactionReport_Store(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		Stage primaryStage_2 = new Stage();
		
		FXMLLoader loader = new FXMLLoader();
		FXMLLoader loader_2 = new FXMLLoader();
		
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager.fxml").openStream());
		SatisfactionReportController_For_CompanyManager satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager_2.fxml").openStream());
		SatisfactionReportController_For_CompanyManager_2 satisfactionReportController_2 = loader_2.getController();
		satisfactionReportController_2.loadStore(CompanyManagerReportUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		Scene scene_2 = new Scene(root_2);
		primaryStage.setScene(scene);
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}	
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
/**	public void SatisfactionReport_Store_Two(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
/**	{
/**		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
/** 	Stage primaryStage = new Stage();
/**		FXMLLoader loader = new FXMLLoader();
/**		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager.fxml").openStream());
/**		
/** 	SatisfactionReportController_For_CompanyManager satisfactionReportController = loader.getController();
/**		satisfactionReportController.loadStore(CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_Second_Store()));
/**		
/**		Scene scene = new Scene(root);			
/**		primaryStage.setScene(scene);
/**		primaryStage.show();
/**	}	
**/
	
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
		if(cmbSecondStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbSecondStores.getSelectionModel().getSelectedIndex();
		
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public int getItemIndexFromDateComboBox_For_CompanyManager_FirstStore()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReportsFirstStore.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReportsFirstStore.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public int getItemIndexFromDateComboBox_For_CompanyManager_SecondStore()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReportsSecondStore.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReportsSecondStore.getSelectionModel().getSelectedIndex();
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
		
		for(int i = 0 ; i < CompanyManagerReportUI.Dates_For_Company_Manager_2.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerReportUI.Dates_For_Company_Manager_2.get(i));
		}
		
		DateList_Two = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsSecondStore.setItems(DateList_Two);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void setStores_One_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
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
	
	public void setStores_Two_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerReportUI.stores_For_Company_Manager_2)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_Two = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbSecondStores.setItems(storeList_Two);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Click_On_Your_First_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_1 = CompanyManagerReportUI.stores_For_Company_Manager.get(getItemIndex_First_Store()).getStoreId();
		msg = new Message(temp_Store_Id_1,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Dates_For_Company_Manager.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_One_At_ComboBox();
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Click_On_Your_Second_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_2 = CompanyManagerReportUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()).getStoreId();
		msg = new Message(temp_Store_Id_2,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Dates_For_Company_Manager_2.size() == 0);
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_Two_At_ComboBox();
	}
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Click_On_Your_Quarter_Report_Store_One(ActionEvent event) throws Exception
	{
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report_1 = CompanyManagerReportUI.Dates_For_Company_Manager.get(getItemIndexFromDateComboBox_For_CompanyManager_FirstStore());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_1);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_1);
		msg = new Message(Store_Id_And_Date_Of_Report,"Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
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
		
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}	
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Click_On_Your_Quarter_Report_Store_Two(ActionEvent event) throws Exception
	{
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		temp_Date_Quarter_Report_2 = CompanyManagerReportUI.Dates_For_Company_Manager_2.get(getItemIndexFromDateComboBox_For_CompanyManager_SecondStore());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_2);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_2);
		msg = new Message(Store_Id_And_Date_Of_Report,"Company Manager - Take the Revenue Of Specific Quarter Of Specific Store");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.size() == 0);
		Thread.sleep(200);
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(0));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(CompanyManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.get(1));
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report_2);
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Comparing Between Two Different Quarter Report -----------------------------*/
		
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.clear();
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	}
	
	public void Compare(ActionEvent event) throws Exception
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportController_Compare_Between_Two_Diffrent_Quarter.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Store> stores = new ArrayList<Store>();           /* For the First Connection With The DB the ArrayList Of stores Is Empty */
		msg = new Message(stores,"Company Manager - Add Store To Combo Box From DB");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.stores_For_Company_Manager.size() == 0);
		while(CompanyManagerReportUI.stores_For_Company_Manager_2.size() == 0);
		setStores_One_ComboBox();
		setStores_Two_ComboBox();
	}

}
