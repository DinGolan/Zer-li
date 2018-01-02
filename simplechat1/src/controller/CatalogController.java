package controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import java.util.Vector;
import boundery.UserUI;
import entity.Message;
import entity.Product;
import entity.Product.ProductType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CatalogController implements Initializable {
	private Message msg;

	@FXML
	private Button btnBack = null;
	
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
	private Hyperlink FlowerWreathLink;
	
	@FXML
	private Hyperlink VaseLink;
	
	@FXML
	private Hyperlink SalesLink;

	
	@FXML
	private Label label1 ;
	@FXML
	private Label label2 ;
	@FXML
	private Label label3 ;
	@FXML
	private Label label4 ;
	@FXML
	private Label label5 ;
	@FXML
	private Label label6 ;
	@FXML
	private Label label7 ;
	@FXML
	private Label label8 ;
	@FXML
	private Label label9 ;
	@FXML
	private Label label10 ;
	@FXML
	private Label label11 ;
	@FXML
	private Label label12 ;

	private List<Label> labels = new ArrayList<Label>();
	
	
	@FXML
	private ImageView Pic1;
	@FXML
	private ImageView Pic2;
	@FXML
	private ImageView Pic3;
	@FXML
	private ImageView Pic4;
	@FXML
	private ImageView Pic5;
	@FXML
	private ImageView Pic6;
	@FXML
	private ImageView Pic7;
	@FXML
	private ImageView Pic8;
	@FXML
	private ImageView Pic9;
	@FXML
	private ImageView Pic10;
	@FXML
	private ImageView Pic11;
	@FXML
	private ImageView Pic12;

	private List<ImageView> Pics = new ArrayList<ImageView>();

	
	
	@FXML
	private Button info1;
	@FXML
	private Button info2;
	@FXML
	private Button info3;
	@FXML
	private Button info4;
	@FXML
	private Button info5;
	@FXML
	private Button info6;
	@FXML
	private Button info7;
	@FXML
	private Button info8;
	@FXML
	private Button info9;
	@FXML
	private Button info10;
	@FXML
	private Button info11;
	@FXML
	private Button info12;
	
	String flag = null;
	
	private List<Button> btnProductInfo = new ArrayList<Button>();
	
	ObservableList<String> plist;
	
	

	public void back(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */ 
	{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
				
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/ProductForm.css").toExternalForm()); */
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}
	
	public void initall()
	{
		int i=0;
		for( ; i<12 ; i++)
		{
			Pics.get(i).setImage(null);
			labels.get(i).setText(null);
			labels.get(i).setVisible(false);
			btnProductInfo.get(i).setVisible(false);
			btnProductInfo.get(i).setDisable(true);
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		flag = "BOUQUET";
		Pics = Arrays.asList(Pic1 , Pic2 , Pic3 , Pic4 , Pic5 , Pic6 , Pic7 , Pic8 , Pic9 , Pic10 , Pic11 , Pic12);
		btnProductInfo = Arrays.asList(info1, info2 , info3 ,info4 , info5 , info6 , info7 , info8 , info9 , info10 , info11 , info12);
		labels = Arrays.asList(label1 , label2 , label3 , label4 , label5 , label6 , label7 , label8 , label9 , label10 , label11 , label12);
		CatalogUI.products.clear();
		ArrayList<String> s = new ArrayList<String>();
		Image image;
		InputStream is;
		msg = new Message(s, "get all products in DB");
		int i=0 , j;
		Product p;
		UserUI.myClient.accept(msg);
		while(CatalogUI.products.size()==0); 
		for(j=0 ; j<CatalogUI.products.size() ; j++)
		{
			p = CatalogUI.products.get(j);
			if(p.getpType().equals(ProductType.valueOf("BOUQUET")))
			{
			       try {
			            is = new FileInputStream(p.getpPicture()); 
						image = new Image(is);
						Pics.get(i).setImage(image);
						labels.get(i).setText(p.getpName());
						labels.get(i).setVisible(true);
						btnProductInfo.get(i).setVisible(true);
						btnProductInfo.get(i).setDisable(false);
						i++;
			        } catch (FileNotFoundException e) {
			            e.printStackTrace();
			        }
			}
		}
	}
	
	public void productCategory(ActionEvent event) throws Exception //create and the catalog by categories
	{
		initall();
		CatalogUI.products.clear();
		ArrayList<String> s = new ArrayList<String>();
		Image image;
		InputStream is;
		msg = new Message(s, "get all products in DB");
		Iterator<Product> iter = CatalogUI.products.iterator();
		int i=0 , j;
		Product p;
		String type = null;
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
		case "FlowerWreathLink":
			type = "FLOWER_WREATH";
			flag = "FLOWER_WREATH";
			break;
		case "VaseLink":
			type = "VASE";
			flag = "VASE";
			break;
		}
		
		UserUI.myClient.accept(msg);
		while(CatalogUI.products.size()==0);
		for(j=0 ; j<CatalogUI.products.size() ; j++) //create product in catalog
		{
			p = CatalogUI.products.get(j);
			if(p.getpType().equals(ProductType.valueOf(type)))
			{
			       try {
			            is = new FileInputStream(p.getpPicture()); 
						image = new Image(is);
						Pics.get(i).setImage(image);
						labels.get(i).setText(p.getpName());
						labels.get(i).setVisible(true);
						btnProductInfo.get(i).setVisible(true);
						btnProductInfo.get(i).setDisable(false);
						i++;
			        } catch (FileNotFoundException e) {
			            e.printStackTrace();
			        }
			}
		}
	}
	
	
	public void openProductInfoWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ProductForm.fxml").openStream());
		
		ProductController productController = loader.getController();		
		productController.loadProduct(CatalogUI.products.get(getProductIndext(event)));
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public int getProductIndext(ActionEvent event) // return the index of the product the user want info of. 
	{
		String infoId = ((Node)event.getSource()).getId();
		int i , j;
		for(i=0 ; i<btnProductInfo.size() ; i++) // find the index of the info button that pressed
		{
			if(btnProductInfo.get(i).getId().equals(infoId))
				break;
		}
		for(j=0 ; j<CatalogUI.products.size() ; j++)
		{
			if ((i==0) && (String.valueOf(CatalogUI.products.get(j).getpType())).equals(flag)) //if we found the product in product's arrayList
				return j;
			else if ((i!=0) && (String.valueOf(CatalogUI.products.get(j).getpType())).equals(flag))
				i--;	
		}
		return -1;
	}
	
	

}
