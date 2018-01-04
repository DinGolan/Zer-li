package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


import boundery.ReportUI;
import entity.Message;
import entity.Report;
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
	private Report report;
	
	 @FXML
	private TextField txtQuarterDate;
	
    @FXML
	private TextField txtStoreID;

    @FXML
    private TextField txtStoreAddress;

    @FXML
    private TextField txtQuantityOfOrder;
    
    @FXML
    private TextField txtRevenueStore;
    
    @FXML
	private Button btnClose;

   
	
/* ----------------------- Method's For the Second Window GUI ------------------------ */
	
	public void loadStore(Store s) 					/* To load the User details to the text fields */
	{ 
		this.store = s;
		this.txtStoreID.setText(String.valueOf(store.getStoreId()));
		this.txtStoreAddress.setText((store.getStore_Address()));
		this.txtQuantityOfOrder.setText(String.valueOf(store.getQuantityOfOrders()));
		this.txtRevenueStore.setText(String.valueOf(store.getTotalRevenue()));
	}
	
	/*public void loadReport(Report r)
	{
		this.report = r;
		this.txtQuarterDate.setText(report.getQaurterReport());
	}*/
    
	public void closeQuarterlyRevenueReportWindow(ActionEvent event) throws Exception    /* To close the The Window of the Product GUI and Show The Catalog GUI again */
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
		
		
	}
}
