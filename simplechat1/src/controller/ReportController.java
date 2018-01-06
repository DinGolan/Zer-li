package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.ReportUI;
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
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReportController implements Initializable {

	private QuarterlyRevenueReportController quarterlyRevenueReportController;
	private OrderReportController orderReportController;
	private CustomerComplaintStatusReportController customerComplaintStatusReportController;
	private SatisfactionReportController satisfactionReportController;

	private Message msg;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Store from the ComboBox , so we take the Store that in Index 0 By Default */
	
	ObservableList<Integer> storeList;
	
/* -------------------------  For The First Window Of Report ----------------------------------- */	
	
	@FXML
	private ComboBox<Integer> cmbStores;  				    /* ComboBox With List Of Stores */
	
	@FXML
	private RadioButton rbnQuarter_One = null;

	@FXML
	private RadioButton rbnQuarter_Two = null;

	@FXML
	private RadioButton rbnQuarter_Three = null;

	@FXML
	private RadioButton rbnQuarter_Four = null;
	
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
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ReportForm.fxml"));
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
		quarterlyRevenueReportController.loadStore(ReportUI.stores.get(getItemIndex())); 
		
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
		orderReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
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
		customerComplaintStatusReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
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
		satisfactionReportController.loadStore(ReportUI.stores.get(getItemIndex()));
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

/* -------------------------- The Radio Button's ------------------------------------ */	
	
	public void Quarter_One_RadioButten(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rbnQuarter_Two.setSelected(false);
		rbnQuarter_Three.setSelected(false);
		rbnQuarter_Four.setSelected(false);
		Show_The_Info_Of_The_Specific_Store();
	}
	
	public void Quarter_Two_RadioButten(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rbnQuarter_One.setSelected(false);
		rbnQuarter_Three.setSelected(false);
		rbnQuarter_Four.setSelected(false);
		Show_The_Info_Of_The_Specific_Store();
	}
	
	public void Quarter_Three_RadioButten(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rbnQuarter_One.setSelected(false);
		rbnQuarter_Two.setSelected(false);
		rbnQuarter_Four.setSelected(false);
		Show_The_Info_Of_The_Specific_Store();
	}
	
	public void Quarter_Four_RadioButten(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rbnQuarter_One.setSelected(false);
		rbnQuarter_Two.setSelected(false);
		rbnQuarter_Three.setSelected(false);
		Show_The_Info_Of_The_Specific_Store();
	}
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */
	
	public void Show_The_Info_Of_The_Specific_Store()
	{
		if(rbnQuarter_One.isSelected())
		{
			Store temp_Store = new Store();
			temp_Store.setStoreId(getItemIndex());
			temp_Store.setStore_Address("1");       /* This Is Not The Real - Address , This Number Is the Quarter - 1 , Its just Help Me To transfer This Details To DB */
			msg = new Message(temp_Store, "Take All the Revenue Of Specific Store");
			ReportUI.myClient.accept(msg);
			while(ReportUI.Specific_Store == null);
			ReportUI.report_For_Take_Quarter.setQaurterReportNumber("1");
			quarterlyRevenueReportController.loadQuarter("1");
			quarterlyRevenueReportController.loadStore(ReportUI.Specific_Store);
		}
		else if(rbnQuarter_Two.isSelected())
		{
			Store temp_Store = new Store();
			temp_Store.setStoreId(getItemIndex());
			temp_Store.setStore_Address("2");       /* This Is Not The Real - Address , This Number Is the Quarter - 1 , Its just Help Me To transfer This Details To DB */
			msg = new Message(temp_Store, "Take All the Revenue Of Specific Store");
			ReportUI.myClient.accept(msg);
			while(ReportUI.Specific_Store == null);
			ReportUI.report_For_Take_Quarter.setQaurterReportNumber("2");
			quarterlyRevenueReportController.loadQuarter(ReportUI.report_For_Take_Quarter.getQaurterReportNumber());
			quarterlyRevenueReportController.loadStore(ReportUI.Specific_Store);
		}
		else if(rbnQuarter_Three.isSelected())
		{
			Store temp_Store = new Store();
			temp_Store.setStoreId(getItemIndex());
			temp_Store.setStore_Address("3");       /* This Is Not The Real - Address , This Number Is the Quarter - 1 , Its just Help Me To transfer This Details To DB */
			msg = new Message(temp_Store, "Take All the Revenue Of Specific Store");
			ReportUI.myClient.accept(msg);
			while(ReportUI.Specific_Store == null);
			ReportUI.report_For_Take_Quarter.setQaurterReportNumber("3");
			quarterlyRevenueReportController.loadQuarter(ReportUI.report_For_Take_Quarter.getQaurterReportNumber());
			quarterlyRevenueReportController.loadStore(ReportUI.Specific_Store);
		}
		else if(rbnQuarter_Four.isSelected())
		{
			Store temp_Store = new Store();
			temp_Store.setStoreId(getItemIndex());
			temp_Store.setStore_Address("4");       /* This Is Not The Real - Address , This Number Is the Quarter - 1 , Its just Help Me To transfer This Details To DB */
			msg = new Message(temp_Store, "Take All the Revenue Of Specific Store");
			ReportUI.myClient.accept(msg);
			while(ReportUI.Specific_Store == null);
			ReportUI.report_For_Take_Quarter.setQaurterReportNumber("4");
			quarterlyRevenueReportController.loadQuarter(ReportUI.report_For_Take_Quarter.getQaurterReportNumber());
			quarterlyRevenueReportController.loadStore(ReportUI.Specific_Store);
		}
	}
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndex()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbStores.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbStores.getSelectionModel().getSelectedIndex();
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

/* -------------------------------- Initialized The ComboBox In the First Window - Report GUI ------------------------------- */	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Store> strores = new ArrayList<Store>();
		msg = new Message(strores, "Add Store To Combo Box From DB");
		ReportUI.myClient.accept(msg);
		while(ReportUI.stores.size() == 0);
		setStoresComboBox();
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
