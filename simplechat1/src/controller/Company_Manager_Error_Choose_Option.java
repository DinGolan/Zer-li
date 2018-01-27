package controller;

import java.io.IOException;
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

public class Company_Manager_Error_Choose_Option implements Initializable {

    @FXML
    private TextArea txtErrMsg;

    @FXML
    private Button btnTryAgain;
    
    public void TryAgain(ActionEvent event) throws Exception 
    {
    	((Node)event.getSource()).getScene().getWindow().hide();
	    Stage primaryStage = new Stage();						 	 /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); 					 	 /* Load object */
		Pane root = loader.load(getClass().getResource("/controller/CompanyManagerReportForm.fxml").openStream());
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("----- Company Manager Report Form Option -----");
		primaryStage.show();
    }
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}

}
