package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import boundery.EchoServerUI;
import mypackage.EchoServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class EchoServerController implements Initializable {

	public static Connection con;								/* A variable that Make The Connection with SQL DB */
	private EchoServer server;                                  /* A variable That Help Me To Listen For Client After I Connect to DB */
	
	public  static boolean server_Is_Up = false;                /* Flag = If the Server Already Up */
	public static int Flag_Bad_Choise = 0;                      /* Parameter That Tell Me If The Connection Was Good Or Not */
	
	/* Parameter That I Type ---> To Connect The DB */
	public static String Scheme;
	public static String User_Name;
	public static String Password;
	
	/* Parameter To ---> Show The GUI */
	private Parent  root = null;
	private Stage primaryStage;
	
	/* ---------------------------------- Variable Of The Echo Server - GUI -------------------------------------------- */
	
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
    public TextField txtScheme;

    @FXML
    public TextField txtUser;

    @FXML
    public TextField txtPassword;
    
    @FXML
    private Button btnConnect;
    
    @FXML
    private Button btnExit;
    
    @FXML
    private Button btnCloseWindow;
    
    @FXML
    private TextArea txtErrMsg_For_Details;
    
    @FXML
    private TextArea txtErrMsg_For_Cant_Listen_For_Client;
    
    @FXML
    private TextArea txtErrMsg_For_Already_Get_In;
    
    
    /**
     * This Function Run The GUI Of The Echo Server .
     * @param primaryStage
     * @throws Exception
     */
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controller/EchoServerForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesignServerClient.css").toExternalForm()); 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server - Managment Tool");
		primaryStage.show();
	}
	
	/**
	 * In This Function We Try To Connect To The Server (To The DB) .
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void TryToConnectToServer(ActionEvent event) throws Exception 
	{	
		
		primaryStage = new Stage(); 			      /* Object present window with graphics elements */
		
		if(server_Is_Up == false)                     /* We Not Up the Server Already */
		{
			Scheme = this.txtScheme.getText();
			User_Name = this.txtUser.getText();
			Password = this.txtPassword.getText();
			
			txtHeadLine.setTextFill(Color.GREEN.brighter());
			Label_Connected.setText("Connected");
			Label_Connected.setTextFill(Color.GREEN.brighter());
			Label_Name_Of_Scheme.setTextFill(Color.GREEN.brighter());
			Label_User.setTextFill(Color.GREEN.brighter());
			Label_Password.setTextFill(Color.GREEN.brighter());
			btnConnect.setTextFill(Color.GREEN.brighter());
			btnCloseWindow.setTextFill(Color.GREEN.brighter());
			btnExit.setTextFill(Color.GREEN.brighter());
			server_Is_Up = true;
			ConnectToServer(event,EchoServerUI.DEFAULT_PORT_For_Server);
			
			/* We Change The Flag Of The Thread To = 1 Because I Want That He Will Start To Work */
			ThreadController.Flag_For_Thread = 1;   
			
		}
		else 
		{
			Flag_Bad_Choise = 1;
			server_Is_Up = false;
			System.out.println("You Already_Get_In_Into_The_Server ---> Try Again");
			((Node) event.getSource()).getScene().getWindow().hide(); 					/* Hiding primary window */
			root = FXMLLoader.load(getClass().getResource("/controller/Allready_Get_In_Into_The_Server.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Error Massage");
			primaryStage.show();
		}
	}

	/**
	 * In This Function We Close The GUI Of The Echo Server .
	 * @param event
	 */
	public void Close_The_Window(ActionEvent event)
	{
		((Node) event.getSource()).getScene().getWindow().hide(); 		/* Hiding primary window */
	}
	
	/**
	 * In This Function We Connect To The DB 
	 * @param event
	 * @param Number_Of_Port
	 * @throws Exception
	 */
	public void ConnectToServer(ActionEvent event,int Number_Of_Port) throws Exception 
	{
		int port = 0;

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (Exception e) {/* handle the error */}

		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost/" + Scheme , User_Name , Password);
		} 
		catch (SQLException e)  									  /* Show GUI Error */
		{
			Flag_Bad_Choise = 1;
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/controller/Wrong_Details_To_Connect_The_DB.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Error Massage");
			primaryStage.show();
		}
		if (Number_Of_Port != 0) 
		{
			port = Number_Of_Port;
		} 
		else 
		{
			port = EchoServerUI.DEFAULT_PORT_For_Server;
		}
		
		server = new EchoServer(port);
		
		try 
		{
			server.listen(); 											/* Start listening for connections */
		} 
		catch (Exception e) 
		{
			((Node) event.getSource()).getScene().getWindow().hide(); 	/* Hiding primary window */
			Stage primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/controller/Could_Not_Listen_To_Client_AnyMore.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Error Massage");
			primaryStage.show();
		}
	}

	/**
	 * In This Function We try To Login Again To Our GUI Of Echo Server .
	 * @param event
	 * @throws Exception
	 */
	public void tryAgainLogin(ActionEvent event) throws Exception /* With this Method we show the GUI of the First Window */
	{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); 						  /* Object present window with graphics elements */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/EchoServerForm.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Server - Managment Tool");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * In This Function We Exit From The Proggram .
	 * @param event
	 * @throws Exception
	 */
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