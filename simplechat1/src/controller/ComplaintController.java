package controller;

import java.time.LocalDate;
import boundery.AccountUI;
import boundery.ComplaintUI;
import entity.Account;
import entity.Message;
import entity.Account.PaymentArrangement;
import entity.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintController {
	private Complaint c= new Complaint();
	private Account a = new Account();
	public static int complaintIndex=1;
	public static boolean flag = false;

	@FXML
	private TextField txtComplaintNum; //text field for the complaint number
	
	@FXML
	private TextField txtAccountUserId; //text field for the complaint number
	
	@FXML
	private TextField txtAccountBalanceCard; //text field for the complaint number
	
	@FXML
	private Button btnComplaintClose = null; //button close for the complaint form
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCASH; //button close for the complaint form
	
	@FXML
	private RadioButton rdbtnAccountPaymentMethodCREDITCARD; //button close for the complaint form
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentAnnual; //button close for the complaint form
	
	@FXML
	private RadioButton rdbtnAccountPaymentArrangmentMonthly; //button close for the complaint form
	
	
	@FXML
	private DatePicker dpAccountEndSubscriptionDate; //button close for the complaint form
	
	@FXML
	private Button btnComplaintSave = null; //button to add a new complaint
	
	@FXML
	private final DatePicker dpComplaintDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private TextArea txtComplaintReason; //text area for the complaint details
	
	
	public void start(Stage primaryStage) throws Exception //With this Method we show the GUI of the Catalog
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ComplaintForm.fxml"));	
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setTitle("Complaint Form");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
			
	public void saveComplaintButton(ActionEvent event) throws Exception //add new complaint to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		ComplaintUI.complaint=new Complaint();

		a.setAccountUserId(txtAccountUserId.getText()); //set the user id
		a.setAccountBalanceCard(Double.parseDouble(txtAccountBalanceCard.getText())); //set the balance account
		if(rdbtnAccountPaymentMethodCASH.isSelected()) //if we choose CASH
		{
			System.out.println("maymay");
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CASH"));
			a.setAccountCreditCardNum(null);
		}

		if(rdbtnAccountPaymentMethodCREDITCARD.isSelected()) //if we choose CREDITCARD
			a.setAccountPaymentMethod(Account.PaymentMethod.valueOf("CREDITCARD"));			
		
		LocalDate localDate = dpAccountEndSubscriptionDate.getValue();
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		
		if(rdbtnAccountPaymentArrangmentAnnual.isSelected()){ //if we choose Annual
			a.setAccountPaymentArrangement(PaymentArrangement.ANNUAL);	
			a.setAccountSubscriptionEndDate(date); //get the date problem
		}
		else if(rdbtnAccountPaymentArrangmentMonthly.isSelected()) { //if we choose Monthly
			a.setAccountPaymentArrangement(PaymentArrangement.MONTHLY);
			a.setAccountSubscriptionEndDate(date); //get the date problem
		}
		else {//if we choose full price or none
			a.setAccountPaymentArrangement(PaymentArrangement.FULLPRICE);	
			a.setAccountSubscriptionEndDate(null);
		}
		
		System.out.println(a);
		
		Message msg = new Message(a, "Add new account");	
		AccountUI.myClient.accept(msg);
		
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
		else //success create complaint
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/AddNewComplaintMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("New complaint msg");
			primaryStage.show();
		}
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
		
	
	public void addNewOtherComplaint(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ComplaintForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Complaint Form");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}

	public void closeComplaintFormWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint form GUI
	{ 
		/*ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/boundery/CatalogFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show(); show catalog frame window */

		System.out.println("Exit from- Complaint form");
		System.exit(0);								
	}
}
