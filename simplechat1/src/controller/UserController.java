package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.UserUI;
import entity.Message;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import mypackage.ClientConsole;
import javafx.scene.control.TextArea;

public class UserController implements Initializable {

	private User u;
	public String user_id = null;
	public static boolean flag = false;

	@FXML
	private Label txtHeadLine;
	
	@FXML
    private Label Label_IP;
	
	@FXML
	private Label Label_Port;

	@FXML
	private TextField txtPort;

	@FXML
	private TextField txtIP;
	
    @FXML
	private Button btnIPAndPort;
    
    @FXML
	private Button btnCloseWindow;
    
    @FXML
    private TextArea txtErrorMassage_Wrong_Details;
	
	@FXML
	private Button btnLogin = null;

	@FXML
	private Button btnExit = null;

	@FXML
	private Button btnTryAgain = null;

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private Label lblName;
	
	@FXML
	private Label lblSerialNum;
	
	@FXML
    private Button Exit_From_Client;

	@FXML
	private Label Lablel_Not_Enter_The_Server_Allready;


	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controller/Enter_The_IP_And_Port.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Client IP - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void After_You_Enter_Your_Port_And_IP_Go_To_User_Login(ActionEvent event) throws Exception
	{	
		UserUI.IP = this.txtIP.getText();
		UserUI.Port = Integer.parseInt(this.txtPort.getText());
		UserUI.myClient = new ClientConsole(event,this.txtIP.getText(), Integer.parseInt(this.txtPort.getText())); 
		txtHeadLine.setTextFill(Color.GREEN.brighter());
		Label_IP.setTextFill(Color.GREEN.brighter());
		Label_Port.setTextFill(Color.GREEN.brighter());
		btnIPAndPort.setTextFill(Color.GREEN.brighter());
		btnCloseWindow.setTextFill(Color.GREEN.brighter());
		Lablel_Not_Enter_The_Server_Allready.setTextFill(Color.GREEN.brighter());
		Exit_From_Client.setTextFill(Color.GREEN.brighter());
	}
	
	public void Close_The_Window(ActionEvent event) throws Exception
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 		/* Hiding primary window */
		User_Login();
	}
	
	public void User_Login() throws Exception
	{
		Stage primaryStage = new Stage(); 				/* Object present window with graphics elements */
		Parent root = null;
		root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void tryAgainToTypeIP_OR_Port(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 						  /* Object present window with graphics elements */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/Enter_The_IP_And_Port.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Client IP - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void tryLoginUser(ActionEvent event) throws Exception /* To load the product details to the text fields */
	{
		u = new User();
		Parent  root = null;
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */

		UserUI.user = new User();
		u.setUserName(this.txtUserName.getText());
		u.setPassword(this.txtPassword.getText());

		Message msg = new Message(u.getUserName(), "UserStatus");
		UserUI.myClient.accept(msg);
		while (flag == false) {
			System.out.print(""); // DOES NOT RUN WITHOUT THIS LINE
		}
		flag = false;
		if (UserUI.user.getId().equals("Does Not Exist")) // user dos NOT exist
		{
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/UserDoesNotExist.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("error msg");
			primaryStage.show();
	} 
		
		else if (!UserUI.user.getPassword().equals(u.getPassword())) // insert the wrong password
		{			System.out.println("WrongPasswordMsg");
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/WrongPasswordMsg.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("error msg");
			primaryStage.show();
		} 
		else if (UserUI.user.getStatus().equals(User.UserStatus.DISCONNECTED)) // User DO login
		{
			String permission = null;
			msg.setMsg(UserUI.user.getId());
			msg.setOption("change User status to CONNECTED");
			UserUI.myClient.accept(msg); // change User status to CONNECTED in DB
			switch (UserUI.user.getPermission()) {
			case COMPANY_MANAGER:
				permission = "CompanyManagerOptions";
				break;
			case STORE_MANAGER:
				permission = "StoreManagerOptions";
				break;
			case EXPERT:
				permission = "ExpertOptions";
				break;
			case CUSTOMER_SERVICE_WORKER:
				permission = "CustomerServiceWorkerOptions";
				//CustomerServiceWorkerController.checkComplaintsFlag=true;
				break;
			case CUSTOMER:
				permission = "CustomerChooseStore";
				break;
			case DATA_COMPANY_MANAGER:
				permission = "DataCompanyManagerOptions";
				break;
			case COMPANY_WORKER:
				permission = "CompanyWorkerOptions";
				break;
			case STORE_WORKER:
				permission = "StoreWorkerOptions";
				break;
			}
			
			/*if (permission.equals("CustomerServiceWorkerOptions")) //customerServiceWorker
				permission="24HoursComplaints";
			else
				permission = "/controller/" + permission + ".fxml";*/
			
			permission = "/controller/" + permission + ".fxml";
			URL o = getClass().getResource(permission);
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(o);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu");
			primaryStage.show();
		}

		else {
			if (UserUI.user.getStatus().equals(User.UserStatus.CONNECTED)) // user all ready logged in
			{
				System.out.println("user all ready logged in");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/AllReadyLoginMsg.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("error msg");
				primaryStage.show();
			} else // user Blocked
			{
				System.out.println("user Blocked");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/BlockedMsg.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("error msg");
				primaryStage.show();
			}
		}
	}

	public void tryAgainLogin(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
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
	
	

	public void getExitBtn(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */
	{
		System.out.println("Exit From - Login form");
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product
	{
		
	}	
}
