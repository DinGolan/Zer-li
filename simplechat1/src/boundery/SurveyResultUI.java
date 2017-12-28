package boundery;

import java.util.Scanner;

import controller.SurveyController;
import controller.SurveyResultController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

public class SurveyResultUI extends Application{

	public static ClientConsole myClient;

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
		SurveyResultController aFrame = new SurveyResultController(); /* Create SurveyFrame */				  
		aFrame.start(arg0);
	}

}
