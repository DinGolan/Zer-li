package controller;
import java.net.URL;
import java.util.ResourceBundle;

import boundery.ProductUI;
import entity.Message;
import entity.Order;
import entity.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProductController implements Initializable{
	private Product p;
	private Message msg;
	public static Order order;
	private static int flag =0 ;
	
	@FXML
	private TextField txtPID; /* text field for the product Id */
	
	@FXML
	private TextField txtPName; /* text field for the product Name */

	@FXML
	private TextField txtPPrice; /* text field for the product Name */
	
	@FXML
	private TextField txtPType; /* text field for the product Name */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnAddCart = null; /* button update for update product */
	


	
	ObservableList<String> list;

	
	public void loadProduct(Product p1) /* To load the product details to the text fields */
	{ 
		this.p=p1;
		this.txtPName.setText(p.getpName());
		this.txtPID.setText(p.getpID());	
		this.txtPType.setText(p.getpType().toString());
		this.txtPPrice.setText(String.valueOf(p.getpPrice()));
	}
	
	public void closeProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CatalogBouquet.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{

	}
	
	public void addToCart(ActionEvent event) throws Exception // add product to cart
	{
		if(flag == 0)
		{
			order = new Order();
			flag = 1;
		}
		if(order.getProductsInOrder().containsKey(p))
			order.getProductsInOrder().put(p, (order.getProductsInOrder().get(p))+1);
		else
			order.getProductsInOrder().put(p, 1);
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CatalogBouquet.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	

}
