package controller;

import java.net.URL;
import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.fxml.Initializable;

public class UserInfoController implements Initializable {
	
	private User user;
	private Message msg;
	private  Account single_Account;
	private ArrayList<Object> temp_User;
	
	ObservableList<User.UserStatus> list_1;
	ObservableList<User.UserPermission> list_2;
	
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

/* ----------------------- Method's For the Second Window GUI ------------------------ */
	
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
	
	public void closeUserWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		UserUI.users.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/UserToChooseFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();										   /* show catalog frame window */
	}
	
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
	                     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setPremissionComboBox();
		setStatusComboBox();
	} 
	
	public void updateUser(ActionEvent event) throws Exception         /* Update the product name */
	{
		temp_User = new ArrayList<Object>();
		temp_User.add((String)txtUserID.getText());
		temp_User.add((String)txtUserName.getText());
		temp_User.add((String)txtUserPhone.getText());
		temp_User.add((String)txtUserPassword.getText());
		temp_User.add((User.UserPermission)cmbPremmission.getValue());
		temp_User.add((User.UserStatus)cmbStatus.getValue());
		msg = new Message(temp_User, "Update User At Data Base");
		UserUI.myClient.accept(msg);
		
		//user.setId(txtUserID.getText());
		//user.setUserName(txtUserName.getText());
		//user.setPhone(txtUserPhone.getText());
		//user.setPassword(txtUserPassword.getText());
		//user.setPermission((User.UserPermission)cmbPremmission.getValue());
		//user.setStatus((User.UserStatus)cmbStatus.getValue());
		//msg = new Message(user, "Update User At Data Base");
		//UserUI.myClient.accept(msg);
	}

/*---------------------------------------------- This Is Belong To the Data Base --------------------------------------------*/
	
//      protected ArrayList<Account> getAccounts_With_Negetive_Balance_From_DB(Connection conn) /* This method get products table details from DB */
//     {
//	      ArrayList<Account> accounts = new ArrayList<Account>();
//	      Statement stmt;
//	      String result_Str;
//
//	      try {
//		 	  stmt = conn.createStatement();
//		 	  String getProductsTable = "SELECT * FROM account WHERE BalanceInCustomerAccount < 0;"; /* Get all the Table from the DB */
//		      ResultSet rs = stmt.executeQuery(getProductsTable);
//		  while(rs.next())
//	 	  {
//		      single_Account = new Account();									
//		      result_Str = rs.getString("CustomerID");
//		      single_Account.setAccountUserId(result_Str);                    /* Take The Id Of the Customer */
//		      result_Str = rs.getString("BalanceInCustomerAccount");
//		      single_Account.setAccountBalanceCard(Double.parseDouble(result_Str));    /* Take The BalanceInCustomerAccount Of the Customer */
//		      result_Str = rs.getString("PaymentWay");
//		      single_Account.setAccountPaymentMethod(Account.PaymentMethod.valueOf(result_Str));          /* Take The PaymentWay Of the Customer */
//		      result_Str = rs.getString("Arrangement");
//		      single_Account.setAccountPaymentArrangement(Account.PaymentArrangement.valueOf(result_Str));        /* Take The Arrangement Of the Customer */
//		      result_Str = rs.getString("NumberOfCreditCard");
//		      single_Account.setAccountCreditCardNum(result_Str);                              /* Take The NumberOfCreditCard Of the Customer */
//		      accounts.add(single_Account);
//	 	   }
//	  } catch (SQLException e) {	e.printStackTrace();}	
//	    return accounts;
//  }
	
/*------------------------------------------------------------------------------------------------------------------------------- */	
	

}
