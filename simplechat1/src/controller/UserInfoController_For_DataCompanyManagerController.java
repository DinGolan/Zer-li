package controller;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.DataCompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.User;
import entity.User.UserPermission;
import entity.User.UserStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserInfoController_For_DataCompanyManagerController implements Initializable{
	
	private User user;
	private Message msg;
	
	ObservableList<User.UserStatus> list_1;
	ObservableList<User.UserPermission> list_2;
	
/*-------------------------  For The User Info Window After The Data Company Manager Pick Specific User ----------------------------------- */	
	
	@FXML
	private TextField txtUserID;       /* text field for the User Id */
	
	@FXML
	private TextField txtUserName;     /* text field for the User Name */
	
	@FXML
	private TextField txtUserPhone;    /* text field for the User Phone */
	
	@FXML
	private TextField txtUserPassword; /* text field for the User Password */
	
	@FXML
	private ComboBox<User.UserPermission> cmbPremmission;  /* ComboBox of User Permission */
	
	@FXML
	private ComboBox<User.UserStatus> cmbStatus;           /* ComboBox of User Status */
	
	@FXML
	private Button btnClose = null;    /* button close for close product form */
	
	@FXML
	private Button btnUpdate = null;   /* button update for update product */

/* --------------------------------- Loading User To the Text Fields ------------------------------------------------- */	 
	
	public void loadUser(User u) 					/* To load the User details to the text fields */
	{ 
		this.user = u;
		this.txtUserID.setText(user.getId());
		this.txtUserName.setText(user.getUserName());		
		this.txtUserPhone.setText(user.getPhone());
		this.txtUserPassword.setText(user.getPassword());
		this.cmbPremmission.setValue(user.getPermission());
		this.cmbStatus.setValue(user.getStatus());
	}
	
/* --------------------------------- Close the User Info Window ------------------------------------------------- */	 		
	
	public void closeUserWindow(ActionEvent event) throws Exception   
	{ 
		DataCompanyManagerUI.users.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/DataCompanyManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();										   /* show catalog frame window */
	}

/* ----------------------------------------- Set The Combo Box Of Users Status ----------------------------------- */	
	
	private void setStatusComboBox() 							       /* creating list of Faculties */
	{
		ArrayList<User.UserStatus> all_Users = new ArrayList<User.UserStatus>(); 	
		for(User.UserStatus status : User.UserStatus.values())         /* We add to the ArrayList all the Faculty */
		{ 
			all_Users.add(status);
		}
		
		list_1 = FXCollections.observableArrayList(all_Users); 
		cmbStatus.setItems(FXCollections.observableArrayList(list_1)); /* Set the Items Of Faculty at the ComboBox */
	}
	
/* ----------------------------------------- Set The Combo Box Of Users Permission ----------------------------------- */		
	
	private void setPremissionComboBox() 							   /* creating list of Faculties */
	{
	   ArrayList<User.UserPermission> all_Users = new ArrayList<User.UserPermission>(); 	
		for(User.UserPermission permission : User.UserPermission.values())   /* We add to the ArrayList all the Faculty */
		{
			all_Users.add(permission);
		}
		
		list_2 = FXCollections.observableArrayList(all_Users); 
		cmbPremmission.setItems(FXCollections.observableArrayList(list_2)); /* Set the Items Of Faculty at the ComboBox */
	}

/* -------------------------------- Initialized The ComboBoxs In User Info Window ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setPremissionComboBox();
		setStatusComboBox();
	} 

/* -------------------------------- Update The User Fields In And Send To the Server To Update The DB ------------------------------- */			
	
	public void updateUser(ActionEvent event) throws Exception         /* Update the product name */
	{
		User temp_User = new User();
		temp_User.setId(txtUserID.getText());
		temp_User.setUserName(txtUserName.getText());
		temp_User.setPhone(txtUserPhone.getText());
		temp_User.setPassword(txtUserPassword.getText());
		temp_User.setPermission((UserPermission) cmbPremmission.getValue());
		temp_User.setStatus((UserStatus) cmbStatus.getValue());
		msg = new Message(temp_User, "Update User At Data Base");
		UserUI.myClient.accept(msg);
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}


	

