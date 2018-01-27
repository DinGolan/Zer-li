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

public class DataCompanyManagerController implements Initializable {
	
	/**
	 * This Variable Helping Me To Load The ComboBox Of User .
	 */
	private UserInfoController_For_DataCompanyManagerController uic;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	public static boolean Flag_Press_On_User_ComboBox = false;
	
	/**
	 *  Defult Index If I Not Press On the ComboBox Of the User's .
	 */
	private static int itemIndex = 2; 	/* This Variable Need for the the Case - that we not choose any User from the ComboBox , so we take the User that in Index 2 By Default */
	
	/**
	 * Variable That Help Me To Compare Between Two User's .
	 */
	private User temp_User_To_Know_If_I_Press_On_Combo_Box; 
	
	ArrayList<Object> Temp_Array_For_Update;
	ObservableList<String> userList;

	
/* -------------------------  For The First Window Of the Data Company Manager -------------------------------------- */	
	
    @FXML
    private Button btnLogin = null;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private ComboBox<String> cmbUsers;  /* ComboBox With List Of Users */
	
	@FXML
	private Button btnUserInfo = null;  /* Button Of User Info */	
	
/* ----------------------- Method's For the First Window GUI -------------------------------------------------------- */	
	
	/**
	 * If We Not Run The Proggram From User UI We Can Run Only The Data Company Manager From Here . 
	 * @param primaryStage - Show The GUI .
	 * @throws Exception - If The FXML Not Working .
	 */
	public void start(Stage primaryStage) throws Exception         
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/DataCompanyManagerOptions.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Data Company Manager - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

/* ----------------------- Method's For the Second Window GUI ------------------------------------------------------- */	
	
	/**
	 * In this Function I Can See The Details Of Each User . 
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void UserInfo(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		temp_User_To_Know_If_I_Press_On_Combo_Box = DataCompanyManagerUI.users.get(getItemIndex());
		
		if(Flag_Press_On_User_ComboBox == true)
		{
			((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/UserInfoForm_For_DataCompanyManager.fxml").openStream());
			
			UserInfoController_For_DataCompanyManagerController userInfoController_For_DataCompanyManagerController = loader.getController();		                                        
			userInfoController_For_DataCompanyManagerController.loadUser(temp_User_To_Know_If_I_Press_On_Combo_Box);  
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- User - Info -----");
			primaryStage.show();
		}
		else if(Flag_Press_On_User_ComboBox == false)
		{
			((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
			Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/Data_Company_Manager_Not_Press_On_Combo_Box_Of_User.fxml").openStream());
			
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- Data Company Manager Not Press On ComboBox Of User -----");		
			primaryStage.show();
		}
	}

/* -------------------------- Taking User From The Combo Box of User ------------------------------------------------ */		
	
	/**
	 * This Function Return For Us The Index That We Choose From the Combo Box Of User's .
	 * @return
	 */
	public int getItemIndex()   
	{
		if(cmbUsers.getSelectionModel().getSelectedIndex() == -1)
		{
			Flag_Press_On_User_ComboBox = false;
			return itemIndex;
		}
		else
		{
			Flag_Press_On_User_ComboBox = true;
		}
	
		return cmbUsers.getSelectionModel().getSelectedIndex();
	}
	
/* ----------------------------------------- Exit Button ------------------------------------------------------------ */	

	/**
	 * In This Function We Can Exit From The Proggram .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void getExitBtn(ActionEvent event) throws Exception       
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}
	
	/* ----------------------------------- Logout From Data Company Manager Option ---------------------------------- */
	
	/**
	 * In This Function We Logout From The System . 
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void logoutBtn(ActionEvent event) throws Exception 
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 										/* change User status to DISCONNECTED in DB */
		((Node) event.getSource()).getScene().getWindow().hide(); 			/* Hiding primary window */
		Stage primaryStage = new Stage(); 									/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 								/* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
/* --------------------------------- Loading User To the Text Fields ------------------------------------------------- */
	
	/**
	 * In This Function We Can Load User To The Combo Box Of User's . 
	 * @param user
	 */
	public void loadUser(User user) 	/* Loading User */
	{
		this.uic.loadUser(user);
	}
	
/* ----------------------------------------- Set The Combo Box Of Users ----------------------------------- */	
	
	/**
	 * In This Function We Set All The Users In The Combo Box . 
	 */
	public void setUsersComboBox()      
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
	
	/**
	 * In This Function We Initialize The GUI Of The First Window Of ---> DataCompanyManagerUI
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  
	{
		Flag_Press_On_User_ComboBox = false;
		
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

