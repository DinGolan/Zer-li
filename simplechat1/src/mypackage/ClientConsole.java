package mypackage;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import boundery.AccountUI;
import boundery.CatalogUI;
import boundery.CompanyManagerUI;
import boundery.ComplaintUI;
import boundery.CustomerUI;
import boundery.DataCompanyManagerUI;
import boundery.StoreManagerUI;
import boundery.StoreUI;
import boundery.UserUI;
import client.ChatClient;
import common.ChatIF;
import controller.AccountController;
import controller.ComplaintController;
import controller.ComplaintHandleController;
import controller.OrderController;import controller.StoreManagerController;import controller.SurveyResultController;
import controller.UserController;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Store;
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
  @SuppressWarnings("unchecked")
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
	    
	    if(((Message)message).getOption().compareTo("get all products in sale from DB") ==0)
	    {
	  	  	int i=0,j;
	  	  	ArrayList<Product> temp = new ArrayList<Product>();
	  	  	temp = (ArrayList<Product>)((Message)message).getMsg();
	  	  	CatalogUI.productsInSale.clear();

	  	  	for(i=0;i<temp.size();i++)
	  	  	{
	  	  	CatalogUI.productsInSale.add(temp.get(i));
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
	    
	    if(((Message)message).getOption().compareTo("Update customer account") == 0)
	    {
	    	CustomerUI.account = new Account();
	    	CustomerUI.account.setAccountBalanceCard(((Account)(((Message)message).getMsg())).getAccountBalanceCard());
	    	CustomerUI.account.setAccountCreditCardNum(((Account)(((Message)message).getMsg())).getAccountCreditCardNum());
	    	CustomerUI.account.setAccountPaymentArrangement(((Account)(((Message)message).getMsg())).getAccountPaymentArrangement());
	    	CustomerUI.account.setAccountPaymentMethod(((Account)(((Message)message).getMsg())).getAccountPaymentMethod());
	    	CustomerUI.account.setAccountSubscriptionEndDate(((Account)(((Message)message).getMsg())).getAccountSubscriptionEndDate());
	    	CustomerUI.account.setAccountUserId(((Account)(((Message)message).getMsg())).getAccountUserId());
	    	OrderController.accountFlag = true;
	    }
	    
	    if(((Message)message).getOption().compareTo("get all stores from DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  StoreUI.stores.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  StoreUI.stores.add(temp.get(i));
		  	  }
	    }
	    
	    if(((Message)message).getOption().compareTo("get all the survey") == 0)
	    {
	    	UserUI.Id = (ArrayList<Integer>)(((Message)message).getMsg());
	    	SurveyResultController.flag = true;
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get all orders for this customer") == 0) //get all the orders to specific customer
	    {
	  	  	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	ComplaintUI.ordersNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		ComplaintUI.ordersNumbers.add(temp.get(i));
	  	  	
	  	  	ComplaintController.loadOrdersFlag=true; //finish to get all the orders to this customer
	    }
	    
	    else if(((Message)message).getOption().compareTo("Get all complaints numbers for this customer service worker") == 0) //get all the complaints to specific customer service worker
	    {
	  	  	int i=0;
	  	  	ArrayList<Integer> temp = new ArrayList<Integer>();
	  	  	temp = (ArrayList<Integer>)((Message)message).getMsg();
	  	  	ComplaintUI.complaintsNumbers.clear();

	  	  	for(i=0;i<temp.size();i++)	  	  	
	  	  		ComplaintUI.complaintsNumbers.add(temp.get(i));
	  	  	
	  	  	ComplaintHandleController.loadComplaintsFlag=true; //finish to get all the complaints to this customer service worker
	    }
    else if(((Message)message).getOption().compareTo("Get complaint details") == 0) //get all the details for this complaint
	    {
	    	System.out.println(ComplaintUI.complaint+"clientcon");
	  	  	ComplaintUI.complaint=new Complaint();
	  	  System.out.println(ComplaintUI.complaint+"clientcon2");
	  	  	ComplaintUI.complaint=(Complaint)((Message)message).getMsg(); //save the complaint from the DB with all the details at the ComplaintUI
	  	  	ComplaintHandleController.complaintFlag=true; //finish to get all the details for this complaint
	  	  System.out.println(ComplaintUI.complaint+"clientcon3");
	    }
	    
    else if(((Message)message).getOption().compareTo("Store manager want store number") == 0) //get the store number for this store manager
    {
  	  	StoreManagerController.storeID=((Integer)((Message)message).getMsg()); //save the store number
  	  	StoreManagerController.flag=true; //finish to get the store number
    }
	    
	    /*else if(((Message)message).getOption().compareTo("Update complaint") == 0) //update complaint
	    {
	    	System.out.println("clientcons");
	  	  	//ComplaintUI.complaint=new Complaint();
	  	  	ComplaintUI.complaint=(Complaint)((Message)message).getMsg(); //save the complaint from the DB with all the details at the ComplaintUI
	  	  	ComplaintHandleController.complaintFlag=true; //finish to get all the details for this complaint
	  	  	System.out.println(ComplaintUI.complaint);
	    }*/ else if(((Message)message).getOption().compareTo("Store Manager - Add Store To Combo Box From DB") == 0){
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  StoreManagerUI.stores.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  StoreManagerUI.stores.add(temp.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Orders Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Order> temp_Order = new ArrayList<Order>();
			  temp_Order = (ArrayList<Order>)((Message)message).getMsg();
			  StoreManagerUI.orders.clear();

			  for(i=0;i<temp_Order.size();i++)
		  	  {
				  StoreManagerUI.orders.add(temp_Order.get(i));
		  	  }
	    } 
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Complaints Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Complaint> temp_Complaint = new ArrayList<Complaint>();
			  temp_Complaint = (ArrayList<Complaint>)((Message)message).getMsg();
			  StoreManagerUI.complaints.clear();

			  for(i=0;i<temp_Complaint.size();i++)
		  	  {
				  StoreManagerUI.complaints.add(temp_Complaint.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Store Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0)
	    {
	    	  int i = 0;
			  ArrayList<Object> temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = new ArrayList<Object>();
			  temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = (ArrayList<Object>)((Message)message).getMsg();
			  StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.clear();

			  for(i=0;i<temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.size();i++)
		  	  {
				  StoreManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Date Of All the Report Of Specific Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Date> temp_Date_Of_Report = new ArrayList<Date>();
			  temp_Date_Of_Report = (ArrayList<Date>)((Message)message).getMsg();
			  StoreManagerUI.Dates.clear();

			  for(i=0;i<temp_Date_Of_Report.size();i++)
		  	  {
				  StoreManagerUI.Dates.add(temp_Date_Of_Report.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Store Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Double> temp_Survey_Result = new ArrayList<Double>();
			  temp_Survey_Result = (ArrayList<Double>)((Message)message).getMsg();
			  StoreManagerUI.Average_Result_Of_Each_Qustions_In_surveys.clear();

			  for(i=0;i<temp_Survey_Result.size();i++)
		  	  {
				  StoreManagerUI.Average_Result_Of_Each_Qustions_In_surveys.add(temp_Survey_Result.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Company Manager - Add Store To Combo Box From DB") == 0)
	    {
		  	  int i=0;
			  ArrayList<Store> temp = new ArrayList<Store>();
			  temp = (ArrayList<Store>)((Message)message).getMsg();
			  CompanyManagerUI.stores_For_Company_Manager.clear();
			  CompanyManagerUI.stores_For_Company_Manager_2.clear();

			  for(i=0;i<temp.size();i++)
		  	  {
				  CompanyManagerUI.stores_For_Company_Manager.add(temp.get(i));
				  CompanyManagerUI.stores_For_Company_Manager_2.add(temp.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Orders Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Order> temp_Order = new ArrayList<Order>();
			  temp_Order = (ArrayList<Order>)((Message)message).getMsg();
			  CompanyManagerUI.orders_For_Company_Manager.clear();
			  CompanyManagerUI.orders_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Order.size();i++)
		  	  {
				  CompanyManagerUI.orders_For_Company_Manager.add(temp_Order.get(i));
				  CompanyManagerUI.orders_For_Company_Manager_2.add(temp_Order.get(i));
		  	  }
	    } 
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Complaints Of Specific Store") == 0)
	    {
		  	  int i=0;
			  ArrayList<Complaint> temp_Complaint = new ArrayList<Complaint>();
			  temp_Complaint = (ArrayList<Complaint>)((Message)message).getMsg();
			  CompanyManagerUI.complaints_For_Company_Manager.clear();
			  CompanyManagerUI.complaints_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Complaint.size();i++)
		  	  {
				  CompanyManagerUI.complaints_For_Company_Manager.add(temp_Complaint.get(i));
				  CompanyManagerUI.complaints_For_Company_Manager_2.add(temp_Complaint.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Company Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0)
	    {
	    	  int i = 0;
			  ArrayList<Object> temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = new ArrayList<Object>();
			  temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter = (ArrayList<Object>)((Message)message).getMsg();
			  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.clear();
			  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.clear();
			  for(i=0;i<temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.size();i++)
		  	  {
				  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
				  CompanyManagerUI.Total_Revenue_In_Specific_Quarter_And_Number_Of_Order_In_Specific_Quarter_For_Company_Manager_2.add(temp_Revenue_And_Number_Of_Order_Of_Specific_Store_Of_Specific_Quarter.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Comapny Manager - Take The Date Of All the Report Of Specific Store") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Date> temp_Date_Of_Report = new ArrayList<Date>();
			  temp_Date_Of_Report = (ArrayList<Date>)((Message)message).getMsg();
			  CompanyManagerUI.Dates_For_Company_Manager.clear();
			  CompanyManagerUI.Dates_For_Company_Manager_2.clear();

			  for(i=0;i<temp_Date_Of_Report.size();i++)
		  	  {
				  CompanyManagerUI.Dates_For_Company_Manager.add(temp_Date_Of_Report.get(i));
				  CompanyManagerUI.Dates_For_Company_Manager_2.add(temp_Date_Of_Report.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Company Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Double> temp_Survey_Result = new ArrayList<Double>();
			  temp_Survey_Result = (ArrayList<Double>)((Message)message).getMsg();
			  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.clear();
			  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.clear();
			  
			  for(i=0;i<temp_Survey_Result.size();i++)
		  	  {
				  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager.add(temp_Survey_Result.get(i));
				  CompanyManagerUI.Average_Result_Of_Each_Qustions_In_surveys_For_Company_Manager_2.add(temp_Survey_Result.get(i));
		  	  }
	    }
	    else if(((Message)message).getOption().compareTo("Company Manager - Compare Between Two Different Quarter") == 0)
	    {
		  	  int i = 0;
			  ArrayList<Object> temp_Object_From_Comparing = new ArrayList<Object>();
			  temp_Object_From_Comparing = (ArrayList<Object>)((Message)message).getMsg();
			  CompanyManagerUI.Object_From_Comparing_For_Store_1.clear();
			  CompanyManagerUI.Object_From_Comparing_For_Store_2.clear();
			  
			  for(i = 0 ; i < temp_Object_From_Comparing.size() ; i++)
		  	  {
				  if(i % 2 == 0)
				  {
					  CompanyManagerUI.Object_From_Comparing_For_Store_1.add(temp_Object_From_Comparing.get(i));
				  }
				  else 
				  {
					  CompanyManagerUI.Object_From_Comparing_For_Store_2.add(temp_Object_From_Comparing.get(i));
				  }
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
	  AccountUI.success=(String)((Message)message).getMsg();
	  AccountUI.account=null;
	  AccountController.flag = true;  
  }
  
  public void addComplaint(Object message)
  {
	 // if(((Complaint)((Message)message).getMsg()).getComplaintDetails().equals("Complaint already exist")) //complaint is already exist
		//  ComplaintUI.complaint.setComplaintDetails("Complaint already exist");
	//  if(((String)((Message)message).getMsg()).equals("Complaint already exist")) //complaint is already exist
		//  ComplaintUI.success="Complaint already exist";

	//  else //אולי לבטל בכלל
	 // {
		  /*ComplaintUI.complaint.setComplaintNum(((Complaint)((Message)message).getMsg()).getComplaintNum());
		  ComplaintUI.complaint.setComplaintStat(((Complaint)((Message)message).getMsg()).getComplaintStat());
		  ComplaintUI.complaint.setComplaintDate(((Complaint)((Message)message).getMsg()).getComplaintDate());
		  ComplaintUI.complaint.setComplaintDetails(((Complaint)((Message)message).getMsg()).getComplaintDetails());
		  ComplaintUI.complaint.setComplaintOrderId(((Complaint)((Message)message).getMsg()).getComplaintOrderId());
		  ComplaintUI.complaint.setComplaintServiceWorkerUserName(((Complaint)((Message)message).getMsg()).getComplaintServiceWorkerUserName());
		  ComplaintUI.complaint.setComplaintUserId(((Complaint)((Message)message).getMsg()).getComplaintUserId());*/
		  ComplaintUI.success=(String)((Message)message).getMsg();
	 // }
	  
	  //System.out.println(UserUI.complaint);
		  ComplaintUI.complaint=null;	  
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

