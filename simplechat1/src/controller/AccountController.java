package controller;
import java.util.Scanner;
import java.util.Vector;
import controller.CatalogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Product;
import entity.User;
import java.util.ArrayList;

import entity.Account;
import entity.Account.PaymentArrangement;
import entity.Account.PaymentMethod;
import entity.Message;
import boundery.AccountUI;
import boundery.ProductUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AccountController {
	
	private Account a;
	private PaymentMethod m;
	private Message msg;
	private static int itemIndex = 0; //This Variable Need for the the Case - that we not choose any paymentMethod from the ComboBox , so we take the FULLPRICE option By Defualt */
	ObservableList<PaymentArrangement> PaymentArrangmentList;

	@FXML
	private TextField txtAccountUserId; //text field for the user id
	
	@FXML
	private Button btnAccountClose = null; //button close for the account card form
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCASH = null; //radio button for the account payment method cash
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCREDITCARD = null; //radio button for the account payment method CREDITCARD
	
	@FXML
	private TextField txtAccountCreditCardNum; //text field for the credit card number
	
	@FXML
	private ComboBox cmbAccountPaymentArrangment;  //ComboBox with list of options for payment arrangment
	
	@FXML
	private DatePicker dpAccountEndSubscriptionDate;  //DatePicker with the end date of the subscription
	
	@FXML
	private TextField txtAccountBalanceCard; //text field for the account balance in card
	
	@FXML
	private Button btnAccountAddAccount = null; //button add account for the account card form

	public void addNewAccount(ActionEvent event) throws Exception //add new account to Zer-Li system
	{
		a = new Account();
		a.setAccountUserId(txtAccountUserId.getText());
		if(rdbtnAccountPaymentMethodCASH.isSelected()) //if we choose CASH
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CASH"));
		if(rdbtnAccountPaymentMethodCREDITCARD.isSelected()) //if we choose CREDITCARD
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CREDITCARD"));
		a.setAccountCreditCardNum(txtAccountCreditCardNum.getText());
		a.setAccountPaymentArrangement((PaymentArrangement) cmbAccountPaymentArrangment.getValue());
//		a.setAccountSubscriptionEndDate(dpAccountEndSubscriptionDate.getValue()); //get the date problem
		a.setAccountBalanceCard(Double.parseDouble(txtAccountBalanceCard.getText()));
		msg = new Message(a, "add new account");			
		AccountUI.myClient.accept(msg);
	} 
	
	public void setAccountPaymentArranngmentComboBox() //set the paymentArrangment at the ComboBox
	{ 				
		ArrayList<Account.PaymentArrangement> paymentA = new ArrayList<Account.PaymentArrangement>();	
		
		for(Account.PaymentArrangement arrangment: Account.PaymentArrangement.values()) //we add the arrayList all the payment arrangment
		{
			paymentA.add(arrangment);
		}
		
		PaymentArrangmentList = FXCollections.observableArrayList(paymentA);
		cmbAccountPaymentArrangment.setItems(PaymentArrangmentList); //Set the payment arrangment options at the combobox
	}
		
	public void closeAccountFormWindow(ActionEvent event) throws Exception  //To close the The Window of the Account card GUI
	{ 
		/*ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/boundery/CatalogFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show(); show catalog frame window */

		System.out.println("exit Account card form");
		System.exit(0);								
	}

}
