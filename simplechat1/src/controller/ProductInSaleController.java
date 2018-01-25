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
	
	public void loadProductsInSaleId(ActionEvent event) throws Exception // add product to cart
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
	
	public void removeProductInSale(ActionEvent event) throws Exception // add product to cart
	{
		msg = new Message(p , "Remove Product In Sale from DB");
		UserUI.myClient.accept(msg);
		backToSalesOptions(event);
	}
	
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
	
	public void loadFields(Product p)
	{
		txtPID.setText(String.valueOf(p.getpID()));
		txtSid.setText(String.valueOf(p.getpStore()));
		txtPPrice.setText("");
		txtSType.setText(String.valueOf(p.getpType()));
	}
	
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
