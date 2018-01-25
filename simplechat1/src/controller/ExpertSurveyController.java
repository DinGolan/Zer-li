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
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * controller for add conclusion for each customer that fill a survey
 * @author itait
 *
 */
public class ExpertSurveyController implements Initializable {
	@FXML
	private TextArea txt1; 
	@FXML
	private Button btnClose = null; /* button close for close product form */
	@FXML
	private Button btnAddConclusion = null; /* button close for close product form */
	@FXML
	private Button btnInfo = null; 
	@FXML
	private Button btnCloseError = null;	
	@FXML
	private Button btnCloseSuccessful = null;	

	@FXML
	private ComboBox cmbSurveyCustomerId;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbSurveyId;  /* ComboBox With List Of Product */
	
	private ArrayList<Integer> temp;
	private Message msg,msg2;
	public static boolean flag = false,flagError=false;
	private ObservableList<Integer> slist;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */
	public static String surveyId;
	public static String customerId;
	public static String errorMsg= null;

	/**
	 * initelize the combo box "cmbSurveyId" the survey id with all the survey id in the database
	 * initelize the combo box "cmbcustomerId" the survey id with all the customer id in the database
	 * if I in the error window I dont use initelize "flagError" responsibale for it
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(flagError ==false)
		{

		temp = new ArrayList<Integer>();
		msg = new Message(temp, "get all the survey");
		//UserUI.Id.clear();
		UserUI.myClient.accept(msg);
		while(SurveyResultController.flag == false)
		{
			System.out.print(" ");
		}

		flag = false;
		//CustomerServiceWorkerButton.flag =false;
		slist = FXCollections.observableArrayList(UserUI.Id);
		cmbSurveyId.setItems(slist); // initelize id combo box
		//---------------------------------------------------------customer----------------------------
		msg.setOption("get all the customerId");
		UserUI.myClient.accept(msg);
		//msg2 = new Message(temp, "get all the customerId");
		//UserUI.myClient.accept(msg2);
		while(SurveyResultController.flag2 == false)
		{
			System.out.print("SACA");
		}

		SurveyResultController.flag2 = false;
		//CustomerServiceWorkerButton.flag =false;
		slist = FXCollections.observableArrayList(UserUI.CId);
		cmbSurveyCustomerId.setItems(slist); // initelize id combo box
		}

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
	 * add survey conclusion to the DB for each customer we send the user and customer id,
	 * and we check if the conclusion is bigger than 0 if he is we put error msg and dont add this survey result,
	 * and we also check if the customer already fill this survey if he is we put error msg and dont add this survey result,
	 * if all the field is correct (we dont have error) we add the survey result to the database
	 * @param event
	 * @throws IOException
	 */
	public void addConclusion(ActionEvent event) throws IOException {
		ArrayList<String> i = new ArrayList<String>();
		i.add(Integer.toString(UserUI.Id.get(getItemIndex(cmbSurveyId))));//id
		i.add(Integer.toString(UserUI.CId.get(getItemIndex(cmbSurveyCustomerId))));
		i.add(txt1.getText());//conclusion
		
		if(txt1.getText().length()==0)
		{
			errorMsg = null;
			flagError =true;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/errorMsgNoConclusion.fxml").openStream());
			Scene scene = new Scene(root);	
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();

			return;
		}
		
		msg2 = new Message(i, "add surveyConclusion");	
		UserUI.myClient.accept(msg2);
		
		while(errorMsg == null)
		{
			System.out.print("");
		}
		
		if(errorMsg.compareTo("11") ==0)
		{
			errorMsg = null;
			flagError =true;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/errorCustomerExpert.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Error msg");
			primaryStage.show();
			 errorMsg = null;

		}
		else {
			errorMsg = null;
			flagError =true;
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
			root = loader.load(getClass().getResource("/controller/MsgSuccecfulExpert2.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setScene(scene);	
			primaryStage.setTitle("Successful add conclusion");
			primaryStage.show();
		}
		 errorMsg = null;


		

	}
	/**
	 * close this window and open the SurveyInfo window
	 * @param event
	 * @throws IOException
	 */
	public void openInfo(ActionEvent event)throws IOException  {

		surveyId = Integer.toString(UserUI.Id.get(getItemIndex(cmbSurveyId)));
		customerId= Integer.toString(UserUI.CId.get(getItemIndex(cmbSurveyCustomerId)));
		
		ArrayList<String> temp2 = new ArrayList<String>();
		temp2.add(ExpertSurveyController.surveyId);
		temp2.add(ExpertSurveyController.customerId);
		
		msg = new Message(temp2, "get info survey");
		UserUI.myClient.accept(msg);
		
		ExpertSurveyController.errorMsg = null;

		while(ExpertSurveyController.errorMsg == null)
		{
			System.out.print("");
		}
		
		if(ExpertSurveyController.errorMsg.compareTo("11") ==0)
		{
			try {
				check(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		
			ExpertSurveyController.errorMsg = null;
		
		
		
		
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyInfo.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Survey Info");
		primaryStage.show();	
		}
	}
	/**
	 * check if there is error msg if there is open error msg if not open SurveyInfo window
	 * @param event
	 * @throws IOException
	 */
	public void check(ActionEvent event)throws IOException {
		ExpertSurveyController.errorMsg = null;
		ExpertSurveyController.flagError =true;
		Pane root = null;
		Stage primaryStage = new Stage(); //Object present window with graphics elements
		FXMLLoader loader = new FXMLLoader(); //load object
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		root = loader.load(getClass().getResource("/controller/errorCustomerExpert.fxml").openStream());
		Scene scene = new Scene(root);		
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Error msg");
		primaryStage.show();

}
	
	/**
	 * close this window and open ExpertOptions window
	 * @param event
	 * @throws IOException
	 */

	public void close(ActionEvent event)throws IOException  {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Expert Options");
		primaryStage.show();	
	}
	/**
	 * close this error window and open the ExpertSurvey window 
	 * @param event
	 * @throws IOException
	 */
	public void CloseError(ActionEvent event) throws IOException {
		flagError = false;
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertSurvey.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Expert Conclusion");
		primaryStage.show();		

	}

}
