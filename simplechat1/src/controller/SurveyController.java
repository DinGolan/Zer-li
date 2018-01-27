package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.UserUI;
import entity.Message;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for add survey to the DB
 * @author itait
 *
 */
public class SurveyController implements Initializable {

	@FXML
	private DatePicker dp = new DatePicker(LocalDate.now());
	//@FXML
	//private DatePicker dp = new DatePicker();
	
	@FXML
	private ComboBox cmb;  /* ComboBox With List Of Product */
	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	@FXML
	private Button btnCloseError = null; /* button close for close product form */
	@FXML
	private Button btnCloseError2 = null; /* button close for close product form */
	@FXML
	private Button btnCloseError3 = null; /* button close for close product form */

	@FXML
	private Button btnCloseSuccessful = null;	

	@FXML
	private Button btnAdd = null; /* button close for close product form */
	
	ArrayList<String> temp;
	Message msg;
	private ObservableList<Integer> slist;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */
	public static String errorMsg= null;
	public static boolean flagError=false;

	/**
	 * open SurveyFrame fxml
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception     /* With this Method we show the GUI of the Catalog */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SurveyFrame.fxml"));
				
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Add Survey");
		
		primaryStage.show();		
	}
	/**
	 * get the index of the item in the wanted comboBox
	 * @param cmb
	 * @return
	 */
	public int getItemIndex(ComboBox cmb) /* With this Method we Take Product from the List of the Product at the ComboBox */
	{
		if(cmb.getSelectionModel().getSelectedIndex() ==-1)
			return -1;
	
		return cmb.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * add survey to the datebase we send the store id and the end date we check if there is already
	 * a survey to this store and if there is we put error msg and dont add this survey
	 * and we check if the end date is less than today(the start date) we put error msg and dont add this survey
	 * if all the field is correct (we dont have error) we add the survey to the database
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void addSurvey(ActionEvent event) throws IOException {
		temp = new ArrayList<String>();
		
		temp.add((Integer.toString(getItemIndex(cmb)+1))); // store id
		LocalDate localDate = dp.getValue();
		if(localDate == null || getItemIndex(cmb)==-1)
		{
			errorMsg = null;
			flagError =true;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/errorNoComboSurvey.fxml").openStream());
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
			return;
		
		}
		System.out.println(localDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate today = LocalDate.now();
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  		

  		String formattedString = localDate.format(formatter);
	
		temp.add(formattedString); // end date
		msg = new Message(temp, "add survey");

		UserUI.myClient.accept(msg);
		
		//-----------------why!------------------------------
		while(errorMsg == null)
		{
			System.out.print("");
		}
		
		if(errorMsg.compareTo("date between error") ==0)
		{
			errorMsg = null;
			flagError =true;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/errorMsgDateBetween.fxml").openStream());
			Scene scene = new Scene(root);		
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		else if(errorMsg.compareTo("error store have survey")==0) {
			flagError =true;
			 errorMsg = null;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/errorMsgStoreNoSurvey.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
		}
		
		else if(errorMsg.compareTo("succes survey")==0) {
		     //flagError =false;
				flagError =true;
				 errorMsg = null;
				Pane root = null;
				Stage primaryStage = new Stage(); //Object present window with graphics elements
				FXMLLoader loader = new FXMLLoader(); //load object
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/MsgSuccecfulAddSurvey.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("succed msg");
				primaryStage.show();
		}

		
		
	}
	/**
	 * close this window and open CustomerServiceWorkerOptions window
	 * @param event
	 * @throws IOException
	 */
	public void Close(ActionEvent event) throws IOException {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerServiceWorkerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Customer Service Worker Options");
		primaryStage.show();		

	}
	/**
	 * close this error window and open the SurveyFrame window 
	 * @param event
	 * @throws IOException
	 */
	public void CloseError(ActionEvent event) throws IOException {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.setTitle("Add Survey");
		primaryStage.show();		

	}
	/**
	 * initelize the combo box "cmb" the store id (num 1,2,3,4) 
	 * if I in the error window I dont use initelize "flagError" responsibale for it
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(flagError ==false)
		{
		Integer[] storeID = new Integer[] {1, 2, 3, 4};
		slist = FXCollections.observableArrayList(storeID);
		cmb.setItems(slist);
		flagError =true;
		}
		
		
	}
}
