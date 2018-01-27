package boundery;

import java.util.Vector;
import controller.DataCompanyManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.User;

/**
 * This Class Is the Boundary Of the Data Company Manager
 * @author dingo
 *
 */
public class DataCompanyManagerUI extends Application                  
{
	
	public static Vector<User> users = new Vector<User>();
	public static ClientConsole myClient;
	
	public static void main( String args[] ) throws Exception {}
	 
	
/**
*  The Function 'Start' - Run The Application of the Data Company Manager If I Not Running From UserUI
*/
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		DataCompanyManagerController aFrame = new DataCompanyManagerController(); 			  
		aFrame.start(arg0);
	}

}

