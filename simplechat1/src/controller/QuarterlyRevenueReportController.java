package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.ReportUI;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class QuarterlyRevenueReportController implements Initializable{
	
	private Store store;
 
  
    
/* -------------------------  For The Window Of Quarterly Revenue Report ----------------------------------- */	
	
	@FXML
    private TextField txtQuarterNum;
	
    @FXML
	private TextField txtStoreID;               /* Text Field Of the Store ID */

    @FXML
    private TextField txtStoreAddress;          /* Text Field Of the Store Address */

    @FXML
    private TextField txtQuantityOfOrder;       /* Text Field Of the Quantity Of Order At The Store */
    
    @FXML
    private TextField txtRevenueStore;          /* Text Field Of the Revenue Store */
    
    @FXML
	private Button btnClose;                    /* Button For Close The Window */

   
	
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */
 
	public void loadStore(Store s) 					/* To load the Store details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
		this.txtStoreAddress.setText((store.getStore_Address()));
		this.txtQuantityOfOrder.setText(String.valueOf(store.getQuantityOfOrders()));
		this.txtRevenueStore.setText(String.valueOf(store.getTotalRevenue()));
	}

/* --------------------------------- Loading Quarter Number To the Text Fields ------------------------------------------------- */	
	
	public void loadQuarter(String string) 					/* To load the Store details to the text fields */
	{ 
		this.txtQuarterNum.setText(string);
	}
 
/* --------------------------------- Close the Quarterly Revenue Report Window ------------------------------------------------- */		
	
	public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception
	{
		ReportUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/ReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}

/* --------------------------------- Initialize The Quarterly Revenue Report GUI ------------------------------------------------- */	 		
	
	@Override
	 public void initialize(URL location, ResourceBundle resources) 
	{
		
	} 
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
