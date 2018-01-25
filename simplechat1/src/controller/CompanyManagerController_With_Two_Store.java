package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CompanyManagerController_With_Two_Store implements Initializable{

	private Message msg;
	private static int itemIndex_For_Store_2 = 0;
	private static int itemIndex_For_Store_1 = 0; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default - Store 1 */
	private static int itemIndex_For_Report_Store_1 = 0;
	private static int itemIndex_For_Report_Store_2 = 0;
	private int temp_Store_Id_1;
	private int temp_Store_Id_2;
	private Date temp_Date_Quarter_Report_1;
	private Date temp_Date_Quarter_Report_2;
	public static boolean Flag_Enter_Two_Store = false;
	
	public static boolean Flag_Enter_On_The_Combo_Box_Store_1 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Store_2 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_1 = false;
	public static boolean Flag_Enter_On_The_Combo_Box_Date_2 = false;
	
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
	 
	 @FXML
	 private Button btnTryAgain;

	 @FXML
	 private TextArea txtArea_Error_Message;
	
/* --------------------------------- Close the Order Report Window ------------------------------------------------- */	 	
	
	public void closeTwoStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
		CompanyManagerUI.stores_For_Company_Manager.clear();
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
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
		quarterlyRevenueReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store())); 
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager_2.fxml").openStream());
		QuarterlyRevenueReportController_For_CompanyManager_2 quarterlyRevenueReportController_2 = loader_2.getController();
		quarterlyRevenueReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store())); 
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		Scene scene_2 = new Scene(root_2);
		scene_2.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}
	
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
		orderReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager_2.fxml").openStream());
		OrderReportController_For_ComapnyManager_2 orderReportController_2 = loader_2.getController();
		orderReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		Scene scene_2 = new Scene(root_2);
		scene_2.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}
		
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
		customerComplaintStatusReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager_2.fxml").openStream());
		CustomerComplaintStatusReportController_For_CompanyManager_2 customerComplaintStatusReportController_2 = loader_2.getController();
		customerComplaintStatusReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		Scene scene_2 = new Scene(root_2);
		scene_2.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();	
	}
	
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
		satisfactionReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()));
		
		Pane root_2 = loader_2.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager_2.fxml").openStream());
		SatisfactionReportController_For_CompanyManager_2 satisfactionReportController_2 = loader_2.getController();
		satisfactionReportController_2.loadStore(CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		Scene scene_2 = new Scene(root_2);
		scene_2.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage_2.setScene(scene_2);
		primaryStage.show();
		primaryStage_2.show();
	}	
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public int getItemIndex_First_Store()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbFirstStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex_For_Store_1;
	
		return cmbFirstStores.getSelectionModel().getSelectedIndex();
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public int getItemIndex_Second_Store()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbSecondStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex_For_Store_2;
	
		return cmbSecondStores.getSelectionModel().getSelectedIndex();
		
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public int getItemIndexFromDateComboBox_For_CompanyManager_FirstStore()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReportsFirstStore.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex_For_Report_Store_1;
	
		return cmbReportsFirstStore.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Report ------------------------------------ */	
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public int getItemIndexFromDateComboBox_For_CompanyManager_SecondStore()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbReportsSecondStore.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex_For_Report_Store_2;
	
		return cmbReportsSecondStore.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void set_Dates_Of_Report_Of_Store_One_At_ComboBox()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList_One = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsFirstStore.setItems(DateList_One);
	}
	
	/* -------------------------------- Store 2 ----------------------------------- */
	
	public void set_Dates_Of_Report_Of_Store_Two_At_ComboBox()      								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager_2.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager_2.get(i));
		}
		
		DateList_Two = FXCollections.observableArrayList(Date_Of_Report);
		cmbReportsSecondStore.setItems(DateList_Two);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ----------------------------------- */	
	
	/* -------------------------------- Store 1 ----------------------------------- */
	
	public void setStores_One_ComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager)
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
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager_2)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList_Two = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbSecondStores.setItems(storeList_Two);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Click_On_Your_First_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_1 = CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_First_Store()).getStoreId();
		msg = new Message(temp_Store_Id_1,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
			{
				break;
			}
		}
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_One_At_ComboBox();
		
		Flag_Enter_On_The_Combo_Box_Store_1 = true;
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------- */		
	
	public void Click_On_Your_Second_Store_Choise(ActionEvent event) throws Exception
	{
		temp_Store_Id_2 = CompanyManagerUI.stores_For_Company_Manager_2.get(getItemIndex_Second_Store()).getStoreId();
		msg = new Message(temp_Store_Id_2,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Dates_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.Dates_For_Company_Manager_2.size() == 0)
			{
				break;
			}
		}
		Thread.sleep(200);
		set_Dates_Of_Report_Of_Store_Two_At_ComboBox();
		
		Flag_Enter_On_The_Combo_Box_Store_2 = true;
	}
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Click_On_Your_Quarter_Report_Store_One(ActionEvent event) throws Exception
	{
		
		temp_Date_Quarter_Report_1 = CompanyManagerUI.Dates_For_Company_Manager.get(getItemIndexFromDateComboBox_For_CompanyManager_FirstStore());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_1);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_1);
	
		/* ---------------------- For The Revenue Report ---------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(0));
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(Store_Id_And_Date_Of_Report.get(1));
		
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
		
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.clear();
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		Flag_Enter_On_The_Combo_Box_Date_1 = true;
	}	
	
/* -------------------------------- The Button Of The Report That We Choose ------------------------------- */				
	
	public void Click_On_Your_Quarter_Report_Store_Two(ActionEvent event) throws Exception
	{
		
		temp_Date_Quarter_Report_2 = CompanyManagerUI.Dates_For_Company_Manager_2.get(getItemIndexFromDateComboBox_For_CompanyManager_SecondStore());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id_2);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report_2);
		
		/* ---------------------- For The Revenue Report ---------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		/* ----------------------- For The Comparing Between Two Different Quarter Report -----------------------------*/
		
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.clear();
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
	
		Flag_Enter_Two_Store = true; 				/* Very Important Variable For The Comparing */
		
		Flag_Enter_On_The_Combo_Box_Date_2 = true;
	}
	
	
	public void Compare(ActionEvent event) throws Exception
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportController_Compare_Between_Two_Diffrent_Quarter.fxml").openStream());
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */		
	
	public void Not_Press_On_Any_Store_For_Compare(ActionEvent event) throws Exception
	{
		CompanyManagerUI.Object_From_Comparing_For_Store_1.clear();
		CompanyManagerUI.Object_From_Comparing_For_Store_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Flag_Enter_Two_Store = false;
		
		Flag_Enter_On_The_Combo_Box_Store_1 = false;
		Flag_Enter_On_The_Combo_Box_Store_2 = false;
		Flag_Enter_On_The_Combo_Box_Date_1 = false;
		Flag_Enter_On_The_Combo_Box_Date_2 = false;
		
		ArrayList<Store> stores = new ArrayList<Store>();           /* For the First Connection With The DB the ArrayList Of stores Is Empty */
		msg = new Message(stores,"Company Manager - Add Store To Combo Box From DB");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
			{
				break;
			}
		}
		while(CompanyManagerUI.stores_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.stores_For_Company_Manager_2.size() == 0)
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
		
		setStores_One_ComboBox();
		setStores_Two_ComboBox();
	}

}
