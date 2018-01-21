package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.CustomerUI;
import boundery.UserUI;
import entity.Account;
import entity.Complaint;
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
	
	@FXML
	private TextField txtCustomerName;

	@FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerPhone;
	
    /* ------------------------- Table Of Order -------------------------------------------- */
    
	@FXML
    private TableView<Order> Table_Order_Details;

	/* ------------------------- Table Of Complaint -------------------------------------------- */
	
	@FXML
	private TableView<Complaint> Table_Complaint_Details;

	/* ------------------------- Table Column Of Order ----------------------------------------- */
	
	@FXML 
	private TableColumn<Order, Integer> Table_Column_Order_ID = new TableColumn<>();

	@FXML 
	private TableColumn<Order, Order.SupplyOption> Table_Column_Supply_Option = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Double> Table_Column_Total_Price = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, String> Table_Column_Address = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Date> Table_Column_Order_Date = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Account.PaymentMethod> Table_Column_Payment_Method = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Order.orderStatus> Table_Column_Order_Status = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Integer> Table_Column_Store_ID = new TableColumn<>();
	
	@FXML 
	private TableColumn<Order, Double> Table_Column_Refund = new TableColumn<>();
	
	/* ------------------------- Table Column Of Complaint ----------------------------------------- */
	
	@FXML 
	private TableColumn<Complaint, String> Table_Column_Month = new TableColumn<>();
	
	@FXML 
	private TableColumn<Complaint, Double> Table_Column_Complaint_Compansation = new TableColumn<>();
	
	@FXML 
	private TableColumn<Complaint, Date> Table_Column_Complaint_Date = new TableColumn<>();
	
	@FXML 
	private TableColumn<Complaint, Integer> Table_Column_Complaint_ID = new TableColumn<>();
	
	@FXML 
	private TableColumn<Complaint, Complaint.ComplaintStatus> Table_Column_Complaint_Status = new TableColumn<>();
	
	@FXML 
	private TableColumn<Complaint, Integer> Table_Column_Complaint_Order_ID = new TableColumn<>();
	
	/* ----- Button - Back ----- */
	
	@FXML
	private Button btnBack;
	
	/* ----- ObservableList ----- */
	
	ObservableList<Order> Profile_Customer_Order;
	ObservableList<Complaint> Profile_Customer_Complaint = FXCollections.observableArrayList();

	
	public void BackToCatalogOption(ActionEvent event) throws Exception   
	{ 
		CustomerUI.Order_Of_Specific_Customer.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}
	
	public void Put_The_Order_Of_Specific_Customer_In_Table()
	{
		ArrayList<Order> Temp_Orders = new ArrayList<Order>();
		
		for(int i = 0 ; i < CustomerUI.Order_Of_Specific_Customer.size() ; i++)
		{ 
			Temp_Orders.add(CustomerUI.Order_Of_Specific_Customer.get(i));
		}
		
		Table_Column_Order_ID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Order ID"));
		Table_Column_Store_ID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Store ID"));
		Table_Column_Order_Date.setCellValueFactory(new PropertyValueFactory<Order, Date>("Order Date"));
		Table_Column_Total_Price.setCellValueFactory(new PropertyValueFactory<Order, Double>("Order Total Price"));
		Table_Column_Address.setCellValueFactory(new PropertyValueFactory<Order, String>("Address"));
		Table_Column_Order_Status.setCellValueFactory(new PropertyValueFactory<Order, Order.orderStatus>("Order Status"));
		Table_Column_Supply_Option.setCellValueFactory(new PropertyValueFactory<Order, Order.SupplyOption>("Order Supply"));
		Table_Column_Payment_Method.setCellValueFactory(new PropertyValueFactory<Order, Account.PaymentMethod>("Payment Method"));
		Table_Column_Refund.setCellValueFactory(new PropertyValueFactory<Order, Double>("Order Refund"));
		
		Profile_Customer_Order = FXCollections.observableArrayList(Temp_Orders);	
	
		Table_Order_Details.setItems(Profile_Customer_Order);
		
	}
	
	public void Put_The_Complaint_Of_Specific_Customer_In_Table()
	{
		ArrayList<Complaint> Temp_Complaint = new ArrayList<Complaint>();
		
		for(int i = 0 ; i < CustomerUI.Complaint_Of_Specific_Customer.size() ; i++)
		{ 
			Temp_Complaint.add(CustomerUI.Complaint_Of_Specific_Customer.get(i));
		}

		Table_Column_Complaint_ID.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("Complaint ID"));
		Table_Column_Complaint_Date.setCellValueFactory(new PropertyValueFactory<Complaint, Date>("Complaint Date"));
		Table_Column_Complaint_Status.setCellValueFactory(new PropertyValueFactory<Complaint, Complaint.ComplaintStatus>("Complaint Status"));
		Table_Column_Month.setCellValueFactory(new PropertyValueFactory<Complaint, String>("Complaint Month"));
		Table_Column_Complaint_Compansation.setCellValueFactory(new PropertyValueFactory<Complaint, Double>("Complaint Compensation"));
		Table_Column_Complaint_Order_ID.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("Complaint Of - Order ID"));
			
		Profile_Customer_Complaint = FXCollections.observableArrayList(Temp_Complaint);
		
		Table_Complaint_Details.setItems(Profile_Customer_Complaint);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		this.txtCustomerName.setText(UserUI.user.getUserName());
		this.txtCustomerID.setText(UserUI.user.getId());
		Message Message_One = new Message(UserUI.user,"Customer - Want To Take His Order");
		UserUI.myClient.accept(Message_One);
		while(CustomerUI.Order_Of_Specific_Customer.size() == 0);
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
		
		Message Message_Two = new Message(UserUI.user,"Customer - Want To Take His Complaints");
		UserUI.myClient.accept(Message_Two);	
		while(CustomerUI.Complaint_Of_Specific_Customer.size() == 0);
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
