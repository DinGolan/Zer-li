package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CompanyManager_With_Two_Store_Not_Press_On_ComboBox_Of_Store_Or_Date implements Initializable { 

	@FXML
	private TextArea txtErrMsg;

	@FXML
	private Button btnTryAgain;
	
	public void TryAgain(ActionEvent event) throws Exception 
	{
	    ((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm_Window_With_Two_Store.fxml").openStream());
			
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager ---> Watch On Two Store -----");
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
}
