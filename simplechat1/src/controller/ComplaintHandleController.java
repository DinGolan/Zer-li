package controller;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import boundery.CatalogUI;
import boundery.ComplaintUI;
import boundery.UserUI;
import entity.Complaint;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintHandleController implements Initializable{
	private Complaint c;
	//public static int complaintIndex=6;
	//public static boolean saveComplaintflag = false;
	public static boolean loadComplaintsFlag = false;
	public static boolean viewComplaintFlag = false;
	public static boolean complaintFlag = false;
	private static int itemIndex = 1; //INPROGRESS
	//private static int itemIndex = 0; //the default if he didn't choose an order take the first one 
	
	@FXML
	private TextField txtComplaintNumber; //text field for the complaint number
	
	@FXML
	private TextField txtComplaintUserId; //text field for the complaint customer id
	
	@FXML
	private TextField txtComplaintDate; //text field for the complaint date
	
	@FXML
	private TextField txtComplaintOrderId; //text field for the order number that the complaint about
	
	@FXML
	private TextField txtComplaintCompansationAmount; //text field for the Compansation amount
	
	@FXML
	private ComboBox <String> cmbComplaintStatus=null; //combobox to change the status of the complain
	ObservableList<String> listForStatusComboBox;
	
	@FXML
	private ComboBox <Integer> cmbComplaintForWorker=null; //combobox to view all the complaint for the specific customer service worker
	ObservableList<Integer> listForComplaintsWorkerComboBox;
		
	@FXML
	private Button btnComplaintTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnComplaintClose = null; //button close for the complaint form
	
	//@FXML
	//private Button btnComplaintMsgClose = null; //button close for the complaint error msg

	@FXML
	private Button btnComplaintSave = null; //button to save complaint answer
	
	@FXML
	private Button btnComplaintOpen = null; //button to open complaint details
	
	@FXML
	private Button btnComplaintHandleOther = null; //button to handle other complaint
	
	@FXML
	private TextArea txtComplaintReason; //text area for the complaint details
	
	@FXML
	private TextArea txtComplaintAnswer; //text area for the complaint answer
	
	ArrayList <String> stat=new ArrayList<String>(Arrays.asList("OPEN", "INPROGRESS", "CLOSE"));
	
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the complaint form
	{

	}
	
	/*public void start(ActionEvent event) throws Exception     // With this Method we show the GUI of the Catalog 
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ComplaintForWorker.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	    
	}*/
	
	public void loadHisComplaints() //load his complaints
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		String cuurentCustomerServiceWorkerUserName=UserUI.user.getUserName();
		System.out.println(cuurentCustomerServiceWorkerUserName);
		ArrayList<Integer> complaintsNum = new ArrayList<Integer>();
		Message msg = new Message(cuurentCustomerServiceWorkerUserName , "Get all complaints numbers for this customer service worker");
		UserUI.myClient.accept(msg); // get all complaints for this customer service worker from DB
		while(loadComplaintsFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		loadComplaintsFlag=false;
		for(Integer c : ComplaintUI.complaintsNumbers)
			complaintsNum.add(c);//לבדוק מקרה שאין לו תלונות בכלל
		if(complaintsNum.get(0)==-1) //we didn't have complaint to handle for this customer service worker at DB
		{
			//((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			try {
				root = loader.load(getClass().getResource("/controller/ComplaintDontHaveMsg.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();		
		}
		else 
		{
			//cmbComplaintForWorker.setPromptText("Choose a complaint number");
			listForComplaintsWorkerComboBox = FXCollections.observableArrayList(complaintsNum); 
			cmbComplaintForWorker.setItems(FXCollections.observableArrayList(listForComplaintsWorkerComboBox)); //set the complaints to this user
		}
	}
	
	public int getItemIndex() //With this Method we Take the selected complaint number
	{
		if(cmbComplaintForWorker.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		return cmbComplaintForWorker.getSelectionModel().getSelectedIndex();
	}
	
	public void viewComplaintDetails(ActionEvent event) throws Exception //open window with the complaint details
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		
		if(getItemIndex() == -1) //didn't choose complaint number from the combobox
		{ 
			//((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintToHandleComboboxMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else //choose complaint at the combobox
		{
			int complaintN=ComplaintUI.complaintsNumbers.get(getItemIndex()); //the requested complaint number
			Message msg = new Message(complaintN , "Get complaint details");
			UserUI.myClient.accept(msg); // get complaint details from DB
			while(complaintFlag==false)
			{
				System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			complaintFlag=false;
			//loadStatusFlag=true;
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintAnswerForm.fxml").openStream());
			
			//show complaint details
			//this.txtComplaintNumber.setText("may");// מכאן זה נופל
			this.c=ComplaintUI.complaint;
			this.txtComplaintNumber.setText(String.valueOf(c.getComplaintNum()));
			/*this.txtComplaintNumber.setText(String.valueOf(ComplaintUI.complaint.getComplaintNum()));
			this.txtComplaintUserId.setText(String.valueOf(ComplaintUI.complaint.getComplaintUserId()));
			this.txtComplaintDate.setText(String.valueOf(ComplaintUI.complaint.getComplaintDate()));
			this.txtComplaintOrderId.setText(String.valueOf(ComplaintUI.complaint.getComplaintOrderId()));*/
			listForStatusComboBox = FXCollections.observableArrayList(stat); 
			//this.cmbComplaintStatus.setItems(FXCollections.observableArrayList(listForStatusComboBox)); //set the status to this user
			//this.cmbComplaintStatus.setPromptText(String.valueOf(ComplaintUI.complaint.getComplaintStat()));
			
			//System.out.println(ComplaintUI.complaint);
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Complaint answer form");
			primaryStage.show();					
			//txtComplaintAnswer;
			//txtComplaintCompansationAmount;	
		}
	}
	
	public int getStatusIndex() //With this Method we Take the selected status
	{
		if(cmbComplaintStatus.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
		return cmbComplaintStatus.getSelectionModel().getSelectedIndex();
	}
	
	public void saveComplaintButton(ActionEvent event) throws Exception //update complaint to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		//ComplaintUI.complaint=new Complaint();
		
		if(txtComplaintAnswer.getLength()>200) //enter complain answer more then 200 characters
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintAnswerLengthMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		
		else 
		{ //enter 200 characters for the reason field
			c.setComplaintCompanyServiceWorkerAnswer(txtComplaintAnswer.getText()); 
			c.setComplaintCompansation(Double.parseDouble(txtComplaintCompansationAmount.getText()));
			c.setComplaintStat(Complaint.ComplaintStatus.valueOf(stat.get(getStatusIndex()))); //take the status
			Message msg = new Message(c, "Update complaint");	
			UserUI.myClient.accept(msg);
			/*while(saveComplaintflag == false)
			{
					System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			saveComplaintflag = false;*/
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			if(c.getComplaintStat().equals("INPROGRESS"))
				root = loader.load(getClass().getResource("/controller/UpdateComplaintMsg.fxml").openStream());
			else if(c.getComplaintStat().equals("CLOSE"))
				root = loader.load(getClass().getResource("/controller/UpdateComplaintCloseMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Update complaint msg");
			primaryStage.show();						
		}					
	}
	
	/*public void loadOrdersComboBox(ActionEvent event) throws Exception
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		String customerThatComplain=txtComplaintUserId.getText(); //אולי להפוך לגלובלי
		ArrayList<Integer> ordersNum = new ArrayList<Integer>();
		Message msg = new Message(customerThatComplain , "Get all orders for this customer");
		UserUI.myClient.accept(msg); // get all orders for this user from DB
		//while(ComplaintUI.ordersNumbers.size() == 0); //the waiting time is low להחליף  ל flag ואז לנסות לבטל את הכפתור של טעינת הזמנות
		while(loadOrdersFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		loadOrdersFlag=false;
		for(Integer o : ComplaintUI.ordersNumbers)
			ordersNum.add(o);
		if(ordersNum.get(0)==-1) //we didn't have orders to this customer at DB
		{
			//לבדוק הדפסות ונעילות של אנבל דיסאבל
			txtComplaintUserId.clear();
			cmbComplaintOrderId.setPromptText("Doesn't have orders");
			cmbComplaintOrderId.setDisable(true); //view the option to open combobox
			root = loader.load(getClass().getResource("/controller/ComplaintOrderMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();		
		}
		else if(ordersNum.get(0)==-2) //we didn't have this customer at DB
		{
			//txtComplaintUserId.clear();
			cmbComplaintOrderId.setPromptText("Press Load orders");
			cmbComplaintOrderId.setDisable(true); //view the option to open combobox
			txtComplaintUserId.setPromptText("User doesn't exist");
			root = loader.load(getClass().getResource("/controller/ComplaintCustomerMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else {
		cmbComplaintOrderId.setPromptText("Press Load orders");
		listForOrderComboBox = FXCollections.observableArrayList(ordersNum); 
		cmbComplaintOrderId.setItems(FXCollections.observableArrayList(listForOrderComboBox)); //set the orders to this user
		cmbComplaintOrderId.setDisable(false); //view the option to open combobox
		}
	}*/
			
		
	public void EditOtherComplaint(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ComplaintForWorker.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Complaints to handle");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public void closeComplaintErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}

	public void closeComplaintHandleWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint form GUI
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/CustomerServiceWorkerOptions.fxml").openStream());	
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Menu");
		primaryStage.show(); //show customer service worker options window
		//System.out.println("Exit from- Account card form");											
	}
}
