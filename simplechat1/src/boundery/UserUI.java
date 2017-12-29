package boundery;

import java.util.Scanner;
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
		myClient = new ClientConsole(IP, 5555);
        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		DataCompanyManagerController aFrame = new DataCompanyManagerController(); /* Create CatalogFrame */				  
		aFrame.start(arg0);
	}

}
