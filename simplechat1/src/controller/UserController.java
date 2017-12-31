package controller;

import java.io.InputStream;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class UserController implements Initializable {

	private User u;
	public String user_id = null;
	public static boolean flag = false;

	@FXML
	private Button btnLogin = null;

	@FXML
	private Button btnExit = null;

	@FXML
	private Button btnTryAgain = null;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;
	
	@FXML
	private Label lblName;
	@FXML
	private Label lblSerialNum;


	public void getExitBtn(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */
	{
		System.out.println("Exit From - Tool");
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) // Initialized The ComboBox of the Product
	{
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
			primaryStage.setScene(scene);
			primaryStage.setTitle("error msg");
			primaryStage.show();
		} else // user exist
		if (!UserUI.user.getPassword().equals(u.getPassword())) // insert the wrong password
		{
			System.out.println("WrongPasswordMsg");
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/WrongPasswordMsg.fxml"));
			Scene scene = new Scene(root);
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
			System.out.println("user can do Login");
			switch (UserUI.user.getPermission()) {
			case COMPANY_MANAGER:
				permission = "CompanyManagetOptions";
				break;
			case STORE_MANAGER:
				permission = "StoreManagetOptions";
				break;
			case EXPERT:
				permission = "ExpertOptions";
				break;
			case CUSTOMER_SERVICE_WORKER:
				permission = "CustomerServiceWorkerOptions";
				break;
			case CUSTOMER:
				permission = "CustomerOptions";
				break;
			case DATA_COMPANY_MANAGER:
				permission = "DataCompanyManagetOptions";
				break;
			}
			permission = "/controller/" + permission + ".fxml";
			URL o = getClass().getResource(permission);
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(o);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(permission);
			primaryStage.show();
		}

		else {
			if (UserUI.user.getStatus().equals(User.UserStatus.CONNECTED)) // user all ready logged in
			{
				System.out.println("user all ready logged in");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/AllReadyLoginMsg.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("error msg");
				primaryStage.show();
			} else // user Blocked
			{
				System.out.println("user Blocked");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/BlockedMsg.fxml"));
				Scene scene = new Scene(root);
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
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
