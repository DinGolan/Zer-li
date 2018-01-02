package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.UserUI;
import entity.Message;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerController implements Initializable{

	@FXML
	private Button btnViewProfile;
	
	@FXML
	private Button btnCancelOrder;
	
	@FXML
	private Button btnCatalog;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Button btnLogout;

	
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
}
