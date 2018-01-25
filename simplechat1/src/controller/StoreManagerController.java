package controller;
import java.net.URL;
import java.util.ResourceBundle;
import boundery.StoreManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *Controller for the store manager permission with options of view report, add new account and logout options
 */
public class StoreManagerController implements Initializable {
	
	public static Integer storeID;
	public static boolean flag = false;

    @FXML
    private Button btnStoreManagerLogout; /* Button For Logout From The User Of Store Manager */

    @FXML
    private Button btnViewReport;         /* Button For View Report */

    @FXML
    private Button btnStoreManagerOpenNewAccount;  /* Button For Open New Account */

/* ----------------------------------- Open For Us The GUI Of the Store Manager -------------------------------------- */
    
	public void start(Stage primaryStage) throws Exception          			  /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/StoreManagerOptions.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
	    primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
/* ----------------------------------- Open For Us The GUI Of the Store Manager Report Window -------------------------------------- */	
	
	public void viewReportBtn(ActionEvent event) throws Exception //To open the view report option
	{
		Message msg = new Message(UserUI.user.getId(),"Store Manager - Want To Store Number And Address Of The Store");
		UserUI.myClient.accept(msg);
		while(StoreManagerUI.stores.size() == 0);
		Thread.sleep(200);
		
		UserUI.store = new Store();                             					/* Create New Object Of Store */
		UserUI.store.setStoreId(Integer.parseInt(StoreManagerUI.stores.get(0)));	/* Put In the New Object The Store_Id */
		UserUI.store.setStore_Address(StoreManagerUI.stores.get(1));                /* Put In the New Object The Store_Address */
		 
		((Node)event.getSource()).getScene().getWindow().hide();    			    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream()); 
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	
/* ----------------------------------- Open For Us The GUI Of the Store Manager - Open New Account -------------------------------------- */
	
	/**
	 * Open the add new account option and check the store manager store number
	 * @param event- click on open new account button
	 * @throws Exception if we can't load the fxml
	 */
	public void openNewAccountBtn(ActionEvent event) throws Exception //To open the add new account option
	{
		Message msg = new Message(UserUI.user.getId(), "Store manager want store number");
		UserUI.myClient.accept(msg);
		while (flag == false) {
			System.out.print(""); // DOES NOT RUN WITHOUT THIS LINE
		}
		flag = false;
		UserUI.store=new Store();
		UserUI.store.setStoreId(storeID);
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/AccountForm.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Account Card Form");
		primaryStage.show();
	}
	
/* ---------------------------------------- Logout From The GUI Of The Store Manager ---------------------------------------  */	
	
	/**
	 * logout by the store manager and "UserLogin" window open.
	 * @param event- click on logout button
	 * @throws Exception if we can't load the fxml
	 */
	public void logoutBtn(ActionEvent event) throws Exception //logout by the store manager
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 				/* change User status to DISCONNECTED in DB */
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 			/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 		/* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}

}
