package mypackage;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Product.ProductType;
import entity.Report;
import entity.Store;
import entity.Survey;
import entity.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  static String url,username,password,name;
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  public static int counter = 2;
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port - The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg - The message received from the client.
   * @param client - The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  	Connection conn = connectToDB();
	    System.out.println("Message received: " + msg + " from " + client);
	    
	    
	    if(((Message)msg).getOption().compareTo("0") == 0) 									/* Check that its update */
	    		UpdateProductName(msg,conn); 
	    
	    if(((Message)msg).getOption().compareTo("get all products in DB") ==0) 	    		/* Check that we get from DB Because We want to Initialized */
	    {										
				/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    		((Message)msg).setMsg(getProductsFromDB(conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    if(((Message)msg).getOption().compareTo("Add User To Combo Box From DB") == 0) 	    /* Take All the Users from The DB */
        {			
	    	((Message)msg).setMsg(getUsersFromDB(conn));	
    		this.sendToAllClients(msg);
        }
	    
	    if(((Message)msg).getOption().compareTo("add survey") ==0) 							/* add survey to DB */
	    {
	    	System.out.println("a");
	    	AddSurveyToDB(msg,conn);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Update User At Data Base") == 0) 	    	/* Update The User In the DB */
        {										
	    	UpdateUserAtDB(msg,conn);
		}
	    
	    if(((Message)msg).getOption().compareTo("Add new account") == 0) 					/* check if we add new account */
        {
	    	System.out.println("a14:44");
    		((Message)msg).setMsg(AddNewAccountToDB(msg,conn));	
			
    		this.sendToAllClients(msg);	
		}
	    
	    if(((Message)msg).getOption().compareTo("UserStatus") ==0) 	    /* return user with specific UserName */
        {									
	    	((Message)msg).setMsg(getUserStatusFromDB(msg,conn));    
    		this.sendToAllClients(msg);
		}	
	    
	    if(((Message)msg).getOption().compareTo("change User status to CONNECTED") ==0) 	    /* change User status to CONNECTED in DB */
        {									
	    	changhUserStatus(msg,conn);    
		}
	    if(((Message)msg).getOption().compareTo("change User status to DISCONNECTED") ==0) 	    /* change User status to DISCONNECTED in DB */							
	    {
	    	changhUserStatus(msg,conn);    
	    }
	    
	    if(((Message)msg).getOption().compareTo("Add Store To Combo Box From DB") == 0) 	    /* Taking All the Stores From the DB */							
	    {
	    	((Message)msg).setMsg(GetStoresFromDB(conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Take the Revenue Of Specific Quarter Of Specific Store") == 0) /* Taking All the Revenue Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_The_Revenue_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Take The Orders Of Specific Store") == 0) 	    /* Taking All the Orders Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Orders_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Take The Complaints Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Complaints_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    if(((Message)msg).getOption().compareTo("Take The Date Of All the Report Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Date_Of_Report_Of_Specific_Store_FromDB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    if(((Message)msg).getOption().compareTo("Update The Total Revenue Of All the Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	Update_The_Revenue_Of_All_The_Store(msg,conn);  
	    } 
	    if(((Message)msg).getOption().compareTo("Take The Surveys Of Specific Store In Specific Quarter") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Survey_Of_Specific_Quarter_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
  }

    
  /**
   * This method overrides the one in the superclass.  
   * Called when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  
  /**
   * This method overrides the one in the superclass.  
   * Called when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  protected Connection connectToDB()
  {
	  Connection conn = null;
	  try 
		{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
      } catch (Exception ex) {/* handle the error*/}
      
      try 
      {
      	//String url = "jdbc:mysql://localhost/project";
      	//String username = "root";
     	//String password = "Braude";
     	
      	
         conn = DriverManager.getConnection(url,username,password);
         /* Option B - To connect to the DB ---> Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root"); */


   	} catch (SQLException ex)  /* handle any errors*/
   	    {
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          }
    
      return conn;
     
  }
  
  @SuppressWarnings("unchecked")
  protected Vector<Survey> Get_All_The_Survey_Of_Specific_Quarter_Of_Specific_Store_From_DB(Object msg , Connection conn)
  {
	  Vector<Survey> All_The_Survey_To_Return = new Vector<Survey>();
	  ArrayList<Object> StoreID_And_Date_Of_Report = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Survey> Surveys_Of_Specific_Store = new ArrayList<Survey>();
	  ArrayList<Survey> Final_Survey_ArrayList_To_Return = new ArrayList<Survey>();
	  ArrayList<Integer> Sum_of_Result = new ArrayList<Integer>();
	  int Store_ID = (int)StoreID_And_Date_Of_Report.get(0);
	  Date date_Of_Report = (Date)StoreID_And_Date_Of_Report.get(1);
	  Statement stmt;
	  double Total_Average_Rank = 0;
	  String survey_field;
	  String Report_Field;
	  Report temp_Report = null;
	  Survey temp_Survey;
	  
	  try {
		  
			  stmt = conn.createStatement();
			  
			  /* -------------------------------- Take The Quarter Of Specific Report ------------------------- */
			  
			  String getSpecificQuarterReportTable = "SELECT * FROM project.report WHERE StoreID = " + "'" + Store_ID + "'" + "AND DateOfCreateReport = " + "'" + date_Of_Report + "'" + ";"; 
			  ResultSet rs = stmt.executeQuery(getSpecificQuarterReportTable);
			  
			  while(rs.next())
		 	  {
				  temp_Report = new Report();
				  Report_Field = rs.getString("reportNumber");
				  temp_Report.setSerialNumberReport(Integer.parseInt(Report_Field));
				  Report_Field = rs.getString("storeID");
				  temp_Report.setStoreId(Integer.parseInt(Report_Field));
				  Report_Field = rs.getString("QuarterNumber");
				  temp_Report.setQaurterReportNumber(Report_Field);
		 	  }
			  
			  /* -------------------------------- Take All The Survey Of Specific Store In Specific Quarter ------------------------- */
			  
			  String getSurveysOfSpecificStoreTable = "SELECT * FROM project.survey WHERE StoreID =" + "'" + Store_ID + "'" + "AND QuarterNumber = " + "'" + temp_Report.getQaurterReportNumber() + "'" + ";"; 
			  ResultSet rs_2 = stmt.executeQuery(getSurveysOfSpecificStoreTable);
			  
			  while(rs_2.next())
		 	  {
				   temp_Survey = new Survey();
				   survey_field = rs_2.getString("Surveyid");
				   temp_Survey.setSurvey_Id(Integer.parseInt(survey_field));
				   survey_field = rs_2.getString("SurveyDate");
				   temp_Survey.setSurvey_Date(Date.valueOf(survey_field));
				   survey_field = rs_2.getString("QuarterNumber");
				   temp_Survey.setQuarterNumber(survey_field);
				   Surveys_Of_Specific_Store.add(temp_Survey);
		 	  }
			  
			  /* -------------------------------- Take All The Survey Of Specific Store In Specific Quarter ------------------------- */
			  
			  /* Variable That Represent The DB In Table Order */
			  String Month_From_DB;
			  String Year_From_DB;
			  String Date_From_DB;
			  Date temp_Date;
			  int Integer_Month_From_DB = 0;
			  int Integer_Year_From_DB = 0;
			  
			  /* Variable That Represent The Client */
			  String Month_From_Client;
			  String Year_From_Client;
			  String Date_From_Client;
			  int Integer_Month_From_Client = 0;
			  int Integer_Year_From_Client = 0;
			  
			  for(int i = 0 ; i < Surveys_Of_Specific_Store.size() ; i++)
			  {
				  /* Take The Date From The DB */
				  temp_Date = Surveys_Of_Specific_Store.get(i).getSurvey_Date();   		/* Take The Date */
				  Date_From_DB = String.valueOf(temp_Date); 							/* Casting To String */
				  Month_From_DB = Date_From_DB.substring(5, 7);                 		/* Take The Month */
				  Year_From_DB = Date_From_DB.substring(0,4);                  			/* Take The Year */
				  Integer_Month_From_DB = Integer.parseInt(Month_From_DB);      		/* Casting The Month To Integer */
				  Integer_Year_From_DB = Integer.parseInt(Year_From_DB);        		/* Casting The Year To Integer */
				  
				  /* Take The Date From The Client */
				  Date_From_Client = String.valueOf(date_Of_Report); 					/* Casting To String */
				  Month_From_Client = Date_From_Client.substring(5, 7);                 /* Take The Month */
				  Year_From_Client = Date_From_Client.substring(0,4);                   /* Take The Year */
				  Integer_Month_From_Client = Integer.parseInt(Month_From_Client);      /* Casting The Month To Integer */
				  Integer_Year_From_Client = Integer.parseInt(Year_From_Client);        /* Casting The Year To Integer */
				  
				  if(Integer_Year_From_DB == Integer_Year_From_Client)
				  {
					  Final_Survey_ArrayList_To_Return.add(Surveys_Of_Specific_Store.get(i));
				  }
			  }
			  
			  /* -------------------------------- Take All The Answer Of The Survey's Of ---> Specific Store In Specific Quarter ------------------------- */
			  
			  int Sum_Of_Clients = 0;
			  int Sum_Of_Result_Per_Question_Per_Survey = 0;
			  int Sum_All_The_Result_Of_All_The_Survey = 0;
			  
			  for(int i = 0 ; i < Final_Survey_ArrayList_To_Return.size() ; i++)
			  {
				  Sum_Of_Result_Per_Question_Per_Survey = 0;
				  String getAnswerSurveyOfSpecificStoreTable = "SELECT * FROM project.survey_result WHERE StoreID =" + "'" + Final_Survey_ArrayList_To_Return.get(i).getSurvey_Id() + "'" + ";"; 
				  ResultSet rs_3 = stmt.executeQuery(getAnswerSurveyOfSpecificStoreTable);
			  
				  while(rs_3.next())
			 	  {
					  for(int Index_Of_Question = 1 ; Index_Of_Question < 7 ; Index_Of_Question++)                 /* The Iteration is ---> 1 To 7 Because We Have 6 Question's */
					  {														
						  survey_field = rs_3.getString("sumQ" + Index_Of_Question);
						  Sum_Of_Result_Per_Question_Per_Survey += Integer.parseInt(survey_field);
					  }
					  
					  survey_field = rs_3.getString("numOfClients");
					  Sum_Of_Clients += Integer.parseInt(survey_field);
			 	  }
				  
				  Sum_of_Result.add(Sum_Of_Result_Per_Question_Per_Survey);
			  }
			  
			  for(int i = 0 ; i < Sum_of_Result.size() ; i++)
			  {
				  Sum_All_The_Result_Of_All_The_Survey += Sum_of_Result.get(i);
			  }
			  
			  Total_Average_Rank = (double)(Sum_All_The_Result_Of_All_The_Survey / Sum_Of_Clients);
			  
	  }
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }
	  return All_The_Survey_To_Return;
  }
  
  @SuppressWarnings("unchecked")
  protected void Update_The_Revenue_Of_All_The_Store(Object msg , Connection conn) /* This method get Orders Of Specific Store from DB */
  {
	  ArrayList<Store> All_Stores = (ArrayList<Store>)(((Message)msg).getMsg());
	  ArrayList<Order> Order_Of_Spicific_Store = new ArrayList<Order>();
	  ArrayList<Complaint> Complaint_Of_Spicific_Store = new ArrayList<Complaint>();
	  Statement stmt;
	  double Sum_Of_Revenue = 0;
	  double Sum_Of_Compansation = 0;
	  int Zero_The_Field_Of_Total_Revenue = 0;
	  String order_field;
	  String Complaint_Field;
	  Order temp_Order;
	  Complaint temp_Complaint;
	  try {
		  stmt = conn.createStatement();
		  
		  for(int index = 0 ; index < All_Stores.size() ; index++)
		  {
			      String Update_Table_Before_Store_Revenue = "UPDATE project.store SET TotalRevenue =" + "'" + Zero_The_Field_Of_Total_Revenue + "'" + "WHERE StoreID=" + "'" + All_Stores.get(index).getStoreId() + "'" + ";" ;
			      stmt.executeUpdate(Update_Table_Before_Store_Revenue);
			  
				  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + All_Stores.get(index).getStoreId() + "'" + ";"; 
				  ResultSet rs = stmt.executeQuery(getOrdersOfSpecificStoreTable);
				  Order_Of_Spicific_Store = new ArrayList<Order>();
				  Complaint_Of_Spicific_Store = new ArrayList<Complaint>();
				  Sum_Of_Revenue = 0;
				  Sum_Of_Compansation = 0;
				  
				  while(rs.next())
			 	  {
					   temp_Order = new Order();
					   order_field = rs.getString("orderID");
					   temp_Order.setOrderID(Integer.parseInt(order_field));
					   order_field = rs.getString("orderTotalPrice");
					   temp_Order.setOrderTotalPrice(Double.parseDouble(order_field));
					   Order_Of_Spicific_Store.add(temp_Order);
			 	  }
				  
				  /* -------------------------------- We Take The Compensation of Each Complaint About The Order's Of Specific Store ------------------------- */
				  
				  for(int i = 0 ; i < Order_Of_Spicific_Store.size() ; i++)
				  {
					  String getComplaintOfSpecificStoreTable = "SELECT * FROM project.complaint WHERE OrderID = " + "'" + Order_Of_Spicific_Store.get(i).getOrderID() + "'" + ";";
					  ResultSet rs_2 = stmt.executeQuery(getComplaintOfSpecificStoreTable);
					  while(rs_2.next())
				 	  {
						   temp_Complaint = new Complaint();
						   Complaint_Field = rs_2.getString("complaintCompansation");
						   temp_Complaint.setComplaintCompansation(Double.parseDouble(Complaint_Field));
						   Complaint_Of_Spicific_Store.add(temp_Complaint);
				 	  }  
				  }
				  
				  /* -------------------------------- Calculate The Sum of Compensation Of Specific Store ------------------------- */
				  
				  for(int i = 0 ; i < Complaint_Of_Spicific_Store.size() ; i++)
				  {
					  Sum_Of_Compansation += Complaint_Of_Spicific_Store.get(i).getComplaintCompansation();
				  }
				  
				  /* -------------------------------- Calculate The Sum of Revenue Of Specific Store ------------------------- */
				  
				  for(int i = 0 ; i < Order_Of_Spicific_Store.size() ; i++)
				  {
					  Sum_Of_Revenue += Order_Of_Spicific_Store.get(i).getOrderTotalPrice();
				  }
				  
				  /* -------------------------------- The Final Sum ------------------------- */
				  
				  Sum_Of_Revenue = Sum_Of_Revenue - Sum_Of_Compansation;
				  
				  /* -------------------------------- Update The DB ------------------------- */
				  
				  String Update_Table_Store_Revenue = "UPDATE project.store SET TotalRevenue =" + "'" + Sum_Of_Revenue + "'" + "WHERE StoreID=" + "'" + All_Stores.get(index).getStoreId() + "'" + ";" ;
				  stmt.executeUpdate(Update_Table_Store_Revenue);
		  }
	  } 
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }	
  }
  
  protected ArrayList<Date> Get_All_The_Date_Of_Report_Of_Specific_Store_FromDB(Object msg , Connection conn) /* This method get Orders Of Specific Store from DB */
  {
	  int temp_Store_Id = (Integer)(((Message)msg).getMsg());
	  ArrayList<Date> Date_Of_Report = new ArrayList<Date>();
	  Statement stmt;
	  String order_field;
	  
	  /* -------------------------------- We Take The Order Of Specific Store ------------------------- */
	  
	  try {
		  stmt = conn.createStatement();
		  String getDatesNumbersTable = "SELECT * FROM project.report WHERE StoreID = " + "'" + temp_Store_Id + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getDatesNumbersTable);
		  
		  while(rs.next())
	 	  {
			   order_field = rs.getString("DateOfCreateReport");
			   Date_Of_Report.add(Date.valueOf(order_field));
	 	  }
		  
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return Date_Of_Report;
  }
  
  @SuppressWarnings("unchecked")
  protected ArrayList<Order> Get_Orders_Of_Specific_Store_From_DB(Object msg , Connection conn) /* This method get Orders Of Specific Store from DB */
  {
	  ArrayList<Object> StoreID_And_Date_Of_Report = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Order> orders_Of_Specific_Store = new ArrayList<Order>();
	  ArrayList<Order> Final_Order_Of_Specific_Quarter = new ArrayList<Order>();
	  int Store_ID = (int)StoreID_And_Date_Of_Report.get(0);
	  Date date_Of_Report = (Date)StoreID_And_Date_Of_Report.get(1);
	  Statement stmt;
	  String order_field;
	  String product_In_OrderField;
	  String Report_Field;
	  Order temp_Order;
	  Product temp_Product;
	  Report temp_Report = null;
	  
	  try {
		  
		  stmt = conn.createStatement();
		  
		  /* -------------------------------- Take The Quarter Of Specific Report ------------------------- */
		  
		  String getSpecificQuarterReportTable = "SELECT * FROM project.report WHERE StoreID = " + "'" + Store_ID + "'" + "AND DateOfCreateReport = " + "'" + date_Of_Report + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getSpecificQuarterReportTable);
		  
		  while(rs.next())
	 	  {
			  temp_Report = new Report();
			  Report_Field = rs.getString("reportNumber");
			  temp_Report.setSerialNumberReport(Integer.parseInt(Report_Field));
			  Report_Field = rs.getString("storeID");
			  temp_Report.setStoreId(Integer.parseInt(Report_Field));
			  Report_Field = rs.getString("QuarterNumber");
			  temp_Report.setQaurterReportNumber(Report_Field);
	 	  }
		  
		  /* -------------------------------- Take All The Order Of Specific Store In Specific Quarter ------------------------- */
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_ID + "'" + "AND QuarterNumber = " + "'" + temp_Report.getQaurterReportNumber() + "'" +  ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  
		  while(rs_2.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs_2.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   order_field = rs_2.getString("orderDate");
			   temp_Order.setOrderDate(Date.valueOf(order_field));
			   orders_Of_Specific_Store.add(temp_Order);
	 	  }
		  
		  /* ----------------------------------- we Check The Year Of Each Order That We Get From The DB ----------------------------------*/
		  
		  /* Variable That Represent The DB In Table Order */
		  String Month_From_DB;
		  String Year_From_DB;
		  String Date_From_DB;
		  Date temp_Date;
		  int Integer_Month_From_DB = 0;
		  int Integer_Year_From_DB = 0;
		  double Revenue_Of_Specific_Quarter = 0;
		  
		  /* Variable That Represent The Client */
		  String Month_From_Client;
		  String Year_From_Client;
		  String Date_From_Client;
		  int Integer_Month_From_Client = 0;
		  int Integer_Year_From_Client = 0;
		  
		  for(int i = 0 ; i < orders_Of_Specific_Store.size() ; i++)
		  {
			  /* Take The Date From The DB */
			  temp_Date = orders_Of_Specific_Store.get(i).getOrderDate();   		/* Take The Date */
			  Date_From_DB = String.valueOf(temp_Date); 							/* Casting To String */
			  Month_From_DB = Date_From_DB.substring(5, 7);                 		/* Take The Month */
			  Year_From_DB = Date_From_DB.substring(0,4);                  			/* Take The Year */
			  Integer_Month_From_DB = Integer.parseInt(Month_From_DB);      		/* Casting The Month To Integer */
			  Integer_Year_From_DB = Integer.parseInt(Year_From_DB);        		/* Casting The Year To Integer */
			  
			  /* Take The Date From The Client */
			  Date_From_Client = String.valueOf(date_Of_Report); 					/* Casting To String */
			  Month_From_Client = Date_From_Client.substring(5, 7);                 /* Take The Month */
			  Year_From_Client = Date_From_Client.substring(0,4);                   /* Take The Year */
			  Integer_Month_From_Client = Integer.parseInt(Month_From_Client);      /* Casting The Month To Integer */
			  Integer_Year_From_Client = Integer.parseInt(Year_From_Client);        /* Casting The Year To Integer */
			  
			  if(Integer_Year_From_DB == Integer_Year_From_Client)
			  {
				  Final_Order_Of_Specific_Quarter.add(orders_Of_Specific_Store.get(i));
			  }
		  }
		  
		  /* -------------------------------- We Take The Products Type Of Each Order Of Specific Store & Quarter ------------------------- */
		  
		  for(int i = 0 ; i < Final_Order_Of_Specific_Quarter.size() ; i++)
		  {
			  ArrayList<Product> productsInOrder = new ArrayList<Product>();
			  String getOrdersProductTable = "SELECT * FROM project.productinorder WHERE OrderID = " + "'" + Final_Order_Of_Specific_Quarter.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_3 = stmt.executeQuery(getOrdersProductTable);
			  while(rs_3.next())
		 	  {
				   temp_Product = new Product();
				   product_In_OrderField = rs_3.getString("ProductType");
				   temp_Product.setpType(ProductType.valueOf(product_In_OrderField));
				   productsInOrder.add(temp_Product);
		 	  }  
			  
			  Final_Order_Of_Specific_Quarter.get(i).setProductsInOrder(productsInOrder);
		  }
		  
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return Final_Order_Of_Specific_Quarter;
  }
  
  protected ArrayList<Store> GetStoresFromDB(Connection conn) /* This method get Stores table details from DB */
  {
	  ArrayList<Store> stores = new ArrayList<Store>();
	  Statement stmt;
	  String s;
	  Store sr;
	  try {
		  stmt = conn.createStatement();
		  String getStoresTable = "SELECT * FROM store;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getStoresTable);
		  while(rs.next())
	 	  {
			  	  sr = new Store();
				  s = rs.getString("StoreID");
				  sr.setStoreId(Integer.parseInt(s));
				  s = rs.getString("StoreAddress");
				  sr.setStore_Address(s);
				  s = rs.getString("QuantityOfOrder");
				  sr.setQuantityOfOrders(Integer.parseInt(s));
				  stores.add(sr);
	 	  }
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return stores;
  }
  
  @SuppressWarnings("unchecked")
  protected ArrayList<Complaint> Get_Complaints_Of_Specific_Store_From_DB(Object msg , Connection conn)
  {
	  ArrayList<Object> StoreID_And_Date_Of_Report = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Order> orders_Of_Specific_Store = new ArrayList<Order>();
	  ArrayList<Order> Final_Order_Of_Specific_Quarter = new ArrayList<Order>();
	  ArrayList<Complaint> complaints = new ArrayList<Complaint>();
	  int Store_ID = (int)StoreID_And_Date_Of_Report.get(0);
	  Date date_Of_Report = (Date)StoreID_And_Date_Of_Report.get(1);
	  Statement stmt;
	  String order_field;
	  String complaint_Field;
	  String Report_Field;
	  Order temp_Order;
	  Complaint temp_Complaint;
	  Report temp_Report = null;
	  
	  try {
		  
		  stmt = conn.createStatement();
		  
		  /* -------------------------------- Take The Quarter Of Specific Report ------------------------- */
		  
		  String getSpecificQuarterReportTable = "SELECT * FROM project.report WHERE StoreID = " + "'" + Store_ID + "'" + "AND DateOfCreateReport = " + "'" + date_Of_Report + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getSpecificQuarterReportTable);           
		  
		  while(rs.next())
	 	  {
			  temp_Report = new Report();
			  Report_Field = rs.getString("reportNumber");
			  temp_Report.setSerialNumberReport(Integer.parseInt(Report_Field));
			  Report_Field = rs.getString("storeID");
			  temp_Report.setStoreId(Integer.parseInt(Report_Field));
			  Report_Field = rs.getString("QuarterNumber");
			  temp_Report.setQaurterReportNumber(Report_Field);
	 	  }
		  
		  /* -------------------------------- Take All The Order Of Specific Store In Specific Quarter ------------------------- */
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_ID + "'" + "AND QuarterNumber = " + "'" + temp_Report.getQaurterReportNumber() + "'" +  ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  
		  while(rs_2.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs_2.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   order_field = rs_2.getString("orderDate");
			   temp_Order.setOrderDate(Date.valueOf(order_field));
			   orders_Of_Specific_Store.add(temp_Order);
	 	  }
		  
		  /* ----------------------------------- We Check The Year Of Each Order That We Get From The DB ----------------------------------*/
		  
		  /* Variable That Represent The DB In Table Order */
		  String Month_From_DB;
		  String Year_From_DB;
		  String Date_From_DB;
		  Date temp_Date;
		  int Integer_Month_From_DB = 0;
		  int Integer_Year_From_DB = 0;
		  
		  /* Variable That Represent The Client */
		  String Month_From_Client;
		  String Year_From_Client;
		  String Date_From_Client;
		  int Integer_Month_From_Client = 0;
		  int Integer_Year_From_Client = 0;
		  
		  for(int i = 0 ; i < orders_Of_Specific_Store.size() ; i++)
		  {
			  /* Take The Date From The DB */
			  temp_Date = orders_Of_Specific_Store.get(i).getOrderDate();   		/* Take The Date */
			  Date_From_DB = String.valueOf(temp_Date); 							/* Casting To String */
			  Month_From_DB = Date_From_DB.substring(5, 7);                 		/* Take The Month */
			  Year_From_DB = Date_From_DB.substring(0,4);                  			/* Take The Year */
			  Integer_Month_From_DB = Integer.parseInt(Month_From_DB);      		/* Casting The Month To Integer */
			  Integer_Year_From_DB = Integer.parseInt(Year_From_DB);        		/* Casting The Year To Integer */
			  
			  /* Take The Date From The Client */
			  Date_From_Client = String.valueOf(date_Of_Report); 					/* Casting To String */
			  Month_From_Client = Date_From_Client.substring(5, 7);                 /* Take The Month */
			  Year_From_Client = Date_From_Client.substring(0,4);                   /* Take The Year */
			  Integer_Month_From_Client = Integer.parseInt(Month_From_Client);      /* Casting The Month To Integer */
			  Integer_Year_From_Client = Integer.parseInt(Year_From_Client);        /* Casting The Year To Integer */
			  
			  if(Integer_Year_From_DB == Integer_Year_From_Client)
			  {
				  Final_Order_Of_Specific_Quarter.add(orders_Of_Specific_Store.get(i));
			  }
		  }
		  
		  /* -------------------------------- We Take The Complaints Of Each Order ------------------------- */
		  
		  for(int i = 0 ; i < Final_Order_Of_Specific_Quarter.size() ; i++)
		  {
			  String getComplaintTable = "SELECT * FROM project.complaint WHERE OrderID = " + "'" + Final_Order_Of_Specific_Quarter.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_3 = stmt.executeQuery(getComplaintTable);
			  while(rs_3.next())
		 	  {
				  temp_Complaint = new Complaint();
				  complaint_Field = rs_3.getString("complaintMonth");
				  temp_Complaint.setComplaintMonth(complaint_Field);
				  complaints.add(temp_Complaint);
		 	  }  
		  }
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return complaints;
  }
  
  @SuppressWarnings("unchecked")
  protected ArrayList<Object> Get_The_Revenue_Of_Specific_Store_From_DB(Object msg , Connection conn)
  {
	  ArrayList<Object> temp_Store_With_ID = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Order> orders_Of_Specific_Store = new ArrayList<Order>();
	  ArrayList<Complaint> Complaint_Of_Specific_Store = new ArrayList<Complaint>();
	  ArrayList<Object> Revenue_To_Return_And_Number_Of_Order = new ArrayList<Object>();                /* I Only Use With One cell But I Need This ArrayList ---> To Save After I return To the Client */
	  int temp_Store_Id = (int)temp_Store_With_ID.get(0);
	  Date date_Of_Report = (Date)temp_Store_With_ID.get(1);
	  Statement stmt;
	  String order_field;
	  String Complaint_Field;
	  String Report_Field;
	  Order temp_Order;
	  Complaint temp_Complaint;
	  Report temp_Report = null;
	  
	  try {
		  stmt = conn.createStatement();
		  
		  /* -------------------------------- Take The Quarter Of Specific Report ------------------------- */
		  
		  String getSpecificQuarterReportTable = "SELECT * FROM project.report WHERE StoreID = " + "'" + temp_Store_Id + "'" + "AND DateOfCreateReport = " + "'" + date_Of_Report + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getSpecificQuarterReportTable);
		  
		  while(rs.next())
	 	  {
			  temp_Report = new Report();
			  Report_Field = rs.getString("QuarterNumber");
			  temp_Report.setQaurterReportNumber(Report_Field);
	 	  }
		    
		  /* -------------------------------- Take All The Order Of Specific Store In Specific Quarter ------------------------- */
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + temp_Store_Id + "'" + "AND QuarterNumber = " + "'" + temp_Report.getQaurterReportNumber() + "'" +  ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  
		  while(rs_2.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs_2.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   order_field = rs_2.getString("orderTotalPrice");
			   temp_Order.setOrderTotalPrice(Double.parseDouble(order_field));
			   order_field = rs_2.getString("orderDate");
			   temp_Order.setOrderDate(Date.valueOf(order_field));
			   orders_Of_Specific_Store.add(temp_Order);
	 	  }
		  
		  /* -------------------------------- Take All The Complaint Of Specific Store ------------------------- */
		  
		  for(int i = 0 ; i < orders_Of_Specific_Store.size() ; i++)
		  {
			  String getComplaintOfSpecificStoreTable = "SELECT * FROM project.complaint WHERE OrderID = " + "'" + orders_Of_Specific_Store.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_3 = stmt.executeQuery(getComplaintOfSpecificStoreTable);
			  while(rs_3.next())
		 	  {
				   temp_Complaint = new Complaint();
				   Complaint_Field = rs_3.getString("complaintCompansation");
				   temp_Complaint.setComplaintCompansation(Double.parseDouble(Complaint_Field));
				   Complaint_Field = rs_3.getString("complaintDate");
				   temp_Complaint.setComplaintDate(Date.valueOf(Complaint_Field));
				   Complaint_Of_Specific_Store.add(temp_Complaint);
		 	  }  
		  }
		  
		  /* -------------------------------- Calculate The Revenue According To Quarter ------------------------- */
		  
		  int Count_Of_Order_Of_Specific_Quarter = 0;
		  
		  /* Variable That Represent The DB In Table Order */
		  String Month_From_DB;
		  String Year_From_DB;
		  String Date_From_DB;
		  Date temp_Date;
		  int Integer_Month_From_DB = 0;
		  int Integer_Year_From_DB = 0;
		  double Revenue_Of_Specific_Quarter = 0;
		  
		  /* Variable That Represent The Client */
		  String Month_From_Client;
		  String Year_From_Client;
		  String Date_From_Client;
		  int Integer_Month_From_Client = 0;
		  int Integer_Year_From_Client = 0;
		  
		  
		  for(int i = 0 ; i < orders_Of_Specific_Store.size() ; i++)
		  {
			  /* Take The Date From The DB */
			  temp_Date = orders_Of_Specific_Store.get(i).getOrderDate();   		/* Take The Date */
			  Date_From_DB = String.valueOf(temp_Date); 							/* Casting To String */
			  Month_From_DB = Date_From_DB.substring(5, 7);                 		/* Take The Month */
			  Year_From_DB = Date_From_DB.substring(0,4);                  			/* Take The Year */
			  Integer_Month_From_DB = Integer.parseInt(Month_From_DB);      		/* Casting The Month To Integer */
			  Integer_Year_From_DB = Integer.parseInt(Year_From_DB);        		/* Casting The Year To Integer */
			  
			  /* Take The Date From The Client */
			  Date_From_Client = String.valueOf(date_Of_Report); 					/* Casting To String */
			  Month_From_Client = Date_From_Client.substring(5, 7);                 /* Take The Month */
			  Year_From_Client = Date_From_Client.substring(0,4);                   /* Take The Year */
			  Integer_Month_From_Client = Integer.parseInt(Month_From_Client);      /* Casting The Month To Integer */
			  Integer_Year_From_Client = Integer.parseInt(Year_From_Client);        /* Casting The Year To Integer */
			  
			  /* Note - I Can Make The Operation Of The Sum Only With The 'If' Statement of The Year */
			  /* Note - I Not Need The Other 'If' Statement ---> Of the Month */
			  
			  if(Integer_Year_From_DB == Integer_Year_From_Client)
			  {
				  if(Integer_Month_From_Client == 1 || Integer_Month_From_Client == 2 || Integer_Month_From_Client == 3)
				  {
					  Revenue_Of_Specific_Quarter += orders_Of_Specific_Store.get(i).getOrderTotalPrice();
					  Count_Of_Order_Of_Specific_Quarter++;
				  }
				  else if(Integer_Month_From_Client == 4 || Integer_Month_From_Client == 5 || Integer_Month_From_Client == 6)
				  {
					  Revenue_Of_Specific_Quarter += orders_Of_Specific_Store.get(i).getOrderTotalPrice();
					  Count_Of_Order_Of_Specific_Quarter++;
				  } 
				  else if(Integer_Month_From_Client == 7 || Integer_Month_From_Client == 8 || Integer_Month_From_Client == 9)
				  {
					  Revenue_Of_Specific_Quarter += orders_Of_Specific_Store.get(i).getOrderTotalPrice();
					  Count_Of_Order_Of_Specific_Quarter++;
				  } 
				  else if(Integer_Month_From_Client == 10 || Integer_Month_From_Client == 11 || Integer_Month_From_Client == 12)
				  {
					  Revenue_Of_Specific_Quarter += orders_Of_Specific_Store.get(i).getOrderTotalPrice();
					  Count_Of_Order_Of_Specific_Quarter++;
				  } 
			  }  
		  }
		  
		  /* -------------------------------- Calculate The Compensation According To Quarter ------------------------- */
		  
		  /* Variable That Represent The DB In Table Complaint */
		  String Complaint_Month_From_DB;
		  String Complaint_Year_From_DB;
		  String Complaint_Date_From_DB;
		  Date Complaint_temp_Date;
		  int Complaint_Integer_Month_From_DB = 0;
		  int Complaint_Integer_Year_From_DB = 0;
		  double Compensation_Of_Specific_Quarter = 0;
		  
		  /* Variable That Represent The Client */
		  String Complaint_Month_From_Client;
		  String Complaint_Year_From_Client;
		  String Complaint_Date_From_Client;
		  int Complaint_Integer_Month_From_Client = 0;
		  int Complaint_Integer_Year_From_Client = 0;
		  
		  for(int i = 0 ; i < Complaint_Of_Specific_Store.size() ; i++)
		  {
			  /* Take The Date From The DB */
			  Complaint_temp_Date = Complaint_Of_Specific_Store.get(i).getComplaintDate();   		/* Take The Date */
			  Complaint_Date_From_DB = String.valueOf(Complaint_temp_Date); 							    /* Casting To String */
			  Complaint_Month_From_DB = Complaint_Date_From_DB.substring(5,7);                 		    /* Take The Month */
			  Complaint_Year_From_DB = Complaint_Date_From_DB.substring(0,4);                  			    /* Take The Year */
			  Complaint_Integer_Month_From_DB = Integer.parseInt(Complaint_Month_From_DB);      		    /* Casting The Month To Integer */
			  Complaint_Integer_Year_From_DB = Integer.parseInt(Complaint_Year_From_DB);        			 /* Casting The Year To Integer */
			  
			  /* Take The Date From The Client */
			  Complaint_Date_From_Client = String.valueOf(date_Of_Report); 						 /* Casting To String */
			  Complaint_Month_From_Client = Complaint_Date_From_Client.substring(5, 7);                 	 /* Take The Month */
			  Complaint_Year_From_Client = Complaint_Date_From_Client.substring(0,4);                   	 /* Take The Year */
			  Complaint_Integer_Month_From_Client = Integer.parseInt(Complaint_Month_From_Client);      	 /* Casting The Month To Integer */
			  Complaint_Integer_Year_From_Client = Integer.parseInt(Complaint_Year_From_Client);       		 /* Casting The Year To Integer */
			  
			  if(Complaint_Integer_Year_From_DB == Complaint_Integer_Year_From_Client)
			  {
				  if(Complaint_Integer_Month_From_Client == 1 || Complaint_Integer_Month_From_Client == 2 || Complaint_Integer_Month_From_Client == 3)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  }
				  else if(Complaint_Integer_Month_From_Client == 4 || Complaint_Integer_Month_From_Client == 5 || Complaint_Integer_Month_From_Client == 6)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  } 
				  else if(Complaint_Integer_Month_From_Client == 7 || Complaint_Integer_Month_From_Client == 8 || Complaint_Integer_Month_From_Client == 9)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  } 
				  else if(Complaint_Integer_Month_From_Client == 10 || Complaint_Integer_Month_From_Client == 11 || Complaint_Integer_Month_From_Client == 12)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  } 
			  }
		  }
		  
		  Revenue_Of_Specific_Quarter = Revenue_Of_Specific_Quarter - Compensation_Of_Specific_Quarter;
		  Revenue_To_Return_And_Number_Of_Order.add(Revenue_Of_Specific_Quarter);
		  Revenue_To_Return_And_Number_Of_Order.add(Count_Of_Order_Of_Specific_Quarter);
	  }
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }
	  return Revenue_To_Return_And_Number_Of_Order;
  } 
  
  @SuppressWarnings("unchecked")
  protected void UpdateProductName(Object msg, Connection conn) /* This Method Update the DB */
  {
	  ArrayList<String> temp = new ArrayList<String>();
	  ArrayList<String> temp2 = (ArrayList<String>)(((Message)msg).getMsg());

	  for (String s : temp2) {
		  	temp.add(s);
	  		}
			Statement stmt;
			try {
			stmt = conn.createStatement();

			String updateProductName = "UPDATE project.product SET ProductName =" + "'" + temp.get(1) +"'" + "WHERE ProductID=" +"'" +temp.get(0) + "'" +";";
			stmt.executeUpdate(updateProductName);
			} catch (SQLException e) {	e.printStackTrace();}	  
  }
  
  protected void UpdateUserAtDB(Object msg, Connection conn) /* This Method Update the DB */
  {
	  Statement stmt;
	  User temp_User = (User)((Message)msg).getMsg();
	  try {
		  stmt = conn.createStatement();
		  
		  String UpdateTableUsersPremmision = "UPDATE project.user SET UserPermission =" + "'" + temp_User.getPermission() + "'" + "WHERE UserName=" + "'" + temp_User.getUserName() + "'" + ";" ;
		  
		  String UpdateTableUsersStatus = "UPDATE project.user SET UserStatus =" + "'" + temp_User.getStatus() + "'" + "WHERE UserName=" + "'" + temp_User.getUserName() + "'" + ";" ;
		  
		  stmt.executeUpdate(UpdateTableUsersPremmision);
		  stmt.executeUpdate(UpdateTableUsersStatus);
	  } 
	  catch (SQLException e) {	e.printStackTrace();}	  
  }
  
  protected void AddSurveyToDB(Object msg, Connection conn)
    {
  	  ArrayList<String> temp = (ArrayList<String>)(((Message)msg).getMsg());
  	  
  		Statement stmt;

  		try {
  		//stmt = conn.createStatement();
  		//String AddSurvey = "insert into project.survey VALUES(1," + "1" + "," + "'" + temp.get(1) + "'" + "," + "'" + temp.get(2) + "'" + "," + "'" + temp.get(3) + "'" + "," + "'" + temp.get(4) + "'" + "," + "'" + temp.get(5) + "'" + "," + "'" + temp.get(6) + "'" + ";";
  		//String AddSurvey = "INSERT INTO project.survey ('Surveyid', 'Question1', 'Question2', 'Question3', 'Question4', 'Question5', 'Question6') VALUES ( " +"''" + "," + "'" + temp.get(1) + "'" + "," + "'" + temp.get(2) + "'" + "," + "'" + temp.get(3) + "'" + "," + "'" + temp.get(4) + "'" + "," + "'" + temp.get(5) + "'" + "," + "'" + temp.get(6) + "'" + ";";
  	// the mysql insert statement
        String query = "INSERT INTO project.survey (Surveyid, Question1, Question2, Question3, Question4, Question5, Question6)"
          + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
        preparedStmt.setInt(1, counter);
        preparedStmt.setString (2, temp.get(0));
        preparedStmt.setString (3, temp.get(1));
        preparedStmt.setString (4, temp.get(2));
        preparedStmt.setString (5, temp.get(3));
        preparedStmt.setString (6, temp.get(4));
        preparedStmt.setString (7, temp.get(5));
        counter++;
        preparedStmt.execute();

  		//stmt.executeUpdate(query);
  		} catch (SQLException e) {	e.printStackTrace();}	  
  
  
    }
  
  protected ArrayList<Product> getProductsFromDB(Connection conn) /* This method get products table details from DB */
  {
	  ArrayList<Product> products = new ArrayList<Product>();
	  Statement stmt;
	  String p;
	  Product pr;
	  try {
		  stmt = conn.createStatement();
		  String getProductsTable = "SELECT * FROM product;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getProductsTable);
		  while(rs.next())
	 	{
		  pr = new Product("", "", ProductType.valueOf("BOUQUET") , 0 , "");
		  pr.setpID(rs.getString("ProductID"));
		  pr.setpName(rs.getString("ProductName"));
		  pr.setpType(ProductType.valueOf(rs.getString("productType")));
		  pr.setpPrice(rs.getDouble("productPrice"));
		  pr.setpPicture(rs.getString("productPicture"));
		  products.add(pr);
	 	}
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return products;
  }
  
  protected ArrayList<User> getUsersFromDB(Connection conn) /* This method get Users table details from DB */
  {
	  ArrayList<User> users = new ArrayList<User>();
	  Statement stmt;
	  String u;
	  User ur;
	  try {
		  stmt = conn.createStatement();
		  String getUsersTable = "SELECT * FROM user;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getUsersTable);
		  while(rs.next())
	 	  {
				  ur = new User();
				  u = rs.getString("UserId");
				  ur.setId(u);
				  u = rs.getString("UserName");
				  ur.setUserName(u);
				  u = rs.getString("UserPhone");
				  ur.setPhone(u);
				  u = rs.getString("UserPassword");
				  ur.setPassword(u);
				  u = rs.getString("UserPermission");
				  ur.setPermission(User.UserPermission.valueOf(u));
				  u = rs.getString("UserStatus");
				  ur.setStatus(User.UserStatus.valueOf(u));
				  users.add(ur);
	 	  }
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return users;
  }
  
  protected Account AddNewAccountToDB(Object msg, Connection conn) //this method add new account to DB
  {
	  Account newAccount = (Account)(((Message)msg).getMsg());
	  System.out.println(((Account)((Message)msg).getMsg()));
	  Account account=new Account();
	  Statement stmt;	  
	  try {
		  stmt = conn.createStatement(); //this statement check if we didn't have account with this userID
		  String getAccountToID = "SELECT * FROM project.account WHERE AccountUserId="+newAccount.getAccountUserId()+";"; // get the account that connected to new account id of exist
		  ResultSet rs = stmt.executeQuery(getAccountToID);
		  if(!rs.isBeforeFirst()) //this statement enter new account to the DB  
		  {
			  stmt = conn.createStatement(); 
			  String InsertAccountToID = "INSERT INTO project.account(AccountUserId, AccountBalanceCard, AccountPaymentMethod, AccountPaymentArrangement,AccountCreditCardNum,AccountSubscriptionEndDate)" + 
			  		"VALUES("+newAccount.getAccountUserId()+","+newAccount.getAccountBalanceCard()+ ",'"+newAccount.getAccountPaymentMethod()+"','"+newAccount.getAccountPaymentArrangement()+"',"+newAccount.getAccountCreditCardNum()+",'"+newAccount.getAccountSubscriptionEndDate()+"');";
			  stmt.executeUpdate(InsertAccountToID);	 
			 // success="Add user successfully"; 
			  account.setAccountUserId(newAccount.getAccountUserId());
			  account.setAccountUserId(newAccount.getAccountUserId());
			  account.setAccountPaymentArrangement(newAccount.getAccountPaymentArrangement());
			  account.setAccountPaymentMethod(newAccount.getAccountPaymentMethod());
			  account.setAccountBalanceCard(newAccount.getAccountBalanceCard());
			  account.setAccountCreditCardNum(newAccount.getAccountCreditCardNum());
			  account.setAccountSubscriptionEndDate(newAccount.getAccountSubscriptionEndDate());
		  }
		  else //if this user already had an account
			  account.setAccountUserId("Account already exist");

	  } catch (SQLException e) {	e.printStackTrace();}	
	  
	  //finally{
		  return account;
	 // }
  }
  
  protected User getUserStatusFromDB(Object msg, Connection conn) /* This method get products table details from DB */
  {
	  Statement stmt;
	  String userName=(String)((Message)msg).getMsg();
	  String getUserStatus = null;
	  String p;
	  User user= new User();
	  Product pr;
	  try {
		  stmt = conn.createStatement();
		  getUserStatus = "SELECT * FROM project.user WHERE UserName='"+userName+"';"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getUserStatus);
		  if (!rs.isBeforeFirst())
		  {
			  user.setId("Does Not Exist"); 
		  }
		  else // if the user DOSE  exist
		  {
			  if(rs.next() != false)
			  {
				  user.setId(rs.getString("UserId"));
				  user.setUserName(rs.getString("UserName"));
				  user.setPhone(rs.getString("UserPhone"));
				  user.setPassword(rs.getString("UserPassword"));
				  user.setPermission(User.UserPermission.valueOf(rs.getString("UserPermission")));
				  user.setStatus(User.UserStatus.valueOf(rs.getString("UserStatus")));
			  }
		  }
	  } 
	  catch (SQLException e) {	e.printStackTrace();}	
	  return user;
  }
  
  protected void changhUserStatus(Object msg, Connection conn) /* This Method Update the DB */
  {
	  String userId=(String)((Message)msg).getMsg();
	  String createTablecourses;
	  Statement stmt;
	  try {
		  stmt = conn.createStatement();
		  if(((Message)msg).getOption().compareTo("change User status to CONNECTED") == 0) 
			   createTablecourses = "UPDATE project.user SET UserStatus =" + "'" + "CONNECTED" + "'" + "WHERE UserId=" +"'" +userId + "'" +";";
		  else 
			  createTablecourses = "UPDATE project.user SET UserStatus =" + "'" + "DISCONNECTED" + "'" + "WHERE UserId=" +"'" +userId + "'" +";";
		  stmt.executeUpdate(createTablecourses);
			
	  } 
	  catch (SQLException e) {	e.printStackTrace();}	  
  }
  

  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] - The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; /* Port to listen on */

    try
    {
      port = Integer.parseInt(args[0]); /* Get port from command line */
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; /* Set port to 5555 */
    }
	
    EchoServer sv = new EchoServer(port);
    

  //System.out.println("Please enter the mySQL scheme name:");
		//Scanner scanner = new Scanner(System.in);
		 //name= scanner.next();
  		name = "project";
		 url = "jdbc:mysql://localhost/" + name;/* Enter jbdc mySQL */
		//String sql = "jdbc:mysql://localhost/project";
	
	//System.out.println("Please enter the mySQL user name:");
		 //username =scanner.next(); /* Enter mySQL name */
		  username = "root";
	//System.out.println("Please enter the mySQL password:");
		 //password = scanner.next(); /* Enter mySQL password */
	     password = "Dingolan203247697";
    
    try 
    {
      sv.listen(); /* Start listening for connections */
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}

