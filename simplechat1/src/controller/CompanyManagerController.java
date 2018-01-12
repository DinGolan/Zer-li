package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Button btnExit;

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
	
/* ----------------------------------- Open For Us The GUI Of the Company Manager - Update Catalog And Make Sales -------------------------------------- */	
	
	public void updateCatalogAndSalesBtn(ActionEvent event) throws Exception //To open the update catalog and sales option
	{
	
	}
	
/* ----------------------------------- Exit From the Window Of The Company Manager -------------------------------------- */
	
	public void exitBtn(ActionEvent event) throws Exception //Exit from the store manager options
	{
		System.out.println("exit store manager options");
		System.exit(0);			
	}
	
/* ----------------------------------- Logout From the User Of - Company Manager -------------------------------------- */
	
	public void logoutBtn(ActionEvent event) throws Exception //logout by the store manager
	{
		UserController u = new UserController();
		u.logout(event);		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		
	}

}
