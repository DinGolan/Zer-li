package controller;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import boundery.SurveyUI;
import controller.CatalogController;
import controller.SurveyController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mypackage.ClientConsole;
import entity.Message;
import entity.Product;
import entity.User;

public class SurveyResultController {


	@FXML
	private TextField txtQ1; /* text field for the product Id */
	@FXML
	private TextField txtQ2; /* text field for the product Id */
	@FXML
	private TextField txtQ3; /* text field for the product Id */
	@FXML
	private TextField txtQ4; /* text field for the product Id */
	@FXML
	private TextField txtQ5; /* text field for the product Id */
	@FXML
	private TextField txtQ6; /* text field for the product Id */
	
	@FXML
	private Button btnClose = null; /* button close for close product form */
	@FXML
	private Button btnAdd = null; /* button close for close product form */
	ArrayList<String> temp;
	Message msg;
	
	
	public void start(Stage primaryStage) throws Exception     /* With this Method we show the GUI of the Catalog */
	{	
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SurveyResultFrame.fxml"));
				
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public void addSurveyResult() {
		temp = new ArrayList<String>();
		temp.add(this.txtQ1.getText());
		temp.add(this.txtQ2.getText());
		temp.add(this.txtQ3.getText());
		temp.add(this.txtQ4.getText());
		temp.add(this.txtQ5.getText());
		temp.add(this.txtQ6.getText());
		msg = new Message(temp, "add survey");

		SurveyUI.myClient.accept(msg);
	}
	
	public void Close() {
		System.exit(0);			

	}
}
