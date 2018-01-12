package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.CompanyManagerReportUI;
import boundery.StoreManagerReportUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManagerReportController implements Initializable {

	private int Integer_The_Option_You_Choose;
	private String String_The_Option_You_Choose;
	private static int itemIndex = 0; 		/* This Variable Need for the the Case - that we not choose any Option - So By Default it will Be The Index 0 Its Mean - One */
	public static int Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
	
	ObservableList<String> OptionList;
	
/* -------------------------  For The First Window Of Company Manager Report ----------------------------------- */	

	@FXML
	private ComboBox<String> cmbOptions;  				    /* ComboBox With List Of Option To See The Amount Of Store */
	
	@FXML
	private Button btnClick;
	
	@FXML
	private Button btnClose;
	
/* ------------------------------------- The First Option Is - To see 1 Store , The Second Option Is to see 2 Store --------------------------------- */	
	
	public void Button_To_See_One_Store_Or_Two_Store(ActionEvent event) throws Exception
	{
		String_The_Option_You_Choose = CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.get(getItemIndex_For_Company_Mangager_For_Option());
		String_The_Option_You_Choose = String_The_Option_You_Choose.substring(0,1);
		Integer_The_Option_You_Choose = Integer.parseInt(String_The_Option_You_Choose);
		if(Integer_The_Option_You_Choose == 1)
		{
			Flag_For_Return_Window_With_One_Store_Or_With_Two_Store = 1;
			((Node)event.getSource()).getScene().getWindow().hide();    			  /* Hiding primary window */
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_Only_One_Store.fxml").openStream());
			
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);		
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
			primaryStage.setScene(scene);		
			primaryStage.show();
		}
	}
	
/* -------------------------- Taking Store From The Combo Box of Store ------------------------------------ */	
	
	public int getItemIndex_For_Company_Mangager_For_Option()                                   	/* With this Method we Take User from the List of the Users at the ComboBox */
	{
		if(cmbOptions.getSelectionModel().getSelectedIndex() == -1)
			return itemIndex;
	
		return cmbOptions.getSelectionModel().getSelectedIndex();
	}
	
/* --------------------------------- Close the Store Manager Report Window ------------------------------------------------- */	 	
	
	public void closeCompanyManagerReportWindow(ActionEvent event) throws Exception   
	{ 
		CompanyManagerReportUI.stores_For_Company_Manager.clear();
		CompanyManagerReportUI.stores_For_Company_Manager_2.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		primaryStage.setScene(scene);		
		primaryStage.show();										   /* show catalog frame window */
	}
	
/* ----------------------------------------- Set The Combo Box Of Option's ----------------------------------- */		
	
	public void setOptionsComboBox()    								/* In this Method we Set the Stores at the ComboBox */
	{ 				
		CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.clear();
		ArrayList<String> Option_To_See_Amount_Of_Store = new ArrayList<String>();	 
		CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.add("1 - To See One Store");
		CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.add("2 - To See Two Store");
		for(int i = 0 ; i < CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.size() ; i++)
		{
			Option_To_See_Amount_Of_Store.add(CompanyManagerReportUI.Option_Of_See_One_Store_Or_To_Store_For_Company_Manager.get(i));
		}
		
		OptionList = FXCollections.observableArrayList(Option_To_See_Amount_Of_Store);
		cmbOptions.setItems(OptionList);
	}
	
/* -------------------------------- Initialized The ComboBox In the First Window - Of The Options ComboBox GUI ------------------------------- */		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setOptionsComboBox();
	}

}
