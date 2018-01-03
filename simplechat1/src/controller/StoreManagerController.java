package controller;
import boundery.ProductUI;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StoreManagerController {
	@FXML
	private Button btnStoreManagerViewSReport = null; //button to view report

	@FXML
	private Button btnStoreManagerUpdateCatalogSales = null; //button to view catalog and update sales
	
	@FXML
	private Button btnStoreManagerOpenNewAccount = null; //button to open new customer account
	
	@FXML
	private Button btnStoreManagerExit = null; //button to exit the menu options
	
	@FXML
	private Button btnStoreManagerLogout = null; //button to do logout
	
	public void viewReportBtn(ActionEvent event) throws Exception //To open the view report option
	{

	}
	
	public void updateCatalogAndSalesBtn(ActionEvent event) throws Exception //To open the update catalog and sales option
	{
	
	}
	
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
	
	
	/////אולי להוריד את קיום הכפתור יציאה
	public void exitBtn(ActionEvent event) throws Exception //Exit from the store manager options
	{
		System.out.println("exit store manager options");
		System.exit(0);			
	}
	
	
	///יש בעיה!!!!
	public void logoutBtn(ActionEvent event) throws Exception //logout by the store manager
	{
		UserController u = new UserController();
		u.logout(event);		
	}

}
