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

	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg; 
	
	/**
	 * This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default - Store 1 .
	 */
	private static int itemIndex = 0; 
	
	/* Variable That Help's me To Transfer Details To the DB */
	private int temp_Store_Id;
	private Date temp_Date_Quarter_Report;
	public static boolean Flag_Enter_On_The_Date_Combo_Box = false;
	public static boolean Flag_Enter_On_The_Store_Combo_Box = false;
	
	
	/**
	 * With This Variable I Can Fill The ComboBox With Number Of Store & Address , And Date Of Report .
	 */
	ObservableList<String> storeList;
	ObservableList<Date> DateList;
	
/* -------------------------  For The Window Of Company Manager Report - To See One Store ----------------------------------- */		
	
	@FXML
	private ComboBox<String> cmbStores;  				    /* ComboBox With List Of Stores */
	
	@FXML
    private ComboBox<Date> cmbReports;						/* ComboBox With List Of Reports */
	
	@FXML
	private Button btnQuarterlyRevenueReport = null;        /* Button Of Quarterly Revenue Report */
	
	@FXML
	private Button btnOrderReport = null; 			 	    /* Button Of Order Report */

	@FXML
	private Button btnCustomerComplaintStatusReport = null; /* Button Of Customer Complaint Status Report */
	
	@FXML
	private Button btnSatisfactionReport = null;            /* Button Of Satisfaction Report */
	
	@FXML
	private Button btnExit = null;							/* Exit Button */
	
/* --------------------------------- Close the Second Window Of Company Manager -------------------------------------- */
	
	/**
	 * In This Function We Close The Window Of That We saw On the Screen And return to the previous window .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void closeOneStoreWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
		CompanyManagerUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		Scene scene = new Scene(root);	
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Report Form -----");
		primaryStage.show();										 
	}
	
/* --------------------------------  The Report About Store Quarterly Revenue ---------------------------------------- */	
	
	/**
	 * In This Function We See The GUI Of - Quarterly Revenue Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void QuarterlyRevenueReport_For_Company_Worker(ActionEvent event) throws Exception       
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm_For_CompanyManager.fxml").openStream());
		
		QuarterlyRevenueReportController_For_CompanyManager quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager())); 
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Quarterly Revenue Report -----");
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Customer Order ------------------------------------------------- */	
	
	/**
	 * In This Function We See The GUI Of - Order Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void OrderReport_For_Company_Worker(ActionEvent event) throws Exception        
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm_For_CompanyManager.fxml").openStream());
		
		OrderReportController_For_ComapnyManager orderReportController = loader.getController();
		orderReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Order Report -----");
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Customer Complaint --------------------------------------------- */
	
	/**
	 * In This Function We See The GUI Of - Customer Complaint Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void CustomerComplaintStatusReport_For_Company_Worker(ActionEvent event) throws Exception        
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm_For_ComapnyManager.fxml").openStream());
		
		CustomerComplaintStatusReportController_For_CompanyManager customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Customer Complaint Report -----");
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Satisfaction Of The Customer ----------------------------------- */
	
	/**
	 * In This Function We See The GUI Of - Satisfaction Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void SatisfactionReport_For_Company_Worker(ActionEvent event) throws Exception        
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm_For_CompanyManager.fxml").openStream());
		
		SatisfactionReportController_For_CompanyManager satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Satisfaction Report -----");
		primaryStage.show();
	}	
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------------------ */	
	
	/**
	 * In This Function We Return The Index Of Our Choise From the ComboBox Of Stores .
	 * @return
	 */
	public int getItemIndex_For_CompanyManager()                                   	
	{
		if(cmbStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbStores.getSelectionModel().getSelectedIndex();
	}
	
/* -------------------------- Taking Date From The Combo Box of Store ------------------------------------------------- */	
	
	/**
	 * In This Function We Return The Index Of Our Choise From the ComboBox Of Reports .
	 * @return
	 */
	public int getItemIndexFromDateComboBox_For_CompanyManager()                                   	
	{
		if(cmbReports.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReports.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ----------------------------------- */		
	
	/**
	 * In This Function We Set All The Date's Of the Report's Of All the Stores - In the ComboBox Of the Date's . 
	 */
	public void set_Dates_Of_Report_At_ComboBox_For_CompanyManager()      								
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < CompanyManagerUI.Dates_For_Company_Manager.size() ; i++)
		{
			Date_Of_Report.add(CompanyManagerUI.Dates_For_Company_Manager.get(i));
		}
		
		DateList = FXCollections.observableArrayList(Date_Of_Report);
		cmbReports.setItems(DateList);
	}
	
/* ----------------------------------------- Set The Combo Box Of Stores ---------------------------------------------- */	
	
	/**
	 * In This Function We Set All The Store's Of the Company Of - In the ComboBox Of the Store's 
	 */
	public void setStoresComboBox_ComapnyManager()    								
	{ 				
		ArrayList<String> temp_Stores_ID_And_Address_List = new ArrayList<String>();	
		
		for(Store s: CompanyManagerUI.stores_For_Company_Manager)
		{
			temp_Stores_ID_And_Address_List.add(String.valueOf(s.getStoreId()) + " ---> " + s.getStore_Address());
		}
		
		storeList = FXCollections.observableArrayList(temp_Stores_ID_And_Address_List);
		cmbStores.setItems(storeList);
	}	
	
/* -------------------------------- The Button Of The Store That You Choose ------------------------------------------- */		
	
	/**
 	 * In This Function - a. We Choose Our Store Choise .
 	 * 					  b. We Going With Our Store Choise To the DB And Take Details .
 	 * 					  c. We Initalized All The Date Of the Report , Of our Store Choise In the ComboBox Of Date's .
 	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
 	 */
	public void Click_On_Your_Store_Choise_For_CompanyManager(ActionEvent event) throws Exception
	{
		temp_Store_Id = CompanyManagerUI.stores_For_Company_Manager.get(getItemIndex_For_CompanyManager()).getStoreId();
		msg = new Message(temp_Store_Id,"Comapny Manager - Take The Date Of All the Report Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.Dates_For_Company_Manager.size() == 0)
			{
				break;
			}
		}
		Thread.sleep(200);
		set_Dates_Of_Report_At_ComboBox_For_CompanyManager();
		
		/* ---------------------- For The Revenue Report ------------------------------------------------ */
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Store_Id);
		
		/* ---------------------- For The Order Report -------------------------------------------------- */
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.addElement(temp_Store_Id);
		
		/* ---------------------- For The Customer Complaint Report ------------------------------------- */
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Store_Id);  /* The Store_Id */
		
		/* ---------------------- For The Satisfaction Report ------------------------------------------- */
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.clear();
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Store_Id);  /* The Store_Id */
		
		Flag_Enter_On_The_Store_Combo_Box = true;
	}	
	
/* -------------------------------- The Button Of The Date Of the Report That We Choose ------------------------------- */				
	
	/**
 	 * In This Function - a. We Choose Our Quarter Choise .
 	 * 					  b. We Going With Our Quarter Choise To the DB And Take Details .
 	 * 					  c. After I Finish To Take The Details From DB , I start To Build All The Report - { Order Report , Revenue Report , Complaint Report , Satisfaction Report } .
 	 * @param  event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
 	 */
	public void Click_On_Your_Quarter_Report_For_CompanyManager(ActionEvent event) throws Exception
	{
		temp_Date_Quarter_Report = CompanyManagerUI.Dates_For_Company_Manager.get(getItemIndexFromDateComboBox_For_CompanyManager());
		
		/* ---------------------- For The Revenue Report ------------------------------------------------------------------------------------------------------------------------------------------------------------- */
		
		/**
		 * This Variable Helps Me To Transfer Important Details For Building The Revenue Report
		 */
		CompanyManagerUI.Help_To_Transfer_Object_At_Revenue_Report_For_Company_Manager.add(temp_Date_Quarter_Report); /* The Date Of the Report */
		
		/* ----------------------- For The Order Report ----------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		/**
		 * This Variable Helps Me To Transfer Important Details For Building The Order Report
		 */
		CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Date_Quarter_Report);  /* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report ----------------------------------------------------------------------------------------------------------------------------------------------------------- */
		
		/**
		 * This Variable Helps Me To Transfer Important Details For Building The Complaint Report
		 */
		CompanyManagerUI.Help_To_Transfer_Object_At_Complaint_Report_For_Company_Manager.add(temp_Date_Quarter_Report);  /* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report -------------------------------------------------------------------------------------------------------------------------------------------------------- */
		
		/**
		 * This Variable Helps Me To Transfer Important Details For Building The Satisfaction Report
		 */
		
		CompanyManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report_For_Company_Manager.add(temp_Date_Quarter_Report);  /* The Date Of the Report */
		
		Flag_Enter_On_The_Date_Combo_Box = true;
	}	
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ---------------------------------------------------- */		
	
	/**
	 * The Function initialize ---> We initialize The Details On the Second GUI Of The Company Manager .
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Flag_Enter_On_The_Date_Combo_Box = false;
		Flag_Enter_On_The_Store_Combo_Box = false;
		
		ArrayList<Store> stores = new ArrayList<Store>();           
		msg = new Message(stores,"Company Manager - Add Store To Combo Box From DB");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.stores_For_Company_Manager.size() == 0)
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
		setStoresComboBox_ComapnyManager();
	}
}
