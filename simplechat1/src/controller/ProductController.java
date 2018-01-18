package controller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.ProductUI;
import boundery.UserUI;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProductController implements Initializable{
	private static Product p;
	private Message msg;
	public static Order order;
	public static int flag =0 ;
	
	@FXML
	private TextField txtPID; /* text field for the product Id */
	
	@FXML
	private TextField txtPName; /* text field for the product Name */

	@FXML
	private TextField txtPPrice; /* text field for the product Name */
	
	@FXML
	private TextField txtPPicPath; /* text field for the product Name */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnUpdateProduct = null; /* button update for update product */
	
	@FXML
	private Button btnShow = null; /* button update for update product */
	
	@FXML
	private Button btnExit = null; /* button update for update product */
	
	@FXML
	private Button btnTryAgain = null; /* button update for update product */
	
	@FXML
	private Button btnAddProduct = null; /* button update for update product */
	
	@FXML
	private Button btnDelteProduct = null; /* button update for update product */
	
	@FXML
	private ImageView IVpPic; /* image of product */

	@FXML
	private ComboBox<String> cmbpColor = new ComboBox<>(); /* list of product in cart */

	@FXML
	private ComboBox<String> cmbPtype = new ComboBox<>(); /* list of product in cart */
	
	ObservableList<String> colorlistForComboBox = FXCollections.observableArrayList();
	ObservableList<String> typelistForComboBox = FXCollections.observableArrayList();
	
	public void loadProduct(Product p1) /* To load the product details to the text fields */
	{ 
		if(p!=null)
		{
		this.p=p1;
		this.txtPName.setText(p.getpName());
		this.txtPID.setText(String.valueOf(p.getpID()));	
		this.cmbPtype.setValue(String.valueOf(p.getpType()));
		this.txtPPrice.setText(String.valueOf(p.getpPrice()));
		InputStream is = new ByteArrayInputStream(p.getByteArray());
		Image image = new Image(is);
		IVpPic.setImage(image);
		this.cmbpColor.setValue(String.valueOf(p.getpColor()));
		}
	}
	
	public void closeProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		CompanyWorkerController.cwflag = 1;
		p = new Product();
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/UpdateProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Update Product");		
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		if(flag == 0)
		{
			colorlistForComboBox.add("ORANGE");
			colorlistForComboBox.add("PINK");
			colorlistForComboBox.add("RED");
			colorlistForComboBox.add("WHITE");
			colorlistForComboBox.add("YELLOW");
			cmbpColor.setItems(colorlistForComboBox);
			typelistForComboBox.add("ARRANGEMENT");
			typelistForComboBox.add("BOUQUET");
			typelistForComboBox.add("BRIDAL_BOUQUET");
			typelistForComboBox.add("FLOWER_CROWN");
			typelistForComboBox.add("SWEET_BOUQUET");
			typelistForComboBox.add("VASE");
			typelistForComboBox.add("WREATH_FLOWERS");
			cmbPtype.setItems(typelistForComboBox);
		}
		if(flag == 1)
			flag =0;
	}
	
	public void updateProduct(ActionEvent event) throws Exception // add product to cart
	{
		try 
		{
			if((this.txtPName.getText().compareTo("") !=0) && (this.txtPPrice.getText().compareTo("") !=0) && (Double.valueOf(this.txtPPrice.getText()) > 0)) 
			{
				try {
				Product toCompare = new  Product();
				if(this.txtPPicPath.getText().compareTo("")!=0)
				{
					InputStream in = new FileInputStream(this.txtPPicPath.getText());
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while((len=in.read(buffer)) != -1) {
						os.write(buffer ,0 , len);
					}
					toCompare.setBuffer(os.toByteArray());
				}
				toCompare.setpID(Integer.valueOf(this.txtPID.getText()));
				toCompare.setpName(this.txtPName.getText());
				toCompare.setpType(Product.ProductType.valueOf(this.cmbPtype.getValue()));
				toCompare.setpPrice(Double.valueOf(this.txtPPrice.getText()));
				toCompare.setpColor(Product.ProductColor.valueOf(this.cmbpColor.getValue()));
				msg = new Message(toCompare , "Update Product in DB");
				UserUI.myClient.accept(msg);
				closeProductWindow(event);
				} catch (FileNotFoundException e) {
					flag =1;
					((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
					Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
					FXMLLoader loader = new FXMLLoader(); 					 /* load object */
					Pane root = loader.load(getClass().getResource("/controller/DontExistPicErr.fxml").openStream());
					
					Scene scene = new Scene(root);			
					primaryStage.setScene(scene);	
					primaryStage.setTitle("Error message");	
					primaryStage.show();									 /* show catalog frame window */
				}
			}
			else
			{
				flag =1;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/ErrInFields.fxml").openStream());
				
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error message");	
				primaryStage.show();									 /* show catalog frame window */
			}	
		}
		catch (NumberFormatException e)
		{
			flag =1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/ErrInFields.fxml").openStream());
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error message");	
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	public void showPicture(ActionEvent event) throws Exception // add product to cart
	{
		if(this.txtPPicPath.getText().compareTo("") !=0)
		{
			try {
			InputStream is = new FileInputStream(this.txtPPicPath.getText());
			Image image = new Image(is);
			IVpPic.setImage(image);
			}
			catch(Exception e) // picture dont exist
			{
				flag =1;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/DontExistPicErr.fxml").openStream());
				
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error message");	
				primaryStage.show();									 /* show catalog frame window */
			}
		}
	}
	
	public void openProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		flag =0;
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/ProductForm.fxml").openStream());
		
		ProductController productController = loader.getController();		
		productController.loadProduct(p);
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Product Form");			
		primaryStage.show();									 /* show catalog frame window */
	}
	
	public void addProduct(ActionEvent event) throws Exception // add product to cart
	{
		try
		{
			if((this.txtPPicPath.getText().compareTo("")!=0) &&  (this.txtPName.getText().compareTo("")!=0)&& (this.txtPPrice.getText().compareTo("")!=0)&& (this.cmbPtype.getValue()!=null)&& (this.cmbpColor.getValue()!=null)&& (Double.valueOf(this.txtPPrice.getText()) > 0))
			{
				try {
				Product toadd = new  Product();
				InputStream in = new FileInputStream(this.txtPPicPath.getText());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while((len=in.read(buffer)) != -1) {
					os.write(buffer ,0 , len);
				}
				toadd.setBuffer(os.toByteArray());
				toadd.setpName(this.txtPName.getText());
				toadd.setpType(Product.ProductType.valueOf(this.cmbPtype.getValue()));
				toadd.setpPrice(Double.valueOf(this.txtPPrice.getText()));
				toadd.setpColor(Product.ProductColor.valueOf(this.cmbpColor.getValue()));
				msg = new Message(toadd , "Add new Product in DB");
				UserUI.myClient.accept(msg);
				addProductWindow(event);
				} catch (FileNotFoundException e) {
					flag =1;
					((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
					Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
					FXMLLoader loader = new FXMLLoader(); 					 /* load object */
					Pane root = loader.load(getClass().getResource("/controller/DontExistPicErr.fxml").openStream());
					
					Scene scene = new Scene(root);			
					primaryStage.setScene(scene);	
					primaryStage.setTitle("Error message");			
					primaryStage.show();									 /* show catalog frame window */
				}
			}
			else
			{
				flag =1;
				((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
				FXMLLoader loader = new FXMLLoader(); 					 /* load object */
				Pane root = loader.load(getClass().getResource("/controller/EmptyFieldsErr.fxml").openStream());
				
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error message");			
				primaryStage.show();									 /* show catalog frame window */
			}
		}
		catch (NumberFormatException e)
		{
			flag =1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 /* load object */
			Pane root = loader.load(getClass().getResource("/controller/EmptyFieldsErr.fxml").openStream());
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error message");	
			primaryStage.show();									 /* show catalog frame window */
		}
	}
	
	public void addProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		CompanyWorkerController.cwflag = 0;
		p = new Product();
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/AddProductForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Add product");		
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	public void removeProduct(ActionEvent event) throws Exception // add product to cart
	{
		msg = new Message(p , "Remove Product from DB");
		UserUI.myClient.accept(msg);
		closeProductWindow(event);
	}
	
	public void closeAddtWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		CompanyWorkerController.cwflag = 0;
		p = new Product();
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyWorkerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Update Product");		
		primaryStage.show();									 /* show catalog frame window */
	}
}
