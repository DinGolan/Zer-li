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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the option of create new account
 */
public class AccountController {
	
	private Account a= new Account();
	public static boolean flag = false;

	@FXML
	private TextField txtAccountUserId; //text field for the user id
	
	@FXML
	private Button btnAccountClose = null; //button close for the account card form
	
	@FXML
	private Button btnAccountTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnAccountAddOther = null; //button to add a new account
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentFullPrice = null; //radio button for the account payment arrangment full price
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentMonthly = null; //radio button for the account payment arrangment monthly
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentAnnual = null; //radio button for the account payment arrangment annual
	
	@FXML
	private TextField txtAccountCreditCardNum; //text field for the credit card number
	
	@FXML
	private Button btnAccountAddAccount = null; //button add account for the account card form
	
	/**
	 * Turn off the black dot at the others options for subscription
	 * @param event- choose full price radio button
	 */
	public void FULLPRICEbtn(ActionEvent event) 
	{
		rdbtnAccountPaymentArrangmentAnnual.setSelected(false);
		rdbtnAccountPaymentArrangmentMonthly.setSelected(false);
	}
	
	/**
	 * Turn off the black dot at the others options for subscription
	 * @param event- choose annual radio button
	 */
	public void ANNUALbtn(ActionEvent event) 
	{
		rdbtnAccountPaymentArrangmentFullPrice.setSelected(false);
		rdbtnAccountPaymentArrangmentMonthly.setSelected(false);
	}
	
	/**
	 * Turn off the black dot at the others options for subscription
	 * @param event- choose monthly radio button
	 */
	public void MONTHLYbtn(ActionEvent event) 
	{
		rdbtnAccountPaymentArrangmentFullPrice.setSelected(false);
		rdbtnAccountPaymentArrangmentAnnual.setSelected(false);
	}

	/**
	 * Add new account to Zer-Li system and check all the information and show error msg if wrong
	 * @param event- click on add account button
	 * @throws Exception if we can't load the fxml
	 */
	public void addNewAccountButton(ActionEvent event) throws Exception //add new account to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		AccountUI.account=new Account();

		a.setAccountUserId(txtAccountUserId.getText()); //set the user id
		a.setAccountBalanceCard(0); //set the balance account
		a.setAccountStoreNum(UserUI.store.getStoreId()); //save the store number that connected to this account and user
		if(rdbtnAccountPaymentArrangmentAnnual.isSelected()) //if we choose Annual
		{
			a.setAccountPaymentArrangement(PaymentArrangement.ANNUAL);
			LocalDate localDate = LocalDate.now().plusYears(1); //end subscription date one year later
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr=localDate.toString();
			Date parsed = format.parse(dateStr);
			java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
			a.setAccountSubscriptionEndDate(dateSql); //set the date
		}
					
		else if(rdbtnAccountPaymentArrangmentMonthly.isSelected())  //if we choose Monthly
		{
			a.setAccountPaymentArrangement(PaymentArrangement.MONTHLY);
			LocalDate localDate = LocalDate.now().plusMonths(1); //end subscription date one month later
			java.sql.Date dateSql=null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr=localDate.toString();
			Date parsed = format.parse(dateStr);
			dateSql = new java.sql.Date(parsed.getTime());
			a.setAccountSubscriptionEndDate(dateSql); //set the date
		}
			
		else //if we choose full price or none
		{
			a.setAccountPaymentArrangement(PaymentArrangement.FULLPRICE);
			a.setAccountSubscriptionEndDate(null); //set the date
		}
		
		if((txtAccountCreditCardNum.getLength()!=16)||(!(txtAccountCreditCardNum.getText().matches("[0-9]+")))) //enter credit card number length wrong
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/AccountCreditCardLengthMsg.fxml").openStream());
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		
		else 
		{ //enter good credit card number
			a.setAccountCreditCardNum(txtAccountCreditCardNum.getText()); //set the credit card number
			Message msg = new Message(a, "Add new account");
			System.out.println(msg);
			UserUI.myClient.accept(msg);
		
			while(flag == false) 
			{
				System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			flag = false;
		
			System.out.print(AccountUI.success); 
			if(AccountUI.success.compareTo("User doesnt exist")==0)//user for this customer doesnt exist
			{
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/AccountUserNotExistMsg.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();	
			}
			
			else if(AccountUI.success.compareTo("Account already exist")==0) //account for this user already exist
			{
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/UserAccountExistMsg.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();	
			}
			
			else //success create account
			{
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/AddNewUserAccountMsg.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("New account card msg");
				primaryStage.show();
			}
		}
	} 
	
	/**
	 * Show the GUI again of empty account form to add a new other account
	 * @param event- click on try again or add other acoount button
	 * @throws Exception if we can't load the fxml
	 */
	public void addNewOtherAccount(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AccountForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Account card form");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	/**
	 * To close the The Window of the Account card and return the store manager menu
	 * @param event- click on close button
	 * @throws Exception if we can't load the fxml
	 */
	public void closeAccountFormWindow(ActionEvent event) throws Exception  //To close the The Window of the Account card GUI
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerOptions.fxml").openStream());	
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Menu");
		primaryStage.show(); //show sore manager options window							
	}
		
}
