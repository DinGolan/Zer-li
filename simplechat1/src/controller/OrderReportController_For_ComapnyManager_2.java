package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OrderReportController_For_ComapnyManager_2 implements Initializable 
{

	/**
	 * This Variable Help Me To Load The ComboBox Of Store's .
	 */
	private Store store;
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/**
	 * Variabel's That Help Me To Show The Product Type In The BarChart . 
	 */
	private static String[] Type_Of_Products_In_Order = {"BOUQUET","ARRANGEMENT","VASE","BRIDAL_BOUQUET","FLOWER_CROWN","SWEET_BOUQUET","WREATH_FLOWERS"};
	
/* -------------------------  For The Window Of Order Report - For Company Manager ----------------------------------- */		
	
	 @FXML
	 private TextField txtStoreID;               			/* Text Field Of the Store ID */
	
	 @FXML
	 private TextField txtYear;                   			/* Text Field Of The Year */

	 @FXML
	 private TextField txtNumberOfQuarter;        			/* Text Field Of The Number Of Quarter */
	
	 @FXML
	 private BarChart<String, Integer> ChartOrderReport;    /* Bar Chart */

	 @FXML
	 private CategoryAxis X_ProductType;            		/* Axis X of the - Bar Chart */

	 @FXML
	 private NumberAxis Y_OrderSpecificStore;       		/* Axis Y of the - Bar Chart */
	 
	 @FXML
	 private Button btnClose;                       		/* Button For Close The Window */
	 
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */	 
	 
	/**
	* In This Function We Load The ComboBox Of Store's .
	* @param s
	*/
	 public void loadStore(Store s) 					
	 { 
			if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
			{
				this.store = s;
				this.txtStoreID.setText(String.valueOf(store.getStoreId()));
			}
			else if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
					|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)) 
			{
				this.txtStoreID.setText(String.valueOf(2));
			}
	 }
	
/* --------------------------------- Close the Order Report Window ---------------------------------------------------------------- */	 	
	
	/**
	 * In This Function I close The GUI Of Order Report Of the Second Store .
	 * @param event - When Client Press On the Button This Parameter Start To Work . 
	 * @throws Exception
	 */
	public void closeOrderReportWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */										   
	}

/* --------------------------------- Initialize The Bar Chart Of the Order Report ------------------------------------------------- */	 	
	
	/** 
	 * In This Function We Initialize The GUI Of Order Report Of the Second Store .
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer = 0;
		int Month_Integer = 0;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		
		/* If the Company Manager Not Press On Specific Second Store  */
		if((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true))
		{
			int temp_Store_Id = 2;
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.clear();
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(temp_Store_Id);
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == false)
		{
			temp_Date_Quarter_Report = Date.valueOf("2017-09-30");
			CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.add(temp_Date_Quarter_Report);
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
		}
		/* This else Statement Is Not For Defult Value */
		else if(CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2 == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2 == true)
		{
			temp_Date_Quarter_Report = (Date)CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.get(1); /* The Date */
			Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
			Year = Full_Date_String.substring(0 , 4);
			Month = Full_Date_String.substring(5 , 7);
			Year_Integer = Integer.parseInt(Year);
			Month_Integer = Integer.parseInt(Month);
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
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.get(0)); /* The Store Id */
		StoreID_And_Date_Of_Report.add(CompanyManagerUI.Help_To_Transfer_Object_At_Order_Report_For_Company_Manager_2.get(1)); /* The Date Of the Report */
		
		msg = new Message(StoreID_And_Date_Of_Report,"Company Manager - Take The Orders Of Specific Store");
		UserUI.myClient.accept(msg);
		while(CompanyManagerUI.orders_For_Company_Manager_2.size() == 0)
		{
			if(CompanyManagerUI.orders_For_Company_Manager_2.size() == 0)
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
		ArrayList<Order> orders = new ArrayList<Order>();                         						   						 /* All The Orders That We Order On Specific Store */
		
		for(int i = 0 ; i < CompanyManagerUI.orders_For_Company_Manager_2.size() ; i++)                                          /* In This Loop We Initialize All the Orders At ArrayList Of Orders */                                             
		{
			orders.add(CompanyManagerUI.orders_For_Company_Manager_2.get(i));
			for (Entry<ProductType, Integer> entry : orders.get(i).getProductInOrderType().entrySet()) 
			{
				if((productType_Of_Specific_Order_Of_Specific_Store.contains(entry.getKey()) == false))
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
