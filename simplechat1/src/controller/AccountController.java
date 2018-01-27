package controller;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import boundery.AccountUI;
import boundery.UserUI;
import entity.Account;
import entity.Account.PaymentArrangement;
import entity.Message;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the option of create new account and renewal subscription
 */
public class AccountController implements Initializable{
	
	private Account a= new Account();
	public static boolean flag = false;
	public static boolean loadCustomersFlag = false;
	public static boolean customerNeedSubFlag = false;

	@FXML
	private TextField txtAccountUserId; //text field for the user id
	
	@FXML
	private Button btnAccountClose = null; //button close for the account card form
	
	@FXML
	private Button btnAccountTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnAccountAddOther = null; //button to add a new account
	
	@FXML
	private Button btnRenew = null; //button to renewal subscription
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentFullPrice = null; //radio button for the account payment arrangment full price
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentMonthly = null; //radio button for the account payment arrangment monthly
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentAnnual = null; //radio button for the account payment arrangment annual
	
	@FXML
	private RadioButton rdbtnWantsMonthly = null; //radio button for the account payment arrangment monthly
	
	@FXML
	private RadioButton rdbtnWantsAnnual = null; //radio button for the account payment arrangment annual
	
	@FXML
	private TextField txtAccountCreditCardNum; //text field for the credit card number
	
	@FXML
	private Button btnAccountAddAccount = null; //button add account for the account card form
	
	@FXML
	private Button btnSubnext = null; 
	
	@FXML
	private ComboBox <String> cmbCustomersForStore=null; 
	ObservableList<String> listForCustomersComboBox;
	
	/**
	 * Initialized The combobox of the customers Id for the renewal option
	 */
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox
	{
		if(customerNeedSubFlag == true)
		{
			ArrayList<String> customers = new ArrayList<String>();
			for(String id : AccountUI.customersId)
				customers.add(id);
			listForCustomersComboBox = FXCollections.observableArrayList(customers); 
			cmbCustomersForStore.setItems(FXCollections.observableArrayList(listForCustomersComboBox)); 
			customerNeedSubFlag = false;
		}
	}

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
	 * Turn off the black dot at the others options for subscription
	 * @param event- choose monthly radio button
	 */
	public void chooseMONTHLYbtn(ActionEvent event) 
	{
		rdbtnWantsAnnual.setSelected(false);
	}
	
	/**
	 * Turn off the black dot at the others options for subscription
	 * @param event- choose annual radio button
	 */
	public void chooseAnnualbtn(ActionEvent event) 
	{
		rdbtnWantsMonthly.setSelected(false);

	}

	/**
	 * Add new account to Zer-Li system and check all the information and that this user doesn't 
	 * have an account at this store and show error msg if wrong
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
			UserUI.myClient.accept(msg);
		
			while(flag == false) 
			{
				System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			flag = false;
		 
			if(AccountUI.success.compareTo("User doesnt exist")==0)//user for this customer doesn't exist
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
	 * @param event- click on try again or add other account button
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
	
	/**
	 * To load all the customers that have account in this store
	 * and their subscription is over or full price from the start
	 * @param event- click on "next" button in the instructions
	 * @throws Exception if we can't load the fxml
	 */
	public void renSubscription(ActionEvent event) throws Exception 
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		int storeNumber = UserUI.store.getStoreId();
		System.out.println(storeNumber);
		Message msg = new Message(storeNumber , "get all customers have FULLPRICE in this store");
		UserUI.myClient.accept(msg); 
		while(loadCustomersFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		loadCustomersFlag=false;
		
		if(AccountUI.customersId.get(0).compareTo("-1") == 0) 
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			root = loader.load(getClass().getResource("/controller/DontHaveCust.fxml").openStream());
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();		
		}
		else 
		{
			customerNeedSubFlag=true;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			root = loader.load(getClass().getResource("/controller/CustomerForStore.fxml").openStream());
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
	}
		
	/**
	 * update the subscription of the selected customer - renew
	 * @param event- click on "renew" button
	 * @throws Exception if we can't load the fxml
	 */
	public void saveRenew(ActionEvent event) throws Exception 
	{
		int costomerIndex = 0;
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		ArrayList<Object> forRenew = new ArrayList<>();
		if((costomerIndex = getItemIndex()) != -1)
		{
			if(rdbtnWantsMonthly.isSelected()) //if we choose Annual
			{
				forRenew.add(0, PaymentArrangement.MONTHLY);
				LocalDate localDate = LocalDate.now().plusMonths(1); //end subscription date one month later
				java.sql.Date dateSql=null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr=localDate.toString();
				Date parsed = format.parse(dateStr);
				dateSql = new java.sql.Date(parsed.getTime());
				forRenew.add(1,dateSql); //set the date
			}			
			else if(rdbtnWantsAnnual.isSelected())  //if we choose Monthly
			{
				
				forRenew.add(0, PaymentArrangement.ANNUAL);
				LocalDate localDate = LocalDate.now().plusYears(1); //end subscription date one year later
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr=localDate.toString();
				Date parsed = format.parse(dateStr);
				java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
				forRenew.add(1,dateSql); //set the date			
			}
			else
			{ //defult if they didn't choose something
				forRenew.add(0, PaymentArrangement.MONTHLY);
				LocalDate localDate = LocalDate.now().plusMonths(1); //end subscription date one month later
				java.sql.Date dateSql=null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String dateStr=localDate.toString();
				Date parsed = format.parse(dateStr);
				dateSql = new java.sql.Date(parsed.getTime());
				forRenew.add(1,dateSql); //set the date
			}
				
			forRenew.add(2, AccountUI.customersId.get(costomerIndex));
			Message msg = new Message(forRenew, "Update renew account");
			UserUI.myClient.accept(msg);
			
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			root = loader.load(getClass().getResource("/controller/StoreManagerOptions.fxml").openStream());	
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		else //didn't choose id number
		{
			root = loader.load(getClass().getResource("/controller/CustomerDidNotChooseComboBox.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
	}
	
	/**
	 *Take the selected customer Id
	 * @return int- selected customer Id (index)
	 */
	public int getItemIndex()
	{
		if(cmbCustomersForStore.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		return cmbCustomersForStore.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Close the The Window of the account renew error msg
	 * @param event- click on close button
	 * @throws Exception if we can't hide the fxml that loaded
	 */
	public void closeRenewErrorMsgWindow(ActionEvent event) throws Exception  
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}
}
