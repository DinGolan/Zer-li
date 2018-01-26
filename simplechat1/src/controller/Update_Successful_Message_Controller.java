package controller;

import java.net.URL;
import java.util.ResourceBundle;

import boundery.DataCompanyManagerUI;
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

public class Update_Successful_Message_Controller implements Initializable 
{
	/* ---------------------------------- Button's For the "Update Successful GUI" --------------------------------- */
	@FXML
    private Button btnBack;

    @FXML
    private TextArea txtErrMsg_For_Details;

    /**
     * This Function Is For ---> Closing The "Update Successful GUI" .
     * @param event - When The Client Press On the Butten This Parameter Start To Work .
	 * @throws Exception - If The FXML Not Work .
     */
	public void Close_Window_Of_Great_Update(ActionEvent event) throws Exception 
	{
		DataCompanyManagerUI.users.clear();
		((Node)event.getSource()).getScene().getWindow().hide(); 	 /* Hiding primary window */
		Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* load object */
		Pane root = loader.load(getClass().getResource("/controller/DataCompanyManagerOptions.fxml").openStream());
		
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);	
		primaryStage.setTitle("----- Data Company Manager Option -----");	
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}

}
