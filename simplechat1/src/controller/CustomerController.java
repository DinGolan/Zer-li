package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CustomerUI;
import boundery.StoreUI;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the Customer options - make an order, view catalog, 
 * cancel order, self definition product or view his profile
 *
 */
public class CustomerController implements Initializable{

	public static int cflag =0;
	
	public static int storeID;
	
	public static boolean flag = false;
	
	public static boolean Customer_Have_Account = false;
	
	@FXML
	private Button btnViewProfile;
	
	@FXML
	private Button btnOK;
	
	@FXML
	private Button btnCancelOrder;
	
	@FXML
	private Button btnCatalog;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Button btnLogout;
	
	@FXML
	private Button btnSelfDef;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnBackToChooseStore;
	
	@FXML
	private ComboBox<String> cmbStores = null; /* list of product in cart */
	
	ObservableList<String> listForComboBox;

	/**
	 * open "catalog" window 
	 * @param event - customer click "view catalog" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openCatalogWindow(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/Catalog.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Catalog");
		primaryStage.setScene(scene);	
		primaryStage.show();
	}
	
	/**
	 * open "profile" window - customer can view his orders, his
	 * account details
	 * @param event - customer click "view profile" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openProfileWindow(ActionEvent event) throws Exception 
	{
		/* Check If To The Customer There Have Account */
		
		Message Message_One = new Message(UserUI.user,"Customer - Check If To The Customer There Have Account");
		UserUI.myClient.accept(Message_One);
		while(CustomerUI.Account_Of_Specific_Customer.size() == 0)
		{
			if(CustomerUI.Account_Of_Specific_Customer.size() == 0)
			{
				break;
			}
		}
		
		Thread.sleep(200);
		
		if(Customer_Have_Account == true)
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CustomerProfile.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Profile");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
		else if(Customer_Have_Account == false)
		{
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/Customer_Dont_Have_Account.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Customer Dont Have Account");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	/**
	 * open "cancel order" window - customer can cancel only his
	 * orders in condition its not already close order
	 * @param event - customer click "cancel order" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void cancelOrderWindow(ActionEvent event) throws Exception { //this method for the option to cancel order- open the instruction
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CancelRules.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());	
		primaryStage.setTitle("Cancel Rules");
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}
	
	/**
	 * call function "setComboBox" that set stores comboBox that the 
	 * customer have to choose where he is at (the catalog sales change
	 * between different stores) 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		Customer_Have_Account = false;
		
		if(flag == false)
			setComboBox();
	}
	
	
	/**
	 * logout - customer logout the system and "UserLogin" window open.
	 * @param event - the customer clicked "logout" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void logout(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		flag= false;
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * open "customer options" window after the customer choose store he is at
	 * @param event - customer click "OK" button after he choose store
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openCustomerOptions(ActionEvent event) throws Exception 
	{
		if(cmbStores.getValue() != null) 
		{
		UserUI.store = StoreUI.stores.get(indexOfStore(cmbStores.getValue()));
		/* System.out.println(UserUI.store.getStoreId()); */
		flag = true;
		StoreUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Customer Options");
		primaryStage.setScene(scene);		
		primaryStage.show();
		}
		else
		{
			flag = true;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/DidNotChooseStore.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Message");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	/**
	 * if the customer choose to make an self definition product
	 * we open "Self Definition Product" window
	 * @param event - customer click "Self Definition Product" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openSelfDef(ActionEvent event) throws Exception {
		SelfDefenitionProductController.modeFlag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SelfDefenitionProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Self Defenition Product");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/**
	 * set stores comboBox that the 
	 * customer have to choose where he is at (the catalog sales change
	 * between different stores) 
	 */
	public void setComboBox()
	{
		StoreUI.stores.clear();
		ArrayList<String> address = new ArrayList<String>();
		Message msg = new Message(null , "get all stores from DB");
		UserUI.myClient.accept(msg); // get all stores from DB
		cflag =0;
		while(cflag == 0)
		{
			System.out.print("");
		}
		cflag =0;
		for(Store s : StoreUI.stores)
			address.add(s.getStore_Address());
		listForComboBox = FXCollections.observableArrayList(address); 
		cmbStores.setItems(FXCollections.observableArrayList(listForComboBox)); /*  */
	}
	
	
	/**
	 * "indexOfStore" - search, find and return the store id from combo-Box
	 * that the customer choose he is in this store
	 * @param pId - gets the store ID to find it in the stores list
	 * @return - return the store Id that the choose
	 */
	public int indexOfStore(String address)
	{
		for(int i=0 ; i<StoreUI.stores.size() ; i++)
			if (StoreUI.stores.get(i).getStore_Address().equals(address))
				return i;
		return -1;
	}
	
	/**
	 * back to "choose store" window
	 * @param event - customer click "back" after error message shown
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToChooseStore(ActionEvent event) throws Exception {
		flag = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerChooseStore.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Choose store");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
}
