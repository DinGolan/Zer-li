package controller;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import boundery.AccountUI;
import entity.Account;
import entity.Account.PaymentArrangement;
import entity.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountController implements Initializable{
	
	private Account a;
	private Message msg;
//	private static int itemIndex = 0; //This Variable Need for the the Case - that we not choose any paymentMethod from the ComboBox , so we take the FULLPRICE option By Defualt */
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
	private final DatePicker dpAccountEndSubscriptionDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private TextField txtAccountBalanceCard; //text field for the account balance in card
	
	@FXML
	private Button btnAccountAddAccount = null; //button add account for the account card form

	public void addNewAccount(ActionEvent event) throws Exception //add new account to Zer-Li system
	{
		a = new Account();
		a.setAccountUserId(txtAccountUserId.getText());
		if(rdbtnAccountPaymentMethodCASH.isSelected()) //if we choose CASH
		{
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CASH"));
			a.setAccountCreditCardNum(null);
		}

		if(rdbtnAccountPaymentMethodCREDITCARD.isSelected()) //if we choose CREDITCARD
		{
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CREDITCARD"));
			a.setAccountCreditCardNum(txtAccountCreditCardNum.getText());
		}
		
		if(cmbAccountPaymentArrangment.getSelectionModel().getSelectedIndex() ==-1) //if we didn't choose payment arrangment take default
			a.setAccountPaymentArrangement(PaymentArrangement.FULLPRICE);
		else
			a.setAccountPaymentArrangement((PaymentArrangement) cmbAccountPaymentArrangment.getValue());
		
		LocalDate localDate = dpAccountEndSubscriptionDate.getValue();
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		if((PaymentArrangement) cmbAccountPaymentArrangment.getValue()==PaymentArrangement.FULLPRICE)
		{
			a.setAccountSubscriptionEndDate(null); //get the date problem*/
			dpAccountEndSubscriptionDate.setDisable(true);
		}
		a.setAccountSubscriptionEndDate(date); //get the date problem*/
		
		a.setAccountBalanceCard(Double.parseDouble(txtAccountBalanceCard.getText()));
		msg = new Message(a, "Add new account");			
		AccountUI.myClient.accept(msg);
	} 
	
	public void CASHbtn(ActionEvent event) throws Exception //To turn-off the cash/credit card radio button
	{
		rdbtnAccountPaymentMethodCREDITCARD.setSelected(false);
		txtAccountCreditCardNum.setDisable(true); //can't enter credit card number
	}
	
	public void CREDITCARDbtn(ActionEvent event) throws Exception //To turn-off the cash/credit card radio button
	{
		rdbtnAccountPaymentMethodCASH.setSelected(false);
		txtAccountCreditCardNum.setDisable(false); //can't enter credit card number
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
	
/*	public int getItemIndex() //With this Method we Take an payment arrangment from the List at the ComboBox
	{
		if(cmbAccountPaymentArrangment.getSelectionModel().getSelectedIndex() ==-1)
			return itemIndex;
	
		return cmbAccountPaymentArrangment.getSelectionModel().getSelectedIndex();
	}*/
	
	public void start(Stage primaryStage) throws Exception //With this Method we show the GUI of the Catalog
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AccountForm.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setTitle("Account Card Form");
		primaryStage.setScene(scene);		
		primaryStage.show();		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		setAccountPaymentArranngmentComboBox();
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
