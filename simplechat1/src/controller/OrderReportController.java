package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.ReportUI;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrderReportController implements Initializable{

	private Store store;
	private Message msg;
	
	 @FXML
	 private TextField txtStoreID;
	
	 @FXML
	 private BarChart<String, Integer> ChartOrderReport;

	 @FXML
	 private CategoryAxis X_ProductType;

	 @FXML
	 private NumberAxis Y_OrderSpecificStore;
	 
	 @FXML
	 private Button btnClose;
	 
	public void loadStore(Store s) 					/* To load the User details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
	public void closeOrderReportWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
	{ 
		ReportUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/ReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		Order temp_Order = new Order();                      /* Help me For take Only The Orders of the Store That I choose */
		orders.add(temp_Order);
		orders.get(0).setStoreId(1); 						 /* Integer.parseInt(this.txtStoreID.getText()) */
		msg = new Message(orders, "Take The Orders Of Specific Store");
		ReportUI.myClient.accept(msg);
		while(ReportUI.orders.size() == 0);
		PutAtTheChartAllTheOrders();
	}
	
	public void PutAtTheChartAllTheOrders()
	{
		int [] Count_In_Chart;
		ArrayList<Product.ProductType> product_Of_Specific_Store = new ArrayList<Product.ProductType>();   /* All the Product That We Order On Specific Store */
		ArrayList<Order> orders = new ArrayList<Order>();                          /* All The Orders That We Order On Specific Store */
		for(int i = 0 ; i < ReportUI.orders.size() ; i++)
		{
			orders.add(ReportUI.orders.get(i));
			for(int j = 0 ; j < orders.get(i).getProductsInOrder().size() ; j++)
			{
				if((product_Of_Specific_Store.contains(orders.get(i).getProductsInOrder().get(j).getpType())) == false)
				{
					product_Of_Specific_Store.add(orders.get(i).getProductsInOrder().get(j).getpType());
				}
			}
		}
		
		Count_In_Chart = new int[product_Of_Specific_Store.size()];
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		for(int i = 0 ; i < product_Of_Specific_Store.size() ; i++)
		{
			XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
			Chart.setName(String.valueOf(product_Of_Specific_Store.get(i)));
			setChart.add(Chart);
		}
		
		for(int i = 0 ; i < orders.size() ; i++)
		{
			for(int k = 0 ; k < orders.get(i).getProductsInOrder().size() ; k++)
			{
				for(int j = 0 ; j < product_Of_Specific_Store.size(); j++)
				{
					if(orders.get(i).getProductsInOrder().get(k).getpType().equals(product_Of_Specific_Store.get(j)) == true)
					{
						setChart.get(j).getData().add(new XYChart.Data<String, Integer>(String.valueOf(product_Of_Specific_Store.get(j)), ++(Count_In_Chart[j])));
					}
				}
			}
		}
		
		ChartOrderReport.barGapProperty();
		ChartOrderReport.getData().addAll(setChart);
	}
}
