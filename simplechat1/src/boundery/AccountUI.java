package boundery;
import java.util.Vector;

import entity.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

/**
 * class to save static variables that belong to the account
 */
public class AccountUI extends Application //With This Class We Show the Account GUI
{
	public static ClientConsole myClient;
	public static Account account;
	public static String success=null;
	public static Vector<String> customersId = new Vector<String>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}	
}

