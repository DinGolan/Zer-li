package mypackage;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

import java.io.IOException;
import java.util.ArrayList;
import boundery.CatalogUI;
import boundery.DataCompanyManagerUI;
import boundery.UserUI;
import client.ChatClient;
import common.ChatIF;
import controller.AccountController;
import controller.ComplaintController;import controller.SurveyResultController;import controller.UserController;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Product;
import entity.User;
import boundery.SurveyResultUI;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client; 
  
  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host - The host to connect to.
   * @param port - The port to connect on.
   */
  public ClientConsole(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept(Message msg) 
  {

    try
    {
    	client.handleMessageFromClientUI(msg);
    	//msg.getMsg().
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  
  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the Console.
   *
   * @param message - The string to be displayed.
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }
  
  
   /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the Screen In shape Of GUI.
   *
   * @param message - The string to be displayed.
   */
  public void displayUI(Object message) 
  {
	    if(((Message)message).getOption().compareTo("get all products in DB") ==0) 		/* Check that its update */
	    {
	  	  	int i=0;
	  	  	ArrayList<Product> temp = new ArrayList<Product>();
	  	  	temp = (ArrayList<Product>)((Message)message).getMsg();
	  	  	CatalogUI.products.clear();

	  	  	for(i=0;i<temp.size();i++)
	  	  	{
	  	  	CatalogUI.products.add(temp.get(i));
	  	  	}
	    }
	    
	    if(((Message)message).getOption().compareTo("Add User To Combo Box From DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<User> temp = new ArrayList<User>();
			  temp = (ArrayList<User>)((Message)message).getMsg();
			  DataCompanyManagerUI.users.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  DataCompanyManagerUI.users.add(temp.get(i));
		  	  }
	    }
	    
	    if(((Message)message).getOption().compareTo("get all the survey") == 0)
	    {
	    	SurveyResultUI.Id = (ArrayList<Integer>)(((Message)message).getMsg());
	    	SurveyResultController.flag = true;
	    }
   }
  
  
  public void sendUser(Object message) 
  {
	  if(((User)((Message)message).getMsg()).getId().equals("Does Not Exist")) //user is NOT exist
	  {
		  UserUI.user.setId("Does Not Exist");
	  }
	  else
	  {
		  UserUI.user.setId(((User)((Message)message).getMsg()).getId());
		  UserUI.user.setPassword(((User)((Message)message).getMsg()).getPassword());
		  UserUI.user.setPermission(((User)((Message)message).getMsg()).getPermission());
		  UserUI.user.setPhone(((User)((Message)message).getMsg()).getPhone());
		  UserUI.user.setStatus(((User)((Message)message).getMsg()).getStatus());
		  UserUI.user.setUserName(((User)((Message)message).getMsg()).getUserName());
	  }
	  UserController.flag = true;
  }
  
  public void addAccount(Object message)
  {
	  if(((Account)((Message)message).getMsg()).getAccountUserId().equals("Account already exist")) //account is already exist
	  {
		  UserUI.account.setAccountUserId("Account already exist");
	  }
	  else
	  {
		  UserUI.account.setAccountUserId(((Account)((Message)message).getMsg()).getAccountUserId());
		  UserUI.account.setAccountPaymentArrangement(((Account)((Message)message).getMsg()).getAccountPaymentArrangement());
		  UserUI.account.setAccountPaymentMethod(((Account)((Message)message).getMsg()).getAccountPaymentMethod());
		  UserUI.account.setAccountBalanceCard(((Account)((Message)message).getMsg()).getAccountBalanceCard());
		  UserUI.account.setAccountCreditCardNum(((Account)((Message)message).getMsg()).getAccountCreditCardNum());
		  UserUI.account.setAccountSubscriptionEndDate(((Account)((Message)message).getMsg()).getAccountSubscriptionEndDate());
	  }
	  
	  System.out.println(UserUI.account);
	  AccountController.flag = true;  
  }
  
  public void addComplaint(Object message)
  {
	  if(((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Complaint already exist")) //complaint is already exist
		  UserUI.complaint.setComplaintDetails("Complaint already exist");
	  
	  else if (((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Customer id that complain doesn't exist")) //customer id doesn't exist
		  UserUI.complaint.setComplaintDetails("Customer id that complain doesn't exist");
	  
	  else if (((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Order number to complain doesn't exist"))
		  UserUI.complaint.setComplaintDetails("Order number to complain doesn't exist");
	  
	  else if (((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Customer id and this order number doesn't match"))
		  UserUI.complaint.setComplaintDetails("Customer id and this order number doesn't match");
	  
	  else if (((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Customer service worker doesn't exist"))
		  UserUI.complaint.setComplaintDetails("Customer service worker doesn't exist");
		  
	  else
	  {
		  UserUI.complaint.setComplaintNum(((Complaint)((Message)message).getMsg()).getComplaintNum());
		  UserUI.complaint.setComplaintStat(((Complaint)((Message)message).getMsg()).getComplaintStat());
		  UserUI.complaint.setComplaintDate(((Complaint)((Message)message).getMsg()).getComplaintDate());
		  UserUI.complaint.setComplaintDetails(((Complaint)((Message)message).getMsg()).getComplaintDetails());
		  UserUI.complaint.setComplaintOrderId(((Complaint)((Message)message).getMsg()).getComplaintOrderId());
		  UserUI.complaint.setComplaintServiceWorkerUserName(((Complaint)((Message)message).getMsg()).getComplaintServiceWorkerUserName());
		  UserUI.complaint.setComplaintUserId(((Complaint)((Message)message).getMsg()).getComplaintUserId());
	  }
	  
	  System.out.println(UserUI.complaint);
	  ComplaintController.flag = true;  
  }

  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] - The host to connect to.
   */
 /* public static void main(String[] args) 
  {
	ArrayList<String> s = new ArrayList<String>();	  	  
    String host = "";
    int port = 0;  				

    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    
    
    ClientConsole chat = new ClientConsole(host, DEFAULT_PORT);
    chat.accept(s);  // Wait for console data 
  }*/
}

