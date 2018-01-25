package controller;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the option of create new complaint
 */
public class ComplaintController implements Initializable{
	private Complaint c= new Complaint();
	public static boolean flag = false;
	public static boolean loadOrdersFlag = false; 
	
	@FXML
	private TextField txtComplaintUserId; //text field for the complaint customer id
	
	@FXML
	private TextField txtComplaintStatus; //text field for the complaint status
	
	@FXML
	private ComboBox<Integer> cmbComplaintOrderId=null; //combobox for the order id to complain that connected to this user
	ObservableList<Integer> listForOrderComboBox;
	
	@FXML
	private TextField txtComplaintSreviceWorkerUserName; //text field for the customer service worker Id
	
	@FXML
	private Button btnComplaintTryAgain = null; //button exit for the error msg
	
	@FXML
	private Button btnComplaintLoadOrders = null; //button to load orders to combobox
	
	@FXML
	private Button btnComplaintClose = null; //button close for the complaint form
	
	@FXML
	private Button btnComplaintMsgClose = null; //button close for the complaint error msg

	@FXML
	private Button btnComplaintSave = null; //button to add a new complaint
	
	@FXML
	private Button btnComplaintAddOther = null; //button to add a new other complaint
	
	@FXML
	private TextArea txtComplaintReason; //text area for the complaint details
	
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the complaint form
	{
	//if(loadOrdersFlag == true)
	//	loadOrdersComboBox();
	}

	/**
	 * load all the orders that connected to this customer id to a combobox and enable/disable 
	 * fields at the complaint and error msg if something isn't good
	 * @param event- click on load orders button after we entered user id
	 * @throws Exception
	 */
	public void loadOrdersComboBox(ActionEvent event) throws Exception
	{
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		String customerThatComplain=txtComplaintUserId.getText(); 
		ArrayList<Integer> ordersNum = new ArrayList<Integer>();
		Message msg = new Message(customerThatComplain , "Get all orders for this customer");
		UserUI.myClient.accept(msg); // get all orders for this user from DB
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
			//txtComplaintUserId.clear();
			txtComplaintReason.setDisable(true);
			cmbComplaintOrderId.setPromptText("Doesn't have orders");
			cmbComplaintOrderId.setDisable(true); //view the option to open combobox
			root = loader.load(getClass().getResource("/controller/ComplaintOrderMsg.fxml").openStream());
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();		
		}
		else if(ordersNum.get(0)==-2) //we didn't have this customer at DB
		{
			cmbComplaintOrderId.setPromptText("Press Load orders");
			cmbComplaintOrderId.setDisable(true); //view the option to open combobox
			txtComplaintUserId.setPromptText("User doesn't exist");
			txtComplaintReason.setDisable(true);
			root = loader.load(getClass().getResource("/controller/ComplaintCustomerMsg.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();	
		}
		else //the customer exist and have some orders
		{
			cmbComplaintOrderId.setPromptText("Press Load orders");
			txtComplaintUserId.setDisable(true);
			txtComplaintReason.setDisable(false);
			listForOrderComboBox = FXCollections.observableArrayList(ordersNum); 
			cmbComplaintOrderId.setItems(FXCollections.observableArrayList(listForOrderComboBox)); //set the orders to this user
			cmbComplaintOrderId.setDisable(false); //view the option to open combobox
		}
	}
	
	/**
	 * Take the selected order number from the combobox
	 * @return int -order number (index)
	 */
	public int getItemIndex() //With this Method we Take the selected order number
	{
		if(cmbComplaintOrderId.getSelectionModel().getSelectedIndex() == -1)
			return -1;
		return cmbComplaintOrderId.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Add new complaint to Zer-Li system- check if all the required data is correct and show error msg if not
	 * @param event- click on save button
	 * @throws Exception  if we can't load the fxml
	 */
	public void saveComplaintButton(ActionEvent event) throws Exception //add new complaint to Zer-Li system
	{		
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		ComplaintUI.complaint=new Complaint();
		c.setComplaintStat(Complaint.ComplaintStatus.OPEN);
		c.setComplaintUserId(txtComplaintUserId.getText());
		
		//casting for the date
		LocalDate localDate = LocalDate.now(); //get the current date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=localDate.toString();
		Date parsed = format.parse(dateStr);
		java.sql.Date dateSql = new java.sql.Date(parsed.getTime());
		c.setComplaintDate(dateSql); //set the date
		 
		LocalTime nowTime=LocalTime.parse(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())); //get the current time
		c.setComplaintTime(nowTime.toString());
		
		if((txtComplaintReason.getLength()>200)||(txtComplaintReason.getLength()<1)) //enter complain reason more then 200 characters or less then 10
		{
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/ComplaintReasonLengthMsg.fxml").openStream());
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		
		else 
		{ //enter until 200 characters for the reason field
			c.setComplaintDetails(txtComplaintReason.getText()); 
		
			if(getItemIndex() == -1) //didn't choose order number from the combobox
			{ 
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/ComplaintComboboxMsg.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();	
			}
			else //choose order at the combobox
			{
				c.setComplaintOrderId(ComplaintUI.ordersNumbers.get(getItemIndex())); //take the order number
				c.setComplaintServiceWorkerUserName(UserUI.user.getUserName()); //take the current connected user name
				Message msg = new Message(c, "Add new complaint");	
				//complaintIndex++; // for the next complaint number
				UserUI.myClient.accept(msg);
		
				while(flag ==false)
				{
					System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
				}
				flag =false;
		
				if(ComplaintUI.success.compareTo("Complaint already exist")==0) //this complaint already exist
				{
					((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
					root = loader.load(getClass().getResource("/controller/ComplaintExistMsg.fxml").openStream());
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setScene(scene);	
					primaryStage.setTitle("Error msg");
					primaryStage.show();	
				}
				else //start create complaint, all the details are good
				{
					((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
					root = loader.load(getClass().getResource("/controller/AddNewComplaintMsg.fxml").openStream());
					Scene scene = new Scene(root);	
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.setTitle("New complaint msg");
					primaryStage.show();						
				}					
			}
		} 
	}
	
	/**
	 * Show the GUI again of empty complaint form to add a new other complaint
	 * @param event- click on add other complaint button or try again
	 * @throws Exception  if we can't load the fxml
	 */
	public void addNewOtherComplaint(ActionEvent event) throws Exception //With this Method we show the GUI of the First Window
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Parent root = FXMLLoader.load(getClass().getResource("/controller/ComplaintForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Complaint Form");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	/**
	 * Close the The Window of the complaint error msg
	 * @param event- click on close button
	 * @throws Exception if we can't hide the fxml that loaded
	 */
	public void closeComplaintErrorMsgWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint error msg
	{ 
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window								
	}

	/**
	 * Close the The Window of the complaint form GUI and return the customer service worker menu
	 * @param event- click on close button
	 * @throws Exception if we can't load the fxml
	 */
	public void closeComplaintFormWindow(ActionEvent event) throws Exception  //To close the The Window of the complaint form GUI
	{ 
		//CustomerServiceWorkerController.checkComplaintsFlag=true;
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();						 //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); 					 //load object
		Pane root = loader.load(getClass().getResource("/controller/CustomerServiceWorkerOptions.fxml").openStream());	
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);			
		primaryStage.setTitle("Menu");
		primaryStage.show(); //show customer service worker options window											
	}
}
