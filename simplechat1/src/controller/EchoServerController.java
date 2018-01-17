package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.Timer;

import boundery.EchoServerUI;
import mypackage.EchoServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class EchoServerController implements Initializable {

    @FXML
	private Label txtHeadLine;
	
    @FXML
    private Label Label_Connected;

    @FXML
    private Label Label_Name_Of_Scheme;

    @FXML
    private Label Label_User;  

    @FXML
    private Label Label_Password;

    @FXML
    private TextField txtScheme;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;
    
    @FXML
    private Button btnConnect;
    
    @FXML
    private Button btnExit;
    
    @FXML
    private Button btnCloseWindow;
    
    @FXML
    private Button btnTryAgain = null;

    @FXML
    private TextArea txtErrMsg_For_Already_Get_In;
    
    @FXML
    private TextArea txtErrMsg_For_Password;
    
    @FXML
    private TextArea txtErrMsg_For_Scheme_Name;
    
    @FXML
    private TextArea txtErrMsg_For_User_Name;
    
	
	final public static int DEFAULT_PORT = 5555;
	private Connection con;								 /* A variable that Make The Connection with SQL DB */
	private boolean server_Is_Up = false;                /* Flag = If the Server Already Up */
	private EchoServer server;
	
	
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controller/EchoServerForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void TryToConnectToServer(ActionEvent event) throws Exception 
	{	
		
		Parent  root = null;
		Stage primaryStage = new Stage(); 			/* Object present window with graphics elements */
		
		if(server_Is_Up == false)                     /* We Not Up the Server Already */
		{
			if((this.txtScheme.getText().compareTo(EchoServerUI.Project_Scheme) != 0))
			{
				System.out.println("The Detail Of The Scheme Name Is Wrong ---> Try Again");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/Wrong_Scheme_Name.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Error Massage");
				primaryStage.show();
			}
			if(this.txtUser.getText().compareTo(EchoServerUI.username) != 0) 
			{
				System.out.println("The Detail Of The User Name Of The DB Is Wrong ---> Try Again");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/Wrong_User_Name_Of_DB.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Error Massage");
				primaryStage.show();
			}
			if(this.txtPassword.getText().compareTo(EchoServerUI.password) != 0)
			{
				System.out.println("The Detail Of The Password To The DB Is Wrong ---> Try Again");
				((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
				root = FXMLLoader.load(getClass().getResource("/controller/Wrong_Password_To_Connect_To_DB.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Error Massage");
				primaryStage.show();
			}
			else
			{
			    txtHeadLine.setTextFill(Color.GREEN.brighter());
				Label_Connected.setText("Connected");
				Label_Connected.setTextFill(Color.GREEN.brighter());
				Label_Name_Of_Scheme.setTextFill(Color.GREEN.brighter());
				Label_User.setTextFill(Color.GREEN.brighter());
				Label_Password.setTextFill(Color.GREEN.brighter());
				btnConnect.setTextFill(Color.GREEN.brighter());
				btnExit.setTextFill(Color.GREEN.brighter());
				btnCloseWindow.setTextFill(Color.GREEN.brighter());
				server_Is_Up = true;
				ConnectToServer(DEFAULT_PORT);
			}
		}
		else
		{
			System.out.println("You Already_Get_In_Into_The_Server ---> Try Again");
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/Allready_Get_In_Into_The_Server.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Error Massage");
			primaryStage.show();
		}
	}

	public void Close_The_Window(ActionEvent event)
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 		/* Hiding primary window */
	}
	
	public void ConnectToServer(int Number_Of_Port) 
	{
		int port = 0;

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (Exception e) {/* handle the error */}

		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + this.txtScheme.getText() , this.txtUser.getText() , this.txtPassword.getText());
		} 
		catch (SQLException e)
		{
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		if (Number_Of_Port != 0) 
		{
			port = Number_Of_Port;
		} 
		else 
		{
			port = DEFAULT_PORT;
		}
		
		server = new EchoServer(port);
		
		try 
		{
			server.listen(); // Start listening for connections
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

	public void tryAgainLogin(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 						  /* Object present window with graphics elements */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/EchoServerForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void getExitBtn(ActionEvent event) throws Exception /* With this Method we Exit from the Catalog */
	{
		System.out.println("Exit From - Login form");
		System.exit(0);
	}
	
	public EchoServer getServer()
	{
		return this.server;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}