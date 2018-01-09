package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import boundery.StoreManagerReportUI;
import entity.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class QuarterlyRevenueReportController implements Initializable{
	
	private Store store;
 
/* -------------------------  For The Window Of Quarterly Revenue Report ----------------------------------- */	
	
	@FXML
	private TextField txtYear;
	
	@FXML
    private TextField txtQuarterNum;
	
    @FXML
	private TextField txtStoreID;               /* Text Field Of the Store ID */

    @FXML
    private TextField txtStoreAddress;          /* Text Field Of the Store Address */

    @FXML
    private TextField txtQuantityOfOrder;       /* Text Field Of the Quantity Of Order At The Store */
    
    @FXML
    private TextField txtRevenueOfSpecificQuarter;
    
    @FXML
	private Button btnClose;                    /* Button For Close The Window */

   
	
/* --------------------------------- Loading Store To the Text Fields ------------------------------------------------- */
 
	public void loadStore(Store s) 					/* To load the Store details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
		this.txtStoreAddress.setText((store.getStore_Address()));
	}

/* --------------------------------- Loading Quarter Number To the Text Fields ------------------------------------------------- */	
	
	public void loadQuarter(String string) 					/* To load the Store details to the text fields */
	{ 
		this.txtQuarterNum.setText(string);
	}
 
/* --------------------------------- Close the Quarterly Revenue Report Window ------------------------------------------------- */		
	
	public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception
	{
		StoreManagerReportUI.stores.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}

/* --------------------------------- Initialize The Quarterly Revenue Report GUI ------------------------------------------------- */	 		
	
	@Override
	 public void initialize(URL location, ResourceBundle resources) 
	{
		int Year_Integer;
		int Month_Integer;
		String Month;
		String Year;
		String Full_Date_String;
		Date temp_Date_Quarter_Report;
		String Revenue_Of_Specific_Quarter = String.valueOf(StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.get(0));         /* The Revenue */
		String Amount_Of_Order_Of_Specific_Quarter = String.valueOf(StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.get(1)); /* The Amount Of Order */
		temp_Date_Quarter_Report = (Date)StoreManagerReportUI.Help_To_Transfer_Object_At_Revenue_Report.get(2);                             /* The Date */
		Full_Date_String = String.valueOf(temp_Date_Quarter_Report);
		Year = Full_Date_String.substring(0 , 4);
		Month = Full_Date_String.substring(5 , 7);
		Year_Integer = Integer.parseInt(Year);
		Month_Integer = Integer.parseInt(Month);
		
		this.txtYear.setText(String.valueOf(Year_Integer)); 					/* Set The Year */
		
		if(Month_Integer == 1 || Month_Integer == 2 || Month_Integer == 3)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(1));
		}
		if(Month_Integer == 4 || Month_Integer == 5 || Month_Integer == 6)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(2));
		}
		if(Month_Integer == 7 || Month_Integer == 8 || Month_Integer == 9)      /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(3));
		}
		if(Month_Integer == 10 || Month_Integer == 11 || Month_Integer == 12)   /* Set The Month */
		{
			this.txtQuarterNum.setText(String.valueOf(4));
		}
		
		this.txtQuantityOfOrder.setText(String.valueOf(Amount_Of_Order_Of_Specific_Quarter));
		this.txtRevenueOfSpecificQuarter.setText(Revenue_Of_Specific_Quarter);   /* Set the Revenue */
	} 
	
/* ------------------------------------------------------------------------------------------------------------------- */
	
}
