package controller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.OrderUI;
import boundery.UserUI;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogController implements Initializable {
	private Message msg;
	
	public static Order order;
	
	public static boolean basicFlowersFlag=false;
	@FXML
	private Button btnBack = null;	
	@FXML
	private Button btnAddToCard = null;	
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
	private TextField txtPId; /* text field for the product Name */
	@FXML
	private TextField txtPAmmount; /* text field for the product Name */
	@FXML private TableView<CatalogItemRow> catalog_table;

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_id;

	@FXML private TableColumn<CatalogItemRow, String> tablecolumn_name;

	@FXML private TableColumn<CatalogItemRow, Product.ProductType> tablecolumn_type;

	@FXML private TableColumn<CatalogItemRow, Double> tablecolumn_price;

	@FXML private TableColumn<CatalogItemRow, ImageView> tablecolumn_image;

	ObservableList<CatalogItemRow> catalog = FXCollections.observableArrayList();
	
		
	static String flag = null;
	
	ObservableList<String> plist;

	public void back(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */ 
	{
		OrderUI.order = null;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
				
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/ProductForm.css").toExternalForm()); */
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}

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
				if(p.getpID().compareTo(CatalogUI.productsInSale.get(k).getpID()) == 0)
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
	}
	
	public void productCategory(ActionEvent event) throws Exception //create and the catalog by categories
	{
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
		while(CatalogUI.products.size()==0);
		msg.setOption("get all products in sale from DB");
		msg.setMsg(UserUI.store);		
		UserUI.myClient.accept(msg);
		while(CatalogUI.productsInSale.size()==0);
		if(type.compareTo("SALE") !=0)
			{
			for(j=0 ; j<CatalogUI.products.size() ; j++) //create product in catalog
			{
				flagSale = false;
				p = CatalogUI.products.get(j);
				for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
				{
					if(p.getpID().compareTo(CatalogUI.productsInSale.get(k).getpID()) == 0)
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
				for(k = 0 ; k<CatalogUI.productsInSale.size() ; k++)
				{
					if(p.getpID().compareTo(CatalogUI.productsInSale.get(k).getpID()) == 0)
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
	}

	
	
	public void showCart(ActionEvent event) throws Exception // show all products in cart. 
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CartFrame.fxml").openStream());
		
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void logout(ActionEvent event) throws Exception /* logout and open login window */
	{
		CustomerController.flag = false;
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void addToCart(ActionEvent event) throws Exception // add product to cart
	{
		String pId = txtPId.getText();
		int pAmmount;
		if(txtPAmmount.getText().compareTo("")!=0)
		{
			pAmmount = Integer.valueOf(txtPAmmount.getText());
			Product p = getproductById(pId);
			if(p != null)
			{
				if(order.getProductsInOrder().containsKey(p))
					order.getProductsInOrder().put(p, (order.getProductsInOrder().get(p))+pAmmount);
				else
					order.getProductsInOrder().put(p, pAmmount);
				txtPId.setText("");
				txtPAmmount.setText("");
			}
		}
	}

	private Product getproductById(String pId)
	{
		int i;
		for (i=0; i<CatalogUI.products.size() ; i++)
		{
			if(CatalogUI.products.get(i).getpID().compareTo(pId) == 0)
				return CatalogUI.products.get(i);
		}
		return null;
	}
}
