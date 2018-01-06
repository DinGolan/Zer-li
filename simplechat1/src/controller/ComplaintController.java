package controller;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import boundery.UserUI;
import entity.Complaint;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintController {
	private Complaint c= new Complaint();
	public static int complaintIndex=1;
	public static boolean flag = false;

	@FXML
	private TextField txtComplaintNum; //text field for the complaint number
	
	@FXML
	private TextField txtComplaintUserId; //text field for the complaint customer id
	
	@FXML
	private TextField txtComplaintStatus; //text field for the complaint status
	
	@FXML
	private TextField txtComplaintOrderId; //text field for the order id to complain
	
	@FXML
	private TextField txtComplaintSreviceWorkerUserName; //text field for the customer service worker Id
	
	@FXML
	private Button btnComplaintTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnComplaintClose = null; //button close for the complaint form
	
	@FXML
	private Button btnComplaintMsgClose = null; //button close for the complaint error msg

	@FXML
	private Button btnComplaintSave = null; //button to add a new complaint
	
	@FXML
	private Button btnComplaintAddOther = null; //button to add a new other complaint
	
	@FXML
	private DatePicker dpComplaintDate=new DatePicker(LocalDate.now());  //DatePicker with the end date of the subscription
	
	@FXML
	private TextArea txtComplaintReason; //text area for the complaint details
	
	public void loadComplaint() //view complaint num+status
	{
		this.txtComplaintNum.setText(String.valueOf(complaintIndex));
		//this.txtComplaintStatus.setText("OPEN");
	}
			
	public void saveComplaintButton(ActionEvent event) throws Exception //add new complaint to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		UserUI.complaint=new Complaint();
		c.setComplaintNum(complaintIndex); //set the complaint num
		c.setComplaintStat(Complaint.ComplaintStatus.OPEN);
			
		LocalDate localDate = dpComplaintDate.getValue();
		java.sql.Date dateSql=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=localDate.toString();
		Date parsed = format.parse(dateStr);
		dateSql = new java.sql.Date(parsed.getTime());
		
		if(!(localDate.equals(LocalDate.now()))) //if the date that selected for the complain isn't today
		{
			root = loader.load(getClass().getResource("/controller/ComplaintDateMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		else
			c.setComplaintDate(dateSql); //set the date
		
		if(txtComplaintReason.getLength()>200) //enter complain reason more then 200 characters
		{
			root = loader.load(getClass().getResource("/controller/ComplaintReasonLengthMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		else
			c.setComplaintDetails(txtComplaintReason.getText()); 
		
		c.setComplaintOrderId(Integer.parseInt(txtComplaintOrderId.getText()));
		c.setComplaintServiceWorkerUserName(txtComplaintSreviceWorkerUserName.getText());

		Message msg = new Message(c, "Add new complaint");	
		complaintIndex++; // for the next complaint number
		UserUI.myClient.accept(msg);
		
		while(flag == false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		flag = false;
		
		if(UserUI.complaint.getComplaintDetails().equals("Complaint already exist")) //this complaint already exist
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintExistMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else //start create complaint
		{
			if(UserUI.complaint.getComplaintDetails().equals("Order number to complain doesn't exist")) //check that this order to complain about is exist
			{
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/ComplaintOrderMsg.fxml").openStream());
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();			
			}
			else
			{
				if(UserUI.complaint.getComplaintDetails().equals("Customer id that complain doesn't exist")) //check that this customer that complain is exist
				{
					((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
					root = loader.load(getClass().getResource("/controller/ComplaintCustomerMsg.fxml").openStream());
					Scene scene = new Scene(root);			
					primaryStage.setScene(scene);	
					primaryStage.setTitle("Error msg");
					primaryStage.show();					
				}
				else
				{
					if(UserUI.complaint.getComplaintDetails().equals("Customer id and this order number doesn't match")) //check that this customer that complain and this order number are match
					{
						((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
						root = loader.load(getClass().getResource("/controller/ComplaintOrderCustomerMatchMsg.fxml").openStream());
						Scene scene = new Scene(root);			
						primaryStage.setScene(scene);	
						primaryStage.setTitle("Error msg");
						primaryStage.show();						
					}
					
					else 
					{
						if(UserUI.complaint.getComplaintDetails().equals("Customer service worker doesn't exist"))
						{
							((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
							root = loader.load(getClass().getResource("/controller/ComplaintCustomerServiceWorkerMsg.fxml").openStream());//עדיין לא קיים צריך להוסיף
							Scene scene = new Scene(root);			
							primaryStage.setScene(scene);	
							primaryStage.setTitle("Error msg");
							primaryStage.show();
						}
						else //all the details are good
						{
							((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
							root = loader.load(getClass().getResource("/controller/AddNewComplaintMsg.fxml").openStream());
							Scene scene = new Scene(root);			
							primaryStage.setScene(scene);	
							primaryStage.setTitle("New complaint msg");
							primaryStage.show();						
						}				
					}					
				}			
			}
		}
	} 
		
	public void addNewOtherComplaint(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ComplaintForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Complaint Form");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	public void closeComplaintErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}

	public void closeComplaintFormWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint form GUI
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/CustomerServiceWorkerOptions.fxml").openStream());	
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);					
		primaryStage.show(); //show customer service worker options window
		System.out.println("Exit from- Account card form");											
	}
}

