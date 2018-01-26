package controller;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import boundery.UserUI;
import entity.Message;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerReportController_Compare_Between_Two_Diffrent_Quarter implements Initializable {
	
	/**
	 * This Variable Helping To Transfer Message From the Client to the DB .
	 */
	private Message msg;
	
	/* Variable That Help's me To Transfer Details To the DB */
	private int Store_ID_1;
	private int Store_ID_2;
	private Date Date_Quarter_Report_1;
	private Date Date_Quarter_Report_2;
	
	/* Defult Variable That Help's me To Transfer Details To the DB */
	private int Store_1_Defult;
	private int Store_2_Defult;
	private Date Date_1_Defult;
	private Date Date_2_Defult;
	
	/**
	 * With This Variable I Can Fill The ComboBox With Product & Product Type .
	 */
	ObservableList<String> ProductTypeList_Store_1;
	ObservableList<String> ProductTypeList_Store_2;
	ObservableList<String> Product_Of_Store_1;
	ObservableList<String> Product_Of_Store_2;
	
	/* -------------------------  For The Window Of Company Manager Report - Compare Between Two Store's Or Two Different Quarter ----------------------------------- */		
	
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
    private ListView<String> ListViewQuantity_Store_1;
    
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
    private ListView<String> ListViewQuantity_Store_2;
    
    @FXML
    private Button btnClose;
	
    
    /**
     * In This Function I Close The GUI Of the Compare Between Two Store's Or Two Different Quarter . 
     * @param event - When The Client Click On the Button This Parameter Start To Work .
     * @throws Exception
     */
	public void close_Window_Compare_Between_TWo_Different_Quarter(ActionEvent event) throws Exception   
	{ 
		CompanyManagerUI.Object_From_Comparing_For_Store_1.clear();
		CompanyManagerUI.Object_From_Comparing_For_Store_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("----- Company Manager Report Form -----");
		primaryStage.show();										   
	}
	
	/**
	 * In This Function ---> We Put In The GUI Of Compare All The Details Of The First Store .
	 */
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
		
		this.txtStoreID_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(0)));
		this.txtNumOfQuarter_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(1)));
		this.txtQuantityOfOrder_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(2)));
		set_List_Of_Product_Type_Of_Store_One();
		this.txtRevenuOfStore_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(5)));
		this.txtNumberOfComplaint_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(6)));
		this.txtNumberOfClientInSurvey_1.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(7)));
		
		double Total_Avearge = Double.parseDouble(new DecimalFormat("##.###").format(CompanyManagerUI.Object_From_Comparing_For_Store_1.get(8)));
		this.txtTotalAverageInSurvey_1.setText(String.valueOf(Total_Avearge));
	}
	
	/**
	 * In This Function ---> We Set All The Details Of The Product of The First Store .
	 */
	@SuppressWarnings("unchecked")
	public void set_List_Of_Product_Type_Of_Store_One()
	{
		ArrayList<Product> Product_Of_Store_One = new ArrayList<Product>();       /* ArrayList Of Product */
		ArrayList<String> String_Product_Of_Store_One = new ArrayList<String>();  /* ArrayList Of The String Of The Product */
		ArrayList<Product> temp_Product_Of_Store_One = (ArrayList<Product>)CompanyManagerUI.Object_From_Comparing_For_Store_1.get(4);	
		
		for(int i = 0 ; i < temp_Product_Of_Store_One.size() ; i++)
		{
			Product_Of_Store_One.add(temp_Product_Of_Store_One.get(i));
		}
		
		for(int i = 0 ; i < Product_Of_Store_One.size() ; i++)
		{
			String_Product_Of_Store_One.add(String.valueOf(Product_Of_Store_One.get(i).getQuantity()) + " ---> " + String.valueOf(Product_Of_Store_One.get(i).getpType()));
		}
		
		Product_Of_Store_1 = FXCollections.observableArrayList(String_Product_Of_Store_One);
		ListViewQuantity_Store_1.setItems(Product_Of_Store_1);
	}
	
	/**
	 * In This Function ---> We Put In The GUI Of Compare All The Details Of The Second Store .
	 */
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
		this.txtStoreID_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(0)));
		this.txtNumOfQuarter_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(1)));
		this.txtQuantityOfOrder_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(2)));
		set_List_Of_Product_Type_Of_Store_Two();
		this.txtRevenuOfStore_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(5)));
		this.txtNumberOfComplaint_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(6)));
		this.txtNumberOfClientInSurvey_2.setText(String.valueOf(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(7)));
		
		double Total_Average = Double.parseDouble(new DecimalFormat("##.###").format(CompanyManagerUI.Object_From_Comparing_For_Store_2.get(8)));
		this.txtTotalAverageInSurvey_2.setText(String.valueOf(Total_Average));
	}
	
	/**
	 * In This Function ---> We Set All The Details Of The Product of The Second Store .
	 */
	@SuppressWarnings("unchecked")
	public void set_List_Of_Product_Type_Of_Store_Two()
	{
		ArrayList<Product> Product_Of_Store_Two = new ArrayList<Product>();        /* ArrayList Of Product */
		ArrayList<String> String_Product_Of_Store_Two = new ArrayList<String>();   /* ArrayList Of The String Of The Product */
		ArrayList<Product> temp_Product_Of_Store_Two = (ArrayList<Product>)CompanyManagerUI.Object_From_Comparing_For_Store_2.get(4);	
		
		for(int i = 0 ; i < temp_Product_Of_Store_Two.size() ; i++)
		{
			Product_Of_Store_Two.add(temp_Product_Of_Store_Two.get(i));
		}
		
		for(int i = 0 ; i < Product_Of_Store_Two.size() ; i++)
		{
			String_Product_Of_Store_Two.add(String.valueOf(Product_Of_Store_Two.get(i).getQuantity()) + " ---> " + String.valueOf(Product_Of_Store_Two.get(i).getpType()));
		}
		
		Product_Of_Store_2 = FXCollections.observableArrayList(String_Product_Of_Store_Two);
		ListViewQuantity_Store_2.setItems(Product_Of_Store_2);
	}
	
	/**
	 * In This Function ---> We Initialize The GUI Of The Compare Between Two Store's Or Two Different Quarter . 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		ArrayList<Object> ArrayList_Of_Field_To_Compare = new ArrayList<Object>();
		
		/* We Not Press On Any One */
		
		if(((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == false) 
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true)) 
				&& ((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == false) 
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true))) 
		{
			
			Store_1_Defult = 1;
			Date_1_Defult = Date.valueOf("2017-12-31");
			Store_2_Defult = 2;
			Date_2_Defult = Date.valueOf("2017-09-30");
			ArrayList_Of_Field_To_Compare.add(Store_1_Defult);
			ArrayList_Of_Field_To_Compare.add(Store_2_Defult);
			ArrayList_Of_Field_To_Compare.add(Date_1_Defult);
			ArrayList_Of_Field_To_Compare.add(Date_2_Defult);
		}
		
		/* We Press Only On the First Store */
		
		else if(((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == false) 
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true)) 
				&& ((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == false) 
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true)))
		{
			Store_ID_1 = (int) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(0);
			Date_Quarter_Report_1 = (Date) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(1);
			Store_2_Defult = 2;
			Date_2_Defult = Date.valueOf("2017-09-30");
			ArrayList_Of_Field_To_Compare.add(Store_ID_1);
			ArrayList_Of_Field_To_Compare.add(Store_2_Defult);
			ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_1);
			ArrayList_Of_Field_To_Compare.add(Date_2_Defult);
		}
		
		/* We Press Only On the Second Store */
		
		else if(((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == false) 
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == false && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true)) 
				&& ((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == false) 
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true)))
		{
			Store_1_Defult = 1;
			Date_1_Defult = Date.valueOf("2017-12-31");
			Store_ID_2 = (int) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(0);
			Date_Quarter_Report_2 = (Date) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(1);
			ArrayList_Of_Field_To_Compare.add(Store_1_Defult);
			ArrayList_Of_Field_To_Compare.add(Store_ID_2);
			ArrayList_Of_Field_To_Compare.add(Date_1_Defult);
			ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_2);
		}
		
		/* We Press On Both Of Them */
		
		else if(((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == true) 
				|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_1_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_1_For_Compare == false)) 
				&& ((CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == true) 
						|| (CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Store_2_For_Compare == true && CompanyManagerController_With_Two_Store.Flag_Enter_On_The_Combo_Box_Date_2_For_Compare == false)))
		{
			Store_ID_1 = (int) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(0);
			Store_ID_2 = (int) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(0);
			Date_Quarter_Report_1 = (Date) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_1.get(1);
			Date_Quarter_Report_2 = (Date) CompanyManagerUI.Help_To_Transfer_Object_From_Comparing_For_Store_2.get(1);
			 
			ArrayList_Of_Field_To_Compare.add(Store_ID_1);
			ArrayList_Of_Field_To_Compare.add(Store_ID_2);
			ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_1);
			ArrayList_Of_Field_To_Compare.add(Date_Quarter_Report_2);
		}
		
		msg = new Message(ArrayList_Of_Field_To_Compare,"Company Manager - Compare Between Two Different Quarter");
		UserUI.myClient.accept(msg);
		
		ArrayList_Of_Field_To_Compare.clear();
		
		while(CompanyManagerUI.Object_From_Comparing_For_Store_1.size() == 0);
		while(CompanyManagerUI.Object_From_Comparing_For_Store_2.size() == 0);
		
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
