package controller;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *  * controller for add survey result for each customer to the DB

 * @author itait
 *
 */
public class SurveyResultController implements Initializable{


	@FXML
	private ComboBox cmbAnswer1;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbAnswer2;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbAnswer3;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbAnswer4;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbAnswer5;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbAnswer6;  /* ComboBox With List Of Product */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	@FXML
	private Button btnAdd = null; /* button close for close product form */
	@FXML
	private Button btnCloseStore = null; /* button close for close product form */
	@FXML
	private Button btnCloseDate = null; /* button close for close product form */
	@FXML
	private Button btnCloseCustomer = null; /* button close for close product form */
	@FXML
	private Button btnCloseSuccessful = null;	

	@FXML
	private ComboBox cmbSurveyId= null;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbSurveyStore= null;  
	@FXML
	private ComboBox cmbSurveyCustomerId= null;  

	
	private ArrayList<Integer> temp;
	private Message msg,msg2;
	private ObservableList<Integer> slist;
	public static boolean flag = false,flag2=false,flagError=false;
	public static String errorMsg= null;
	
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */

	/**
	 * open SurveyResultFrame fxml
	 * @param event
	 * @throws Exception
	 */
	public void start(ActionEvent event) throws Exception     // With this Method we show the GUI of the Catalog 
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyResultFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Result");

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
			return itemIndex;
	
		return cmb.getSelectionModel().getSelectedIndex();
	}
	/**
	 * add survey result to the DB for each customer we send the user id the 6 answer the store id and customer id,
	 * and we check if The storeId is not correct if he is we put error msg and dont add this survey result,
	 * we also check if our date is not correct(between start to end) if he is we put error msg and dont add this survey result,
	 * and we also check if the customer already fill this survey if he is we put error msg and dont add this survey result,
	 * if all the field is correct (we dont have error) we add the survey result to the database
	 * @param event
	 * @throws IOException
	 */
	public void addSurveyResult(ActionEvent event) throws IOException {
		ArrayList<Integer> i = new ArrayList<Integer>();
		i.add(UserUI.Id.get(getItemIndex(cmbSurveyId)));
		i.add(getItemIndex(cmbAnswer1) +1);
		i.add(getItemIndex(cmbAnswer2) +1);
		i.add(getItemIndex(cmbAnswer3) +1);
		i.add(getItemIndex(cmbAnswer4) +1);
		i.add(getItemIndex(cmbAnswer5) +1);
		i.add(getItemIndex(cmbAnswer6) +1);
		i.add(getItemIndex(cmbSurveyStore) +1);
		i.add(UserUI.CId.get(getItemIndex(cmbSurveyCustomerId)));

		// add customer id----------------------------------
		
		//if(resulrId.contains(i.get(0))==true)
		//{
			//msg = new Message(i, "update surveyResult");
		//}
		//else {
			msg = new Message(i, "add surveyResult");
		//}
			UserUI.myClient.accept(msg);
			
			while(errorMsg == null)
			{
				System.out.print("");
			}
			
			if(errorMsg.compareTo("The storeId is not correct") ==0)
			{
				 errorMsg = null;
				flagError =true;
				Pane root = null;
				Stage primaryStage = new Stage(); //Object present window with graphics elements
				FXMLLoader loader = new FXMLLoader(); //load object
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/errorMsgStore.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();
			}
			else if(errorMsg.compareTo("Error your date not between start and end date of the survey")==0) {
				flagError =true;
				 errorMsg = null;
				Pane root = null;
				Stage primaryStage = new Stage(); //Object present window with graphics elements
				FXMLLoader loader = new FXMLLoader(); //load object
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/errorMsgDate.fxml").openStream());
				Scene scene = new Scene(root);		
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();
			}
			
			else if(errorMsg.compareTo("customer twice")==0) {
				flagError =true;
				 errorMsg = null;
				Pane root = null;
				Stage primaryStage = new Stage(); //Object present window with graphics elements
				FXMLLoader loader = new FXMLLoader(); //load object
				((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
				root = loader.load(getClass().getResource("/controller/errorMsgCustomer.fxml").openStream());
				Scene scene = new Scene(root);	
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();
			}
			
			else if(errorMsg.compareTo("succed!")==0) {
			     //flagError =false;
					flagError =true;
					 errorMsg = null;
					Pane root = null;
					Stage primaryStage = new Stage(); //Object present window with graphics elements
					FXMLLoader loader = new FXMLLoader(); //load object
					((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
					root = loader.load(getClass().getResource("/controller/MsgSuccecfulResult.fxml").openStream());
					Scene scene = new Scene(root);	
					scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
					primaryStage.setScene(scene);	
					primaryStage.setTitle("succed msg");
					primaryStage.show();
			}
			
	}
	
	/**
	 * close this window and open StoreWorkerOptions window
	 * @param event
	 * @throws IOException
	 */
	public void Close(ActionEvent event) throws IOException {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/StoreWorkerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Store Worker Options");

		primaryStage.show();		

	}

	/**
	 * initelize the combo box "cmbSurveyId" the survey id with all the survey id in the database
	 * initelize the combo box "cmbAnswer1"-6 the survey answer with num 1-5
	 * initelize the combo box "cmbcustomerId" the survey id with all the customer id in the database
	 * if I in the error window I dont use initelize "flagError" responsibale for it
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(flagError ==false)
		{
		temp = new ArrayList<Integer>();
		msg = new Message(temp, "get all the survey");
		//UserUI.Id.clear();
		UserUI.myClient.accept(msg);
		while(SurveyResultController.flag == false)
		{
			System.out.print("SACA");
		}

		flag = false;
		//CustomerServiceWorkerButton.flag =false;
		slist = FXCollections.observableArrayList(UserUI.Id);
		cmbSurveyId.setItems(slist); // initelize id combo box
		Integer[] answar = new Integer[] {1, 2, 3, 4, 5};
		Integer[] storeId = new Integer[] {1, 2, 3, 4};
		slist = FXCollections.observableArrayList(answar);
		cmbAnswer1.setItems(slist);
		cmbAnswer2.setItems(slist);
		cmbAnswer3.setItems(slist);
		cmbAnswer4.setItems(slist);
		cmbAnswer5.setItems(slist);	
		cmbAnswer6.setItems(slist);
		slist = FXCollections.observableArrayList(storeId);
		cmbSurveyStore.setItems(slist);
		
		
		//Integer[] tempCustoemr = new Integer[] {308155123, 308155147, 308155308, 308155555, 308155125};
		//slist = FXCollections.observableArrayList(tempCustoemr);
		//cmbSurveyCustomerId.setItems(slist);
		
		// get customer id--------------------------------------
		msg.setOption("get all the customerId");
		UserUI.myClient.accept(msg);
		//msg2 = new Message(temp, "get all the customerId");
		//UserUI.myClient.accept(msg2);
		while(SurveyResultController.flag2 == false)
		{
			System.out.print("SACA");
		}

		flag2 = false;
		//CustomerServiceWorkerButton.flag =false;
		slist = FXCollections.observableArrayList(UserUI.CId);
		cmbSurveyCustomerId.setItems(slist); // initelize id combo box
		}
		
		
	}
	/**
	 * close this error window and open the SurveyResultFrame window 
	 * @param event
	 * @throws IOException
	 */
	public void CloseError(ActionEvent event) throws IOException {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyResultFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Result");
		primaryStage.show();		

	}
	
	

}
