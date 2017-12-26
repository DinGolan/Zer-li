package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.Message;
import entity.Product;
import boundery.ProductUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogController implements Initializable {
	private ProductController pfc;
	private static int itemIndex = 2; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */
	private Message msg;

	@FXML
	private Button btnExit = null;
	
	@FXML
	private ComboBox cmbProducts;  /* ComboBox With List Of Product */
	
	@FXML
	private Button btnProductInfo = null; /* Button Of Product Info */
	
	ObservableList<String> plist;
	
	
	public void ProductInfo(ActionEvent event) throws Exception /* With this Method we Hide the GUI of the Catalog and Show the GUI of the Product that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/ProductForm.fxml").openStream());
		
		ProductController productFormController = loader.getController();		
		productFormController.loadProduct(ProductUI.products.get(getItemIndex())); /* In this Line We take the Product that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/ProductForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

	
	public int getItemIndex() /* With this Method we Take Product from the List of the Product at the ComboBox */
	{
		if(cmbProducts.getSelectionModel().getSelectedIndex() ==-1)
			return itemIndex;
	
		return cmbProducts.getSelectionModel().getSelectedIndex();
	}
	

	public void start(Stage primaryStage) throws Exception     /* With this Method we show the GUI of the Catalog */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/boundery/CatalogFrame.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}

	public void getExitBtn(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */ 
	{
		System.out.println("exit Catalog Tool");
		System.exit(0);			
	}
	
	public void loadProduct(Product p1) /* Loading Product */
	{
		this.pfc.loadProduct(p1);
	}
	
	/* We Need to return To Private ? */
	public void setProductsComboBox() /* In this Method we Set the Product at the ComboBox */
	{ 				
		ArrayList<String> pl = new ArrayList<String>();	
		
		for(Product s: ProductUI.products)
		{
			pl.add(s.getpName());
		}
		
		plist = FXCollections.observableArrayList(pl);
		cmbProducts.setItems(plist);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		ArrayList<String> s = new ArrayList<String>();
		//s.add("1"); // 1 - Its Mean That we want to Initialized the from the DB 
		msg = new Message(s, "1");

		ProductUI.myClient.accept(msg);
		while(ProductUI.products.size() == 0);
		setProductsComboBox();
	}

}
