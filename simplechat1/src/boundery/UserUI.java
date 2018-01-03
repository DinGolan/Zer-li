package boundery;

import java.util.Scanner;
import java.util.Vector;
import controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Product;
import entity.User;
import controller.CatalogController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import java.util.Vector;
import controller.DataCompanyManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.User;

public class UserUI extends Application /* With This Class We Show the Product GUI */{
	
	public static Vector<User> users = new Vector<User>();
	public static ClientConsole myClient;
	public static User user;

	
	public static void main( String args[] ) throws Exception 
	{ 
		System.out.println("Please enter the server IP");
		Scanner scanner = new Scanner(System.in);
		String IP = scanner.next(); /* Enter Server IP */
		myClient = new ClientConsole(IP, 5555); //create connection with server
        launch(args);		 
	} 
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{		
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		
		Scene scene = new Scene(root);
		/* scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm()); */
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		
		primaryStage.show();	
	}
}
