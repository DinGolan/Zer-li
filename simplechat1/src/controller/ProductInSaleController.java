package controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.ProductUI;
import boundery.StoreUI;
import boundery.UserUI;
import entity.Message;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the Product in sale options - company worker can:
 * add/ remove or update sale of specific product in specific store.
 *
 */
public class ProductInSaleController implements Initializable{

	private static Product p;
	private Message msg;

	public static int flag = 0;
	
	@FXML
	private ComboBox<String> cmbPid = new ComboBox<>(); /* comboBox of products id */
	
	@FXML
	private ComboBox<String> cmbSid = new ComboBox<>(); /* comboBox of products id */
	
    @FXML
    private Button btnLoadSales; /* Button For loading product */
    
    @FXML
    private Button btnLoadProduct; /* Button For loading product */
    
    @FXML
    private Button btnBackToChooseProduct; /* Button For loading product */
    
    @FXML
    private Button btnUpdateSale; /* Button For loading product */
    
    @FXML
    private Button btnRemoveSale; /* Button For loading product */
    
    @FXML
    private Button btnBackToSalesOptions; /* Button For loading product */
    
    @FXML
    private Button btnTryAgain; /* Button For loading product */
    
    @FXML
    private Button btnContinue; /* Button For loading product */
    
    @FXML
    private Button btnBackToAdd; /* Button For loading product */
    
    @FXML
    private Button btnBackToAddSale; /* Button For loading product */
    
    @FXML
    private Button btnAddSale; /* Button For loading product */
    
    @FXML
    private Button btnTryAgainAddSale; /* Button For loading product */
    
    @FXML
    private Button btnBackToAddSaleFromErr; /* Button For loading product */
    
    @FXML
    private Button btnBack; /* Button For loading product */
    
    
	@FXML
	private TextField txtPID; /* text field for the product Name */
	
	@FXML
	private TextField txtSid; /* text field for the product Name */
	
	@FXML
	private TextField txtSType; /* text field for the product Name */
	
	@FXML
	private TextField txtPPrice; /* text field for the product Name */
    	
	ObservableList<String> storesId = FXCollections.observableArrayList();
	
	ObservableList<String> productsId = FXCollections.observableArrayList();
	
	private static double producePrice;

	/**
	 * company worker have to choose store Id and product Id
	 * that he want to add/remove or update sale in this store
	 * for this product. this function call functions that set the 
	 * comboBos of existing stores and products
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(flag == 1)
		{
			storesId.clear();
			productsId.clear();
			setStoresCmb();
		}
		if(flag == 3)
		{
			storesId.clear();
			productsId.clear();
			setAllStoresAndProductsCmb();
		}
	}
	
	/**
	 * set the comboBox of existing stores that the company
	 * worker need to choose one of them
	 */
	public void setStoresCmb(){
		int j;
		CatalogUI.productsInSale.clear();
		msg = new Message(null,"get all stores from DB");
		UserUI.myClient.accept(msg);
		CustomerController.cflag=0;
		while(CustomerController.cflag==0) {
		System.out.print("");
		}
		CustomerController.cflag=0;
		for(j=0 ; j<StoreUI.stores.size() ; j++)
		{
			storesId.add(String.valueOf(StoreUI.stores.get(j).getStoreId()));
		}

		cmbSid.setItems(storesId);
	}
	
	/**
	 * set the comboBox of existing products in specific store
	 *  that the company worker need to choose one of them in case
	 *  he want to add sale
	 */
	public void setAllStoresAndProductsCmb(){
		int j;
		CatalogUI.productsInSale.clear();
		msg = new Message(null,"get all stores from DB");
		UserUI.myClient.accept(msg);
		CustomerController.cflag=0;
		while(CustomerController.cflag==0) {
		System.out.print("");
		}
		CustomerController.cflag=0;
		for(j=0 ; j<StoreUI.stores.size() ; j++)
		{
			storesId.add(String.valueOf(StoreUI.stores.get(j).getStoreId()));
		}
		
		msg = new Message(null,"get all products in DB");
		UserUI.myClient.accept(msg);
		CatalogUI.products.clear();
		CatalogController.waitFlag=0;
		while(CatalogController.waitFlag==0) {
		System.out.print("");
		}
		CatalogController.waitFlag=0;
		for(j=0 ; j<CatalogUI.products.size() ; j++)
		{
			productsId.add(String.valueOf(CatalogUI.products.get(j).getpID()));
		}
		cmbSid.setItems(storesId);
		cmbPid.setItems(productsId);
	}
	
	/**
	 * in case the company worker want to update or remove an existing
	 * sale we load all the product in sale of specific store he chose.
	 * if he did Not chose any store we pop an Error message.
	 * @param event - company worker click "load products" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void loadProductsInSaleId(ActionEvent event) throws Exception 
	{
		productsId.clear();
		String storeId;
		int j;
		CatalogUI.productsInSale.clear();
		msg = new Message(null,"get products in sale from DB");
		UserUI.myClient.accept(msg);
		CatalogController.waitFlag=0;
		while(CatalogController.waitFlag==0) {
		System.out.print("");
		}
		CatalogController.waitFlag=0;
		if(cmbSid.getValue() != null)
		{
			storeId = cmbSid.getValue();
			for(j=0 ; j<CatalogUI.productsInSale.size() ; j++)
			{
				if(CatalogUI.productsInSale.get(j).getpStore() == Integer.valueOf(storeId))
					productsId.add(String.valueOf(CatalogUI.productsInSale.get(j).getpID()));
			}

			cmbPid.setItems(productsId);
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/DidNotPickStoreId.fxml").openStream());
			
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error Message");		
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	/**
	 * in case the company worker want to update or remove an existing
	 * sale we load the details of a specific product and open "product form" window
	 * @param event - company worker click "add or remove sale" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void loadProduct(ActionEvent event) throws Exception // add product to cart
	{
		if(cmbPid.getValue() != null)
		{
			flag = 2;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/UpdateProductSale.fxml").openStream());
					
			ProductInSaleController productInSaleController = loader.getController();	
			productInSaleController.loadProduct(cmbPid.getValue());
				
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Sales Options");		
			primaryStage.show();									 /* show catalog frame window */
		}
		else
		{
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/DidNotPickProductId.fxml").openStream());
			
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error Message");		
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	/**
	 * back to "Sales Options" window 
 	 * @param event - company worker click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToSalesOptions(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/SalesOptions.fxml").openStream());
		
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Sales Options");		
		primaryStage.show();									 /* show catalog frame window */
	}
	
	/**
	 * this function load to "product form" the details
	 * on specific product
	 * @param pid - the product Id of specific product we load its details
	 */
	public void loadProduct(String pid) /* To load the product details to the text fields */
	{ 
		p= new Product();
		int j;
		for(j=0 ; j<CatalogUI.productsInSale.size() ; j++)
		{
			if(String.valueOf(CatalogUI.productsInSale.get(j).getpID()).compareTo(pid) == 0)
			{
				p.setpID(CatalogUI.productsInSale.get(j).getpID());
				p.setpStore(CatalogUI.productsInSale.get(j).getpStore());
				p.setpPrice(CatalogUI.productsInSale.get(j).getpPrice());
				p.setpType(CatalogUI.productsInSale.get(j).getpType());
				break;
			}
		}
		txtPID.setText(String.valueOf(p.getpID()));
		txtSid.setText(String.valueOf(p.getpStore()));
		txtPPrice.setText(String.valueOf(p.getpPrice()));
		txtSType.setText(String.valueOf(p.getpType()));
	}
	
	/**
	 * in case Company worker want to remove sale of product we remove it
	 * from the Data base and open "sale options" window
	 * @param event - Company worker click "remove sale" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void removeProductInSale(ActionEvent event) throws Exception // add product to cart
	{
		msg = new Message(p , "Remove Product In Sale from DB");
		UserUI.myClient.accept(msg);
		backToSalesOptions(event);
	}
	
	/**
	 * in case Company worker want to update sale of product we  check
	 * check all the fields he inserted. we also check if the price
	 * he try to insert bigger than the original price. in case one
	 * of the fields is Invalid we pop an Error message , else
	 * we update the sale in the Data base. 
	 * @param event - Company worker click "Update sale" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void updateSaleInDB(ActionEvent event) throws Exception // add product to cart
	{
		double pPrice=0;
		msg = new Message(null,"get all products in DB");
		UserUI.myClient.accept(msg);
		CatalogUI.products.clear();
		CatalogController.waitFlag=0;
		while(CatalogController.waitFlag==0) {
		System.out.print("");
		}
		CatalogController.waitFlag=0;
		for(int j=0 ; j<CatalogUI.products.size() ; j++)
		{
			if(CatalogUI.products.get(j).getpID() == Integer.valueOf(this.txtPID.getText())) {
				pPrice = CatalogUI.products.get(j).getpPrice();
				break;
			}
		}
		try
		{
			if((this.txtPPrice.getText().compareTo("")!=0) && (Double.valueOf(this.txtPPrice.getText())<pPrice) && (Double.valueOf(this.txtPPrice.getText())>pPrice))
			{
				p.setpPrice(Double.valueOf(this.txtPPrice.getText()));
				msg = new Message(p , "Update Product In Sale In DB");
				UserUI.myClient.accept(msg);
				backToSalesOptions(event);
			}
			else
				senfErrMsg(event);
		}
		catch (NumberFormatException e)
		{
			senfErrMsg(event);
		}
	}
	
	/**
	 * error message in case one of the fields the company worker
	 * insert is wrong
	 * @param event - Company worker click "Update sale" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void senfErrMsg(ActionEvent event) throws IOException
	{
		flag= 2;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/EmptyFieldsErrInSale.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Error message");	
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	/**
	 * in case we pop an error message and the company worker want
	 * go back to update the sale - we load again the product sale
	 * @param event - Company worker click "back" button from Error message
 	 * @throws Exception - if we can't load the fxml file
	 */
	public void loadProductAgain(ActionEvent event) throws Exception // add product to cart
	{
		flag = 2;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/UpdateProductSale.fxml").openStream());
					
		ProductInSaleController productInSaleController = loader.getController();
		productInSaleController.loadProduct(String.valueOf(p.getpID()));
				
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Sales Options");		
		primaryStage.show();									 /* show catalog frame window */
		
	}
	
	/**
	 * open "Update Sales In Store" window in case the company worker
	 * want to update or remove another sale 
	 * @param event - Company worker click "back" button
	 * @throws IOException - if we can't load the fxml file
	 */
	public void backToChooseProduct(ActionEvent event) throws IOException
	{
		flag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/UpdateSalesInStore.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Update Sales In Store");	
		primaryStage.show();									 /* show catalog frame window */
	}
	
	/**
	 * in case the company worker want to add a new sale in specific store
	 * we check if the sale that he try to add is already exist , 
	 * if the company worker didn't pick any store or any product,
	 * in case one of this options happen we pop an error message,
	 * else we open "add sale" form
	 * @param event  - Company worker click "add sale" button
	 * @throws IOException - if we can't load the fxml file
	 */
	public void tryAddSale(ActionEvent event) throws IOException
	{
		flag = 2;
		int j;
		boolean addFlag = true;
		p= new Product();
		CatalogUI.productsInSale.clear();
		msg = new Message(null,"get products in sale from DB");
		UserUI.myClient.accept(msg);
		CatalogController.waitFlag=0;
		while(CatalogController.waitFlag==0) {
		System.out.print("");
		}
		CatalogController.waitFlag=0;
		if(cmbPid.getValue() != null && cmbSid.getValue() != null)
		{
			for(j=0 ; j<CatalogUI.productsInSale.size() ; j++)
			{
	
				if((CatalogUI.productsInSale.get(j).getpID() == Integer.valueOf(cmbPid.getValue())) && (CatalogUI.productsInSale.get(j).getpStore() == Integer.valueOf(cmbSid.getValue())))
					addFlag = false;
			}
			msg = new Message(null,"get all products in DB");
			UserUI.myClient.accept(msg);
			CatalogUI.products.clear();
			CatalogController.waitFlag=0;
			while(CatalogController.waitFlag==0) {
			System.out.print("");
			}
			CatalogController.waitFlag=0;
			for(j=0 ; j<CatalogUI.products.size() ; j++)
			{
				if(CatalogUI.products.get(j).getpID() == Integer.valueOf(cmbPid.getValue()))
				{
					p.setpType(CatalogUI.products.get(j).getpType());
					producePrice = CatalogUI.products.get(j).getpPrice();
				}
			}
			if(addFlag == true) // can add product
			{
				p.setpID(Integer.valueOf(cmbPid.getValue()));
				p.setpStore(Integer.valueOf(cmbSid.getValue()));
				flag = 4;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/AddSalesInStore.fxml").openStream());
				
				ProductInSaleController productInSaleController = loader.getController();
				productInSaleController.loadFields(p);
				
				Scene scene = new Scene(root);		
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Add Sales In Store");	
				primaryStage.show();									 /* show catalog frame window */
			}
			
			else // cant add product
			{
				flag =4;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/SaleAlreadyExist.fxml").openStream());
				
				Scene scene = new Scene(root);		
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Add Sales In Store");	
				primaryStage.show();									 /* show catalog frame window */
			}
		}
		else
		{
			flag =4;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/DidNotPickProOrStore.fxml").openStream());
			
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Add Sales In Store");	
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	/**
	 * if company worker want to go back to "add sale" window
	 * from "Error message" for example
	 * @param event - company worker click "back" button
	 * @throws IOException - if we can't load the fxml file 
	 */
	public void backToAddSale(ActionEvent event) throws IOException
	{
		flag = 3;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/AddSale.fxml").openStream());
		
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Update Sales In Store");	
		primaryStage.show();									 /* show catalog frame window */
	}
	
	/**
	 * we load product Id, store Id and product type of specific 
	 * product that the company worker want to add sale on it.
	 * also we set the price field to empty field because he
	 * need to fill it. 
	 * @param p - the specific product we load its details
	 */
	public void loadFields(Product p)
	{
		txtPID.setText(String.valueOf(p.getpID()));
		txtSid.setText(String.valueOf(p.getpStore()));
		txtPPrice.setText("");
		txtSType.setText(String.valueOf(p.getpType()));
	}
	
	/**
	 * in case company worker filled all the fields in "add sale" form
	 * we check if all the fields he insert, include if the price 
	 * after the sale bigger than the original of the price. 
	 * if one of the fields inValid - we pop
	 * an Error message ,  else we add the sale to the Data base.
	 * @param event - company worker click "add" button
	 * @throws Exception - if we can't load the fxml file 
	 */
	public void addSaleToDB(ActionEvent event) throws Exception
	{
		try
		{
			if(txtPPrice.getText().compareTo("") != 0 && Double.valueOf(txtPPrice.getText()) > 0 && Double.valueOf(txtPPrice.getText())< producePrice)
			{
				p.setpPrice(Double.valueOf(txtPPrice.getText()));
				msg = new Message(p,"add new sale to DB");
				UserUI.myClient.accept(msg);
				backToSalesOptions(event);
			}
			else
			{
				showErrMsg(event);
			}
				
		}
		catch(NumberFormatException e)
		{
			showErrMsg(event);
		}
	}
	
	/**
	 * Error message because one of the fields the company worker insert
	 * is inValid.
	 * @param event - company worker click "add" button
	 * @throws Exception - if we can't load the fxml file 
	 */
	public void showErrMsg(ActionEvent event) throws IOException
	{
		flag =4;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/EmptyFieldInSaleErrMsg.fxml").openStream());
		
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Add Sales In Store");	
		primaryStage.show();									 /* show catalog frame window */
	}
	
	/**
	 * back from error message we reload the product details
	 * that the company worker want to add sale on.
	 * @param event - company worker click "back" button from error message
	 * @throws IOException  - if we can't load the fxml file 
	 */
	public void tryAddSaleAgain(ActionEvent event) throws IOException
	{
		flag = 4;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/AddSalesInStore.fxml").openStream());
		
		ProductInSaleController productInSaleController = loader.getController();
		productInSaleController.loadFields(p);
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Add Sales In Store");	
		primaryStage.show();									 /* show catalog frame window */
	}
	
}
