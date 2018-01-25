package boundery;
import java.util.Vector;

import entity.Complaint;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * class to save static variables that belong to the complaint
 */
public class ComplaintUI extends Application 
{
	public static Vector<Integer> ordersNumbers = new Vector<Integer>();
	public static Vector<Integer> complaintsNumbers = new Vector<Integer>();
	public static Complaint complaint=null; //save the requested complaint 
	public static String success=null;

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
		
}

