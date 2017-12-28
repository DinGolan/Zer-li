package boundery;

import java.util.Scanner;
import java.util.Vector;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Product;
import entity.User;
import controller.CatalogController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

public class UserUI extends Application /* With This Class We Show the Product GUI */{
	
	public static ClientConsole myClient;
	public static Vector<User> users = new Vector<User>();
	
	public static void main( String args[] ) throws Exception
	{ 
		System.out.println("Please enter the server IP");
		Scanner scanner = new Scanner(System.in);
		String IP = scanner.next(); /* Enter Server IP */
		myClient = new ClientConsole(IP, 5555);

        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		UserController aFrame = new UserController(); /* Create CatalogFrame */				  
		aFrame.start(arg0);
	}

}
