package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.ReportUI;
import entity.Message;
import entity.Report;
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

public class ReportController implements Initializable {

	private QuarterlyRevenueReportController quarterlyRevenueReportController;
	private OrderReportController orderReportController;
	private CustomerComplaintStatusReportController customerComplaintStatusReportController;
	private SatisfactionReportController satisfactionReportController;

	private Message msg;
	private static int itemIndex = 0; 						/* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Default */ 
	
	ObservableList<Integer> storeList;
	// ObservableList<String> QuarterReportList;
	
/* -------------------------  For The First Window Of Report ----------------------------------- */	
	
	@FXML
	private ComboBox<Integer> cmbStores;  /* ComboBox With List Of Users */
	
	// @FXML
	// private ComboBox<String> cmbReport;  /* ComboBox With List Of Users */
	
	@FXML
	private Button btnQuarterlyRevenueReport = null; /* Button Of User Info */
	
	@FXML
	private Button btnOrderReport = null; /* Button Of User Info */

	@FXML
	private Button btnCustomerComplaintStatusReport = null; /* Button Of User Info */
	
	@FXML
	private Button btnSatisfactionReport = null; /* Button Of User Info */
	
	@FXML
	private Button btnExit = null;
	
/* ----------------------- Method's For the First Window GUI - Report ------------------------ */
	
	public void start(Stage primaryStage) throws Exception          /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ReportForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Quarterly Report - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public void QuarterlyRevenueReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/QuarterlyRevenueReportForm.fxml").openStream());
		
		QuarterlyRevenueReportController quarterlyRevenueReportController = loader.getController();
		/* quarterlyRevenueReportController.loadReport(ReportUI.reports.get(getItemIndexFromComboBoxOfReports())); */
		quarterlyRevenueReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void OrderReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/OrderReportForm.fxml").openStream());
		
		OrderReportController orderReportController = loader.getController();
		orderReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* --------------------------------  The Report About Complaint ----------------------------------- */
	
	public void CustomerComplaintStatusReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerComplaintStatusReportForm.fxml").openStream());
		
		CustomerComplaintStatusReportController customerComplaintStatusReportController = loader.getController();
		customerComplaintStatusReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* ------------------------------------------------------------------------------------------------------------------ */
	
	public void SatisfactionReport(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SatisfactionReportForm.fxml").openStream());
		
		SatisfactionReportController satisfactionReportController = loader.getController();
		satisfactionReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
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
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */
	
	/* public int getItemIndexFromComboBoxOfReports()
	{
		if(cmbReport.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbReport.getSelectionModel().getSelectedIndex();
	} */
	
/* --------------------------------- Loading Store ------------------------------------------------- */	
	
	public void loadStoreToRevenueReport(Store store) 	/* Loading Product */
	{
		this.quarterlyRevenueReportController.loadStore(store);
	}
	
	/* public void loadReportToRevenueReport(Report report) 	
	{
		this.quarterlyRevenueReportController.loadReport(report);
	} */
	
	public void loadStoreToOrderReport(Store store) 	/* Loading Product */
	{
		this.orderReportController.loadStore(store);
	}
	
	public void loadStoreToSaticfactionReport(Store store) 	/* Loading Product */
	{
		this.satisfactionReportController.loadStore(store);
	}
	
	public void loadStoreToCustomerComplaintStatusReport(Store store) 	/* Loading Product */
	{
		this.customerComplaintStatusReportController.loadStore(store);
	}

/* ----------------------------------------- Exit Button --------------------------------------------------- */
	public void getExitBtn(ActionEvent event) throws Exception      	/* With this Method we Exit from the Report UI */ 
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}

/* ----------------------------------------- Set The Combo Box Of STORES ----------------------------------- */	
	
	public void setStoresComboBox()     								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		ArrayList<Integer> temp_Stores_ID_List = new ArrayList<Integer>();	
		
		for(Store s: ReportUI.stores)
		{
			temp_Stores_ID_List.add(s.getStoreId());
		}
		
		storeList = FXCollections.observableArrayList(temp_Stores_ID_List);
		cmbStores.setItems(storeList);
	}
	
/* ----------------------------------------- Set The Combo Box Of Quarter ----------------------------------- */		
	
	/* public void setReportComboBox()      								
	{ 				
		ArrayList<String> temp_Report = new ArrayList<String>();	
		
		for(Report r: ReportUI.reports)
		{
			temp_Report.add(r.getQaurterReport());
		}
		
		QuarterReportList = FXCollections.observableArrayList(temp_Report);
		cmbReport.setItems(QuarterReportList);
	} */
	
	/*public void SelectStoreFromComboBox(ActionEvent event) throws Exception 
	{
		Store Selected_Store = new Store(); 
		Selected_Store.setStoreId((Integer)event.getSource());
		msg = new Message(Selected_Store, "Give Me All the Report Of the Selected Store");
		ReportUI.myClient.accept(msg);
		while(ReportUI.reports.size() == 0);
		setReportComboBox();
	}*/

/* -------------------------------- Initialized The ComboBox In the First Window ------------------------------- */	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		ArrayList<Store> strores = new ArrayList<Store>();
		msg = new Message(strores, "Add Store To Combo Box From DB");
		ReportUI.myClient.accept(msg);
		while(ReportUI.stores.size() == 0);
		setStoresComboBox();
		/*ArrayList<Report> reports = new ArrayList<Report>();
		/msg = new Message(reports, "Add Report To Combo Box From DB");
		/ReportUI.myClient.accept(msg);
		/while(ReportUI.reports.size() == 0);
		/setReportComboBox();*/
	}
}
