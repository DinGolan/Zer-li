package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerReportUI;
import boundery.StoreManagerReportUI;
import entity.Message;
import entity.Product;
import entity.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerReportController_Compare_Between_Two_Diffrent_Quarter implements Initializable {
	
	private Message msg;
	private int Store_ID_1;
	private int Store_ID_2;
	private Date Date_Quarter_Report_1;
	private Date Date_Quarter_Report_2;
	ObservableList<Product.ProductType> ProductTypeList_Store_1;
	ObservableList<Product.ProductType> ProductTypeList_Store_2;
	ObservableList<String> Product_Of_Store_1;
	ObservableList<String> Product_Of_Store_2;
	
	
	@FXML
    private TextField txtNumOfQuarter_1;

    @FXML
    private TextField txtStoreID_1;

    @FXML
    private TextField txtQuantityOfOrder_1;

    @FXML
    private TextField txtRevenuOfStore_1;

    @FXML
    private TextField txtNumberOfComplaint_1;

    @FXML
    private TextField txtNumberOfClientInSurvey_1;

    @FXML
    private TextField txtTotalAverageInSurvey_1;
    
    @FXML
    private ChoiceBox<Product.ProductType> CheckBox_Product_Type_1;

    @FXML
    private ChoiceBox<String> CheckBox_Quantity_Of_Product_Type_1;
    
    @FXML
    private TextField txtTotalAverageInSurvey_2;

    @FXML
    private TextField txtStoreID_2;

    @FXML
    private TextField txtNumOfQuarter_2;

    @FXML
    private TextField txtQuantityOfOrder_2;

    @FXML
    private TextField txtRevenuOfStore_2;

    @FXML
    private TextField txtNumberOfComplaint_2;

    @FXML
    private TextField txtNumberOfClientInSurvey_2;
    
    @FXML
    private ChoiceBox<Product.ProductType> CheckBox_Product_Type_2;

    @FXML
    private ChoiceBox<String> CheckBox_Quantity_Of_Product_Type_2;
    
    
    @FXML
    private Button btnClose;
	
	public void close_Window_Compare_Between_TWo_Different_Quarter(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportUI.Object_From_Comparing_For_Store_1.clear();
		CompanyManagerReportUI.Object_From_Comparing_For_Store_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}
	
	public void put_The_Details_Of_Store_One_On_GUI()
	{
		/* The Expected ArrayList<Object> --->
		   * Index 0 = First Store ID 
		   * Index 1 = Quarter Report - First Store 
		   * Index 2 = Quantity Of Order - First Store
		   * Index 3 = Type Of Product In Order - First Store
		   * Index 4 = Quantity Of Each Product Type In Order - First Store
		   * Index 5 = The Revenue Of Store - First Store
		   * Index 6 = The Number Of Complaint - First Store
		   * Index 7 = Number Of Client That Fill Survey - First Store
		   * Index 8 = Total Average Of Survey Answer - First Store 
		   * */
		
		this.txtStoreID_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(0)));
		this.txtNumOfQuarter_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(1)));
		this.txtQuantityOfOrder_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(2)));
		set_Check_Box_Product_Type_Of_Store_One();
		set_Check_Box_Quantity_Of_Product_Type_Of_Store_One();
		this.txtRevenuOfStore_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(5)));
		this.txtNumberOfComplaint_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(6)));
		this.txtNumberOfClientInSurvey_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(7)));
		this.txtTotalAverageInSurvey_1.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(8)));
	}
	
	@SuppressWarnings("unchecked")
	public void set_Check_Box_Product_Type_Of_Store_One()
	{
		ArrayList<Product.ProductType> Product_Type_Of_Store_One = new ArrayList<Product.ProductType>();
		ArrayList<Product.ProductType> temp_Product_Type_Of_Store_One = (ArrayList<Product.ProductType>)CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(3);	
		
		for(int i = 0 ; i < temp_Product_Type_Of_Store_One.size() ; i++)
		{
			Product_Type_Of_Store_One.add(temp_Product_Type_Of_Store_One.get(i));
		}
		
		ProductTypeList_Store_1 = FXCollections.observableArrayList(Product_Type_Of_Store_One);
		CheckBox_Product_Type_1.setItems(ProductTypeList_Store_1);
	}
	
	@SuppressWarnings("unchecked")
	public void set_Check_Box_Quantity_Of_Product_Type_Of_Store_One()
	{
		ArrayList<Product> Product_Of_Store_One = new ArrayList<Product>();
		ArrayList<String> String_Product_Of_Store_One = new ArrayList<String>();
		ArrayList<Product> temp_Product_Of_Store_One = (ArrayList<Product>)CompanyManagerReportUI.Object_From_Comparing_For_Store_1.get(4);	
		
		for(int i = 0 ; i < temp_Product_Of_Store_One.size() ; i++)
		{
			Product_Of_Store_One.add(temp_Product_Of_Store_One.get(i));
		}
		
		for(int i = 0 ; i < Product_Of_Store_One.size() ; i++)
		{
			String_Product_Of_Store_One.add(String.valueOf(Product_Of_Store_One.get(i).getQuantity()) + " ---> " + String.valueOf(Product_Of_Store_One.get(i).getpType()));
		}
		
		Product_Of_Store_1 = FXCollections.observableArrayList(String_Product_Of_Store_One);
		CheckBox_Quantity_Of_Product_Type_1.setItems(Product_Of_Store_1);
	}
	
	public void put_The_Details_Of_Store_Two_On_GUI()
	{
		/* The Expected ArrayList<Object> --->
		   * Index 0 = Second Store ID 
		   * Index 1 = Quarter Report - Second Store 
		   * Index 2 = Quantity Of Order - Second Store
		   * Index 3 = Type Of Product In Order - Second Store
		   * Index 4 = Quantity Of Each Product Type In Order - Second Store
		   * Index 5 = The Revenue Of Store - Second Store
		   * Index 6 = The Number Of Complaint - Second Store
		   * Index 7 = Number Of Client That Fill Survey - Second Store
		   * Index 8 = Total Average Of Survey Answer - Second Store 
		   * */
		this.txtStoreID_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(0)));
		this.txtNumOfQuarter_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(1)));
		this.txtQuantityOfOrder_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(2)));
		set_Check_Box_Product_Type_Of_Store_Two();
		set_Check_Box_Quantity_Of_Product_Type_Of_Store_Two();
		this.txtRevenuOfStore_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(5)));
		this.txtNumberOfComplaint_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(6)));
		this.txtNumberOfClientInSurvey_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(7)));
		this.txtTotalAverageInSurvey_2.setText(String.valueOf(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(8)));
	}
	
	@SuppressWarnings("unchecked")
	public void set_Check_Box_Product_Type_Of_Store_Two()
	{
		ArrayList<Product.ProductType> Product_Type_Of_Store_Two = new ArrayList<Product.ProductType>();
		ArrayList<Product.ProductType> temp_Product_Type_Of_Store_Two = (ArrayList<Product.ProductType>)CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(3);	
		
		for(int i = 0 ; i < temp_Product_Type_Of_Store_Two.size() ; i++)
		{
			Product_Type_Of_Store_Two.add(temp_Product_Type_Of_Store_Two.get(i));
		}
		
		ProductTypeList_Store_2 = FXCollections.observableArrayList(Product_Type_Of_Store_Two);
		CheckBox_Product_Type_2.setItems(ProductTypeList_Store_2);
	}
	
	@SuppressWarnings("unchecked")
	public void set_Check_Box_Quantity_Of_Product_Type_Of_Store_Two()
	{
		ArrayList<Product> Product_Of_Store_Two = new ArrayList<Product>();
		ArrayList<String> String_Product_Of_Store_Two = new ArrayList<String>();
		ArrayList<Product> temp_Product_Of_Store_Two = (ArrayList<Product>)CompanyManagerReportUI.Object_From_Comparing_For_Store_2.get(4);	
		
		for(int i = 0 ; i < temp_Product_Of_Store_Two.size() ; i++)
		{
			Product_Of_Store_Two.add(temp_Product_Of_Store_Two.get(i));
		}
		
		for(int i = 0 ; i < Product_Of_Store_Two.size() ; i++)
		{
			String_Product_Of_Store_Two.add(String.valueOf(Product_Of_Store_Two.get(i).getQuantity()) + " ---> " + String.valueOf(Product_Of_Store_Two.get(i).getpType()));
		}
		
		Product_Of_Store_2 = FXCollections.observableArrayList(String_Product_Of_Store_Two);
		CheckBox_Quantity_Of_Product_Type_2.setItems(Product_Of_Store_2);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Store_ID_1 = (int) CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(0);
		Store_ID_2 = (int) CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(0);
		Date_Quarter_Report_1 = (Date) CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(1);
		Date_Quarter_Report_2 = (Date) CompanyManagerReportUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(1);
		ArrayList<Object> ArrayList_Of_Field_To_Compare = new ArrayList<Object>(); 
		ArrayList_Of_Field_To_Compare.add(Store_ID_1);
		ArrayList_Of_Field_To_Compare.add(Store_ID_2);
		ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_1);
		ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_2);
		msg = new Message(ArrayList_Of_Field_To_Compare,"Company Manager - Compare Between Two Different Quarter");
		CompanyManagerReportUI.myClient.accept(msg);
		while(CompanyManagerReportUI.Object_From_Comparing_For_Store_1.size() == 0);
		while(CompanyManagerReportUI.Object_From_Comparing_For_Store_2.size() == 0);
		try 
		{
			Thread.sleep(200);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		put_The_Details_Of_Store_One_On_GUI();
		put_The_Details_Of_Store_Two_On_GUI();
	}
}
