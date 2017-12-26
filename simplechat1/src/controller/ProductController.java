package controller;
import java.util.ArrayList;

import entity.Message;
import entity.Product;
import boundery.ProductUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProductController{
	private Product p;
	private Message msg;

	@FXML
	private TextField txtPID; /* text field for the product Id */
	
	@FXML
	private TextField txtPName; /* text field for the product Name */
	
	@FXML
	private TextField txtPType; /* text field for the product Type */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnUpdate = null; /* button update for update product */
	
	ArrayList<String> temp;

	

	public void loadProduct(Product p1) /* To load the product details to the text fields */
	{ 
		this.p=p1;
		this.txtPName.setText(p.getpName());
		this.txtPID.setText(p.getpID());		
		this.txtPType.setText(p.getpType());
	}
	
	public void closeProductWindow(ActionEvent event) throws Exception  /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		ProductUI.products.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();						 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 /* load object */
		Pane root = loader.load(getClass().getResource("/boundery/CatalogFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
				
		primaryStage.show();									 /* show catalog frame window */
	}
	
	public void updateProduct(ActionEvent event) throws Exception /* Update the product name */
	{
		//temp.add("0"); 						/* 0 - Its mean that we want to do update Specific Prodcut */
		temp = new ArrayList<String>();
		temp.add(txtPID.getText());
		temp.add(txtPName.getText());
		temp.add(txtPType.getText());
		msg = new Message(temp, "0");
				
		ProductUI.myClient.accept(msg);
		/* this.p.setpID(txtPID.getText()); */
		/* this.p.setpName(txtPName.getText()); */
		/* this.p.setpType(txtPType.getText()); */
	} 
}
