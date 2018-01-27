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
	
	/**
	 * This Variable Help Me To Update User Status And Premission .
	 */
	private User user;
	public static User.UserPermission Premmision_Of_Specific_User_Before_Update;
	public static User.UserPermission Premmision_Of_Specific_User_After_Update;
	public static User.UserStatus Status_Of_Specific_User_Before_Update;
	public static User.UserStatus Status_Of_Specific_User_After_Update;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * This Variabel's Fill The User.UserStatus & User.UserPermission To The ComboBox Of ---> User.UserStatus & User.UserPermission .
	 */
	ObservableList<User.UserStatus> list_1;
	ObservableList<User.UserPermission> list_2;
	
/*-------------------------  For The User Info Window After The Data Company Manager Pick Specific User ----------------------------------- */	
	
	@FXML
	private TextField txtUserID;       					   /* text field for the User Id */
	
	@FXML
	private TextField txtUserName;     					   /* text field for the User Name */
	
	@FXML
	private TextField txtUserPhone;    					   /* text field for the User Phone */
	
	@FXML
	private TextField txtUserPassword; 					   /* text field for the User Password */
	
	@FXML
	private ComboBox<User.UserPermission> cmbPremmission;  /* ComboBox of User Permission */
	
	@FXML
	private ComboBox<User.UserStatus> cmbStatus;           /* ComboBox of User Status */
	
	@FXML
	private Button btnClose = null;    					   /* button close for close User form */
	
	@FXML
	private Button btnUpdate = null;  					   /* button update for update User */

/* --------------------------------- Loading User To the Text Fields ---------------------------------------------------------------------- */	 
	
	/**
	 * With This Function I Load All The Text Box Of Specific User .
	 * @param u
	 */
	public void loadUser(User u) 					
	{ 
		this.user = u;
		this.txtUserID.setText(user.getId());
		this.txtUserName.setText(user.getUserName());		
		this.txtUserPhone.setText(user.getPhone());
		this.txtUserPassword.setText(user.getPassword());
		this.cmbPremmission.setValue(user.getPermission());
		this.cmbStatus.setValue(user.getStatus());
		
		/* Premmision & Status Before Update */
		Premmision_Of_Specific_User_Before_Update = u.getPermission();
		Status_Of_Specific_User_Before_Update = u.getStatus();
	}
	
/* --------------------------------- Close the User Info Window --------------------------------------------------------------------------- */	 		
	
	/**
	 * With This Function I Close The User Info GUI .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void closeUserWindow(ActionEvent event) throws Exception   
	{ 
		DataCompanyManagerUI.users.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/DataCompanyManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("----- Data Company Manager Option -----");		
		primaryStage.show();										   
	}

/* ----------------------------------------- Set The Combo Box Of Users Status ------------------------------------------------------------ */	
	
	/**
	 * In This Function I Set All The User Status In The ComboBox Of Status . 
	 */
	private void setStatusComboBox() 							      
	{
		ArrayList<User.UserStatus> all_Users = new ArrayList<User.UserStatus>(); 	
		for(User.UserStatus status : User.UserStatus.values())         
		{ 
			all_Users.add(status);
		}
		
		list_1 = FXCollections.observableArrayList(all_Users); 
		cmbStatus.setItems(FXCollections.observableArrayList(list_1)); 
	}
	
/* ----------------------------------------- Set The Combo Box Of Users Permission -------------------------------------------------------- */		
	
	/**
	 * In This Function I Set All The User Premmision In The ComboBox Of Premmision . 
	 */
	private void setPremissionComboBox() 							 
	{
	   ArrayList<User.UserPermission> all_Users = new ArrayList<User.UserPermission>(); 	
		for(User.UserPermission permission : User.UserPermission.values())   
		{
			all_Users.add(permission);
		}
		
		list_2 = FXCollections.observableArrayList(all_Users); 
		cmbPremmission.setItems(FXCollections.observableArrayList(list_2)); 
	}

/* -------------------------------- Initialized The ComboBoxs In User Info Window --------------------------------------------------------- */		
	
	/**
	 * This Function Initialize The User Info GUI .
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setPremissionComboBox();
		setStatusComboBox();
	} 

/* -------------------------------- Update The User Fields In And Send To the Server To Update The DB ------------------------------------- */			
	
	/**
	 * In This Function I Update The ---> Premmision Or Status Of Specific User .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void updateUser(ActionEvent event) throws Exception         
	{
		User temp_User = new User();
		temp_User.setId(txtUserID.getText());
		temp_User.setUserName(txtUserName.getText());
		temp_User.setPhone(txtUserPhone.getText());
		temp_User.setPassword(txtUserPassword.getText());
		temp_User.setPermission((UserPermission) cmbPremmission.getValue());
		temp_User.setStatus((UserStatus) cmbStatus.getValue());
		
		/* Premmision & Status After Update */
		
		Premmision_Of_Specific_User_After_Update = (UserPermission) cmbPremmission.getValue();
		Status_Of_Specific_User_After_Update = (UserStatus) cmbStatus.getValue();
		
		/* Customer Cant Become One Of The Worker In The Company */
		
		if(((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo("CUSTOMER") == 0) && (String.valueOf(Premmision_Of_Specific_User_After_Update).compareTo("CUSTOMER") != 0))
				&& ((String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) != 0) || ((String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) == 0))))
		{
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Cant_Become_From_Customer_To_Worker_Company.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- Data Company Manager Option -----");		
			primaryStage.show();
		}
		
		/* One Of The Worker In The Company Cant Become To Customer */
		
		else if(((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo("CUSTOMER") != 0) && (String.valueOf(Premmision_Of_Specific_User_After_Update).compareTo("CUSTOMER") == 0))
				&& ((String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) != 0) || ((String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) == 0))))
		{
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Cant_Become_From_Worker_Company_To_Other_Customer.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- Data Company Manager Option -----");		
			primaryStage.show();
		}
		
		/* If The Premission & Status Not Change By The Data Company Manager */
		
		else if((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo(String.valueOf(Premmision_Of_Specific_User_After_Update)) == 0) 
				&& (String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) == 0))
		{
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Data_Company_Manager_You_Not_Update_AnyThing.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- There No Have Any Update -----");		
			primaryStage.show();
		}
		
		/* If The Premission Before And After The Change Is Not Customer && I Make Change In The Premission Or In The Status */
		
		else if(((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo("CUSTOMER") != 0) && (String.valueOf(Premmision_Of_Specific_User_After_Update).compareTo("CUSTOMER") != 0))
				&& ((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo(String.valueOf(Premmision_Of_Specific_User_After_Update)) != 0) || (String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) != 0)))
		{
			msg = new Message(temp_User,"Update User At Data Base");
			UserUI.myClient.accept(msg);
			
			/* After I Update ---> I Show GUI That Say ---> "The Update Successful ! */
			
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Data_Company_Manager_The_Update_Was_Good.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- The Update - Successful -----");		
			primaryStage.show();
		}
		
		/* If The Premission Before And After The Change Is  ustomer && I Make Change In The Status */
		
		else if(((String.valueOf(Premmision_Of_Specific_User_Before_Update).compareTo("CUSTOMER") == 0) && (String.valueOf(Premmision_Of_Specific_User_After_Update).compareTo("CUSTOMER") == 0))
				&& (String.valueOf(Status_Of_Specific_User_Before_Update).compareTo(String.valueOf(Status_Of_Specific_User_After_Update)) != 0))
		{
			msg = new Message(temp_User,"Update User At Data Base");
			UserUI.myClient.accept(msg);
			
			/* After I Update ---> I Show GUI That Say ---> "The Update Successful ! */
			
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Data_Company_Manager_The_Update_Was_Good.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- The Update - Successful -----");		
			primaryStage.show();
		}
	}
	
/* ---------------------------------------------------------------------------------------------------------------------------------------- */
	
}


	

