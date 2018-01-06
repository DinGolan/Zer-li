package controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundery.SurveyResultUI;
import boundery.SurveyUI;
import entity.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
	private ComboBox cmbSurveyId;  /* ComboBox With List Of Product */
	
	private ArrayList<Integer> temp;
	private Message msg;
	private ObservableList<Integer> slist;
	public static boolean flag = false;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */

	
	public void start(Stage primaryStage) throws Exception     /* With this Method we show the GUI of the Catalog */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SurveyResultFrame.fxml"));
				
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
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
		i.add(SurveyResultUI.Id.get(getItemIndex(cmbSurveyId)));
		i.add(getItemIndex(cmbAnswer1) +1);
		i.add(getItemIndex(cmbAnswer2) +1);
		i.add(getItemIndex(cmbAnswer3) +1);
		i.add(getItemIndex(cmbAnswer4) +1);
		i.add(getItemIndex(cmbAnswer5) +1);
		i.add(getItemIndex(cmbAnswer6) +1);

		//if(resulrId.contains(i.get(0))==true)
		//{
			//msg = new Message(i, "update surveyResult");
		//}
		//else {
			msg = new Message(i, "add surveyResult");
		//}
		
		SurveyResultUI.myClient.accept(msg);
	}
	
	public void Close() {
		System.exit(0);			

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		temp = new ArrayList<Integer>();
		msg = new Message(temp, "get all the survey");
		//SurveyResultUI.Id.clear();
		SurveyResultUI.myClient.accept(msg);
		while(SurveyResultController.flag == false)
		{
			System.out.print("");
		}
		flag = false;
		slist = FXCollections.observableArrayList(SurveyResultUI.Id);
		cmbSurveyId.setItems(slist); // initelize id combo box
		Integer[] answar = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		slist = FXCollections.observableArrayList(answar);
		cmbAnswer1.setItems(slist);
		cmbAnswer2.setItems(slist);
		cmbAnswer3.setItems(slist);
		cmbAnswer4.setItems(slist);
		cmbAnswer5.setItems(slist);	
		cmbAnswer6.setItems(slist);

	}
}
