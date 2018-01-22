package boundery;
import java.util.Vector;
import entity.Order;
import entity.Product;
import javafx.application.Application;
import javafx.stage.Stage;


public class OrderUI extends Application /* With This Class We Show the Product GUI */
{
	public static Vector<Order> orders = new Vector<Order>();
	public static Order order=null;
	public static Vector<Integer> ordersNumbers = new Vector<Integer>(); //all the orders numbers he can to cancel
	public static Vector<Product> productInOrder = new Vector<Product>(); //all the product for specific order
	
	@Override
	public void start(Stage arg0) throws Exception 
	{		

	}
	
}

