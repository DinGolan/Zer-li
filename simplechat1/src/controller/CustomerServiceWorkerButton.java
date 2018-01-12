package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import boundery.UserUI;
import entity.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerServiceWorkerButton implements Initializable{
	@FXML
	private Button btnInsert = null; 
	@FXML
	private Button btnOpen = null; 
	@FXML
	private Button btnFollow = null; 
	@FXML
	private Button btnLogOut = null; 
	@FXML
	private Button btnSave = null; 

	@FXML
	private Button btnClose = null; /* button close for close product form */
	String[] primaryStage;
	public static boolean flag = false;
	public void InsertSurvey(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();
		flag=true;


	}
	
	@SuppressWarnings("static-access")
	public void SaveSurvey(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyResultFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		
		primaryStage.setScene(scene);		
		primaryStage.show();
		//SurveyResultController sr = new SurveyResultController();				  
		//sr.start(primaryStage);
		//SurveyResultUI s = new SurveyResultUI();
		//s.main(primaryStage);
	}
	public void Logout(ActionEvent event) throws IOException{
		Message msg = new Message(UserUI.user.getId(), "change User status to DISCONNECTED");
		UserUI.myClient.accept(msg); // change User status to DISCONNECTED in DB
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/UserLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("LOGIN");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	//public void start(Stage primaryStage) throws Exception     
	//{
	//	this.primaryStage = primaryStage;
	//}
	
}
