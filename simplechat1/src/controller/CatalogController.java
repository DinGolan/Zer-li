package controller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.UserUI;
import entity.CatalogItemRow;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Product.ProductType;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the Catalog options - show catalog products by type,
 * add product to cart, open cart window.
 *
 */
public class CatalogController implements Initializable {
	private Message msg;
	
	public static Order order;
	//public static boolean accountFlag = false;
	public static int waitFlag=0;
	//public static boolean accountExistFlag = true;
	public static boolean basicFlowersFlag=false;
	@FXML
	private Button btnBack = null;	
	@FXML
	private Button btnTryAgain = null;	
	@FXML
	private Button btnAddToCard = null;	
	@FXML
	private Button btnBackToCatalog = null;	
	@FXML
	private Button btnBackToCatalog2 = null;	
	@FXML
	private Button btnMyCart = null;	
	
	@FXML
	private Hyperlink BouquetsLink;	
	@FXML
	private Hyperlink ArrangementsLink;	
	@FXML
	private Hyperlink SweetBouquetLink;	
	@FXML
	private Hyperlink FlowerCrownLink;	
	@FXML
	private Hyperlink BridalLink;	
	@FXML
	private Hyperlink WreathFlowerLink;	
	@FXML
	private Hyperlink VaseLink;	
	@FXML
	private Hyperlink SalesLink;
	@FXML
	private Hyperlink LinkCart;
	@FXML
	private Hyperlink LinkLogout;

	@FXML
	private ComboBox<String> cmbPid = new ComboBox<>(); 
	
	@FXML
	private TextField txtPAmmount; /* text field for the product amount */
	
	@FXML private TableView<CatalogItemRow> catalog_table = new TableView<>();

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_id = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_name = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, Product.ProductType> tablecolumn_type = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, Double> tablecolumn_price = new TableColumn<>();

	@FXML private TableColumn<CatalogItemRow, ImageView> tablecolumn_image = new TableColumn<>();

	ObservableList<CatalogItemRow> catalog = FXCollections.observableArrayList();
	ObservableList<String> productsId = FXCollections.observableArrayList();
		
	static String flag = null;
	
	ObservableList<String> plist;
/**
 * back to Customer Options from catalog window.
 * @param event - when customer press "Back" button
 * @throws Exception - if we can't load the fxml file
 */
	public void back(ActionEvent event) throws Exception 
	{
		order = null;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
				
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Customer Options");
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}

	/**
	 * initialize the table view of the catalog, the first product type that appear is "BOUQUET"
	 * take all the product from Data Base and check the product type
	 * if the product type is "BOUQUET" and the product is not on "SALE"
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		if(order == null)
			order= new Order();
		flag = "BOUQUET";
		CatalogUI.products.clear();
		CatalogUI.productsInSale.clear();
		ArrayList<String> s = new ArrayList<String>();
		msg = new Message(s, "get all products in DB");
		int j ,k;
		Product p;
		boolean flagSale = false;
		InputStream targetStream;
		UserUI.myClient.accept(msg);
		waitFlag=0;
		while(waitFlag==0) {
		System.out.print("");
		}
		msg.setOption("get all products in sale from DB");
		msg.setMsg(UserUI.store);
		UserUI.myClient.accept(msg);
		waitFlag=0;
		while(waitFlag==0){
			System.out.print("");
			}
		waitFlag=0;
		for(j=0 ; j<CatalogUI.products.size() ; j++)
		{
			flagSale = false;
			p = CatalogUI.products.get(j);
			productsId.add(String.valueOf(p.getpID()));
			for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
			{
				if(p.getpID() == CatalogUI.productsInSale.get(k).getpID())
				{
					p.setpPrice(CatalogUI.productsInSale.get(k).getpPrice());
					flagSale = true;
					break;
				}
			}
			if(flagSale == false) 
			{
				if(p.getpType().equals(ProductType.valueOf("BOUQUET")))
				{
					targetStream= new ByteArrayInputStream(p.getByteArray());
					CatalogItemRow itemRow = new CatalogItemRow(p.getpID(), p.getpName(),p.getpType().toString(), p.getpPrice(),  p.getpColor().toString(), targetStream );
			catalog.add(itemRow);
				}
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
	 * productCategory - update the table view of the catalog to products that their type
	 * is suit the type the customer choose. 
	 * take all the product from Data Base and check the product type.
	 * if the product type is the type the customer choose and the product is not on "SALE"
	 * we add it to the table view.
	 * @param event - the customer clicked product type he want to see in catalog
	 * @throws Exception - if we can't load the fxml file
	 */
	public void productCategory(ActionEvent event) throws Exception //create and the catalog by categories
	{
		productsId.clear();
		catalog.clear();
		CatalogUI.products.clear();
		CatalogUI.productsInSale.clear();
		ArrayList<String> s = new ArrayList<String>();
		msg = new Message(s, "get all products in DB");
		int j, k;
		Product p;
		boolean flagSale = false;
		String type = null;
		InputStream targetStream;
		switch(((Node)event.getSource()).getId()) { //category that pressed
		case "BouquetsLink":
			type = "BOUQUET";
			flag = "BOUQUET";
			break;
		case "ArrangementsLink":
			type = "ARRANGEMENT";
			flag = "ARRANGEMENT";
			break;
		case "SweetBouquetLink":
			type = "SWEET_BOUQUET";
			flag = "SWEET_BOUQUET";
			break;
		case "FlowerCrownLink":
			type = "FLOWER_CROWN";
			flag = "FLOWER_CROWN";
			break;
		case "BridalLink":
			type = "BRIDAL_BOUQUET";
			flag = "BRIDAL_BOUQUET";
			break;
		case "WreathFlowerLink":
			type = "WREATH_FLOWERS";
			flag = "WREATH_FLOWERS";
			break;
		case "VaseLink":
			type = "VASE";
			flag = "VASE";
			break;
		case "SalesLink":
			type = "SALE";
			flag = "SALE";
			break;
		}
		UserUI.myClient.accept(msg);
		waitFlag=0;
		while(waitFlag==0) {
		System.out.print("");
		}
		msg.setOption("get all products in sale from DB");
		waitFlag=0;
		msg.setMsg(UserUI.store);		
		UserUI.myClient.accept(msg);
		waitFlag=0;
		while(waitFlag==0){
			System.out.print("");
			}
		waitFlag=0;
		if(type.compareTo("SALE") !=0)
			{
			for(j=0 ; j<CatalogUI.products.size() ; j++) //create product in catalog
			{
				flagSale = false;
				p = CatalogUI.products.get(j);
				productsId.add(String.valueOf(p.getpID()));
				for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
				{
					if(p.getpID()== CatalogUI.productsInSale.get(k).getpID())
					{
						p.setpPrice(CatalogUI.productsInSale.get(k).getpPrice());
						flagSale = true;
						break;
					}
				}
				if(flagSale == false) 
				{
					if(p.getpType().equals(Product.ProductType.valueOf(type)))
					{
						targetStream= new ByteArrayInputStream(p.getByteArray());
						CatalogItemRow itemRow = new CatalogItemRow(p.getpID(), p.getpName(),p.getpType().toString(), p.getpPrice(),  p.getpColor().toString(),
								targetStream);
						catalog.add(itemRow);
					}
				}
			}
		}
		else
		{
			for(j=0 ; j<CatalogUI.products.size() ; j++) //create product in catalog
			{
				flagSale = false;
				p = CatalogUI.products.get(j);
				productsId.add(String.valueOf(p.getpID()));
				for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
				{
					if(p.getpID() == CatalogUI.productsInSale.get(k).getpID())
					{
						p.setpPrice(CatalogUI.productsInSale.get(k).getpPrice());
						flagSale = true;
						break;
					}
				}
				if(flagSale == true) 
				{
					targetStream= new ByteArrayInputStream(p.getByteArray());
					CatalogItemRow itemRow = new CatalogItemRow(p.getpID(), p.getpName(),p.getpType().toString(), p.getpPrice(),  p.getpColor().toString(), targetStream);
					catalog.add(itemRow);
				}
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
			Pane root = loader.load(getClass().getResource("/controller/NoAccountMsg.fxml").openStream());
		
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Message");
			primaryStage.setScene(scene);	
				
			primaryStage.show();
		}
		else
		{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CartFrame.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Cart Frame");
		primaryStage.setScene(scene);		
		primaryStage.show();
		}
	}
	
	/**
	 * logout - customer logout the system and "UserLogin" window open.
	 * @param event - the customer clicked "logout" Hyperlink 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void logout(ActionEvent event) throws Exception /* logout and open login window */
	{
		CustomerController.flag = false;
		order= new Order();
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
			if((txtPAmmount.getText().compareTo("")!=0) && (Integer.valueOf(txtPAmmount.getText())) > 0 && pId != null && (Integer.valueOf(txtPAmmount.getText())) <= 1000)
			{
				pAmmount = Integer.valueOf(txtPAmmount.getText());
					Product p = getproductById(pId);
					if(p != null)
					{
						if(order.getProductsInOrder().containsKey(p))
							order.getProductsInOrder().put(p, (order.getProductsInOrder().get(p))+pAmmount);
						else
							order.getProductsInOrder().put(p, pAmmount);
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
		Pane root = loader.load(getClass().getResource("/controller/SuccessfulAddMsg.fxml").openStream());
		
		
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
	 * If the customer did NOT insert valid amount or did NOT choose
	 * any product from the combo-Box we show Error message window.  
	 * @param event - the customer clicked "add To Cart" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void showErrMsg(ActionEvent event) throws Exception 
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ErrMsgInCatalog.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Error Msg");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/**
	 * try Again Make Order - if the customer arrived to error message window
	 * after click on "Try Again" button he return the catalog, his cuurent
	 * cart saved. 
	 * @param event - the customer clicked "Try Again" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void tryAgainMakeOrder(ActionEvent event) throws Exception 
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/Catalog.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Catalog");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
}
