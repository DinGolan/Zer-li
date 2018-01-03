package boundery;

import java.util.Scanner;
import controller.ComplaintController;
import entity.Complaint;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

public class ComplaintUI extends Application{ //With This Class We Show the Account GUI
	
	public static ClientConsole myClient;
	//public static Vector<Account> accounts = new Vector<Account>();
	public static Complaint complaint;
	
	public static void main( String args[] ) throws Exception
	{ 
		System.out.println("Please enter the server IP");
		Scanner scanner = new Scanner(System.in);
		String IP = "localhost"; //scanner.next(); /* Enter Server IP */
		myClient = new ClientConsole(IP, 5555);
        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		ComplaintController aFrame = new ComplaintController(); //Create ComplaintController			  
		aFrame.start(arg0);
	}
	
}
