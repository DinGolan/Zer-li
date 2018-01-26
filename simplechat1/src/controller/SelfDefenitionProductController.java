package controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.OrderUI;
import boundery.UserUI;
import entity.CatalogItemRow;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the self definition product options - show products by
 * the categories the customer choose,
 * add product to cart, open cart window.
 *
 */
public class SelfDefenitionProductController implements Initializable{
	
	private Message msg;
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnShowOptions = null; /* button update for update product */
	
	@FXML
	private Button btnBack = null; /* button update for update product */
	
	@FXML
	private Button btnBackToSelfDef = null; /* button update for update product */
	
	@FXML
	private Button btnTryAgainOrder = null; /* button update for update product */
	
	@FXML
	private Button btnBackToSelfOptions = null; /* button update for update product */
	
	@FXML
	private Button btnBackToSelf = null; /* button update for update product */
	
	@FXML
	private Button btnMyCart = null; /* button update for update product */
	
	@FXML
	private TextField txtPPrice = null; /* button update for update product */
	
	@FXML
	private TextArea txtPInfo = null; /* button update for update product */
	
	@FXML
	private ComboBox<String> cmbPid = new ComboBox<>(); /* button close for close product form */
	
	@FXML
	private ComboBox<String> cmbProductType = new ComboBox<>(); /* button close for close product form */
	
	@FXML
	private ComboBox<String> cmbPriceRange = new ComboBox<>(); /* button close for close product form */
	
	@FXML
	private ComboBox<String> cmbDominantColor = new ComboBox<>(); /* button close for close product form */
	
	@FXML
	private Hyperlink LinkCart;
	@FXML
	private Hyperlink LinkLogout;
	
	@FXML private TableView<CatalogItemRow> catalog_table = new TableView<>();

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_id = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_name = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, Product.ProductType> tablecolumn_type = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, Double> tablecolumn_price = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, ImageView> tablecolumn_image = new TableColumn<>();
	
	@FXML
	private Button btnAddToCard = null;	
	@FXML
	private TextField txtPAmmount; /* text field for the product Name */

	ObservableList<CatalogItemRow> catalog = FXCollections.observableArrayList();
	
	ObservableList<String> listForComboBox;
	
	ObservableList<String> productsId = FXCollections.observableArrayList();
	
	static double price = 0;
	
	static int modeFlag =1;
	static String pType=null;
	static String pRange=null;
	static String pColor=null;
	static int minPrice=0;
	static int maxPrice=0;
	
	/**
	 * call function that set 3 comboBox of the customer options
	 * in case the customer in self definition window,
	 * call function that set the table view with products
	 * suit to the customer chosen options in case the customer
	 * want to see his options.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(modeFlag == 1)
			setComboBox();
		if(modeFlag == 0)
			setOptions();
	}

	/**
	 * set 3 comboBox of the customer options:
	 * comboBox of Product type, comboBox of domain color,
	 * and comboBox of price range
	 */
	public void setComboBox()
	{
		cmbProductType.setItems(FXCollections.observableArrayList("BOUQUET", "ARRANGEMENT", "FLOWER_CROWN","BRIDAL_BOUQUET","VASE" , "WREATH_FLOWERS" , "SWEET_BOUQUET"));
		cmbDominantColor.setItems(FXCollections.observableArrayList("RED", "WHITE", "YELLOW","ORANGE", "PINK"));
		cmbPriceRange.setItems(FXCollections.observableArrayList("0-50", "50-100", "100-150","200-250" ,"250-300" , "300-350", "350-400", "400-450", "450-500"));
		cmbProductType.setValue("BOUQUET");
		cmbDominantColor.setValue("RED");
		cmbPriceRange.setValue("0-50");
	}
	
	/**
	 * set the table view with products suit to the customer chosen options
	 * we take all the products from the Data Base, also the products
	 * in sale of the customer store, and update the table view
	 */
	public void setOptions()
	{
		if(CatalogController.order == null)
			CatalogController.order= new Order();
		CatalogUI.products.clear();
		CatalogUI.productsInSale.clear();
		ArrayList<String> s = new ArrayList<String>();
		msg = new Message(s, "get all products in DB");
		int j ,k;
		Product p;
		boolean flagSale = false;
		InputStream targetStream;
		UserUI.myClient.accept(msg);
		while(CatalogUI.products.size()==0);
		msg.setOption("get all products in sale from DB");
		msg.setMsg(UserUI.store);
		UserUI.myClient.accept(msg);
		while(CatalogUI.productsInSale.size()==0);
		for(j=0 ; j<CatalogUI.products.size() ; j++)
		{
			flagSale = false;
			p = CatalogUI.products.get(j);
			for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
			{
				if(p.getpID() == CatalogUI.productsInSale.get(k).getpID())
				{
					p.setpPrice(CatalogUI.productsInSale.get(k).getpPrice());
					flagSale = true;
					break;
				}
			}
				if((p.getpPrice()>=minPrice) && (p.getpPrice()<=maxPrice) && (String.valueOf(p.getpColor()).compareTo(pColor)==0) && (String.valueOf(p.getpType()).compareTo(pType)==0))
				{
					productsId.add(String.valueOf(p.getpID()));
					targetStream= new ByteArrayInputStream(p.getByteArray());
					CatalogItemRow itemRow = new CatalogItemRow(p.getpID(), p.getpName(),p.getpType().toString(), p.getpPrice(),  p.getpColor().toString(), targetStream );
			catalog.add(itemRow);
				}
		}
		tablecolumn_id.setCellValueFactory(new PropertyValueFactory<CatalogItemRow, String>("id"));
		tablecolumn_name.setCellValueFactory(new PropertyValueFactory<CatalogItemRow, String>("name"));
		tablecolumn_type.setCellValueFactory(new PropertyValueFactory<CatalogItemRow, Product.ProductType>("type"));
		tablecolumn_price.setCellValueFactory(new PropertyValueFactory<CatalogItemRow, Double>("price"));
		tablecolumn_image.setCellValueFactory(new PropertyValueFactory<CatalogItemRow, ImageView>("image"));
		catalog_table.setItems(catalog);
		cmbPid.setItems(productsId);
	}
	
	/**
	 * when the customer fill the categories he wants and
	 * click "show options" button we save his options
	 * and display the products that suit  
	 * @param event - customer click "show options" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void showoptions(ActionEvent event) throws Exception
	{
		modeFlag=0;
		int tempPrice , i=0;
		boolean flag = true;
		pType = new String(cmbProductType.getValue());
		pRange = new String(cmbPriceRange.getValue());
		pColor = new String(cmbDominantColor.getValue());
		minPrice = Integer.valueOf(pRange.substring(0, pRange.indexOf("-")));
		maxPrice = Integer.valueOf(pRange.substring(pRange.indexOf("-")+1, pRange.length()));
		
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/SelfDefOrder.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Self Defenition Order");
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
	
	/**
	 * back to the self definition options in case the
	 * customer click "back" button from cart
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToSelfOption(ActionEvent event) throws Exception
	{
		modeFlag=0;
		int tempPrice , i=0;
		boolean flag = true;
		minPrice = Integer.valueOf(pRange.substring(0, pRange.indexOf("-")));
		maxPrice = Integer.valueOf(pRange.substring(pRange.indexOf("-")+1, pRange.length()));
		
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/SelfDefOrder.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Self Defenition Order");
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
	
	/**
	 * back to the customer options in case the
	 * customer click "back" button
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToCustomerOption(ActionEvent event) throws Exception
	{
		CatalogController.order= new Order();
		OrderUI.order = null;
		modeFlag=1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
	
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Menu");
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
	
	/**
	 * back to self definition categories in case the
	 * customer click "back" button from products option
	 * window
	 * @param event - customer click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToSelfDefOrder(ActionEvent event) throws Exception
	{
		modeFlag =1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/SelfDefenitionProduct.fxml").openStream());
	
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Self Defenition Product Options");
		primaryStage.setScene(scene);	
			
		primaryStage.show();
	}
	
	
	/**
	 * addToCart - the customer choose product Id and amount he want to 
	 * add his cart, after we check the values are valid it "add" to 
	 * his cart. we save the current cart in HashMap - if
	 * its new product we add it, else we increase the amount in cart.
	 * @param event - the customer clicked "add To Cart" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void addToCart(ActionEvent event) throws Exception // add product to cart
	{
		String pId = cmbPid.getValue();
		int pAmmount;
		try
		{
			if((txtPAmmount.getText().compareTo("")!=0) && (Integer.valueOf(txtPAmmount.getText())) > 0 && pId != null && (Integer.valueOf(txtPAmmount.getText())) < 1000)
			{
				pAmmount = Integer.valueOf(txtPAmmount.getText());
					Product p = getproductById(pId);
					if(p != null)
					{
						if(CatalogController.order.getProductsInOrder().containsKey(p))
							CatalogController.order.getProductsInOrder().put(p, (CatalogController.order.getProductsInOrder().get(p))+pAmmount);
						else
							CatalogController.order.getProductsInOrder().put(p, pAmmount);
						txtPAmmount.setText("");
						cmbPid.setValue(null);
						showAddSuccessfulMsg(event);
					}
			}
			else
				showErrMsg(event);
		}
		catch (NumberFormatException e)
		{
			showErrMsg(event);
		}
	}
	
	/**
	 * If the customer did NOT insert valid amount or did NOT choose
	 * any product from the combo-Box we show Error message window.  
	 * @param event - the customer clicked "add To Cart" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void showErrMsg(ActionEvent event) throws Exception // add product to cart
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ErrMsgInSelfDef.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Error Msg");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/**
	 * If the customer insert valid amount and choose
	 *  product from the combo-Box we show successful message window.  
	 * @param event - the customer clicked "add To Cart" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void showAddSuccessfulMsg(ActionEvent event) throws Exception // add product to cart
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SuccessfulAddMsgInSelfDef.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Successful Message");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

	/**
	 * "getproductById" - search, find and return the product from combo-Box
	 * that the customer choose to add his cart
	 * @param pId - gets the product ID to find it in the products list
	 * @return - return the Product that the customer want to add
	 */
	private Product getproductById(String pId)
	{
		int i;
		for (i=0; i<CatalogUI.products.size() ; i++)
		{
			if(CatalogUI.products.get(i).getpID() == Integer.valueOf(pId))
				return CatalogUI.products.get(i);
		}
		return null;
	}
	
	/**
	 * logout - customer logout the system and "UserLogin" window open.
	 * @param event - the customer clicked "logout" Hyperlink 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void logout(ActionEvent event) throws Exception /* logout and open login window */
	{
		modeFlag = 1;
		CustomerController.flag = false;
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * "show - Cart" the cart window open:
	 *  all the products that the customer choose to buy are displayed in cart window
 	 * @param event  - the customer clicked "cart" Hyperlink
	 * @throws Exception - if we can't load the fxml file
	 */
	public void showCart(ActionEvent event) throws Exception // show all products in cart. 
	{
		ArrayList<Object> user = new ArrayList<>();
		user.add(0, UserUI.user.getId());
		user.add(1, UserUI.store.getStoreId());
		Message msg = new Message(user, "Update customer account");
		UserUI.myClient.accept(msg);
		OrderController.accountFlag = false;
		OrderController.accountExistFlag = true;
		while(OrderController.accountFlag == false) {
			System.out.print("");
		}
		OrderController.accountFlag = false;
		if(OrderController.accountExistFlag == false) // no account exist for this customer
		{
			OrderController.accountExistFlag = true;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/NoAccountMsgInSelfDef.fxml").openStream());
		
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Message");
			primaryStage.setScene(scene);	
				
			primaryStage.show();
		}
		else
		{
		modeFlag = 1;
		OrderController.flag=4;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CartFrame.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Cart");
		primaryStage.setScene(scene);		
		primaryStage.show();
		}
	}

}
