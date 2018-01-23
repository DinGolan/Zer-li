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

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnUpdateCatalog;

    @FXML
    private Button btnViewReports;
	
/* ----------------------- Method's For the First Window GUI - Of Company Manager ------------------------ */	
	
	public void start(Stage primaryStage) throws Exception          			  /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/CompanyManagerOptions.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Quarterly Report - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
    
/* ----------------------------------- Open For Us The GUI Of the Company Manager Report Window -------------------------------------- */	
	
	public void viewReportBtn(ActionEvent event) throws Exception //To open the view report option
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream()); 
		Scene scene = new Scene(root);	
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* ----------------------------------- Logout From the User Of - Company Manager -------------------------------------- */
	
	public void logoutBtn(ActionEvent event) throws Exception //logout by the store manager
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 				/* change User status to DISCONNECTED in DB */
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 			/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 		/* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();			
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		
	}

}
