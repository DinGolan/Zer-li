package boundery;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import entity.Message;
import entity.Store;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mypackage.ClientConsole;

public class UserUI extends Application /* With This Class We Show the Product GUI */{
	
	public static Vector<User> users = new Vector<User>();
	public static ClientConsole myClient;
	public static User user = null;
	public static Store store;
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	
	public static void main( String args[] ) throws Exception 
	{ 
		System.out.println("Please enter the server IP");
		Scanner scanner = new Scanner(System.in);
		//String IP = scanner.next(); /* Enter Server IP */
		String IP = "localhost"; /* Enter Server IP */
		myClient = new ClientConsole(IP, 5555); //create connection with server        
		launch(args);		 
	} 
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
	}
	
	@Override
	public void stop(){
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
	}
}
