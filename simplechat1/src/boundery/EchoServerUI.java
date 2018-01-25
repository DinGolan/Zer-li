package boundery;

import controller.EchoServerController;
import controller.ThreadController;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * This Class - is The Boundary of The Echo Server 
 * @author dingo
 *
 */
public class EchoServerUI extends Application 
{		
	final public static int DEFAULT_PORT_For_Server = 5555;  /* The Number Of Port */
	
	
/**
 * In The Main Function We Create Thread That Run When We Start the To Use With the Server
 * @param args
 * @throws InterruptedException
 */
	public static void main(String[] args) throws InterruptedException  
	{
		launch(args);
		
		/* Create New Thread And Wait Until He Finish His Job */
		Thread thread = new Thread(new ThreadController());   
		thread.start();
		thread.join();
	}
	
	
/**
 * The Function 'Start' - This Function run the Application Of The Echo Server 
 */	
	@Override
	public void start(Stage arg0) throws Exception 
	{
		EchoServerController aFrame = new EchoServerController(); /* Create Data Company Manager frame */				  
		aFrame.start(arg0);
	}
}
