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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import mypackage.ClientConsole;
import javafx.scene.control.TextArea;

/**
 * controller for the User options: 
 * login , logout , 
 *
 */
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
	private ImageView ImgLogo;
	
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


	/**
	 * This Function Running The GUI Of the Client - For Connecting .
	 * @param primaryStage - Show The GUI Of the Client .
	 * @throws Exception - If the FXML Not Working .
	 */
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controller/Enter_The_IP_And_Port.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesignServerClient.css").toExternalForm()); 
		primaryStage.setTitle("Client IP - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * After We Login Into The Client All The GUI Label's Become To Green ---> Its mean The Connect Succssed By The Client .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Working Or If We Type On The Text Of the Port String And Not Integer .
	 */
	public void After_You_Enter_Your_Port_And_IP_Go_To_User_Login(ActionEvent event) throws Exception
	{	
		try 
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
		catch(NumberFormatException e)   
		{
			Stage primaryStage = new Stage();
			((Node) event.getSource()).getScene().getWindow().hide(); 
			Parent root = FXMLLoader.load(getClass().getResource("/controller/Wrong_Details_To_Connect_The_Client.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Error Massage");
			primaryStage.show();
		}
	}
	
	/**
	 * With This Function We - Close The GUI Of Connecting To the Client .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void Close_The_Window(ActionEvent event) throws Exception
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 				/* Hiding primary window */
		User_Login();
	}
	
	/**
	 * In This Function We Login By Specific User . 
	 * @throws Exception - If The FXML Not Work .
	 */
	public void User_Login() throws Exception
	{
		Stage primaryStage = new Stage(); 										/* Object present window with graphics elements */
		Parent root = null;
		root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * In This Function We Can Try Again To Start The Connection To The Client .
	 * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
	 */
	public void tryAgainToTypeIP_OR_Port(ActionEvent event) throws Exception 	
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 				/* Hiding primary window */
		Stage primaryStage = new Stage(); 						  				/* Object present window with graphics elements */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/Enter_The_IP_And_Port.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setTitle("Client IP - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * user try login the system , he need to insert User Name and
	 * password , we check if the User name exist and if the password is
	 * correct - if the two fields are correct, we check the permission
	 * of this user and open his options. in case user name doesn't exist
	 * or the password is wrong we pop an Error message.
	 * @param event - user click "login" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void tryLoginUser(ActionEvent event) throws Exception 				
	{
		u = new User();
		Parent  root = null;
		Stage primaryStage = new Stage(); 										/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 									/* load object */

		UserUI.user = new User();
		u.setUserName(this.txtUserName.getText());
		u.setPassword(this.txtPassword.getText());

		Message msg = new Message(u.getUserName(), "UserStatus");
		UserUI.myClient.accept(msg);
		while (flag == false) {
			System.out.print(""); 												/* DOES NOT RUN WITHOUT THIS LINE */
		}
		flag = false;
		if (UserUI.user.getId().equals("Does Not Exist")) 						/* user dos NOT exist */
		{
			((Node) event.getSource()).getScene().getWindow().hide(); 		    /* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/UserDoesNotExist.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("error msg");
			primaryStage.show();
	} 
		
		else if (!UserUI.user.getPassword().equals(u.getPassword())) 			/* insert the wrong password */
		{			
			System.out.println("WrongPasswordMsg");
			((Node) event.getSource()).getScene().getWindow().hide(); 			/* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/WrongPasswordMsg.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("error msg");
			primaryStage.show();
		} 
		else if (UserUI.user.getStatus().equals(User.UserStatus.DISCONNECTED))  /* User DO login */
		{
			String permission = null;
			msg.setMsg(UserUI.user.getId());
			msg.setOption("change User status to CONNECTED");
			UserUI.myClient.accept(msg); 										/* change User status to CONNECTED in DB */
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
				/* CustomerServiceWorkerController.checkComplaintsFlag=true; */
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
					
			permission = "/controller/" + permission + ".fxml";
			URL o = getClass().getResource(permission);
			((Node) event.getSource()).getScene().getWindow().hide(); 							/* Hiding primary window */
			root = FXMLLoader.load(o);
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Menu");
			primaryStage.show();
		}

		else {
			if (UserUI.user.getStatus().equals(User.UserStatus.CONNECTED)) 						/* User Allready logged in */
			{
				System.out.println("user all ready logged in");
				((Node) event.getSource()).getScene().getWindow().hide(); 						/* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/AllReadyLoginMsg.fxml"));
				Scene scene = new Scene(root);
				
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				
				primaryStage.setScene(scene);
				primaryStage.setTitle("error msg");
				primaryStage.show();
			} else // user Blocked
			{
				System.out.println("user Blocked");
				((Node) event.getSource()).getScene().getWindow().hide(); 						/* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/BlockedMsg.fxml"));
				Scene scene = new Scene(root);
				
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				
				primaryStage.setScene(scene);
				primaryStage.setTitle("error msg");
				primaryStage.show();
			}
		}
	}

	/**
	 * after error message pop, if the user click back to try
	 * login again we open "login" window
	 * @param event - user click "back" buttom
	 * @throws Exception - if we can't load the fxml file
	 */
	public void tryAgainLogin(ActionEvent event) throws Exception 	
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 	/* Hiding primary window */
		Stage primaryStage = new Stage(); 							/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 						/* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * user want logout the system, we change his status to "DISCONNECCT"
	 * in the Data Base and open "Login" window
	 * @param event - user click "logout" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void logout(ActionEvent event) throws Exception 					
	{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 											/* change User status to DISCONNECTED in DB */
		((Node) event.getSource()).getScene().getWindow().hide(); 				/* Hiding primary window */
		Stage primaryStage = new Stage(); 										/* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 									/* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
	
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	/**
	 * if the user want to exit from login window we close the system 
	 * @param event - user click "exit" button
	 * @throws Exception - if we can't load the fxml file
	 */
	public void getExitBtn(ActionEvent event) throws Exception 
	{
		System.out.println("Exit From - Login form");
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}	
}
