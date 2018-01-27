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
 * This Class Show Error Message If To The Customer Dont Have Account .
 * @author dingo
 *
 */
public class Customer_Dont_Have_Account_Error implements Initializable{


    @FXML
    private TextArea txtErrMsg;

    @FXML
    private Button btnBack;
	
	 /**
     * This Function Is For - Return To The GUI Of Customer Option .
     * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
     */
	public void Back(ActionEvent event) throws Exception 
	{
	    ((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CustomerOptions.fxml").openStream());
			
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Customer Option -----");
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
}
