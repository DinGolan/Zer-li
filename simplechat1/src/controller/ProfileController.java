package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.CustomerUI;
import boundery.UserUI;
import entity.Account;
import entity.Complaint;
import entity.CustomerAccountDetailsRow;
import entity.CustomerComplaintDetailsRow;
import entity.CustomerOrderDetailsRow;
import entity.Message;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfileController implements Initializable
{	
	
	/* ------------------------------ All The Butten's That We Need For The Profile Customer GUI ---------------------- */
	@FXML
	private TextField txtCustomerName;

	@FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerPhone;
	
    /* ------------------------- Table Of Order ----------------------------------------------------------------------- */
    
	@FXML
    private TableView<CustomerOrderDetailsRow> Table_Order_Details;

	/* ------------------------- Table Of Complaint ------------------------------------------------------------------- */
	
	@FXML
	private TableView<CustomerComplaintDetailsRow> Table_Complaint_Details;

	/* ------------------------- Table Of Account --------------------------------------------------------------------- */
	
    @FXML
    private TableView<CustomerAccountDetailsRow> table_Account;
	
	/* ------------------------- Table Column Of Order ---------------------------------------------------------------- */
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Integer> Table_Column_Order_ID = new TableColumn<>();

	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Order.SupplyOption> Table_Column_Supply_Option = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Double> Table_Column_Total_Price = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, String> Table_Column_Address = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Date> Table_Column_Order_Date = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Account.PaymentMethod> Table_Column_Payment_Method = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Order.orderStatus> Table_Column_Order_Status = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Integer> Table_Column_Store_ID = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerOrderDetailsRow, Double> Table_Column_Refund = new TableColumn<>();
	
	/* ------------------------- Table Column Of Complaint ------------------------------------------------------------ */
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, String> Table_Column_Month = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, Double> Table_Column_Complaint_Compansation = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, Date> Table_Column_Complaint_Date = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, Integer> Table_Column_Complaint_ID = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, Complaint.ComplaintStatus> Table_Column_Complaint_Status = new TableColumn<>();
	
	@FXML 
	private TableColumn<CustomerComplaintDetailsRow, Integer> Table_Column_Complaint_Order_ID = new TableColumn<>();
	
	/* ------------------------- Table Column Of Account -------------------------------------------------------------- */
	
	 @FXML
	 private TableColumn<CustomerAccountDetailsRow , Integer> table_Column_Account_Store_ID;

	 @FXML
	 private TableColumn<CustomerAccountDetailsRow , Account.PaymentArrangement> table_Column_Account_Payment_Arrangment;

	 @FXML
	 private TableColumn<CustomerAccountDetailsRow , String> table_Column_Account_Card_Number;

	 @FXML
	 private TableColumn<CustomerAccountDetailsRow , Double> table_Column_Account_Balance_Card;

	 @FXML
	 private TableColumn<CustomerAccountDetailsRow , Date > table_Column_End_Date;
	
	/* ----- Button - Back ----- */
	
	@FXML
	private Button btnBack;
	
	/* ----- ObservableList ----- */
	
	ObservableList<CustomerOrderDetailsRow> Profile_Customer_Order ;
	ObservableList<CustomerComplaintDetailsRow> Profile_Customer_Complaint ;
	ObservableList<CustomerAccountDetailsRow> Profile_Customer_Account ;

	/**
	 * With This Function - We Back To The Catalog Option .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void BackToCatalogOption(ActionEvent event) throws Exception   
	{ 
		CustomerUI.Order_Of_Specific_Customer.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();										 
	}
	
	
	/**
	 * With This Function We Put All The Details About The Order's Of Specific Customer Into the Table View Of the Order . 
	 */
	public void Put_The_Order_Of_Specific_Customer_In_Table()
	{
		ArrayList<CustomerOrderDetailsRow> Temp_Orders = new ArrayList<CustomerOrderDetailsRow>();
		
		for(int i = 0 ; i < CustomerUI.Order_Of_Specific_Customer.size() ; i++)
		{ 
			CustomerOrderDetailsRow Specific_Order = new CustomerOrderDetailsRow();
			Specific_Order.setOrder_Address(CustomerUI.Order_Of_Specific_Customer.get(i).getRecipientAddress());
			Specific_Order.setOrder_Date(CustomerUI.Order_Of_Specific_Customer.get(i).getOrderDate());
			Specific_Order.setOrder_ID(CustomerUI.Order_Of_Specific_Customer.get(i).getOrderID());
			Specific_Order.setOrder_PayMent(CustomerUI.Order_Of_Specific_Customer.get(i).getPaymentMethod());
			Specific_Order.setOrder_Status(CustomerUI.Order_Of_Specific_Customer.get(i).getoStatus());
			Specific_Order.setRefund(CustomerUI.Order_Of_Specific_Customer.get(i).getRefund());
			Specific_Order.setStore_ID(CustomerUI.Order_Of_Specific_Customer.get(i).getStoreID());
			Specific_Order.setSupply(CustomerUI.Order_Of_Specific_Customer.get(i).getSupply());
			Specific_Order.setTotal_Price(CustomerUI.Order_Of_Specific_Customer.get(i).getOrderTotalPrice());
			Temp_Orders.add(Specific_Order);
		}
		
		Table_Column_Order_ID.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Integer>("Order_ID"));
		Table_Column_Store_ID.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Integer>("Store_ID"));
		Table_Column_Order_Date.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Date>("Order_Date"));
		Table_Column_Total_Price.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Double>("Total_Price"));
		Table_Column_Address.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, String>("Order_Address"));
		Table_Column_Order_Status.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Order.orderStatus>("Order_Status"));
		Table_Column_Supply_Option.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Order.SupplyOption>("Supply"));
		Table_Column_Payment_Method.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Account.PaymentMethod>("Order_PayMent"));
		Table_Column_Refund.setCellValueFactory(new PropertyValueFactory<CustomerOrderDetailsRow, Double>("Refund"));
		
		Profile_Customer_Order = FXCollections.observableArrayList(Temp_Orders);	
	
		Table_Order_Details.setItems(Profile_Customer_Order);
		
	}
	
	/**
	 * With This Function We Put All The Details About The Complaint's Of Specific Customer Into the Table View Of the Complaint . 
	 */
	public void Put_The_Complaint_Of_Specific_Customer_In_Table()
	{
		ArrayList<CustomerComplaintDetailsRow> Temp_Complaint = new ArrayList<CustomerComplaintDetailsRow>();
		
		for(int i = 0 ; i < CustomerUI.Complaint_Of_Specific_Customer.size() ; i++)
		{ 
			CustomerComplaintDetailsRow Specific_Complaint = new CustomerComplaintDetailsRow();
			Specific_Complaint.setComplaint_ID(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintNum());
			Specific_Complaint.setComplaint_Date(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintDate());
			Specific_Complaint.setComplaint_Status(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintStat());
			Specific_Complaint.setComplaint_Month(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintMonth());
			Specific_Complaint.setComplaint_Compansation(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintCompansation());
			Specific_Complaint.setComplaint_Order_ID(CustomerUI.Complaint_Of_Specific_Customer.get(i).getComplaintOrderId());
			Temp_Complaint.add(Specific_Complaint);
		}

		Table_Column_Complaint_ID.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, Integer>("Complaint_ID"));
		Table_Column_Complaint_Date.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, Date>("Complaint_Date"));
		Table_Column_Complaint_Status.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, Complaint.ComplaintStatus>("Complaint_Status"));
		Table_Column_Month.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, String>("Complaint_Month"));
		Table_Column_Complaint_Compansation.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, Double>("Complaint_Compansation"));
		Table_Column_Complaint_Order_ID.setCellValueFactory(new PropertyValueFactory<CustomerComplaintDetailsRow, Integer>("Complaint_Order_ID"));
			
		Profile_Customer_Complaint = FXCollections.observableArrayList(Temp_Complaint);
		
		Table_Complaint_Details.setItems(Profile_Customer_Complaint);
	}
	
	/**
	 * With This Function We Put All The Details About The Account's Of Specific Customer Into the Table View Of the Account . 
	 */
	public void Put_The_Account_Of_Specific_Customer_In_Table()
	{
		ArrayList<CustomerAccountDetailsRow> Temp_Account = new ArrayList<CustomerAccountDetailsRow>();
		
		for(int i = 0 ; i < CustomerUI.Account_Of_Specific_Customer.size() ; i++)
		{ 
			CustomerAccountDetailsRow Specific_Account = new CustomerAccountDetailsRow();
			Specific_Account.setAccount_Store_ID(CustomerUI.Account_Of_Specific_Customer.get(i).getAccountStoreNum());
			Specific_Account.setBalance_Card(CustomerUI.Account_Of_Specific_Customer.get(i).getAccountBalanceCard());
			Specific_Account.setCredit_Card_Number(CustomerUI.Account_Of_Specific_Customer.get(i).getAccountCreditCardNum());
			Specific_Account.setEnd_Date(CustomerUI.Account_Of_Specific_Customer.get(i).getAccountSubscriptionEndDate());
			Specific_Account.setPayment_Arrangment(CustomerUI.Account_Of_Specific_Customer.get(i).getAccountPaymentArrangement());
			Temp_Account.add(Specific_Account);
		}

		table_Column_Account_Store_ID.setCellValueFactory(new PropertyValueFactory<CustomerAccountDetailsRow , Integer>("Account_Store_ID"));
		table_Column_Account_Payment_Arrangment.setCellValueFactory(new PropertyValueFactory<CustomerAccountDetailsRow , Account.PaymentArrangement>("Payment_Arrangment"));
		table_Column_Account_Card_Number.setCellValueFactory(new PropertyValueFactory<CustomerAccountDetailsRow , String>("Credit_Card_Number"));
		table_Column_Account_Balance_Card.setCellValueFactory(new PropertyValueFactory<CustomerAccountDetailsRow , Double>("Balance_Card"));
		table_Column_End_Date.setCellValueFactory(new PropertyValueFactory<CustomerAccountDetailsRow , Date>("End_Date"));
			
		Profile_Customer_Account = FXCollections.observableArrayList(Temp_Account);
		
		table_Account.setItems(Profile_Customer_Account);
		
	}
	
	/**
	 * With This Function We Initialize The GUI Of Profile Customer . 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/* Taking From The DB All The Details About The Account's Of Specific Customer */
		
		Message Message_Three = new Message(UserUI.user,"Customer - Want To Take His Account Details");
		UserUI.myClient.accept(Message_Three);
		while(CustomerUI.Account_Of_Specific_Customer.size() == 0);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Put_The_Account_Of_Specific_Customer_In_Table();
		
		/* Taking From The DB All The Details About The Order's Of Specific Customer */
		
		this.txtCustomerName.setText(UserUI.user.getUserName());
		this.txtCustomerID.setText(UserUI.user.getId());
		Message Message_One = new Message(UserUI.user,"Customer - Want To Take His Order");
		UserUI.myClient.accept(Message_One);
		while(CustomerUI.Order_Of_Specific_Customer.size() == 0)
		{
			if(CustomerUI.Order_Of_Specific_Customer.size() == 0)
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
		
		this.txtCustomerPhone.setText(CustomerUI.Order_Of_Specific_Customer.get(0).getRecipienPhoneNum()); 		 /* The Phone Will Be The Same In All The Cell's Of the Vector<Order> Of the Specific Customer */
		
		Put_The_Order_Of_Specific_Customer_In_Table();
		
		/* Taking From The DB All The Details About The Complaint's Of Specific Customer */
		
		Message Message_Two = new Message(UserUI.user,"Customer - Want To Take His Complaints");
		UserUI.myClient.accept(Message_Two);	
		while(CustomerUI.Complaint_Of_Specific_Customer.size() == 0)
		{
			if(CustomerUI.Complaint_Of_Specific_Customer.size() == 0)
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
		
		Put_The_Complaint_Of_Specific_Customer_In_Table();
	}
}
