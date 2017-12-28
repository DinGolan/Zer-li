package controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.ProductUI;
import boundery.UserUI;
import entity.Message;
import entity.User;
import entity.User.UserStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
	
	private User u;
	public User toCompare;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private Button btnLogin = null;

	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	
	public void start(Stage primaryStage) throws Exception     /* With this Method we show the GUI of the Catalog */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/boundery/UserLogin.fxml"));
				
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public void loadUser(User u) /* To load the product details to the text fields */
	{ 
		u.setUserName(this.txtUserName.getText());
		u.setPassword(this.txtPassword.getText());
		
		Message msg = new Message(u.getUserName(), "UserStatus");
		
		UserUI.myClient.accept(msg);
		while(toCompare == null);
		if(toCompare.getId().compareTo(null) == 0) // user dos NOT exist
		{
			System.out.println("user dos NOT exist");
		}
		else //user exist
			if(toCompare.getStatus().equals(User.UserStatus.DISCONNECTED))
			{
				System.out.println("user can do Login");
			}
		
			else
			{
				if(toCompare.getStatus().equals(User.UserStatus.CONNECTED))
					System.out.println("user all ready logged in");
				else // user Blocked
					System.out.println("user Blocked");
			}
	}
	
	 public void initialize(URL arg0, ResourceBundle arg1) {	 // Initialized The ComboBox of the Product 
	
	}
	
}
