package boundery;

import java.util.Scanner;
import java.util.Vector;
import controller.DataCompanyManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.User;

public class DataCompanyManagerUI extends Application /* With This Class We Show the Data Company Manager GUI */{
	
	public static Vector<User> users = new Vector<User>();
	public static ClientConsole myClient;
	
	public static void main( String args[] ) throws Exception
	{ 
//		System.out.println("Please enter the server IP");
//		Scanner scanner = new Scanner(System.in);
//		String IP = scanner.next(); 				 /* Enter Server IP */
		myClient = new ClientConsole("localhost", 5555);
        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		DataCompanyManagerController aFrame = new DataCompanyManagerController(); /* Create Data Company Manager frame */				  
		aFrame.start(arg0);
	}

}

