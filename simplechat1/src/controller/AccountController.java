package controller;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import boundery.AccountUI;
import boundery.UserUI;
import entity.Account;
import entity.Account.PaymentArrangement;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AccountController {
	
	private Account a= new Account();
	public static boolean flag = false;

	@FXML
	private TextField txtAccountUserId; //text field for the user id
	
	@FXML
	private Button btnAccountClose = null; //button close for the account card form
	
	@FXML
	private Button btnAccountCreditCardClose = null; //button close for the account credit-card form
	
	@FXML
	private Button btnAccountTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnAccountAddOther = null; //button to add a new account
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCASH = null; //radio button for the account payment method cash
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCREDITCARD = null; //radio button for the account payment method CREDITCARD
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentFullPrice = null; //radio button for the account payment arrangment full price
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentMonthly = null; //radio button for the account payment arrangment monthly
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentAnnual = null; //radio button for the account payment arrangment annual
	
	@FXML
	private TextField txtAccountCreditCardNum; //text field for the credit card number
	
	@FXML
	private DatePicker dpAccountEndSubscriptionDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private TextField txtAccountBalanceCard; //text field for the account balance in card
	
	@FXML
	private Button btnAccountAddAccount = null; //button add account for the account card form
	
	public void start(Stage primaryStage) throws Exception //With this Method we show the GUI of the Catalog
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AccountForm.fxml"));	
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setTitle("Account Card Form");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void CASHbtn(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rdbtnAccountPaymentMethodCREDITCARD.setSelected(false);
	}
	
	public void CREDITCARDbtn(ActionEvent event) throws Exception //To turn-off the credit card radio button and show the credit-card number field
	{
		rdbtnAccountPaymentMethodCASH.setSelected(false);
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		root = loader.load(getClass().getResource("/controller/AccountFormCreditCard.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Account Credit-Card details");
		primaryStage.show();		
	}
	
	public void closeAccountCreditCardWindow(ActionEvent event) throws Exception  //To close the The Window of the Account credit- card info
	{ 
		a.setAccountCreditCardNum(txtAccountCreditCardNum.getText());
		//System.out.println(a);
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window			
	}
	
	public void FULLPRICEbtn(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rdbtnAccountPaymentArrangmentAnnual.setSelected(false);
		rdbtnAccountPaymentArrangmentMonthly.setSelected(false);
		dpAccountEndSubscriptionDate.setDisable(true); //can't enter subscription date
	}
	
	public void ANNUALbtn(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rdbtnAccountPaymentArrangmentFullPrice.setSelected(false);
		rdbtnAccountPaymentArrangmentMonthly.setSelected(false);
	}
	
	public void MONTHLYbtn(ActionEvent event) throws Exception //To turn-off the cash radio button
	{
		rdbtnAccountPaymentArrangmentFullPrice.setSelected(false);
		rdbtnAccountPaymentArrangmentAnnual.setSelected(false);
	}

	public void addNewAccountButton(ActionEvent event) throws Exception //add new account to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		AccountUI.account=new Account();

		a.setAccountUserId(txtAccountUserId.getText()); //set the user id
		a.setAccountBalanceCard(Double.parseDouble(txtAccountBalanceCard.getText())); //set the balance account
		if(rdbtnAccountPaymentMethodCASH.isSelected()) //if we choose CASH
		{
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CASH"));
			a.setAccountCreditCardNum(null);
		}

		if(rdbtnAccountPaymentMethodCREDITCARD.isSelected()) //if we choose CREDITCARD
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CREDITCARD"));			
		
		LocalDate localDate = dpAccountEndSubscriptionDate.getValue();
		java.sql.Date dateSql=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(localDate != null)
		{
			String dateStr=localDate.toString();
			Date parsed = format.parse(dateStr);
		    dateSql = new java.sql.Date(parsed.getTime());
		    a.setAccountSubscriptionEndDate(dateSql); //set the date
		}
		else
			 a.setAccountSubscriptionEndDate(null); //set the date   
		
		if(rdbtnAccountPaymentArrangmentAnnual.isSelected()) //if we choose Annual
			a.setAccountPaymentArrangement(PaymentArrangement.ANNUAL);	
					
		else if(rdbtnAccountPaymentArrangmentMonthly.isSelected())  //if we choose Monthly
			a.setAccountPaymentArrangement(PaymentArrangement.MONTHLY);
			
		else //if we choose full price or none
			a.setAccountPaymentArrangement(PaymentArrangement.FULLPRICE);
		
		a.setAccountStoreNum(UserUI.store.getStoreId()); //save the store number that connected to this account and user

		Message msg = new Message(a, "Add new account");
		System.out.println(msg);
		//AccountUI u=new AccountUI();
		//AccountUI.myClient.accept(msg);
		UserUI.myClient.accept(msg);
		
		while(flag == false) 
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		flag = false;
		
		if(AccountUI.account.getAccountUserId().equals("Account already exist")) //account for this user already exist
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/UserAccountExistMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else //success create account
		{
			//msg.setMsg(UserUI.user.getId());
			//msg.setOption("change User status to CONNECTED");
			//UserUI.myClient.accept(msg); // change User status to CONNECTED in DB
			//System.out.println("Account for this user create successfully");
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/AddNewUserAccountMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("New account card msg");
			primaryStage.show();
		}
	} 
		
	public void addNewOtherAccount(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AccountForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Account card form");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	public void closeAccountFormWindow(ActionEvent event) throws Exception  //To close the The Window of the Account card GUI
	{ 
		//ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerOptions.fxml").openStream());	
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Menu");
		primaryStage.show(); //show sore manager options window
		//System.out.println("Exit from- Account card form");							
	}
		
}
