package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.ReportUI;
import entity.Message;
import entity.Order;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class CustomerComplaintStatusReportController implements Initializable {

	private Store store;
	private Message msg;
	private static String[] Month_Of_Quarter_One = {"January","Febuary","March"};
	private static String[] Month_Of_Quarter_Two = {"April","May","June"};
	private static String[] Month_Of_Quarter_Three = {"July","August","September"};
	private static String[] Month_Of_Quarter_Four = {"October","November","December"};
	
	 @FXML
	 private TextField txtStoreID;
	
	 @FXML
	 private BarChart<String, Integer> Complaint_BarChart;
	 
	 @FXML
	 private CategoryAxis X_Month;

	 @FXML
	 private NumberAxis Y_Complaint;
	 
	 @FXML
	 private Button btnClose;
	
	public void loadStore(Store s) 					/* To load the User details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
	}
	
	public void closeCustomerComplaintStatusReportWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
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
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Order temp_Order = new Order();                      /* Help me For take Only The Orders of the Store That I choose */
		orders.add(temp_Order);
		orders.get(0).setStoreId(1); 						 /* Integer.parseInt(this.txtStoreID.getText()) */
		msg = new Message(orders, "Take The Complaints Of Specific Store"); /* I take All the Orders Of Specific Store , And After That I Take All the Complaint Of All The Order Of the Specific Store */
		ReportUI.myClient.accept(msg);
		while(ReportUI.complaints.size() == 0);
		PutAtTheChartAllTheComplaints();
	}

	public void PutAtTheChartAllTheComplaints()
	{
		int [] Count_In_Chart;
		ArrayList<String> Month_Of_Complaint = new ArrayList<String>();   /* All the Product That We Order On Specific Store */
		ArrayList<String> Month_Of_Complaint_Without_Duplicate = new ArrayList<String>();
		                       						  
		for(int i = 0 ; i < ReportUI.complaints.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			Month_Of_Complaint.add(ReportUI.complaints.get(i).getComplaintMonth());
		}
		
		for(int i = 0 ; i < Month_Of_Complaint.size() ; i++)             /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			if((Month_Of_Complaint_Without_Duplicate.contains(Month_Of_Complaint.get(i))) == false) /* If Month_Of_Complaint_Without_Duplicate Not Contain */
				Month_Of_Complaint_Without_Duplicate.add(Month_Of_Complaint.get(i));
		}
		
		Count_In_Chart = new int[Month_Of_Complaint_Without_Duplicate.size()];
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		for(int i = 0 ; i < Month_Of_Complaint_Without_Duplicate.size() ; i++)
		{
			XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
			Chart.setName(Month_Of_Quarter_One[i]);
			setChart.add(Chart);
		}
		
		for(int i = 0 ; i < Month_Of_Complaint.size() ; i++)
		{
			for(int j = 0 ; j < Month_Of_Complaint_Without_Duplicate.size() ; j++)
			{
				if(Month_Of_Complaint.get(i).compareTo(Month_Of_Complaint_Without_Duplicate.get(j)) == 0) /* If Equals Than get In Into The If Statement */
				{
					for(int k = 0 ; k < setChart.size() ; k++)
					{
						if(setChart.get(k).getName().compareTo(Month_Of_Complaint_Without_Duplicate.get(j)) == 0)
							setChart.get(k).getData().add(new XYChart.Data<String, Integer>(Month_Of_Complaint_Without_Duplicate.get(j), ++(Count_In_Chart[j])));
					}
				}
			}
		}
		
		Complaint_BarChart.getData().addAll(setChart);
	}
	
}
