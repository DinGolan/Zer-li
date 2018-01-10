package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundery.ComplaintUI;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintHandleController implements Initializable{
	//private Complaint c= new Complaint();
	//public static int complaintIndex=6;
	//public static boolean flag = false;
	public static boolean loadComplaintsFlag = false;
	public static boolean viewComplaintFlag = false;
	//private static int itemIndex = 0; //the default if he didn't choose an order take the first one 
	
	@FXML
	private TextField txtComplaintNum; //text field for the complaint number
	
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
		
	//@FXML
	//private Button btnComplaintTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnComplaintClose = null; //button close for the complaint form
	
	//@FXML
	//private Button btnComplaintMsgClose = null; //button close for the complaint error msg

	@FXML
	private Button btnComplaintSave = null; //button to save complaint answer
	
	@FXML
	private Button btnComplaintOpen = null; //button to open complaint details
	
	//@FXML
	//private Button btnComplaintHandleOther = null; //button to add a new other complaint
	
	@FXML
	private TextArea txtComplaintReason; //text area for the complaint details
	
	@FXML
	private TextArea txtComplaintAnswer; //text area for the complaint answer
	
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the complaint form
	{
	if(viewComplaintFlag == true)
		loadHisComplaints();
	}
	
	public void loadHisComplaints() //load his complaints
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		String cuurentCustomerServiceWorkerUserName=UserUI.user.getUserName();
		ArrayList<Integer> complaintsNum = new ArrayList<Integer>();
		Message msg = new Message(cuurentCustomerServiceWorkerUserName , "Get all complaints numbers for this customer service worker");
		UserUI.myClient.accept(msg); // get all complaints for this customer service worker from DB
		while(loadComplaintsFlag==false)
		{
			System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
		}
		loadComplaintsFlag=false;
		for(Integer c : ComplaintUI.complaintsNumbers)
			complaintsNum.add(c);
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
			cmbComplaintForWorker.setPromptText("Choose a complaint number");
			listForComplaintsWorkerComboBox = FXCollections.observableArrayList(complaintsNum); 
			cmbComplaintForWorker.setItems(FXCollections.observableArrayList(listForComplaintsWorkerComboBox)); //set the complaints to this user
			cmbComplaintForWorker.setDisable(false); //view the option to open combobox	
		}
	}
	
	/*public void viewComplaintDetails(ActionEvent event) throws Exception
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
	
	//יש בעיה מדפיס את זה בנוסף להודעה אחחרתת
	public int getItemIndex() //With this Method we Take the selected order number
	{
		if(cmbComplaintForWorker.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		/*{
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			root = loader.load(getClass().getResource("/controller/ComplaintComboboxMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
			return 0;
		}*/
			//return itemIndex;// לעשות שיקפיץ הודעה שהוא חייב לבחור משהו
		return cmbComplaintForWorker.getSelectionModel().getSelectedIndex();
	}
			
	/*public void saveComplaintButton(ActionEvent event) throws Exception //add new complaint to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		UserUI.complaint=new Complaint();
		//c.setComplaintNum(complaintIndex); //set the complaint num
		c.setComplaintStat(Complaint.ComplaintStatus.OPEN);
		c.setComplaintUserId(txtComplaintUserId.getText());
		
		LocalDate localDate = LocalDate.now(); //get the current date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=localDate.toString();
		Date parsed = format.parse(dateStr);
		java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
		c.setComplaintDate(dateSql); //set the date
		
		if(txtComplaintReason.getLength()>200) //enter complain reason more then 200 characters
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintReasonLengthMsg.fxml").openStream());
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		
		else 
		{ //enter 200 characters for the reason field
			c.setComplaintDetails(txtComplaintReason.getText()); 
		
			if(getItemIndex() == -1) //didn't choose order number from the combobox
			{ 
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/ComplaintComboboxMsg.fxml").openStream());
				Scene scene = new Scene(root);			
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();	
			}
			else //choose order at the combobox
			{
				c.setComplaintOrderId(ComplaintUI.ordersNumbers.get(getItemIndex())); //take the order number
				c.setComplaintServiceWorkerUserName(txtComplaintSreviceWorkerUserName.getText());
				Message msg = new Message(c, "Add new complaint");	
				//complaintIndex++; // for the next complaint number
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
	}*/
		
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
