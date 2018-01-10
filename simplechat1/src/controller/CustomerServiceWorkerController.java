package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CustomerServiceWorkerController {
	@FXML
	private Button btnCServiceWInsertSurveyQ = null; //button to insert survey questions

	@FXML
	private Button btnCServiceWOpenNewComplaint = null; //button to open new complaint
	
	@FXML
	private Button btnCServiceWHandleComplaint = null; //button to open and view a complaint
	
	@FXML
	private Button btnCServiceWSaveSurveyData = null; //button to save survey data
	
	@FXML
	private Button btnCServiceWExit = null; //button to Exit
	
	@FXML
	private Button btnCServiceWLogout = null; //button to do logout
	
	public void insertSurveyQuestionsBtn(ActionEvent event) throws Exception //To open the view report option
	{

	}
	
	public void openNewComplaintBtn(ActionEvent event) throws Exception //To open new complaint option
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ComplaintForm.fxml").openStream());
		
		ComplaintController complaintController = loader.getController();		
		complaintController.loadComplaint(); //In this Line We take the Product that we Choose and Show his Details On the GUI */
		
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/controller/AccountForm.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.setTitle("Account Credit-Card details");
		primaryStage.show();
	
	}
	
	/////אולי להוריד את קיום הכפתור יציאה
	public void exitBtn(ActionEvent event) throws Exception //Exit from the customer service worker options
	{
		System.out.println("exit customer service worker options");
		System.exit(0);			
	}
	
	
	///יש בעיה!!!!
	public void logoutBtn(ActionEvent event) throws Exception //logout by the customer service worker
	{
		UserController u = new UserController();
		u.logout(event);		
	}

}
