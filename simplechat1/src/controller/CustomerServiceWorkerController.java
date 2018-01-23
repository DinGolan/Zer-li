package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import boundery.ComplaintUI;
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

public class CustomerServiceWorkerController implements Initializable{
	public static boolean checkComplaintsFlag = false;
	
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
	
	String[] primaryStage;
	public static boolean flag = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*if(checkComplaintsFlag==true)
		{
			Pane root = null;
			Stage primaryStage = new Stage(); //Object present window with graphics elements
			FXMLLoader loader = new FXMLLoader(); //load object
			String cuurentCustomerServiceWorkerUserName=UserUI.user.getUserName();
			System.out.println(cuurentCustomerServiceWorkerUserName);
			
			Message msg = new Message(cuurentCustomerServiceWorkerUserName , "Get all complaints numbers for this customer service worker 24");
			UserUI.myClient.accept(msg); // get all complaints for this customer service worker from DB
			while(ComplaintHandleController.loadComplaintsFlag==false)
			{
				System.out.print(""); //DOES NOT RUN WITHOUT THIS LINE
			}
			ComplaintHandleController.loadComplaintsFlag=false;
			
			if(ComplaintUI.complaintsNumbers.get(0)!=-1) //we have complaints to handle for this customer service worker at DB
			{
				//((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
				root = loader.load(getClass().getResource("/controller/ComplaintForWorker.fxml").openStream());
				Scene scene = new Scene(root);		
				scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
				primaryStage.setScene(scene);	
				primaryStage.setTitle("Error msg");
				primaryStage.show();
			}
			
			checkComplaintsFlag=false;
		}*/	
	}
	
	public void insertSurveyQuestionsBtn(ActionEvent event) throws Exception //To open the view report option
	{
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyFrame.fxml").openStream());
		Scene scene = new Scene(root);	
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);		
		primaryStage.show();
		flag=true;

	}
	
	@SuppressWarnings("static-access")
	public void SaveSurveyBtn(ActionEvent event) throws Exception{
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
	
	public void openNewComplaintBtn(ActionEvent event) throws Exception //To open new complaint option
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/ComplaintForm.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Complaint Form");
		primaryStage.show();
	
	}
	
	public void followComplaintBtn(ActionEvent event) throws Exception //To open follow complaint option
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //Hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SearchComplaints.fxml").openStream());
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Search for complaints");
		primaryStage.show();	
	}
	
	/////אולי להוריד את קיום הכפתור יציאה
	public void exitBtn(ActionEvent event) throws Exception //Exit from the customer service worker options
	{
		System.out.println("exit customer service worker options");
		System.exit(0);			
	}
	
	
	public void LogoutBtn(ActionEvent event) throws IOException
	{
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
