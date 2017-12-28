package boundery;

import java.util.Scanner;
import java.util.Vector;
import controller.CatalogController;
import javafx.application.Application;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Product;
import entity.User;

public class SurveyUI extends Application {
	
	public static void main( String args[] ) throws Exception
	{ 

        launch(args);		
	} 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		
		SurveyController aFrame = new SurveyController(); /* Create SurveyFrame */				  
		aFrame.start(arg0);
	}


}
