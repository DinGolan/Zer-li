package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.StoreManagerUI;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StoreManagerReportController implements Initializable {

	/* Controllers That I Need To Load The Combo Box Of Each Specific Report Type */
	
	private QuarterlyRevenueReportController quarterlyRevenueReportController;
	private OrderReportController orderReportController;
	private CustomerComplaintStatusReportController customerComplaintStatusReportController;
	private SatisfactionReportController satisfactionReportController;

	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default - Store 1 */
	private static int itemIndex = 0; 
	
	/**
	 * This Variable's Help Me To Transfer Details (To/From) The DB .
	 */
	public static int temp_Store_Id;
	private Date temp_Date_Quarter_Report;
	
	/* With This Variable I Know If I Press On The Button Of The Date ComboBox */
	public static boolean Flag_Press_On_The_Date_ComboBox = false;
	
	/**
	 * With This ObservableList I Fill The ComboBox of Date's .
	 */
	ObservableList<Date> DateList;
	
/* -------------------------  For The First Window Of Report - Of Store Manager ----------------------------------- */	
	
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
	private Button btnClose = null;
	
	@FXML
    private TextField txtStoreDetails;
	
/* --------------------------------  The Report About Quarterly Revenue - For Store Manager ----------------------------------- */	
	
	/**
	 * In This Function I Show The Quarterly Revenue Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void QuarterlyRevenueReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Quarterly Revenue Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm.fxml").openStream());
		
		QuarterlyRevenueReportController quarterlyRevenueReportController = loader.getController();
		quarterlyRevenueReportController.loadStore(UserUI.store); 
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Order - For Store Manager ----------------------------------------------- */	 	
	
	/**
	 * In This Function I Show The Order Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void OrderReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Order Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();       /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm.fxml").openStream());
		
		OrderReportController orderReportController = loader.getController();
		orderReportController.loadStore(UserUI.store);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Complaint - For Store Manager ------------------------------------------- */
	
	/**
	 * In This Function I Show The Customer Complaint Status Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void CustomerComplaintStatusReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Customer Complaint Status Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    					 /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm.fxml").openStream());
		
		CustomerComplaintStatusReportController customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(UserUI.store);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Satisfaction - For Store Manager ---------------------------------------- */
	
	/**
	 * In This Function I Show The Satisfaction Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void SatisfactionReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Report' and Show the GUI of the Satisfaction Report that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    		  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm.fxml").openStream());
		
		SatisfactionReportController satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(UserUI.store);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
		
/* -------------------------- Taking Date Index From The Combo Box of Store --------------------------------------------------- */	
	
	/**
	 * In This Function I Return The Index From My Choise From the ComboBox Of the Date .
	 * @return
	 */
	public int getItemIndexFromDateComboBox()                                   	
	{
		if(cmbReports.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReports.getSelectionModel().getSelectedIndex();
	}
	
/* --------------------------------- Loading Store To the Text Fields --------------------------------------------------------- */	
	
	/**
	 * With This Function We Load The TextFiels Of the Store In The Revenue Report .
	 * @param store
	 */
	public void loadStoreToRevenueReport(Store store) 					/* Loading Store */
	{
		this.quarterlyRevenueReportController.loadStore(store);
	}
	
	/**
	 * With This Function We Load The TextFiels Of the Store In The Order Report .
	 * @param store
	 */
	public void loadStoreToOrderReport(Store store) 					/* Loading Store */
	{
		this.orderReportController.loadStore(store);
	}
	
	/**
	 * With This Function We Load The TextFiels Of the Store In The Saticfaction Report .
	 * @param store
	 */
	public void loadStoreToSaticfactionReport(Store store) 				/* Loading Store */
	{
		this.satisfactionReportController.loadStore(store);
	}
	
	/**
	 * With This Function We Load The TextFiels Of the Store In The Customer Complaint Status Report .
	 * @param store
	 */
	public void loadStoreToCustomerComplaintStatusReport(Store store) 	/* Loading Store */
	{
		this.customerComplaintStatusReportController.loadStore(store);
	}
	
/* --------------------------------- Close the Store Manager Report Window ------------------------------------------------- */	 	
	
	/**
	 * With This Function We Close The GUI Of The Store Manager Report . 
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void closeStoreManagerReportWindow(ActionEvent event) throws Exception   
	{ 
		StoreManagerUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();										   
	}

/* ----------------------------------------- Set The Text Of Store ------------------------------------------------------------ */	
	
	/**
	 * In This Function We Set The Text Field Of The Store ID And The Store Address . 
	 */
	public void set_Store_At_Text()    							
	{ 				
		this.txtStoreDetails.setText(UserUI.store.getStoreId() + " ---> " + UserUI.store.getStore_Address());
	}

/* ----------------------------------------- Set The Combo Box Of All the Date Of the Report Of Specific Store ---------------- */		
	
	/**
	 * In This Function We Set The ComboBox Of Date's Of Specific Store . 
	 */
	public void set_Dates_Of_Report_At_ComboBox()      						
	{ 				
		ArrayList<Date> Date_Of_Report = new ArrayList<Date>();	
		
		for(int i = 0 ; i < StoreManagerUI.Dates.size() ; i++)
		{
			Date_Of_Report.add(StoreManagerUI.Dates.get(i));
		}
		
		DateList = FXCollections.observableArrayList(Date_Of_Report);
		cmbReports.setItems(DateList);
	}	
	
/* -------------------------------- The Button Of The Report That We Choose --------------------------------------------------- */			
	
	/**
	 * This Function Start To Work When The Store Manager Choose Specific Report .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Click_On_Your_Quarter_Report(ActionEvent event) throws Exception
	{
		temp_Date_Quarter_Report = StoreManagerUI.Dates.get(getItemIndexFromDateComboBox());
		ArrayList<Object> Store_Id_And_Date_Of_Report = new ArrayList<Object>();
		Store_Id_And_Date_Of_Report.add(temp_Store_Id);
		Store_Id_And_Date_Of_Report.add(temp_Date_Quarter_Report);

		/* ---------------------- For The Revenue Report --------------------------- */
		
		StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.clear();
		StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.add(Store_Id_And_Date_Of_Report.get(0));  		/* The Store_Id */
		StoreManagerUI.Help_To_Transfer_Object_At_Revenue_Report.add(Store_Id_And_Date_Of_Report.get(1));    	/* The Date Of the Report */
		
		/* ----------------------- For The Order Report -----------------------------*/
		
		StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.clear();
		StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.add(Store_Id_And_Date_Of_Report.get(0));  		/* The Store_Id */
		StoreManagerUI.Help_To_Transfer_Object_At_Order_Report.add(Store_Id_And_Date_Of_Report.get(1));  		/* The Date Of the Report */
		
		/* ----------------------- For The Complaint Report ------------------------ */
		
		StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.clear();
		StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.add(Store_Id_And_Date_Of_Report.get(0));  	/* The Store_Id */
		StoreManagerUI.Help_To_Transfer_Object_At_Complaint_Report.add(Store_Id_And_Date_Of_Report.get(1));  	/* The Date Of the Report */
		
		/* ----------------------- For The Satisfaction Report --------------------- */
		
		StoreManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report.clear();
		StoreManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report.add(Store_Id_And_Date_Of_Report.get(0));  /* The Store_Id */
		StoreManagerUI.Help_To_Transfer_Object_At_Satisfaction_Report.add(Store_Id_And_Date_Of_Report.get(1));  /* The Date Of the Report */
		
		Flag_Press_On_The_Date_ComboBox = true;
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI --------------------------------- */	
	
	/**
	 * In This Function We Initialize The Store Manager Report GUI . 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Flag_Press_On_The_Date_ComboBox = false;
		
		set_Store_At_Text();
		temp_Store_Id = UserUI.store.getStoreId();
		msg = new Message(temp_Store_Id, "Store Manager - Take The Date Of All the Report Of Specific Store");
		UserUI.myClient.accept(msg);
		while(StoreManagerUI.Dates.size() == 0)
		{
			if(StoreManagerUI.Dates.size() == 0)
				break;
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		set_Dates_Of_Report_At_ComboBox();
	 }
/* ---------------------------------------------------------------------------------------------------------------------------- */
	
}
