package boundery;

import controller.SurveyController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;

/**
 * class to save static variables that belong to the Survey
 */
public class SurveyUI extends Application {
	public static ClientConsole myClient;
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		SurveyController aFrame = new SurveyController(); /* Create SurveyFrame */				  
		aFrame.start(arg0);
	}


}
