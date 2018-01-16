package controller;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

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
	private TextField txtPPicPath; /* text field for the product Name */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnUpdateProduct = null; /* button update for update product */
	
	@FXML
	private Button btnShow = null; /* button update for update product */
	
	@FXML
	private ImageView IVpPic; /* image of product */

	@FXML
	private ComboBox<Product.ProductColor> cmbpColor = new ComboBox<>(); /* list of product in cart */

	@FXML
	private ComboBox<Product.ProductType> cmbPtype = new ComboBox<>(); /* list of product in cart */
	
	ObservableList<Product.ProductColor> colorlistForComboBox = FXCollections.observableArrayList();
	ObservableList<Product.ProductType> typelistForComboBox = FXCollections.observableArrayList();
	
	public void loadProduct(Product p1) /* To load the product details to the text fields */
	{ 
		this.p=p1;
		this.txtPName.setText(p.getpName());
		this.txtPID.setText(p.getpID());	
		this.cmbPtype.setValue(p.getpType());
		this.txtPPrice.setText(String.valueOf(p.getpPrice()));
		InputStream is = new ByteArrayInputStream(p.getByteArray());
		Image image = new Image(is);
		IVpPic.setImage(image);
		this.cmbpColor.setValue(p.getpColor());
	}
	
	public void closeProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		CompanyWorkerController.cwflag = 1;
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/UpdateProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		colorlistForComboBox.add(Product.ProductColor.ORANGE);
		colorlistForComboBox.add(Product.ProductColor.PINK);
		colorlistForComboBox.add(Product.ProductColor.RED);
		colorlistForComboBox.add(Product.ProductColor.WHITE);
		colorlistForComboBox.add(Product.ProductColor.YELLOW);
		cmbpColor.setItems(colorlistForComboBox);
		typelistForComboBox.add(Product.ProductType.ARRANGEMENT);
		typelistForComboBox.add(Product.ProductType.BOUQUET);
		typelistForComboBox.add(Product.ProductType.BRIDAL_BOUQUET);
		typelistForComboBox.add(Product.ProductType.FLOWER_CROWN);
		typelistForComboBox.add(Product.ProductType.SWEET_BOUQUET);
		typelistForComboBox.add(Product.ProductType.VASE);
		typelistForComboBox.add(Product.ProductType.WREATH_FLOWERS);
		cmbPtype.setItems(typelistForComboBox);
	}
	
	public void updateProduct(ActionEvent event) throws Exception // add product to cart
	{
		Product toCompare = new  Product();
		if(this.txtPPicPath.getText().compareTo("") !=0)
		{
			InputStream is = new FileInputStream(this.txtPPicPath.getText());
			byte[] targetArray = new byte[is.available()];
			toCompare.setInput(is);
			toCompare.setBuffer(targetArray);
		}
		toCompare.setpID(this.txtPID.getText());
		toCompare.setpName(this.txtPName.getText());
		toCompare.setpType(this.cmbPtype.getValue());
		toCompare.setpPrice(Double.valueOf(this.txtPPrice.getText()));
		toCompare.setpColor(this.cmbpColor.getValue());
		msg = new Message(toCompare , "Update Product in DB");
		UserUI.myClient.accept(msg);
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
			catch(Exception e)
			{
				System.out.println("File Did NOT found");
			}
		}
	}
	

}
