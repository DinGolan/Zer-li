package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.ProductUI;
import boundery.UserUI;
import entity.Account;
import entity.Message;
import entity.Product;
import entity.User;
import entity.User.UserPermission;
import entity.User.UserStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserController {
	
	private User user;
	private UserInfoController uic;
	private Message msg;
	private  Account single_Account;
	private static int itemIndex = 2; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Default */
	public User toCompare; 
	
	ArrayList<Object> Temp_Array_For_Update;
	ObservableList<UserStatus> list_1;
	ObservableList<UserPermission> list_2;
	ObservableList<String> userList;

	
/* -------------------------  For The First Window ----------------------------------- */	
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private ComboBox cmbUsers;  /* ComboBox With List Of Users */
	
	@FXML
	private Button btnUserInfo = null; /* Button Of User Info */	
	
/* ----------------------- Method's For the First Window GUI ------------------------ */	
	
	public void UserInfo(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/UserInfoForm.fxml").openStream());
		
		UserInfoController userInfoController = loader.getController();		                                    /* ? - Not Sure */        
		this.loadUser(UserUI.users.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/UserInfoForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

	public int getItemIndex()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbUsers.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbUsers.getSelectionModel().getSelectedIndex();
	}
	
	public void start(Stage primaryStage) throws Exception          /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/boundery/UserToChooseFrame.fxml"));
				
		Scene scene = new Scene(root);
		/* scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm()); */
		primaryStage.setTitle("Data Company Manager - Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}

	public void getExitBtn(ActionEvent event) throws Exception      /* With this Method we Exit from the Catalog */ 
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}
	
	public void loadUser(User user) /* Loading Product */
	{
		this.uic.loadUser(user);
	}
	
	public void setUsersComboBox()      /* In this Method we Set the Product at the ComboBox */
	{ 				
		ArrayList<String> temp_Users_Name_List = new ArrayList<String>();	
		
		for(User u: UserUI.users)
		{
			temp_Users_Name_List.add(u.getUserName());
		}
		
		userList = FXCollections.observableArrayList(temp_Users_Name_List);
		cmbUsers.setItems(userList);
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		ArrayList<String> str = new ArrayList<String>();
		 
		msg = new Message(str, "Add User To Combo Box From DB");

		UserUI.myClient.accept(msg);
		while(UserUI.users.size() == 0);
		setUsersComboBox();
	}
}
