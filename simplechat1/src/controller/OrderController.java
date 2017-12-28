package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.OrderUI;
import boundery.ProductUI;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderController {

	private Message msg;
	private Order order;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Default */
	
	ObservableList<String> userList;
	
/* -------------------------  For The First Window ----------------------------------- */	
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private ComboBox cmbCustomerOption;  /* ComboBox With List Of Users */
	
	@FXML
	private Button btnUserInfo = null; /* Button Of User Info */
	
/* ----------------------- Method's For the First Window GUI ------------------------ */
	
	public void start(Stage primaryStage) throws Exception          /* With this Method we show the GUI of the First Window */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/boundery/CustomerChoiseFrame.fxml"));
				
		Scene scene = new Scene(root);
		/* scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm()); */
		primaryStage.setTitle("Customer - Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public int getItemIndex()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbCustomerOption.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbCustomerOption.getSelectionModel().getSelectedIndex();
	}

	public void getExitBtn(ActionEvent event) throws Exception      /* With this Method we Exit from the Catalog */ 
	{
		System.out.println("Exit From - Tool");
		System.exit(0);			
	}
	
	
	public void setCustomerChoiseComboBox()      /* In this Method we Set the Product at the ComboBox */
	{ 				
		ArrayList<String> temp_Customer_Choise_List = new ArrayList<String>();	
		
		
		temp_Customer_Choise_List.add("Cancel Order");
		temp_Customer_Choise_List.add("Create Order");
		temp_Customer_Choise_List.add("Create Complaint");
		temp_Customer_Choise_List.add("View Profile");
		
		
		userList = FXCollections.observableArrayList(temp_Customer_Choise_List);
		cmbCustomerOption.setItems(userList);
	}
	
	public void initialize_One(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product 
	{
		ArrayList<String> str = new ArrayList<String>();  /* We Not Use In This Variable , Its Only For send Parameter To the Message Class */
		 
		msg = new Message(str, "1");

		OrderUI.myClient.accept(msg);
		/* while(OrderUI.orders.size() == 0); */ /* For The Window Of the Customer Choose We not Need This Line */
		setCustomerChoiseComboBox();
	}
	
/*------------------------ For - Cancel Order --------------------------------------- */
	
	public void OpenCancelOrderGUI(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/OrderWindow.fxml").openStream());
		
		loader.getController();		                                    /* ? - Not Sure */        
		this.loadOrder_For_Cancel(OrderUI.orders.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/UserInfoForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void loadOrder_For_Cancel(Order order) /* Loading Product */
	{
		this.loadOrder_For_Cancel_At_Window_Two(order);
	}
	
/*------------------------ For - Create Order --------------------------------------- */	
	
	public void OpenCreateOrderGUI(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/CatalogWindow.fxml").openStream());
		
		loader.getController();		                                    /* ? - Not Sure */        
		//this.loadUser_One(OrderUI.users.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/UserInfoForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void loadOrder_For_Create(Order order) /* Loading Product */
	{
		//this.loadOrder_For_Create_At_Window_Two(order);
	}
	
/*------------------------ For - Open Complaint --------------------------------------- */	
	
	public void OpenCreateComplaintGUI(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/CreateComplaintWindow.fxml").openStream());
		
		loader.getController();		                                    /* ? - Not Sure */        
		//this.loadUser_One(OrderUI.orders.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/UserInfoForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	public void loadOrder_For_Complaint(Complaint complaint) /* Loading Product */
	{
		//this.loadOrder_For_Complaint_At_Window_Two(complaint);
	}
	
	
/*------------------------ For - View Profile --------------------------------------- */	
	
	public void OpenViewProfileGUI(ActionEvent event) throws Exception        /* With this Method we Hide the GUI of the 'Choose User' and Show the GUI of the User that we Choose */
	{
		((Node)event.getSource()).getScene().getWindow().hide();    /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/boundery/ViewProfileWindow.fxml").openStream());
		
		loader.getController();		                                    /* ? - Not Sure */        
		//this.loadUser_One(OrderUI.orders.get(getItemIndex())); 	    /* In this Line We take the User that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		/* scene.getStylesheets().add(getClass().getResource("/boundery/UserInfoForm.css").toExternalForm()); */
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

/* ----------------------- Method's For the Second Window GUI ------------------------ */
	
	public void loadOrder_For_Cancel_At_Window_Two(Order o) 					/* To load the User details to the text fields */
	{ 
		this.order = o;
		//this.txtUserID.setText(user.getId());
		//this.txtUserName.setText(user.getUserName());		
		//this.txtUserPhone.setText(user.getPhone());
		//this.txtUserPassword.setText(user.getPassword());
		//this.cmbPremmission.setValue(user.getPermission());
		//this.cmbStatus.setValue(user.getStatus());
	}
	
/*------------------------ For - Cancel Order ---------------------------------------- */	
			
}
