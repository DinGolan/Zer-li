package controller;

import java.io.IOException;

import boundery.UserUI;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * menu that open the survey conclusion screen 
 * @author itait
 *
 */
public class ExpertButton {
	@FXML
	private Button btnAttach = null; 
	@FXML
	private Button btnLogOut = null;  

	@FXML
	private Button btnClose = null; /* button close for close product form */
	
	/**
	 * close this windows and open ExpertSurvey window
	 * @param event
	 * @throws IOException
	 */
	public void AttachSurveyConclusion(ActionEvent event) throws IOException{
		((Node)event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ExpertSurvey.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());

		primaryStage.setScene(scene);		
		primaryStage.setTitle("Expert Conclusion");
		primaryStage.show();
	}
	/**
	 * logout this user and close this window and open UserLogin windows
	 * @param event
	 * @throws IOException
	 */
	public void Logout(ActionEvent event) throws IOException{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());

		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
