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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SurveyInfoController implements Initializable{
	@FXML
	private TextArea txtA1; 
	@FXML
	private TextField txt1;
	@FXML
	private TextField txt2;
	@FXML
	private TextField txt3;
	@FXML
	private TextField txt4;
	@FXML
	private TextField txt5;
	@FXML
	private TextField txt6;
	@FXML
	private Button btnClose = null;
	
	private Message msg;
	public static boolean flag = false;
	private ObservableList<Integer> slist;
	ArrayList<String> temp;
	private static int itemIndex = 1; /* This Variable Need for the the Case - that we not choose any Product from the ComboBox , so we take the product that in Index 2 By Defualt */
	public static ArrayList<Integer> ans = new ArrayList<Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		temp = new ArrayList<String>();
		temp.add(ExpertSurveyController.surveyId);
		temp.add(ExpertSurveyController.customerId);
		
		msg = new Message(temp, "get info survey");
		UserUI.myClient.accept(msg);
		while(flag == false)
		{
			System.out.print(" ");
		}

		flag = false;
		txt1.setText(Integer.toString(ans.get(0)));
		txt2.setText(Integer.toString(ans.get(1)));
		txt3.setText(Integer.toString(ans.get(2)));
		txt4.setText(Integer.toString(ans.get(3)));
		txt5.setText(Integer.toString(ans.get(4)));
		txt6.setText(Integer.toString(ans.get(5)));

		
	}
	
	public void close(ActionEvent event)throws IOException  {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertSurvey.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}


}
