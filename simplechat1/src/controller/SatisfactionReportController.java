package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.ReportUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SatisfactionReportController implements Initializable {

	private Store store;
	private Message msg;

/* -------------------------  For The Window Of Satisfaction Report ----------------------------------- */	
	
	 @FXML
	 private TextField txtStoreID;           /* Text Field Of the Store ID */
	
	 @FXML
	 private Button btnClose;                /* Button For Exit from The Window */
	
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */	 
	 
	public void loadStore(Store s) 			 /* To load the Store details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
/* --------------------------------- Close the Satisfaction Report Window ------------------------------------------------- */			
	
	public void closeSatisfactionReportWindow(ActionEvent event) throws Exception  
	{ 
		ReportUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/ReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   
	}
	
/* --------------------------------- Initialize The Satisfaction Report GUI ------------------------------------------------- */	 			
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */	

}
