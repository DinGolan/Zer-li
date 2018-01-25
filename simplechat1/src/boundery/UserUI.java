package boundery;

import java.util.ArrayList;
import java.util.Vector;

import controller.UserController;
import entity.Message;
import entity.Store;
import entity.User;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

public class UserUI extends Application /* With This Class We Show the Product GUI */{
	
	public static Vector<User> users = new Vector<User>();
	public static ClientConsole myClient;
	public static User user = null;
	public static Store store;
	public static ArrayList<Integer> Id = new ArrayList<Integer>();
	public static ArrayList<Integer> CId = new ArrayList<Integer>();
	
	public static String IP;
	public static int Port;
	final public static int DEFAULT_PORT_For_Client = 5555;

	
	public static void main( String args[] ) throws Exception 
	{ 
        launch(args);		 
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{	
		UserController aFrame = new UserController();
		aFrame.start(arg0);	
	}
	
	@Override
	public void stop(){
		if(UserUI.user != null)
		{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); 						/* Change User status to DISCONNECTED in DB */
		}
	}
}