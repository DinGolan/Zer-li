package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.StoreUI;
import boundery.UserUI;
import entity.Message;
import entity.Order;
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

public class CustomerController implements Initializable{

	
	public static int storeID;
	
	private static boolean flag = false;
	
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
	private ComboBox<String> cmbStores = null; /* list of product in cart */
	
	ObservableList<String> listForComboBox;

	
	public void openCatalogWindow(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CatalogBouquet.fxml").openStream());
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		if(flag == false)
			setComboBox();
	}
	
	public void logout(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void openCustomerOptions(ActionEvent event) throws Exception {
		if(cmbStores.getValue() != null) {
		UserUI.store = StoreUI.stores.get(indexOfStore(cmbStores.getValue()));
		flag = true;
		StoreUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		
		primaryStage.setScene(scene);		
		primaryStage.show();}
	}
	
	public void setComboBox()
	{
		StoreUI.stores.clear();
		ArrayList<String> address = new ArrayList<String>();
		Message msg = new Message(null , "get all stores from DB");
		UserUI.myClient.accept(msg); // get all stores from DB
		while(StoreUI.stores.size() == 0);
		for(Store s : StoreUI.stores)
			address.add(s.getStore_Address());
		listForComboBox = FXCollections.observableArrayList(address); 
		cmbStores.setItems(FXCollections.observableArrayList(listForComboBox)); /* Set the Items Of Faculty at the ComboBox */
	}
	
	public int indexOfStore(String address)
	{
		for(int i=0 ; i<StoreUI.stores.size() ; i++)
			if (StoreUI.stores.get(i).getStore_Address().equals(address))
				return i;
		return -1;
	}
}
