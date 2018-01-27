package boundery;

import java.util.Vector;
import entity.Store;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * class to save static variables that belong to the store
 */
public class StoreUI extends Application /* With This Class We Show the Catalog GUI */ {
	public static Vector<Store> stores = new Vector<Store>();
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		

	}
	
}