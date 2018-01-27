package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import boundery.OrderUI;
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
/**
 * menu for the store worker to open a survey result window
 * @author itait
 *
 */
public class StoreWorkerButtonController {

	
	public static int storeId = 0;
	@FXML
	private Button btnSave = null; 
	@FXML
	private Button btnLogOut = null; 
	@FXML
	private Button btnUpdateOrderStatus = null; 
	@FXML
	private Button btnBackToSWOptions = null; 
	@FXML
	private Button btnBack = null; 
	
	
	@SuppressWarnings("static-access")
	public void SaveSurvey(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide(); // Hiding primary window 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/controller/SurveyResultFrame.fxml").openStream());
		
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Add Result");
		primaryStage.show();
		//SurveyResultController sr = new SurveyResultController();				  
		//sr.start(primaryStage);
		//SurveyResultUI s = new SurveyResultUI();
		//s.main(primaryStage);
	}
	
	/**
	 * in case store worker need to change Order status to receive
	 * we open "Change Order Status Form" window
	 * @param event - store worker click "Update Order Status" button
	 * @throws IOException - if we can't load the fxml file
	 */
	public void updateOrderStatus(ActionEvent event) throws IOException
	{	
		Message msg = new Message(UserUI.user , "get store ID of specific store worker");
		OrderController.storeIdFlag = false;
		UserUI.myClient.accept(msg);
		while(OrderController.storeIdFlag == false)
		{
			System.out.print("");
		}
		OrderController.storeIdFlag = false;
		msg.setMsg(StoreWorkerButtonController.storeId);
	 	msg.setOption("get all order of specific store with status APPROVED");
	 	OrderController.ordersFlag = false;
		UserUI.myClient.accept(msg);
		while(OrderController.ordersFlag == false)
		{
			System.out.print("");
		}
		OrderController.ordersFlag = false;
		if(OrderUI.ordersNumbers.get(0) != -1)
		{
			OrderController.flag = 5;
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage(); /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); /* load object */
			Parent root = FXMLLoader.load(getClass().getResource("/controller/ChangeOrderStatus.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("Change Order Status Form");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else
		{
			((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
			Stage primaryStage = new Stage(); /* Object present window with graphics elements */
			FXMLLoader loader = new FXMLLoader(); /* load object */
			Parent root = FXMLLoader.load(getClass().getResource("/controller/NoOrdersToUpdate.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
			primaryStage.setTitle("No Orders To Update");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
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
	
	/**
	 * in case store worker want to return his options
	 * @param event - store worker click "back" button
	 * @throws IOException - if we can't load the fxml file
	 */
	public void backToOptions(ActionEvent event) throws IOException{
		((Node) event.getSource()).getScene().getWindow().hide(); /* Hiding primary window */
		Stage primaryStage = new Stage(); /* Object present window with graphics elements */
		FXMLLoader loader = new FXMLLoader(); /* load object */
		Parent root = FXMLLoader.load(getClass().getResource("/controller/StoreWorkerOptions.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/controller/ZerliDesign.css").toExternalForm());
		primaryStage.setTitle("Store Worker Options");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
