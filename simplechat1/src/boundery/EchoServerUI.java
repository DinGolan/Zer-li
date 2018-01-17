package boundery;

import controller.EchoServerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class EchoServerUI extends Application 
{
	public static String Project_Scheme = "project";
	public static String username = "root";
	public static String password = "Dingolan203247697";
	
	public static void main(String[] args)  
	{
		
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception 
	{
		EchoServerController aFrame = new EchoServerController(); /* Create Data Company Manager frame */				  
		aFrame.start(arg0);
		
	}
	
	

}
