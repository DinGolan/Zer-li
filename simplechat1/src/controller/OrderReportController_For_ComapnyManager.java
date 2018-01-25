package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Store;
import entity.Product.ProductType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/* For Company Manager */

public class OrderReportController_For_ComapnyManager implements Initializable {

	private Store store;
	private Message msg;
	private static String[] Type_Of_Products_In_Order = {"BOUQUET","ARRANGEMENT","VASE","BRIDAL_BOUQUET","FLOWER_CROWN","SWEET_BOUQUET","WREATH_FLOWERS"};
	
/* -------------------------  For The Window Of Order Report ----------------------------------- */		
	
	 @FXML
	 private TextField txtStoreID;                /* Text Field Of the Store ID */
	
	 @FXML
	 private TextField txtYear;                   /* Text Field Of The Year */

	 @FXML
	 private TextField txtNumberOfQuarter;        /* Text Field Of The Number Of Quarter */
	
	 @FXML
	 private BarChart<String, Integer> ChartOrderReport;    /* Bar Chart */

	 @FXML
	 private CategoryAxis X_ProductType;            /* Axis X of the - Bar Chart */

	 @FXML
	 private NumberAxis Y_OrderSpecificStore;       /* Axis Y of the - Bar Chart */
	 
	 @FXML
	 private Button btnClose;                       /* Button For Close The Window */
	 
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */	 
	 
	public void loadStore(Store s) 					/* To load the Store details to the text fields */
	{ 
		if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
		{
			if(CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else
			{
				this.txtStoreID.setText(String.valueOf(1));
			}
		}
		else if(CompanyManagerReportController.Integer_The_Option_You_Choose == 2)
		{
			if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else
			{
				this.txtStoreID.setText(String.valueOf(1));
			}
		}
	}
	
/* --------------------------------- Close the Order Report Window ------------------------------------------------- */	 	
	
	public void closeOrderReportWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerUI.stores_For_Company_Manager.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = null;
		if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 1)
		{
			root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_Only_One_Store.fxml").openStream());
		}
		else if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 2)
		{
			root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
		}
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}

/* --------------------------------- Initialize The Bar Chart Of the Order Report ------------------------------------------------- */	 	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		if(CompanyManagerReportController.Flag_For_Return_Window_With_One_Store_Or_With_Two_Store == 2)
		{
			 btnClose.setTranslateX(307);
		}
		int Year_Integer = 0;
		int Month_Integer = 0;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		if(CompanyManagerReportController.Integer_The_Option_You_Choose == 1)
		{
			if((CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false)
					|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == false && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == true) 
					|| (CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Date_Combo_Box == true && CompanyManagerController_With_Only_One_Store.Flag_Enter_On_The_Store_Combo_Box == false))
			{
				int temp_Store_Id = 1;
				temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
				CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
				CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Store_Id);
				CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
				Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
				Year = Full_Date_String.substring(0 , 4);
				Month = Full_Date_String.substring(5 , 7);
				Year_Integer = Integer.parseInt(Year);
				Month_Integer = Integer.parseInt(Month);
			}
			else
			{
				temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.get(1);                             /* The Date */
				Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
				Year = Full_Date_String.substring(0 , 4);
				Month = Full_Date_String.substring(5 , 7);
				Year_Integer = Integer.parseInt(Year);
				Month_Integer = Integer.parseInt(Month);
			}
		}
		else if(CompanyManagerReportController.Integer_The_Option_You_Choose == 2)
		{
			if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false)
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == true) 
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1 == false))
				{
					int temp_Store_Id = 1;
					temp_Date_Quarter_Report = Date.valueOf("2017-12-31");
					CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.clear();
					CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Store_Id);
					CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.add(temp_Date_Quarter_Report);
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
				}
				else
				{
					temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.get(1);                             /* The Date */
					Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
					Year = Full_Date_String.substring(0 , 4);
					Month = Full_Date_String.substring(5 , 7);
					Year_Integer = Integer.parseInt(Year);
					Month_Integer = Integer.parseInt(Month);
				}	
		}
		
		this.txtYear.setText(String.valueOf(Year_Integer)); 					/* Set The Year */
		
		if(Month_Integer == 1 || Month_Integer == 2 || Month_Integer == 3)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(1));
		}
		if(Month_Integer == 4 || Month_Integer == 5 || Month_Integer == 6)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(2));
		}
		if(Month_Integer == 7 || Month_Integer == 8 || Month_Integer == 9)      /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(3));
		}
		if(Month_Integer == 10 || Month_Integer == 11 || Month_Integer == 12)   /* Set The Month */
		{
			this.txtNumberOfQuarter.setText(String.valueOf(4));
		}
		
		
		ArrayList<Object> StoreID_And_Date_Of_Report = new ArrayList<Object>();
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager.get(1)); /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take The Orders Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.orders_For_Company_Manager.size() == 0)
		{
			if(CompanyManagerUI.orders_For_Company_Manager.size() == 0)
			{
				break;
			}
		}
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		Put_At_The_Chart_All_The_Orders();
	}
	
/* --------------------------------- Initialize The Order And the Product Type At the Bar Chart ------------------------------------------------- */	 		
	
	public void Put_At_The_Chart_All_The_Orders()
	{
		int [] Count_In_Chart;
		Product.ProductType product_Type;
		ArrayList<Product.ProductType> productType_Of_Specific_Order_Of_Specific_Store = new ArrayList<Product.ProductType>();   /* All the Product That We Order On Specific Store */
		ArrayList<Order> orders = new ArrayList<Order>();                         						   /* All The Orders That We Order On Specific Store */
		
		for(int i = 0 ; i < CompanyManagerUI.orders_For_Company_Manager.size() ; i++)                                                  /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			orders.add(CompanyManagerUI.orders_For_Company_Manager.get(i));
			
			for (Entry<ProductType, Integer> entry : orders.get(i).getProductInOrderType().entrySet()) 
			{
				if((productType_Of_Specific_Order_Of_Specific_Store.contains(entry.getKey())) == false)
				{
					product_Type = entry.getKey();
					productType_Of_Specific_Order_Of_Specific_Store.add(product_Type);
				}
			}
		}
		
		Count_In_Chart = new int[Type_Of_Products_In_Order.length];
		ArrayList<XYChart.Series<String,Integer>> setChart = new ArrayList<XYChart.Series<String,Integer>>();
		for(int i = 0 ; i < Type_Of_Products_In_Order.length ; i++)
		{
			XYChart.Series<String,Integer> Chart = new XYChart.Series<String,Integer>();
			Chart.setName(Type_Of_Products_In_Order[i]);
			setChart.add(Chart);
		}
		
		
		
		for(int Order_Index = 0 ; Order_Index < orders.size() ; Order_Index++)
		{
			for (Entry<ProductType, Integer> entry : orders.get(Order_Index).getProductInOrderType().entrySet()) 
			{
				for(int Product_Type_Index = 0 ; Product_Type_Index < productType_Of_Specific_Order_Of_Specific_Store.size(); Product_Type_Index++)
				{
					if(entry.getKey().equals(productType_Of_Specific_Order_Of_Specific_Store.get(Product_Type_Index)) == true)
					{
						for(int SetChart_Index = 0 ; SetChart_Index < setChart.size() ; SetChart_Index++)
						{
							if(String.valueOf(productType_Of_Specific_Order_Of_Specific_Store.get(Product_Type_Index)).compareTo(setChart.get(SetChart_Index).getName()) == 0)
								setChart.get(SetChart_Index).getData().add(new XYChart.Data<String, Integer>(setChart.get(SetChart_Index).getName(), ++(Count_In_Chart[SetChart_Index])));
						}	
					}
				}
			}
		}
		ChartOrderReport.getData().addAll(setChart);
	}
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
