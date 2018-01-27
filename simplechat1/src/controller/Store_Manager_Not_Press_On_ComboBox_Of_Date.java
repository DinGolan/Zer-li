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

/**
 * This Class Is For ---> If The Store Manager That See One Store Not Press On The ComboBox Of Store's Or Date's . 
 * @author dingo
 *
 */
public class Store_Manager_Not_Press_On_ComboBox_Of_Date implements Initializable{

	@FXML
	private TextArea txtErrMsg;

	@FXML
	private Button btnTryAgain;
	
	 /**
     * This Function Is For - Return To The GUI Of Store Manager With One Store .
     * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
     */
	public void TryAgain(ActionEvent event) throws Exception 
	{
	    ((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/StoreManagerReportForm.fxml").openStream());
			
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Store Manager ---> Watch His Report Store -----");
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
}
