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
	private Message msg;
	private  Account single_Account;
	private static int itemIndex = 2; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Default */
	
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
	
/*-------------------------  For The Second Window ----------------------------------- */	
	
	@FXML
	private TextField txtUserID;       /* text field for the User Id */
	
	@FXML
	private TextField txtUserName;     /* text field for the User Name */
	
	@FXML
	private TextField txtUserPhone;    /* text field for the User Phone */
	
	@FXML
	private TextField txtUserPassword; /* text field for the User Password */
	
	@FXML
	private ComboBox cmbPremmission;
	
	@FXML
	private ComboBox cmbStatus;
	
	@FXML
	private Button btnClose = null;    /* button close for close product form */
	
	@FXML
	private Button btnUpdate = null;   /* button update for update product */
	
/* ----------------------- Method's For the First Window GUI ------------------------ */	
	
	public void UserInfo(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/UserInfoForm.fxml").openStream());
		
		loader.getController();		                                    /* ? - Not Sure */        
		this.loadUser_One(ProductUI.users.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
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
	
	public void loadUser_One(User user) /* Loading Product */
	{
		this.loadUser_Two(user);
	}
	
	public void setUsersComboBox()      /* In this Method we Set the Product at the ComboBox */
	{ 				
		ArrayList<String> temp_Users_Name_List = new ArrayList<String>();	
		
		for(User u: ProductUI.users)
		{
			temp_Users_Name_List.add(u.getUserName());
		}
		
		userList = FXCollections.observableArrayList(temp_Users_Name_List);
		cmbUsers.setItems(userList);
	}
	
	public void initialize_One(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		ArrayList<String> str = new ArrayList<String>();
		 
		msg = new Message(str, "1");

		ProductUI.myClient.accept(msg);
		while(ProductUI.users.size() == 0);
		setUsersComboBox();
	}
		
/* ----------------------- Method's For the Second Window GUI ------------------------ */
	
	public void loadUser_Two(User u) /* To load the User details to the text fields */
	{ 
		this.user = u;
		this.txtUserID.setText(user.getId());
		this.txtUserName.setText(user.getUserName());		
		this.txtUserPhone.setText(user.getPhone());
		this.txtUserPassword.setText(user.getPassword());
		this.cmbPremmission.setValue(user.getPermission());
		this.cmbStatus.setValue(user.getStatus());
	}
	
	public void closeUserWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		ProductUI.users.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/boundery/UserToChooseFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									/* show catalog frame window */
	}
	
	private void setStatusComboBox() 							       /* creating list of Faculties */
	{
		ArrayList<User.UserStatus> all_Users = new ArrayList<User.UserStatus>(); 	
		for(User.UserStatus status : User.UserStatus.values())   /* We add to the ArrayList all the Faculty */
		{
			all_Users.add(status);
		}
		
		list_1 = FXCollections.observableArrayList(all_Users); 
		cmbStatus.setItems(list_1); /* Set the Items Of Faculty at the ComboBox */
	}
	
	private void setPremissionComboBox() 							   /* creating list of Faculties */
	{
		ArrayList<User.UserPermission> all_Users = new ArrayList<User.UserPermission>(); 	
		for(User.UserPermission status : User.UserPermission.values())   /* We add to the ArrayList all the Faculty */
		{
			all_Users.add(status);
		}
		
		list_2 = FXCollections.observableArrayList(all_Users); 
		cmbStatus.setItems(list_2); /* Set the Items Of Faculty at the ComboBox */
	}

	protected ArrayList<Account> getAccounts_With_Negetive_Balance_From_DB(Connection conn) /* This method get products table details from DB */
	  {
		  ArrayList<Account> accounts = new ArrayList<Account>();
		  Statement stmt;
		  String result_Str;

		  try {
			  stmt = conn.createStatement();
			  String getProductsTable = "SELECT * FROM account WHERE AccountBalanceCard < 0;"; /* Get all the Table from the DB */
			  ResultSet rs = stmt.executeQuery(getProductsTable);
			  while(rs.next())
		 	{
			  single_Account = new Account();									
			  result_Str = rs.getString("AccountUserId");
			  single_Account.setAccountUserId(result_Str);                    /* Take The Id Of the Customer */
			  result_Str = rs.getString("AccountBalanceCard");
			  single_Account.setAccountBalanceCard(Double.parseDouble(result_Str));    /* Take The BalanceInCustomerAccount Of the Customer */
			  result_Str = rs.getString("AccountPaymentMethod");
			  single_Account.setAccountPaymentArrangement(Account.PaymentArrangement.valueOf(result_Str));          /* Take The PaymentWay Of the Customer */
			  result_Str = rs.getString("AccountPaymentArrangement");
			  single_Account.setAccountPaymentMethod(Account.PaymentMethod.valueOf(result_Str));        /* Take The Arrangement Of the Customer */
			  result_Str = rs.getString("AccountCreditCardNum");
			  single_Account.setAccountCreditCardNum(result_Str);                              /* Take The NumberOfCreditCard Of the Customer */
			  result_Str = rs.getString("AccountSubscriptionEndDate");
			 // single_Account.setAccountSubscriptionEndDate((Date)result_Str);                              /* Take The NumberOfCreditCard Of the Customer */
			  accounts.add(single_Account);
		 	}
		  } catch (SQLException e) {	e.printStackTrace();}	
		  return accounts;
	  }
	
	public void initialize_Two(URL arg0, ResourceBundle arg1)  		   /* We Initialized the ComboBox of the Faculty */
	{	
		setStatusComboBox();	
		setPremissionComboBox();
	}
	
	public void updateUser(ActionEvent event) throws Exception         /* Update the product name */
	{
		Temp_Array_For_Update = new ArrayList<Object>();
		Temp_Array_For_Update.add(txtUserID.getText());
		Temp_Array_For_Update.add(txtUserName.getText());
		Temp_Array_For_Update.add(txtUserPhone.getText());
		Temp_Array_For_Update.add(txtUserPassword.getText());
		Temp_Array_For_Update.add(cmbPremmission.getValue());
		Temp_Array_For_Update.add(cmbStatus.getValue());
		msg = new Message(Temp_Array_For_Update, "0");
		ProductUI.myClient.accept(msg);
	} 
}
