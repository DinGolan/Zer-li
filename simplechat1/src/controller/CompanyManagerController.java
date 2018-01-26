package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.UserUI;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerController implements Initializable {

/* -------------------------  For The Window Of Company Manager - To View On The Report Of The Store At the Company ---> { One Store Or Two Store } ----------------------------------- */		
	
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnUpdateCatalog;

    @FXML
    private Button btnViewReports;
	
/* ----------------------- Method's For the First Window GUI - Of Company Manager ------------------------ */
    
	/**
	 * In This Function - I See the Fisrt GUI Of the Company Manager .
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception          			  
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/CompanyManagerOptions.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setTitle("Quarterly Report - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Option -----");
		primaryStage.show();		
	}
    
/* ----------------------------------- Open For Us The GUI Of the Company Manager Report Window -------------------------------------- */	
	
	/**
	 * In This Function - I Click On - View Report  
	 * @param event - When The Company Manager Click On the button , He will see GUI that Allowed to Him to choose Store & Date Of Report
	 * @throws Exception
	 */
	public void viewReportBtn(ActionEvent event) throws Exception 
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream()); 
		Scene scene = new Scene(root);	
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Report Form -----");
		primaryStage.show();
	}
	
/* ----------------------------------- Logout From the User Of - Company Manager -------------------------------------- */
	
	/**
	 * In This Function - The Company Manager Logout From The System .  
	 * @param event
	 * @throws Exception
	 */
	public void logoutBtn(ActionEvent event) throws Exception 
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 				
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 			
		FXMLLoader loader = new FXMLLoader(); 		
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();			
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
