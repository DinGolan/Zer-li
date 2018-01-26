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


/**
 * controller for the Order options - remove products from customer
 * cart, show cart, fill and create order form and check all fields
 *
 */
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
	private Button btnTryAgainOrder = null; /* button order for continue to create order */
	

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
	private RadioButton rdbtnDontUseAccountBalance = null; /* button order for continue to create order */
	@FXML
	private RadioButton rdbtnUseAccountBalance = null; /* button order for continue to create order */
	
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
	private Label lblBalance= null; /* text field for Total price of cart */
	
	@FXML
	private DatePicker dpRequiresSupplyDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private ComboBox <Integer> cmbOrdersForCustomer=null; //combobox to view all the orders for the specific customer
	ObservableList<Integer> listForOrderCustomerComboBox;
	
	public static double totalPrice = 0;
	
	public static boolean deliveryFlag = false;
	
	public double balanceAmount = 0;
	
	ObservableList<String> listForComboBox;
	
	private static boolean imidiateOrderFlag = false;
	
	private static boolean useAccountBakanceFlag = false;
	
	public static double balance = 0;
	
	/**
	 * call function that set comboBox of the products in the customer
	 * cart, set the price. set textfield of ending order. 
	 */
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
			if(useAccountBakanceFlag == true)
				lblBalance.setText( String.valueOf(balance) + " NS");
			else
				lblBalance.setText("0 NS");
		}
	}
	
	/**
	 * set in comboBox the products the customer add to his cart
	 * (by taking the hashMap and put every single product in it)
	 * update the price in textfield.
	 */
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
	
	/**
	 * the customer have an option to regret and remove products
	 * from his cart.
	 * @param event - customer click "remove" button
	 * @throws Exception - if we can't load the fxml file
	 */
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
	
	/**
	 * go back from watching customer cart to one of the two options:
	 * catalog or self definition options, it depend where he "came" from
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file 
	 */
	public void closeCarttWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		Pane root;
		totalPrice =0;
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		if(flag!=4)
		{
			root = loader.load(getClass().getResource("/controller/Catalog.fxml").openStream());
			primaryStage.setTitle("Catalog");	
		}
		else
		{
			SelfDefenitionProductController.modeFlag = 1;
			root = loader.load(getClass().getResource("/controller/SelfDefenitionProduct.fxml").openStream());
			primaryStage.setTitle("Self Defenition Product");	
		}
		flag = 0;
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	
	/**
	 * after the customer satisfied with his products in cart
	 * we open the order form for him to fill
	 * @param event - customer click "continue to order" button
	 * @throws Exception - if we can't load the fxml file
	 */
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
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Order Form");	
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
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Message");	
			primaryStage.setScene(scene);	
				
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	/**
	 * after customer fill the order form we check every single
	 * field and buttons. if all the values the customer insert
	 * are valid: we save in the data base the order, if the customer
	 * chose to use his balance we update his balance, we save
	 * the payment method and all the other details of the order. 
	 * @param event - customer click "order now" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void checkAndSaveOrderDetails(ActionEvent event) throws Exception  /*check all order fields and save in DB*/
	{
		String phoneNumber = txtRecipientsPhoneNumber.getText();
		LocalDate localDate = dpRequiresSupplyDate.getValue();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate today = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE); /*get now time */
        int hour = cal.get(Calendar.HOUR);
        if(cal.get(Calendar.AM_PM) != 0)
        	hour += 12;
        boolean inValidPhone = true;
        if(phoneNumber != null)
        {
	        for(int i = 0; i<phoneNumber.length() ; i++)
	        {
	        	if(phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9')
	        		inValidPhone = false;
	        }
        }
		if((localDate != null) && (localDate.isBefore(today))) // required supply date passed
		{
			flag = 2;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/ErrDateMsg.fxml").openStream());
		
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Message");
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
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setTitle("Error Message");
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
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setTitle("Error Message");
				primaryStage.setScene(scene);	
				
				primaryStage.show();
			}
			else if (inValidPhone == false ||(txtPostCard.getText().length() > 500) || (txtAddress.getText().length() > 45) || (txtRecipientsName.getText().length() > 45) || (txtRecipientsPhoneNumber.getText().length() > 10) || (txtRequiredTime.getText().length() != 5))
			{
				flag = 2;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/ErrInValidFieldMsg.fxml").openStream());
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setTitle("Error Message");
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
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setTitle("Error Message");
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
					if(CustomerUI.account.getAccountPaymentArrangement().equals(Account.PaymentArrangement.ANNUAL))
						totalPrice *= 0.875;
					else if(CustomerUI.account.getAccountPaymentArrangement().equals(Account.PaymentArrangement.MONTHLY))
						totalPrice *= 0.9;
					Account.PaymentMethod pm = null;
					if(rdbtnCash.isSelected())
						pm = Account.PaymentMethod.CASH;
					else
						pm = Account.PaymentMethod.CREDITCARD;
					Order saveOrder = new Order(s, totalPrice, CatalogController.order.getProductsInOrder(), localDate, UserUI.user.getId(), txtRequiredTime.getText(), txtAddress.getText(), txtRecipientsName.getText(), txtRecipientsPhoneNumber.getText(), txtPostCard.getText() , UserUI.store.getStoreId(), pm,Order.orderStatus.APPROVED,0);
					Message msg = new Message(saveOrder, "insert order to DB");
					UserUI.myClient.accept(msg);
					accountFlag = false;
					while(accountFlag == false) {
						System.out.print("");
					}
					accountFlag = false;
					if(rdbtnUseAccountBalance.isSelected())
					{
						useAccountBakanceFlag = true;
						balance = CustomerUI.account.getAccountBalanceCard();
						if(CustomerUI.account.getAccountBalanceCard() > totalPrice)
						{
							balance = totalPrice;
							balanceAmount = CustomerUI.account.getAccountBalanceCard()- totalPrice;
							totalPrice = 0;
						}
						else
						{
							balance = CustomerUI.account.getAccountBalanceCard();
							balanceAmount = 0;
							totalPrice -= CustomerUI.account.getAccountBalanceCard();
						}
						ArrayList<Object> useAndBalance = new ArrayList<>();
						useAndBalance.add(0, UserUI.user.getId());
						useAndBalance.add(1, balanceAmount);
						useAndBalance.add(2, UserUI.store.getStoreId());
						msg = new Message(useAndBalance, "update balance Ammount");
						UserUI.myClient.accept(msg);
					}
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
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setTitle("Thank For Order");
					primaryStage.setScene(scene);	
								
					primaryStage.show();
				}
				 catch (DateTimeParseException e) /*if supply time is NOT valid*/
				 {
						flag = 2;
						((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
						Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
						FXMLLoader loader = new FXMLLoader(); 					 /* load object */
						Pane root = loader.load(getClass().getResource("/controller/ErrTimmeMsg.fxml").openStream());
					
						Scene scene = new Scene(root);		
						scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
						primaryStage.setTitle("Error Message");
						primaryStage.setScene(scene);	
							
						primaryStage.show();
				 }
				}
			}
		}
	}
	
	
	 /**
	  * if the customer want to add postcard we turn the post card
	  * field to editable and the "Not add post card" radio button 
	  * to not selected.
	  * @param event - customer click "yes" radio button (yes for - 
	  * add post card)
	  */
	public void AddPostCard(ActionEvent event)  /*  */
	{
		rdbtnNoAddPostCard.setSelected(false);
		txtPostCard.setEditable(true);
	}
	
	
	 /**
	  * if the customer don't want to add postcard we turn the post card
	  * field to Not editable and the "add post card" radio button 
	  * to not selected. we empty the post card text Area in this case.
	  * @param event - customer click "No" radio button (No for - 
	  * Not adding post card)
	  */
	public void NotAddPostCard(ActionEvent event)  /*  */
	{
		txtPostCard.setText("");
		rdbtnAddPostCard.setSelected(false);
		txtPostCard.setEditable(false);
	}
	
	 /**
	  * if the customer want to pay in Cash we turn the 
	  * "PayByCreditcard" radio button to not selected.
	  * @param event - customer click "Cash" radio button
	  */
	public void payCash(ActionEvent event)  /*  */
	{
		rdbtnCredirCard.setSelected(false);
	}
	
	 /**
	  * if the customer want to pay By Credit card we turn the 
	  * "payCash" radio button to not selected.
	  * @param event - customer click "Creditcard" radio button
	  */
	public void PayByCreditcard(ActionEvent event)  /*  */
	{
		rdbtnCash.setSelected(false);
	}
	
	 /**
	  * if the customer want to use his account balance
	  * we turn the "dontUseAccountBalace" radio button to not selected.
	  * @param event - customer click "Yes" radio button (yes for - 
	  * use Account Balance)
	  */
	public void useAccountBalace(ActionEvent event)  /*  */
	{
		rdbtnDontUseAccountBalance.setSelected(false);
	}
	
	 /**
	  * if the customer dont want to use his account balance
	  * we turn the "useAccountBalace" radio button to not selected.
	  * @param event - customer click "No" radio button (No for - 
	  * not using Account Balance)
	  */
	public void dontUseAccountBalace(ActionEvent event) /*  */
	{
		rdbtnUseAccountBalance.setSelected(false);
	}
	
	 /**
	  * if the customer want to pickup his order
	  * we turn the "Delivery" radio button to not selected.
	  * we empty the textfields of Delivery and turn them to Not Editable.
	  * @param event - customer click "Pickup" radio button 
	  */
	public void supplyByPickup(ActionEvent event) throws Exception  /*  */
	{
		deliveryFlag = false;
		rdbtnDelivery.setSelected(false);
		txtAddress.setText("");
		txtRecipientsName.setText("");
		txtRecipientsPhoneNumber.setText("");
		txtAddress.setEditable(false);
		txtRecipientsName.setEditable(false);
		txtRecipientsPhoneNumber.setEditable(false);
		totalPrice -= Order.getDeliveryPrice();
		txtTotalOrderPrice.setText(String.valueOf(totalPrice));
	}
	
	 /**
	  * if the customer want supply By Delivery
	  * we turn the "Pickup" radio button to not selected.
	  * we empty turn the textfields of Delivery to Editable.
	  * @param event - customer click "Delivery" radio button 
	  */
	public void supplyByDelivery(ActionEvent event) throws Exception  /*  */
	{
		deliveryFlag = true;
		rdbtnPickup.setSelected(false);
		txtAddress.setEditable(true);
		txtRecipientsName.setEditable(true);
		txtRecipientsPhoneNumber.setEditable(true);
		totalPrice = totalPrice + Order.getDeliveryPrice();
		txtTotalOrderPrice.setText(String.valueOf(totalPrice));
	}
	
	/**
	 * return to "order form" from error message
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToOrder(ActionEvent event) throws Exception  /*  */
	{
		if(deliveryFlag == true)
			totalPrice = totalPrice - Order.getDeliveryPrice();
		deliveryFlag = false;
		flag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/OrderForm.fxml").openStream());
	
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Order Form");
		primaryStage.setScene(scene);	
			
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	/**
	 * return to "Customer Options" window
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToCustomerOption(ActionEvent event) throws Exception
	{
		flag=0;
		CatalogController.order= new Order();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
	
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Customer Options");
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
}
	
