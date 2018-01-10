package controller;

import java.io.IOException;
import java.util.ArrayList;

import boundery.UserUI;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SurveyController {

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
		Parent root = FXMLLoader.load(getClass().getResource("/controller/SurveyFrame.fxml"));
				
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/boundery/CatalogFrame.css").toExternalForm());
		primaryStage.setTitle("Catalog Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();		
	}
	
	public void addSurvey() {
		temp = new ArrayList<String>();
		temp.add(this.txtQ1.getText());
		temp.add(this.txtQ2.getText());
		temp.add(this.txtQ3.getText());
		temp.add(this.txtQ4.getText());
		temp.add(this.txtQ5.getText());
		temp.add(this.txtQ6.getText());
		msg = new Message(temp, "add survey");

		UserUI.myClient.accept(msg);
	}
	
	public void Close(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/CustomerServiceWorkerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();		

	}
}
