package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.CatalogUI;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the Company Worker options - add product, delete product,
 * update product, add sale to specific store, remove sale 
 *
 */
public class CompanyWorkerController implements Initializable {
	
	private Message msg;

	public static int cwflag=0;
	
    @FXML
    private Button btnUpdateCatalog; 
    
    @FXML
    private Button btnAddNewProduct; 
    
    @FXML
    private Button btnDeleteProduct;
    
    @FXML
    private Button btnUpdateSaleInCatalog;
    
    @FXML
    private Button btnBackToCompanyMangetOptions;
    
    @FXML
    private Button btnLoadProduct;
    
    @FXML
    private Button btnLogout;
    
    @FXML
    private Button btnBacKToCWO; 
    
    @FXML
    private Button btnUpdateSaleInStore; 
    
    @FXML
    private Button btnAddNewSale;
    
    @FXML
    private Button btnRemoveSale;
    
    @FXML
    private Button btnBack; 
    
    @FXML
    private Button btnBackToDelete; 
    
	@FXML
	private ComboBox<String> cmbPid = new ComboBox<>(); 
	
	ObservableList<String> productsId = FXCollections.observableArrayList();
    
    /**
     * call function that set comboBox of products Id
     * for Company Worker, that he can choose one of
     * the products and update/delete it.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		if(cwflag == 1)
			setProductComboBox();
	}

	/**
	 * open "Update product" window that the Company Worker can
	 * choose one of the product id from the comboBox
	 * @param event - Company Worker click "update" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openUpdateProduct(ActionEvent event) throws Exception {
		cwflag = 1;
		ProductController.flag = 0;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/UpdateProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Update Product");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/**
	 * set comboBox of products Id
     * for Company Worker, that he can choose one of
     * the products and update/delete it.
	 */
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
	
	/**
	 * open "product info" window that the Company Worker can
	 * update the product by change it name, price, color, type
	 * @param event - Company Worker click "update" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openProductInfoWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		if(cmbPid.getValue() != null)
		{
			String pid = cmbPid.getValue();
			cwflag=0;
			int index = getProductIndext(pid);
			Product p = CatalogUI.products.get(index);
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/ProductForm.fxml").openStream());

			ProductController productController = loader.getController();		
			productController.loadProduct(p);
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Product Form");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
		else
		{
			cwflag = 1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/DidNotPickProduct.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Meesage");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	/**
	 * function to open "add new product" window
	 * @param event - Company Worker click "add new product" button 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void addNewProductWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		ProductController.flag = 0;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/AddProductForm.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Add Product");
			primaryStage.setScene(scene);		
			primaryStage.show();
		
	}
	
	/**
	 * function to open "remove product" window
	 * @param event - Company Worker click "remove product" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void removeProductWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		cwflag = 1;
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/RemoveProduct.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Remove Product");
		primaryStage.setScene(scene);		
		primaryStage.show();
	
}
	
	/**
	 * "getProductIndext" - search, find and return the product Id from combo-Box
	 * that the Company Worker choose to remove or update
	 * @param pId - gets the product ID to find it in the products list
	 * @return - return the Product id that the Company Worker want to remove/update
	 */
	public int getProductIndext(String pid) // return the index of the product the user want info of. 
	{
		int i;
		for (i = 0 ; i<CatalogUI.products.size() ; i++ )
		{
			if(CatalogUI.products.get(i).getpID() == Integer.valueOf(pid))
				break;
		}
		return i;
	}
	
	/**
	 * open "Company Worker Options" window
	 * @param event - Company Worker click "back" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void backToCompanyWorkerOptions(ActionEvent event) throws Exception { // open info window of the product the user want info of.

			cwflag=0;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CompanyWorkerOptions.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Company Worker Options");
			primaryStage.show();
	}
	
	/**
	 * logout - customer logout the system and "UserLogin" window open.
	 * @param event - the customer clicked "logout" Hyperlink 
	 * @throws Exception - if we can't load the fxml file
	 */
	public void logout(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * open "product info" window that the Company Worker can
	 * remove the product he choose
	 * @param event - Company Worker click "remove product" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void openProductInfoToDeleteWindow(ActionEvent event) throws Exception { // open info window of the product the user want info of.
		if(cmbPid.getValue() != null)
		{
			cwflag=0;
			String pid = cmbPid.getValue();
			int index = getProductIndext(pid);
			Product p = CatalogUI.products.get(index);
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/ProductDeleteForm.fxml").openStream());
			
			ProductController productController = loader.getController();		
			productController.loadProduct(p);
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Remove Product");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
		else
		{
			cwflag = 1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/DidNotPickProductToDelete.fxml").openStream());
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Error Meesage");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
	/**
	 * open "Sale Options" window for the Company Worker 
	 * @param event - Company Worker click "Update, add or remove sales" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void updateSales(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SalesOptions.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Sales Options");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * open "update or remove sale" window for the Company Worker 
	 * @param event - Company Worker click "update or remove sales" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void updateSalesInStore(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		ProductInSaleController.flag = 1;
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UpdateSalesInStore.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Sales Options");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * open "add sale" window for the Company Worker 
	 * @param event - Company Worker click "add sales" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void addSalesInStore(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		ProductInSaleController.flag = 3;
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/AddSale.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Add Sale");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
