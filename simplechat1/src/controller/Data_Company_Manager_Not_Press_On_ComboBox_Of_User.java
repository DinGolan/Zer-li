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
 * This Class Is For - If The The Data Company Manager Not Press On the ComboBox Of User's In His First GUI .
 * @author dingo
 *
 */
public class Data_Company_Manager_Not_Press_On_ComboBox_Of_User implements Initializable{

		@FXML
		private Button btnBack;

		@FXML
		private TextArea txtErrMsg_For_Details;
	    
	    /**
	     * This Function Is For - Return To The GUI Of Data Company Manager Option .
	     * @param event - When The Client Press On the Butten This Parameter Start To Work .
		 * @throws Exception - If The FXML Not Work .
	     */
	    public void Back_To_Data_Company_Manager_Option(ActionEvent event) throws Exception 
	    {
	    	((Node)event.getSource()).getScene().getWindow().hide();
		    Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
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
