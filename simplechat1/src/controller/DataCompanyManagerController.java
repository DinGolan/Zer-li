package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.DataCompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.User;
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

public class DataCompanyManagerController implements Initializable{
	
	private UserInfoController_For_DataCompanyManagerController uic;
	private Message msg;
	private static int itemIndex = 2; 	/* This Variable Need for the the Case - that we not choose any User from the ComboBox , so we take the User that in Index 2 By Default */
	public User toCompare; 
	
	ArrayList<Object> Temp_Array_For_Update;
	ObservableList<String> userList;

	
/* -------------------------  For The First Window ----------------------------------- */	
	
    @FXML
    private Button btnLogin = null;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private ComboBox<String> cmbUsers;  /* ComboBox With List Of Users */
	
	@FXML
	private Button btnUserInfo = null;  /* Button Of User Info */	
	
/* ----------------------- Method's For the First Window GUI ------------------------ */	
	
	public void start(Stage primaryStage) throws Exception          /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/DataCompanyManagerOptions.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Data Company Manager - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

/* ----------------------- Method's For the Second Window GUI ------------------------ */	
	
	public void UserInfo(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/UserInfoForm_For_DataCompanyManager.fxml").openStream());
		
		UserInfoController_For_DataCompanyManagerController userInfoController_For_DataCompanyManagerController = loader.getController();		                                        
		userInfoController_For_DataCompanyManagerController.loadUser(DataCompanyManagerUI.users.get(getItemIndex()));  /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

/* -------------------------- Taking User From The Combo Box of User ------------------------------------ */		
	
	public int getItemIndex()   /* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbUsers.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbUsers.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Exit Button --------------------------------------------------- */	

	public void getExitBtn(ActionEvent event) throws Exception      /* With this Method we Exit from the Data Company Manager GUI */ 
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}
	
	/* ----------------------------------- Logout From Data Company Manager Option ---------------------------------- */
	
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
	
/* --------------------------------- Loading User To the Text Fields ------------------------------------------------- */
	
	public void loadUser(User user) 	/* Loading User */
	{
		this.uic.loadUser(user);
	}
	
/* ----------------------------------------- Set The Combo Box Of Users ----------------------------------- */	
	
	public void setUsersComboBox()      /* In this Method we Set the Product at the ComboBox */
	{ 				
		ArrayList<String> temp_Users_Name_List = new ArrayList<String>();	
		
		for(User u: DataCompanyManagerUI.users)
		{
			temp_Users_Name_List.add(u.getUserName());
		}
		
		userList = FXCollections.observableArrayList(temp_Users_Name_List);
		cmbUsers.setItems(userList);
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		ArrayList<User> users = new ArrayList<User>();
		
		msg = new Message(users, "Add User To Combo Box From DB");
		UserUI.myClient.accept(msg);
		while(DataCompanyManagerUI.users.size() == 0)
		{
			if(DataCompanyManagerUI.users.size() == 0)
			{
				break;
			}
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		setUsersComboBox();
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */	
	
}

