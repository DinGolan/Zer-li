package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import boundery.ComplaintUI;
import boundery.CustomerUI;
import boundery.OrderUI;
import boundery.ProductUI;
import boundery.UserUI;
import entity.Account;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderController implements Initializable{

	public static int flag=0;
	public static boolean accountFlag = false;
	public static boolean accountExistFlag = true;
	
	@FXML
	private Button btnRemove = null; /* button remove for remove product from cart */
	@FXML
	private Button btnBack = null; /* button back for return to catalog */
	@FXML
	private Button btnOrder = null; /* button order for continue to create order */
	@FXML
	private Button btnBackToCatalog = null; /* button order for continue to create order */
	@FXML
	private Button btnOrderNow = null; /* button order for continue to create order */
	@FXML
	private Button btnBackToCart = null; /* button order for continue to create order */
	@FXML
	private Button btnBackToOrder = null; /* button order for continue to create order */
	@FXML
	private Button btnCustomerOption = null; /* button order for continue to create order */
	@FXML
	private Button btnBackToOptions = null; /* button order for continue to create order */

	@FXML
	private RadioButton rdbtnAddPostCard = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnNoAddPostCard = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnDelivery = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnPickup = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnCash = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnCredirCard = null; /* button order for continue to create order */
	
	@FXML
	private ComboBox<String> cmbProducts = null; /* list of product in cart */
	
	@FXML
	private TextField txtPrice= null; /* text field for Total price of cart */
	@FXML
	private TextField txtTotalOrderPrice= null; /* text field for Total price of cart */
	@FXML
	private TextField txtAddress= null; /* text field for Total price of cart */
	@FXML
	private TextField txtRecipientsName= null; /* text field for Total price of cart */
	@FXML
	private TextField txtRecipientsPhoneNumber= null; /* text field for Total price of cart */
	@FXML
	private TextField txtRequiredTime= null; /* text field for Total price of cart */
	@FXML
	private TextArea txtPostCard= null; /* text field for Total price of cart */
	
	@FXML
	private Label lbltotalprice= null; /* text field for Total price of cart */
	@FXML
	private Label lblArrangement= null; /* text field for Total price of cart */
	@FXML
	private Label lblImidiateOrder= null; /* text field for Total price of cart */

	@FXML
	private DatePicker dpRequiresSupplyDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private ComboBox <Integer> cmbOrdersForCustomer=null; //combobox to view all the orders for the specific customer
	ObservableList<Integer> listForOrderCustomerComboBox;
	
	public static double totalPrice;
	
	ObservableList<String> listForComboBox;
	
	private static boolean imidiateOrderFlag = false;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		if((flag == 0) || (flag == 4))
			setComboBoxAndPrice();
		if(flag == 1)
			txtTotalOrderPrice.setText(String.valueOf(totalPrice));
		if(flag==3)
		{
			lbltotalprice.setText(String.valueOf(totalPrice) + " NS");
			lblArrangement.setText(String.valueOf(CustomerUI.account.getAccountPaymentArrangement()));
			if(imidiateOrderFlag == false)
				lblImidiateOrder.setText("Your order will be delivered at the requested time");
			else
			{
				lblImidiateOrder.setText("Your order will be delivered within 3 hours");
				imidiateOrderFlag = false;
			}
		}
	}
	
	public void setComboBoxAndPrice() // set comboBox of products
	{
		   ArrayList<String> productsNames = new ArrayList<String>();
		   double totalPrice= 0;
		   if(!CatalogController.order.getProductsInOrder().equals(null)) {
			for(Entry<Product, Integer> e : CatalogController.order.getProductsInOrder().entrySet())   /* We add to the ArrayList all the Faculty */
			{
				for(int i=0; i< e.getValue() ; i++)
				{
				productsNames.add(e.getKey().getpName());
				if(e.getKey().getpSalePrice()==0)
					totalPrice += e.getKey().getpPrice();
				else
					totalPrice += e.getKey().getpSalePrice();
				}
			}
		   }
			this.totalPrice = totalPrice;
			listForComboBox = FXCollections.observableArrayList(productsNames); 
			cmbProducts.setItems(FXCollections.observableArrayList(listForComboBox)); /* Set the Items Of Faculty at the ComboBox */
			txtPrice.setText(String.valueOf(totalPrice));
	}
	
	public void removeProduct(ActionEvent event) throws Exception // remove product from cart
	{
		String productToRemove = cmbProducts.getValue();

			if(productToRemove != null)
			{
				for(Product p : CatalogController.order.getProductsInOrder().keySet())   
				{
					if(p.getpName().compareTo(productToRemove) == 0) /*we found the product we want to remove*/
					{
						CatalogController.order.getProductsInOrder().put(p, (CatalogController.order.getProductsInOrder().get(p))-1);
						if(CatalogController.order.getProductsInOrder().get(p) == 0)
							CatalogController.order.getProductsInOrder().remove(p);
						break;
					}
				}
				setComboBoxAndPrice();
			}
	}
	
	public void closeCarttWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		Pane root;
		totalPrice =0;
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		if(flag!=4)
			root = loader.load(getClass().getResource("/controller/Catalog.fxml").openStream());
		else
		{
			SelfDefenitionProductController.modeFlag = 1;
			root = loader.load(getClass().getResource("/controller/SelfDefenitionProduct.fxml").openStream());
		}
		flag = 0;
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	
	public void continueToOrder(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		if(CatalogController.order.getProductsInOrder().size() > 0)
		{
			flag = 1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/OrderForm.fxml").openStream());
		
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
				
			primaryStage.show();									 /* show catalog frame window */
		}
		else {
			flag = 2;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/NoProductsInOrderMsg.fxml").openStream());
		
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
				
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	public void checkAndSaveOrderDetails(ActionEvent event) throws Exception  /*check all order fields and save in DB*/
	{
		LocalDate localDate = dpRequiresSupplyDate.getValue();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate today = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE); /*get now time */
        int hour = cal.get(Calendar.HOUR);
        if(cal.get(Calendar.AM_PM) != 0)
        	hour += 12;
		if((localDate != null) && (localDate.isBefore(today))) // required supply date passed
		{
			flag = 2;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/ErrDateMsg.fxml").openStream());
		
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
				
			primaryStage.show();
		}
		else {
			if((rdbtnDelivery.isSelected() == true) &&	((txtAddress.getText().compareTo("") == 0) || (txtRecipientsName.getText().compareTo("") == 0) ||  localDate == null || (txtRecipientsPhoneNumber.getText().compareTo("") == 0)))
			{
				flag = 2;
					((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
					Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
					FXMLLoader loader = new FXMLLoader(); 					 /* load object */
					Pane root = loader.load(getClass().getResource("/controller/ErrEmptyFieldMsg.fxml").openStream());
					
					Scene scene = new Scene(root);			
					primaryStage.setScene(scene);	
					
					primaryStage.show();
			}
			else if ((txtRequiredTime.getText().compareTo("") == 0) || localDate == null)
			{
				flag = 2;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/ErrEmptyFieldMsg.fxml").openStream());
				
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				
				primaryStage.show();
			}
			else /* requested supply date is today , check time is more than hour from now*/
			{
				String time = txtRequiredTime.getText();
				String hours = time.substring(0, 2);
				String minutes = time.substring(3, 5);
				if ((localDate.isEqual(today)) && ((Integer.valueOf(hours) < hour) || ((Integer.valueOf(hours) == (hour)) && (Integer.valueOf(minutes) <= minute))))
				{
					flag = 2;
					((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
					Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
					FXMLLoader loader = new FXMLLoader(); 					 /* load object */
					Pane root = loader.load(getClass().getResource("/controller/ErrDateMsg.fxml").openStream());
				
					Scene scene = new Scene(root);			
					primaryStage.setScene(scene);	
						
					primaryStage.show();
				}
				else {
				 try 
				 {
					LocalTime.parse(txtRequiredTime.getText()); /*if supply time is NOT valid throw exception*/
					flag=3;  /*for next order*/	
	
					Order.SupplyOption s; 
					if(rdbtnDelivery.isSelected() == true)
						s = Order.SupplyOption.DELIVERY;
					else 
						s = Order.SupplyOption.PICKUP;
					String userId = UserUI.user.getId();
					Message msg = new Message(userId, "Update customer account");
					UserUI.myClient.accept(msg);
					accountFlag = false;
					accountExistFlag = true;
					while(accountFlag == false) {
						System.out.print("");
					}
					accountFlag = false;
					if(accountExistFlag == false) // no account exist for this customer
					{
						accountExistFlag = true;
						flag=2;
						((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
						Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
						FXMLLoader loader = new FXMLLoader(); 					 /* load object */
						Pane root = loader.load(getClass().getResource("/controller/NoAccountMsg.fxml").openStream());
					
						Scene scene = new Scene(root);			
						primaryStage.setScene(scene);	
							
						primaryStage.show();
					}
					else
					{
						if(CustomerUI.account.getAccountPaymentArrangement().equals(Account.PaymentArrangement.ANNUAL))
							totalPrice *= 0.875;
						else if(CustomerUI.account.getAccountPaymentArrangement().equals(Account.PaymentArrangement.MONTHLY))
							totalPrice *= 0.9;
						Account.PaymentMethod pm = null;
						if(rdbtnCash.isSelected())
							pm = Account.PaymentMethod.CASH;
						else
							pm = Account.PaymentMethod.CREDITCARD;
						Order saveOrder = new Order(s, totalPrice, CatalogController.order.getProductsInOrder(), localDate, UserUI.user.getId(), txtRequiredTime.getText(), txtAddress.getText(), txtRecipientsName.getText(), txtRecipientsPhoneNumber.getText(), txtPostCard.getText() , UserUI.store.getStoreId(), pm);
						msg = new Message(saveOrder, "insert order to DB");
						UserUI.myClient.accept(msg);
						accountFlag = false;
						while(accountFlag == false) {
							System.out.print("");
						}
						accountFlag = false;
						OrderUI.order = null;
						if(localDate.equals(today) && (Integer.valueOf(hours) < (hour+3)) || ((Integer.valueOf(hours) == (hour+3)) && (Integer.valueOf(minutes) <= minute)))
						{
							imidiateOrderFlag= true;
						}
						flag=3;
						((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
						Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
						FXMLLoader loader = new FXMLLoader(); 					 /* load object */
						Pane root = loader.load(getClass().getResource("/controller/ThankForOrder.fxml").openStream());
						totalPrice=0;  /*for next order*/
						Scene scene = new Scene(root);			
						primaryStage.setScene(scene);	
								
						primaryStage.show();
					}
				 }
				 catch (DateTimeParseException e) /*if supply time is NOT valid*/
				 {
						flag = 2;
						((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
						Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
						FXMLLoader loader = new FXMLLoader(); 					 /* load object */
						Pane root = loader.load(getClass().getResource("/controller/ErrTimmeMsg.fxml").openStream());
					
						Scene scene = new Scene(root);			
						primaryStage.setScene(scene);	
							
						primaryStage.show();
				 }
			}
		}
		}
	}
	
	public void AddPostCard(ActionEvent event) throws Exception  /*  */
	{
		rdbtnNoAddPostCard.setSelected(false);
		txtPostCard.setEditable(true);
	}
	
	public void NotAddPostCard(ActionEvent event) throws Exception  /*  */
	{
		rdbtnAddPostCard.setSelected(false);
		txtPostCard.setEditable(false);
	}
	
	public void payCash(ActionEvent event) throws Exception  /*  */
	{
		rdbtnCredirCard.setSelected(false);
	}
	
	public void PayByCreditcard(ActionEvent event) throws Exception  /*  */
	{
		rdbtnCash.setSelected(false);
	}
	
	public void supplyByPickup(ActionEvent event) throws Exception  /*  */
	{
		rdbtnDelivery.setSelected(false);
		txtAddress.setText("");
		txtRecipientsName.setText("");
		txtRecipientsPhoneNumber.setText("");
		txtAddress.setEditable(false);
		txtRecipientsName.setEditable(false);
		txtRecipientsPhoneNumber.setEditable(false);
		txtTotalOrderPrice.setText(String.valueOf(totalPrice));
	}
	
	public void supplyByDelivery(ActionEvent event) throws Exception  /*  */
	{
		rdbtnPickup.setSelected(false);
		txtAddress.setEditable(true);
		txtRecipientsName.setEditable(true);
		txtRecipientsPhoneNumber.setEditable(true);
		double price = totalPrice + Order.getDeliveryPrice();
		txtTotalOrderPrice.setText(String.valueOf(price));
	}
	
	public void backToOrder(ActionEvent event) throws Exception  /*  */
	{
		flag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/OrderForm.fxml").openStream());
	
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
			
		primaryStage.show();									 /* show catalog frame window */
	}
	
	public void backToCustomerOption(ActionEvent event) throws Exception
	{
		flag=0;
		CatalogController.order= new Order();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
	
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
}
	
