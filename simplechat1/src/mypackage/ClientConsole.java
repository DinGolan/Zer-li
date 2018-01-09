package mypackage;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import boundery.CatalogUI;
import boundery.DataCompanyManagerUI;
import boundery.ProductUI;
import boundery.StoreManagerReportUI;
import boundery.UserUI;
import controller.AccountController;
import controller.StoreManagerReportController;
import controller.UserController;
import client.ChatClient;
import common.ChatIF;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Report;
import entity.Store;
import entity.User;

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
  @SuppressWarnings("unchecked")
public void displayUI(Object message) 
  {
	    if(((Message)message).getOption().compareTo("get all products in DB") ==0) 		
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
	    
	    else if(((Message)message).getOption().compareTo("Add User To Combo Box From DB") == 0)
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
	    
	    else if(((Message)message).getOption().compareTo("Add Store To Combo Box From DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  StoreManagerReportUI.stores.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  StoreManagerReportUI.stores.add(temp.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Take The Orders Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Order> temp_Order = new ArrayList<Order>();
			  temp_Order = (ArrayList<Order>)((Message)message).getMsg();
			  StoreManagerReportUI.orders.clear();

			  for(i=0;i<temp_Order.size();i++)
		  	  {
				  StoreManagerReportUI.orders.add(temp_Order.get(i));
		  	  }
	    } 
	    else if(((Message)message).getOption().compareTo("Take The Complaints Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Complaint> temp_Complaint = new ArrayList<Complaint>();
			  temp_Complaint = (ArrayList<Complaint>)((Message)message).getMsg();
			  StoreManagerReportUI.complaints.clear();

			  for(i=0;i<temp_Complaint.size();i++)
		  	  {
				  StoreManagerReportUI.complaints.add(temp_Complaint.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Take the Revenue Of Specific Quarter Of Specific Store") == 0)
	    {
	    	  int i = 0;
			  ArrayList<Object> temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = new ArrayList<Object>();
			  temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = (ArrayList<Object>)((Message)message).getMsg();
			  StoreManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.clear();

			  for(i=0;i<temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.size();i++)
		  	  {
				  StoreManagerReportUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Take The Date Of All the Report Of Specific Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Date> temp_Date_Of_Report = new ArrayList<Date>();
			  temp_Date_Of_Report = (ArrayList<Date>)((Message)message).getMsg();
			  StoreManagerReportUI.Dates.clear();

			  for(i=0;i<temp_Date_Of_Report.size();i++)
		  	  {
				  StoreManagerReportUI.Dates.add(temp_Date_Of_Report.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Take The Surveys Of Specific Store In Specific Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Double> temp_Survey_Result = new ArrayList<Double>();
			  temp_Survey_Result = (ArrayList<Double>)((Message)message).getMsg();
			  StoreManagerReportUI.Average_Result_Of_Each_Qustions_In_surveys.clear();

			  for(i=0;i<temp_Survey_Result.size();i++)
		  	  {
				  StoreManagerReportUI.Average_Result_Of_Each_Qustions_In_surveys.add(temp_Survey_Result.get(i));
		  	  }
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
	  System.out.println("may14:01");
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
	  
	  System.out.println("may14:01");
	  System.out.println(UserUI.account);
	  AccountController.flag = true;  
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

