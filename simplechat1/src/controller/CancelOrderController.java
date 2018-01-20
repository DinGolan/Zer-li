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
import entity.Message;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CancelOrderController implements Initializable{

	public static int flag=0;
	public static boolean LoadOrderCombo=false;
	public static boolean LoadOrderDetails=false;
	public static boolean orderFlag=false;
	public static boolean getCancelOrdersFlag=false;
	public static boolean refundMsgflag=false;
	
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
	
	private String textRefundMsg=null; //string for the refund msg
	
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
		// חסר נתוניםםם להתייעץ עם אריאלל
		else if (LoadOrderDetails==true) //Initialized The order details
		{
			this.txtOrderNum.setText(String.valueOf(OrderUI.order.getOrderID())); //text field for the order number
			this.txtOrderDate.setText(String.valueOf(OrderUI.order.getOrderDate())); //text field for the order date
			this.txtOrderReqDate.setText(String.valueOf(OrderUI.order.getRequiredSupplyDate())); //text field for the order required date
			this.txtOrderReqTime.setText(String.valueOf(OrderUI.order.getRequiredSupplyTime())); //text field for the order required time
			this.txtOrderTotalPrice.setText(String.valueOf(OrderUI.order.getOrderTotalPrice())); //text field for the order total price
			LoadOrderDetails=false;
		}
		//notttttttttttttttttttttt working!!!!!!!!!
		else if(refundMsgflag==true) //Initialized the order refund msg
		{
			System.out.println(textRefundMsg);
			this.txtOrderCancelRefund.setText(textRefundMsg);
			refundMsgflag=false;
		}
	}
	
	public void cancelOrderStart(ActionEvent event) throws Exception //this method start the process of search of possible orders to cancel
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		ArrayList <String> toCancel=new ArrayList<String>();
		toCancel.add(UserUI.user.getId());
		toCancel.add(String.valueOf(UserUI.store.getStoreId()));
		System.out.println(toCancel);
		//ArrayList<Integer> ordersNum = new ArrayList<Integer>();
		Message msg = new Message(toCancel , "Get all orders numbers for this customer can cancel");
		UserUI.myClient.accept(msg); // get all possible orders from DB
		while(getCancelOrdersFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		getCancelOrdersFlag=false;
		System.out.println(OrderUI.ordersNumbers);
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
	
	public int getItemIndex() //With this Method we Take the selected order number
	{
		if(cmbOrdersForCustomer.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		return cmbOrdersForCustomer.getSelectionModel().getSelectedIndex();
	}
	
	public void openOrderDetails(ActionEvent event) throws Exception //the method open the details of the order
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		
		if(getItemIndex() == -1) //didn't choose order number from the combobox
		{ 
			//((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
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
	
	//עוד לא בניתי את הפונקציהההההה
	public void cancelThisOrder(ActionEvent event) throws Exception  //To cancel this specific order
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		
		LocalDate today = LocalDate.now(); //get the current date
		LocalTime nowTime=LocalTime.parse(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())); //get the current time 
		
		if((OrderUI.order.getRequiredSupplyDate().equals(today)) && (LocalTime.parse(OrderUI.order.getRequiredSupplyTime()).minusHours(1).isBefore(nowTime))) //if we are at the last hour before the supply time
		{
			textRefundMsg="The order has been canceled successfully. but you didn't get a refund!";
			//OrderUI.order.set-->> to do set refund לעשות עדכון החזר
			//OrderUI.order.set-->> to do set status
		}
		else if(((OrderUI.order.getRequiredSupplyDate().equals(today))&&(LocalTime.parse(OrderUI.order.getRequiredSupplyTime()).minusHours(3).isAfter(nowTime)))||(OrderUI.order.getRequiredSupplyDate().isAfter(today))) //if we are before 3 hours before the supply time
		{
			textRefundMsg="The order has been canceled successfully. You get "+OrderUI.order.getOrderTotalPrice()+" ILS credit to shop at Zer-li "+ UserUI.store.getStore_Address() +" branch";
			//OrderUI.order.set-->> to do set refund לעשות עדכון החזר
			//OrderUI.order.set-->> to do set status
		}
		else //if we are between 1-3 hours before the supply time
		{
			textRefundMsg="The order has been canceled successfully. You get "+0.5*(OrderUI.order.getOrderTotalPrice())+" ILS credit to shop at Zer-li "+ UserUI.store.getStore_Address() +" branch";
			//OrderUI.order.set-->> to do set refund לעשות עדכון החזר
			//OrderUI.order.set-->> to do set status
		}
		
		refundMsgflag=true;
		
		//hereeeeeeeee to do update to the order status and the refund by quary
		System.out.println(textRefundMsg);
		
		Pane root = loader.load(getClass().getResource("/controller/UpdateOrderCancelMsg.fxml").openStream());
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);			
		primaryStage.setTitle("Cancel order");
		primaryStage.show(); //show customer options window
	}
		
	public void closeOrderErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the order cancel error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}
	
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
	
