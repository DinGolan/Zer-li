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

public class ExpertSurveyController implements Initializable {
	@FXML
	private TextArea txt1; 
	@FXML
	private Button btnClose = null; /* button close for close product form */
	@FXML
	private Button btnAddConclusion = null; /* button close for close product form */
	@FXML
	private Button btnInfo = null; /* button close for close product form */

	@FXML
	private ComboBox cmbSurveyId;  /* ComboBox With List Of Product */
	
	private ArrayList<Integer> temp;
	private Message msg,msg2;
	public static boolean flag = false;
	private ObservableList<Integer> slist;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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

	}
	public int getItemIndex(ComboBox cmb) /* With this Method we Take Product from the List of the Product at the ComboBox */
	{
		if(cmb.getSelectionModel().getSelectedIndex() ==-1)
			return itemIndex;
	
		return cmb.getSelectionModel().getSelectedIndex();
	}
	
	public void addConclusion() {
		ArrayList<String> i = new ArrayList<String>();
		i.add(Integer.toString(UserUI.Id.get(getItemIndex(cmbSurveyId))));//id
		i.add(txt1.getText());//conclusion
		msg2 = new Message(i, "add surveyConclusion");	
		UserUI.myClient.accept(msg2);

	}
	
	public void openInfo(ActionEvent event)throws IOException  {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}
	
	public void close(ActionEvent event)throws IOException  {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}
	
	

	
}
