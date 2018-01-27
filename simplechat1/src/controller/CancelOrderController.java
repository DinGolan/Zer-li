package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import boundery.OrderUI;
import boundery.UserUI;
import entity.CancelOrderItemRow;
import entity.Message;
import entity.Order;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the option of cancel an order
 */
public class CancelOrderController implements Initializable{

	public static int flag=0;
	public static boolean LoadOrderCombo=false;
	public static boolean LoadOrderDetails=false;
	public static boolean orderFlag=false;
	public static boolean getCancelOrdersFlag=false;
	public static boolean refundMsgflag=false;
	public static boolean getProducstFlag=false;
	
	@FXML
	private TextField txtOrderNum=null; //text field for the order number
	
	@FXML
	private TextField txtOrderDate=null; //text field for the order date
	
	@FXML
	private TextField txtOrderReqDate=null; //text field for the order required date
	
	@FXML
	private TextField txtOrderReqTime=null; //text field for the order required time
	
	@FXML
	private TextField txtOrderTotalPrice=null; //text field for the order total price
	
	@FXML
	private TextArea txtOrderCancelRefund=null; //text Area for the cancel order customer msg with the refund
	
	private static String textRefundMsg=null; //string for the refund msg
	
	@FXML
	private Button btnCancelOrderOpen=null; //button to open the order details
	
	@FXML
	private Button btnOrderCancel=null; //button to cancel an order
	
	@FXML
	private Button btnOrdernext=null; //button to cancel an order next option
	
	@FXML
	private Button btnCancelOtherOrder=null; //button to cancel other order
	
	@FXML
	private Button btnCancelOrderClose=null; //button close for error msg or for return the menu options	
	
	@FXML
	private ComboBox <Integer> cmbOrdersForCustomer=null; //combobox to view all the orders for the specific customer
	ObservableList<Integer> listForOrderCustomerComboBox;	

	@FXML 
	private TableView<CancelOrderItemRow> cancelOrder_table = new TableView<>();

	@FXML 
	private TableColumn<CancelOrderItemRow, Integer> tablecolumn_id = new TableColumn<>();
	
	@FXML 
	private TableColumn<CancelOrderItemRow, String> tablecolumn_name = new TableColumn<>();
	
	@FXML 
	private TableColumn<CancelOrderItemRow, Double> tablecolumn_price = new TableColumn<>();
	
	@FXML 
	private TableColumn<CancelOrderItemRow, Integer> tablecolumn_quantity = new TableColumn<>();
	
	ObservableList<CancelOrderItemRow> cancelOrder = FXCollections.observableArrayList();
	
	/**
	 * Initialized The ComboBox of the orders that he can to cancel 
	 * or the order details 
	 * or the order refund msg
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		if(LoadOrderCombo==true) // Initialized The ComboBox of the orders that he can to cancel
		{
			ArrayList<Integer> ordersNum = new ArrayList<Integer>();
			for(Integer num : OrderUI.ordersNumbers)
				ordersNum.add(num);
			listForOrderCustomerComboBox = FXCollections.observableArrayList(ordersNum); 
			cmbOrdersForCustomer.setItems(FXCollections.observableArrayList(listForOrderCustomerComboBox)); //set the orders to this customer
			LoadOrderCombo=false;
		}
		else if (LoadOrderDetails==true) //Initialized The order details
		{
			this.txtOrderNum.setText(String.valueOf(OrderUI.order.getOrderID())); //text field for the order number
			this.txtOrderDate.setText(String.valueOf(OrderUI.order.getOrderDate())); //text field for the order date
			this.txtOrderReqDate.setText(String.valueOf(OrderUI.order.getRequiredSupplyDate())); //text field for the order required date
			this.txtOrderReqTime.setText(String.valueOf(OrderUI.order.getRequiredSupplyTime())); //text field for the order required time
			this.txtOrderTotalPrice.setText(String.valueOf(OrderUI.order.getOrderTotalPrice())); //text field for the order total price
			
			//to load the products in order table
			Message msg=new Message(OrderUI.order.getOrderID(),"get all products in order"); 
			UserUI.myClient.accept(msg);
			while(getProducstFlag==false) 
			{
				System.out.print("");
			}
			getProducstFlag=false;
			for(int i=0; i<OrderUI.productInOrder.size();i++)
			{
				Product p = OrderUI.productInOrder.get(i);
				CancelOrderItemRow itemRow = new CancelOrderItemRow(p.getpID(), p.getpName(),p.getQuantity() ,p.getpPrice()); //add 1 product
				cancelOrder.add(itemRow);
			}
			
			tablecolumn_id.setCellValueFactory(new PropertyValueFactory<CancelOrderItemRow, Integer>("m_id"));
			tablecolumn_name.setCellValueFactory(new PropertyValueFactory<CancelOrderItemRow, String>("m_name"));
			tablecolumn_quantity.setCellValueFactory(new PropertyValueFactory<CancelOrderItemRow, Integer>("m_quantity"));
			tablecolumn_price.setCellValueFactory(new PropertyValueFactory<CancelOrderItemRow, Double>("m_price"));	
			cancelOrder_table.setItems(cancelOrder);		
			LoadOrderDetails=false;
		}
		
		else if(refundMsgflag==true) //Initialized the order refund msg
		{
			this.txtOrderCancelRefund.setText(textRefundMsg);
			refundMsgflag=false;
		}
	}
	
	/**
	 * Start the process of search of possible orders to cancel- 
	 * show cancel order instructions and then after click -
	 * the next button show combobox of orders numbers if he has 
	 * or error msg if he doesn't has
	 * @param event- click on next button after the cancel order instruction
	 * @throws Exception if we can't load the fxml
	 */
	public void cancelOrderStart(ActionEvent event) throws Exception //this method start the process of search of possible orders to cancel
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		ArrayList <String> toCancel=new ArrayList<String>();
		toCancel.add(UserUI.user.getId());
		toCancel.add(String.valueOf(UserUI.store.getStoreId()));
		Message msg = new Message(toCancel , "Get all orders numbers for this customer can cancel");
		UserUI.myClient.accept(msg); // get all possible orders from DB
		while(getCancelOrdersFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		getCancelOrdersFlag=false;
		if(OrderUI.ordersNumbers.get(0)==-1) //we didn't have orders to cancel for this customer at DB
		{
			root = loader.load(getClass().getResource("/controller/OrderDontHaveMsg.fxml").openStream());
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();		
		}
		else 
		{
			LoadOrderCombo=true;
			root = loader.load(getClass().getResource("/controller/OrderForCustomer.fxml").openStream());
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Orders to cancel");
			primaryStage.show();	
		}
	}
	
	/**
	 * Take the selected order number from the combobox if return (-1) mean that 
	 * he didn't choose an order number 
	 * @return int- selected order number (index)
	 */
	public int getItemIndex() //With this Method we Take the selected order number
	{
		if(cmbOrdersForCustomer.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		return cmbOrdersForCustomer.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Open the details of the order if we choose one at the combobox
	 * else show error msg that we didn't choose one
	 * @param event- click on open button
	 * @throws Exception if we can't load the fxml
	 */
	public void openOrderDetails(ActionEvent event) throws Exception //the method open the details of the order
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		
		if(getItemIndex() == -1) //didn't choose order number from the combobox
		{ 
			root = loader.load(getClass().getResource("/controller/OrderToHandleComboboxMsg.fxml").openStream()); 
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else //choose order at the combobox
		{
			int orderN=OrderUI.ordersNumbers.get(getItemIndex()); //the requested order number
			Message msg = new Message(orderN , "Get order details");
			UserUI.myClient.accept(msg); // get order details from DB
			while(orderFlag==false)
			{
				System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			orderFlag=false;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			LoadOrderDetails=true; //show order details by initialized
			root = loader.load(getClass().getResource("/controller/OrderDetails.fxml").openStream());	
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Order Details");
			primaryStage.show();	
		}
	}
	
	/**
	 * To cancel this specific order by the 3 options of the time of canceling"
	 * 1- if we are at the last hour before the supply time
	 * 2- if we are before 3 hours before the supply time
	 * 3- if we are between 1-3 hours before the supply time
	 * update the order status and the refund he need to get also show msg to the customer 
	 * @param event-click on cancel button
	 * @throws Exception if we can't load the fxml
	 */
	public void cancelThisOrder(ActionEvent event) throws Exception  //To cancel this specific order
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		
		LocalDate today = LocalDate.now(); //get the current date
		LocalTime nowTime=LocalTime.parse(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())); //get the current time 
		
		if((OrderUI.order.getRequiredSupplyDate().equals(today)) && (LocalTime.parse(OrderUI.order.getRequiredSupplyTime()).minusHours(1).isBefore(nowTime))) //if we are at the last hour before the supply time
		{
			textRefundMsg="The order has been canceled successfully. \nbut you didn't get a refund!";
			OrderUI.order.setRefund(0);
		}
		else if(((OrderUI.order.getRequiredSupplyDate().equals(today))&&(LocalTime.parse(OrderUI.order.getRequiredSupplyTime()).minusHours(3).isAfter(nowTime)))||(OrderUI.order.getRequiredSupplyDate().isAfter(today))) //if we are before 3 hours before the supply time
		{
			textRefundMsg="The order has been canceled successfully. \nYou get "+OrderUI.order.getOrderTotalPrice()+" ILS credit \nto shop at Zer-li "+ UserUI.store.getStore_Address() +" branch";
			OrderUI.order.setRefund(OrderUI.order.getOrderTotalPrice());
		}
		else //if we are between 1-3 hours before the supply time
		{
			textRefundMsg="The order has been canceled successfully. \nYou get "+0.5*(OrderUI.order.getOrderTotalPrice())+" ILS credit \nto shop at Zer-li "+ UserUI.store.getStore_Address() +" branch";
			OrderUI.order.setRefund(0.5*(OrderUI.order.getOrderTotalPrice()));
		}
		
		refundMsgflag=true;
		
		OrderUI.order.setoStatus(Order.orderStatus.CANCEL); 
		
		Message msg = new Message(OrderUI.order , "update cancel order");
		UserUI.myClient.accept(msg); // get order details from DB
		Pane root = loader.load(getClass().getResource("/controller/UpdateOrderCancelMsg.fxml").openStream());
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);			
		primaryStage.setTitle("Cancel order");
		primaryStage.show(); //show customer options window
	}
	
	/**
	 * Close the The Window of the order cancel error msg
	 * @param event- click on close button
	 * @throws Exception if we can't hide the fxml that loaded
	 */
	public void closeOrderErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the order cancel error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}
	
	/**
	 * Close the The Window of the choosen option and open to customer menu
	 * @param event- click on close button
	 * @throws Exception if we can't load the fxml
	 */
	public void closeOrderOptionWindow(ActionEvent event) throws Exception  //To close the The Window of the choose option and return to menu options
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());	
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);			
		primaryStage.setTitle("Menu");
		primaryStage.show(); //show customer options window											
	}
}
	
