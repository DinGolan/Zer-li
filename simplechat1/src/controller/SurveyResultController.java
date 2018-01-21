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
	private ComboBox cmbSurveyId= null;  /* ComboBox With List Of Product */
	@FXML
	private ComboBox cmbSurveyStore= null;  
	@FXML
	private ComboBox cmbSurveyCustomerId= null;  

	
	private ArrayList<Integer> temp;
	private Message msg,msg2;
	private ObservableList<Integer> slist;
	public static boolean flag = false,flag2=false;
	
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */

	
	public void start(ActionEvent event) throws Exception     // With this Method we show the GUI of the Catalog 
	{	
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyResultFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();
	    
	}
	
	public int getItemIndex(ComboBox cmb) /* With this Method we Take Product from the List of the Product at the ComboBox */
	{
		if(cmb.getSelectionModel().getSelectedIndex() ==-1)
			return itemIndex;
	
		return cmb.getSelectionModel().getSelectedIndex();
	}
	
	public void addSurveyResult() {
		ArrayList<Integer> i = new ArrayList<Integer>();
		i.add(UserUI.Id.get(getItemIndex(cmbSurveyId)));
		i.add(getItemIndex(cmbAnswer1) +1);
		i.add(getItemIndex(cmbAnswer2) +1);
		i.add(getItemIndex(cmbAnswer3) +1);
		i.add(getItemIndex(cmbAnswer4) +1);
		i.add(getItemIndex(cmbAnswer5) +1);
		i.add(getItemIndex(cmbAnswer6) +1);
		UserUI.store.setStoreId(4); // to delete
		i.add(UserUI.store.getStoreId());

		//if(resulrId.contains(i.get(0))==true)
		//{
			//msg = new Message(i, "update surveyResult");
		//}
		//else {
			msg = new Message(i, "add surveyResult");
		//}
		
			UserUI.myClient.accept(msg);
	}
	
	public void Close(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/StoreWorkerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();		

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		temp = new ArrayList<Integer>();
		/*msg = new Message(temp, "get all the survey");
		//UserUI.Id.clear();
		UserUI.myClient.accept(msg);
		while(SurveyResultController.flag == false)
		{
			System.out.print("SACA");
		}

		flag = false;
		//CustomerServiceWorkerButton.flag =false;
		slist = FXCollections.observableArrayList(UserUI.Id);
		cmbSurveyId.setItems(slist); // initelize id combo box*/
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
		
		// get customer id--------------------------------------
		msg2 = new Message(temp, "get all the customerId");
		UserUI.myClient.accept(msg2);
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
