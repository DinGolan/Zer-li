package controller;
import java.net.URL;
import java.util.ResourceBundle;

import boundery.ProductUI;
import boundery.StoreManagerUI;
import entity.User;
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

public class StoreManagerController implements Initializable {

    @FXML
    private Button btnStoreManagerExit;   /* Button For Exit From The GUI Of The Store Manager */

    @FXML
    private Button btnStoreManagerLogout; /* Button For Logout From The User Of Store Manager */

    @FXML
    private Button btnViewReport;         /* Button For View Report */

    @FXML
    private Button btnUpdateCatalog;      /* Button For Update Catalog */

    @FXML
    private Button btnStoreManagerOpenNewAccount;  /* Button For Open New Account */

/* ----------------------------------- Open For Us The GUI Of the Store Manager -------------------------------------- */
    
	public void start(Stage primaryStage) throws Exception          			  /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/StoreManagerOptions.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Quarterly Report - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
/* ----------------------------------- Open For Us The GUI Of the Store Manager Report Window -------------------------------------- */	
	
	public void viewReportBtn(ActionEvent event) throws Exception //To open the view report option
	{
		((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream()); 
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
/* ----------------------------------- Open For Us The GUI Of the Store Manager - Update Catalog And Make Sales -------------------------------------- */	
	
	public void updateCatalogAndSalesBtn(ActionEvent event) throws Exception //To open the update catalog and sales option
	{
	
	}
	
/* ----------------------------------- Open For Us The GUI Of the Store Manager - Open New Account -------------------------------------- */
	
	public void openNewAccountBtn(ActionEvent event) throws Exception //To open the add new account option
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/AccountForm.fxml").openStream());
		
		//ProductController productFormController = loader.getController();		
		//productFormController.loadProduct(ProductUI.products.get(getItemIndex())); //In this Line We take the Product that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.setTitle("Account Credit-Card details");
		primaryStage.show();
	}
	
/* ----------------------------------- Exit From the Window Of The Store Manager -------------------------------------- */
	
	/////אולי להוריד את קיום הכפתור יציאה
	public void exitBtn(ActionEvent event) throws Exception //Exit from the store manager options
	{
		System.out.println("exit store manager options");
		System.exit(0);			
	}
	
/* ----------------------------------- Logout From the User Of - Store Manager -------------------------------------- */
	
	///יש בעיה!!!!
	public void logoutBtn(ActionEvent event) throws Exception //logout by the store manager
	{
		UserController u = new UserController();
		u.logout(event);		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	
	}

}
