package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.StoreUI;
import boundery.UserUI;
import entity.Message;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyWorkerController implements Initializable {
	
	private Message msg;

	public static int cwflag=0;
	
    @FXML
    private Button btnUpdateCatalog; /* Button For Update catalog by company worker */
    
    @FXML
    private Button btnAddNewProduct; /* Button For Add product in catalog by company worker */
    
    @FXML
    private Button btnDeleteProduct; /* Button For delete product from catalog by company worker */
    
    @FXML
    private Button btnUpdateSaleInCatalog; /* Button For Update sale in catalog by company worker */
    
    @FXML
    private Button btnBackToCompanyMangetOptions; /* Button For back to choose product to update */
    
    @FXML
    private Button btnLoadProduct; /* Button For loading product */
    
    @FXML
    private Button btnLogout; /* Button For loading product */
    
    @FXML
    private Button btnBacKToCWO; /* Button For loading product */
    
    @FXML
    private Button btnUpdateSaleInStore; /* Button For loading product */
    
    @FXML
    private Button btnAddNewSale; /* Button For loading product */
    
    @FXML
    private Button btnRemoveSale; /* Button For loading product */
    
	@FXML
	private ComboBox<String> cmbPid = new ComboBox<>(); /* comboBox of products id */
	
	ObservableList<String> productsId = FXCollections.observableArrayList();
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		if(cwflag == 1)
			setProductComboBox();
	}

	public void openUpdateProduct(ActionEvent event) throws Exception {
		cwflag = 1;
		ProductController.flag = 0;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/UpdateProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		primaryStage.setTitle("Update Product");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void setProductComboBox(){
		int j;
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

		cmbPid.setItems(productsId);
	}
	
	public void openProductInfoWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		if(cmbPid.getValue() != null)
		{
			cwflag=0;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/ProductForm.fxml").openStream());
			
			ProductController productController = loader.getController();		
			productController.loadProduct(CatalogUI.products.get(getProductIndext()));
			
			Scene scene = new Scene(root);			
			//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
			primaryStage.setTitle("Product Form");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	public void addNewProductWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		ProductController.flag = 1;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/AddProductForm.fxml").openStream());
			
			Scene scene = new Scene(root);			
			//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
			primaryStage.setTitle("Add Product");
			primaryStage.setScene(scene);		
			primaryStage.show();
		
	}
	
	public void removeProductWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		cwflag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/RemoveProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
		primaryStage.setTitle("Remove Product");
		primaryStage.setScene(scene);		
		primaryStage.show();
	
}
	
	public int getProductIndext() // return the index of the product the user want info of. 
	{
		String pid = cmbPid.getValue();
		int i;
		for (i = 0 ; i<CatalogUI.products.size() ; i++ )
		{
			if(CatalogUI.products.get(i).getpID() == Integer.valueOf(pid))
				break;
		}
		return i;
	}
	
	public void backToCompanyWorkerOptions(ActionEvent event) throws Exception { // open info window of the product the user want info of.

			cwflag=0;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CompanyWorkerOptions.fxml").openStream());
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Company Worker Options");
			primaryStage.show();
	}
	
	public void logout(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
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
	
	public void openProductInfoToDeleteWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		if(cmbPid.getValue() != null)
		{
			cwflag=0;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/ProductDeleteForm.fxml").openStream());
			
			ProductController productController = loader.getController();		
			productController.loadProduct(CatalogUI.products.get(getProductIndext()));
			
			Scene scene = new Scene(root);			
			//scene.getStylesheets().add(getClass().getResource("/gui/StudentForm.css").toExternalForm());
			primaryStage.setTitle("Remove Product");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	public void updateSales(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SalesOptions.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Sales Options");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void updateSalesInStore(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		ProductInSaleController.flag = 1;
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UpdateSalesInStore.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Sales Options");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void addSalesInStore(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		ProductInSaleController.flag = 3;
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AddSale.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Add Sale");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
