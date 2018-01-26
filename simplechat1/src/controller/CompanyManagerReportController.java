package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerReportController implements Initializable {

	/**
	 * With This Flag I Know If The Company Manager Choose One Store Or Two Store .
	 */
	public static int Integer_The_Option_You_Choose;
	private String String_The_Option_You_Choose;
	
	/**
	 * Defult Index If I Not Choose Any Option From the Combo Box Of Option . 
	 */
	private static int itemIndex = 0; 
	
	/**
	 * Flag That Help Me To Know To Which GUI Window in Need To Return .
	 */
	public static int Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
	
	/**
	 * ObservableList That Help Me To Initalized The Combo Box Of Option That Have To The Company Manager .
	 */
	ObservableList<String> OptionList;
	
/* -------------------------  For The First Window Of Company Manager Report ------------------------------------------------------------------------ */	

	@FXML
	private ComboBox<String> cmbOptions;  				    
	
	@FXML
	private Button btnClick;
	
	@FXML
	private Button btnClose;
	
/* ------------------------------------- The First Option Is - To see 1 Store , The Second Option Is to see 2 Store --------------------------------- */	
	
	/**
	 * In This Function I Decide How Much Store I Want to Watch .
	 * @param event - When The Client Click On The Button The Parameter Start To Work .
	 * @throws Exception
	 */
	public void Button_To_See_One_Store_Or_Two_Store(ActionEvent event) throws Exception
	{
		String_The_Option_You_Choose = CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.get(getItemIndex_For_Company_Mangager_For_Option());
		String_The_Option_You_Choose = String_The_Option_You_Choose.substring(0,1);
		Integer_The_Option_You_Choose = Integer.parseInt(String_The_Option_You_Choose);
		if(Integer_The_Option_You_Choose == 1)
		{
			Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
			((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_Only_One_Store.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("----- Company Manager Report ---> Watch One Store -----");
			primaryStage.show();
			
		}
		else if(Integer_The_Option_You_Choose == 2)
		{
			Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 2;
			((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("----- Company Manager Report ---> Watch Two Store -----");
			primaryStage.show();
		}
	}
	
/* -------------------------- Taking Option From The Combo Box of Option ------------------------------------ */	
	
	/**
	 * In this Function I return My Choise From the ComboBox Of Option . 
	 * @return
	 */
	public int getItemIndex_For_Company_Mangager_For_Option()                                   
	{
		if(cmbOptions.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbOptions.getSelectionModel().getSelectedIndex();
	}
	
/* --------------------------------- Close the Company Manager Report Window -------------------------------- */	 	
	
	/**
	 * In This Function We Close One Of the Company Manager GUI .
	 * @param event - When The Client Click On The Button The Parameter Start To Work .
	 * @throws Exception
	 */
	public void closeCompanyManagerReportWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerUI.stores_For_Company_Manager.clear();
		CompanyManagerUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Option -----");
		primaryStage.show();										   
	}
	
/* ----------------------------------------- Set The Combo Box Of Option's ---------------------------------- */		
	
	/**
	 * In This Function We Set The Option That We Have in ComboBox . 
	 */
	public void setOptionsComboBox()    								
	{ 				
		CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.clear();
		ArrayList<String> Option_To_See_Amount_Of_Store = new ArrayList<String>();	 
		CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.add("1 - To See One Store");
		CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.add("2 - To See Two Store");
		for(int i = 0 ; i < CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.size() ; i++)
		{
			Option_To_See_Amount_Of_Store.add(CompanyManagerUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.get(i));
		}
		
		OptionList = FXCollections.observableArrayList(Option_To_See_Amount_Of_Store);
		cmbOptions.setItems(OptionList);
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Of The Options ComboBox GUI ---------------------------------------- */		
	
	/**
	 * Initialize One Of the GUI ---> Of The Comapny Manager .
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setOptionsComboBox();
	}

}
