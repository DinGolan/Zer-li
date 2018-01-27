package boundery;
import java.util.Vector;
import entity.Product;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * class to save static variables that belong to the catalog
 */
public class CatalogUI extends Application /* With This Class We Show the Catalog GUI */ {
	public static Vector<Product> products = new Vector<Product>();
	public static Vector<Product> productsInSale = new Vector<Product>(); 
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		

	}
}
