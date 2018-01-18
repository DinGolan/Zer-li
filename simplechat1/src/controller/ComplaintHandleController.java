package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintHandleController implements Initializable{
	public static boolean loadComplaintsFlag = false;
	public static boolean complaintFlag = false;
	public static boolean loadComplaintDetailsFlag=false;
	private static int itemIndex = 1; //INPROGRESS- if we didn't choose a status at the combobox
	
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
		if(loadComplaintDetailsFlag==true) // ב CLOSE לשנות ל-0
		{ //show complaint details	
			System.out.print(ComplaintUI.complaint);
			this.txtComplaintNumber.setText(String.valueOf(ComplaintUI.complaint.getComplaintNum()));
			this.txtComplaintUserId.setText(String.valueOf(ComplaintUI.complaint.getComplaintUserId()));
			this.txtComplaintDate.setText(String.valueOf(ComplaintUI.complaint.getComplaintDate()));
			this.txtComplaintOrderId.setText(String.valueOf(ComplaintUI.complaint.getComplaintOrderId()));
			this.txtComplaintAnswer.setText(ComplaintUI.complaint.getComplaintCompanyServiceWorkerAnswer());
			if(ComplaintUI.complaint.getComplaintDetails()!=null) //לבדוק
				this.txtComplaintReason.setText(ComplaintUI.complaint.getComplaintDetails());
			this.txtComplaintCompansationAmount.setText(String.valueOf(ComplaintUI.complaint.getComplaintCompansation()));
			listForStatusComboBox = FXCollections.observableArrayList(stat); 
			this.cmbComplaintStatus.setItems(FXCollections.observableArrayList(listForStatusComboBox)); //set the status to this user
			this.cmbComplaintStatus.setPromptText(String.valueOf(ComplaintUI.complaint.getComplaintStat()));
		}
	}
	
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
		for(Integer num : ComplaintUI.complaintsNumbers)
			complaintsNum.add(num);//לבדוק מקרה שאין לו תלונות בכלל
		System.out.println(complaintsNum);
		if(complaintsNum.get(0)==-1) //we didn't have complaint to handle for this customer service worker at DB
		{
			//((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			try {
				root = loader.load(getClass().getResource("/controller/ComplaintDontHaveMsg.fxml").openStream());
			} catch (IOException e) {
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
			//cmbComplaintForWorker=null;
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
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			loadComplaintDetailsFlag=true; //show complaint details by initialized
			root = loader.load(getClass().getResource("/controller/ComplaintAnswerForm.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Complaint answer form");
			primaryStage.show();					
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
		
		if(txtComplaintAnswer.getLength()>200) //enter complaint answer more then 200 characters
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
			ComplaintUI.complaint.setComplaintCompanyServiceWorkerAnswer(txtComplaintAnswer.getText()); 
			ComplaintUI.complaint.setComplaintCompansation(Double.parseDouble(txtComplaintCompansationAmount.getText()));
			ComplaintUI.complaint.setComplaintStat(Complaint.ComplaintStatus.valueOf(stat.get(getStatusIndex()))); //take the status
			//לבדוק אם הוא לא בחר סטטוס אם שם בטיפול אוטומטית
			Message msg = new Message(ComplaintUI.complaint, "Update complaint");	
			UserUI.myClient.accept(msg);
			//while(saveComplaintflag == false)
		//	{
			//	System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			//}
			
			loadComplaintDetailsFlag=false; //לבדוק אם כאן זה בסדר
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			if(ComplaintUI.complaint.getComplaintStat().equals(Complaint.ComplaintStatus.CLOSE))
				root = loader.load(getClass().getResource("/controller/UpdateComplaintCloseMsg.fxml").openStream()); //open other msg if you close this complaint
			else
				root = loader.load(getClass().getResource("/controller/UpdateComplaintMsg.fxml").openStream()); //open msg if you still handle this complaint
			ComplaintUI.complaint=null;
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Update complaint msg");
			primaryStage.show();						
		}					
	}
	
	public void EditOtherComplaint(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ComplaintForWorker.fxml").openStream());
		
		//מכועררררררררררררררררררררררררררררר
		ComplaintHandleController complaintHandleController = loader.getController();		
		complaintHandleController.loadHisComplaints(); //we are loading all the requested complaints for this customer service worker
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Complaints to handle");
		primaryStage.show();			
	}
	
	public void closeComplaintErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}

	public void closeComplaintHandleWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint form GUI
	{ 
		loadComplaintDetailsFlag=false; //לא בטוח שטוב פה
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
