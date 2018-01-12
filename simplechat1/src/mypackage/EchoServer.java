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
import java.util.HashMap;
import java.util.Map;
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
  public static int counter=1;
	public static ArrayList<Integer> resulrId = new ArrayList<Integer>();

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
  @SuppressWarnings("unchecked")
public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  	Connection conn = connectToDB();
	    System.out.println("Message received: " + msg + " from " + client);
	    
	    
	    if(((Message)msg).getOption().compareTo("0") == 0) 		/* Check that its update */
	    		UpdateProductName(msg,conn); 
	    
	    if(((Message)msg).getOption().compareTo("get all products in DB") ==0) 	    /* Check that we get from DB Because We want to Initialized */
	    {										
				/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    		((Message)msg).setMsg(getProductsFromDB(conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    if(((Message)msg).getOption().compareTo("Add User To Combo Box From DB") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {			
	    	((Message)msg).setMsg(getUsersFromDB(conn));	
    		this.sendToAllClients(msg);
        }
	    
	    if(((Message)msg).getOption().compareTo("add survey") ==0) // add survey to db
	    {
	    	System.out.println("a");
	    	AddSurveyToDB(msg,conn);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Update User At Data Base") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {										
	    	UpdateUserAtDB(msg,conn);
		}
	    
	    if(((Message)msg).getOption().compareTo("insert order to DB") == 0) //add new Order
	    {
	    	AddNewOrderToDB(msg,conn);	
	    }
		    
	    if(((Message)msg).getOption().compareTo("Add new account") == 0) //check if we add new account
        {
    		((Message)msg).setMsg(addNewAccountToDB(msg,conn));	
    		this.sendToAllClients(msg);	
		}
	    
	    if(((Message)msg).getOption().compareTo("Add new complaint") == 0) //check if we add new complaint
        {
    		((Message)msg).setMsg(addNewComplaintToDB(msg,conn));	
    		this.sendToAllClients(msg);	
		}
	    
	    if(((Message)msg).getOption().compareTo("Get all orders for this customer") == 0) //get all the orders that connected to specific customer
        {
    		((Message)msg).setMsg(getAllOrdersToCustomer(msg,conn));	
    		this.sendToAllClients(msg);	
		}
	    
	    if(((Message)msg).getOption().compareTo("Get all complaints numbers for this customer service worker") == 0) //get all the complaints that connected to specific customer service worker
        {
    		((Message)msg).setMsg(getAllComplaintsForWorker(msg,conn));	
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
	    
	    if(((Message)msg).getOption().compareTo("get all the survey") ==0) 	    /* get all survey ID */							
	    {
	    	
	    	((Message)msg).setMsg(getSurvey(msg,conn));	
    		this.sendToAllClients(msg);

	    }
	    
	    if(((Message)msg).getOption().compareTo("add surveyResult") ==0) 	    /* add new answar ro a survey */							
	    {
	    	int id = ((ArrayList<Integer>)(((Message)msg).getMsg())).get(0);
	    	if(resulrId.contains(id) == true)
	    	{
		    		updateSurveyResult(msg,conn);

	    	}
	    	else {
			    	addSurveyResult(msg,conn);

	    	}
	    }
	    if(((Message)msg).getOption().compareTo("Store Manager - Add Store To Combo Box From DB") == 0) 	    /* Taking All the Stores From the DB */							
	    {
	    	((Message)msg).setMsg(GetStoresFromDB(conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0) /* Taking All the Revenue Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_The_Revenue_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Take The Orders Of Specific Store") == 0) 	    /* Taking All the Orders Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Orders_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Take The Complaints Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Complaints_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Take The Date Of All the Report Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Date_Of_Report_Of_Specific_Store_FromDB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Update The Total Revenue Of All the Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	Update_The_Revenue_Of_All_The_Store(msg,conn);  
	    } 
	    
	    if(((Message)msg).getOption().compareTo("Store Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Survey_Of_Specific_Quarter_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Company Manager - Add Store To Combo Box From DB") == 0) 	    /* Taking All the Stores From the DB */							
	    {
	    	((Message)msg).setMsg(GetStoresFromDB(conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Company Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0) /* Taking All the Revenue Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_The_Revenue_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Company Manager - Take The Orders Of Specific Store") == 0) 	    /* Taking All the Orders Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Orders_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Company Manager - Take The Complaints Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_Complaints_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Comapny Manager - Take The Date Of All the Report Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Date_Of_Report_Of_Specific_Store_FromDB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Comapny Manager - Update The Total Revenue Of All the Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	Update_The_Revenue_Of_All_The_Store(msg,conn);  
	    } 
	    
	    if(((Message)msg).getOption().compareTo("Company Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Survey_Of_Specific_Quarter_Of_Specific_Store_From_DB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    if(((Message)msg).getOption().compareTo("Company Manager - Compare Between Two Different Quarter") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(Get_All_The_Compare_Details_Between_Two_Diffrent_Quarter_From_DB(msg,conn));  
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
  protected ArrayList<Object> Get_All_The_Compare_Details_Between_Two_Diffrent_Quarter_From_DB(Object msg , Connection conn)
  {
	  Statement stmt;
	  ArrayList<Object> Date_Report_And_Store_ID = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Object> All_The_Object_To_Return = new ArrayList<Object>();
	  ArrayList<Order> Order_From_DB_Store_1 = new ArrayList<Order>();
	  ArrayList<Order> Order_From_DB_Store_2 = new ArrayList<Order>();
	  ArrayList<Product.ProductType> Product_Type_Of_Store_One = new ArrayList<Product.ProductType>();
	  ArrayList<Product.ProductType> Product_Type_Of_Store_Two = new ArrayList<Product.ProductType>();
	  ArrayList<Product> All_Product_Of_Store_One = new ArrayList<Product>();
	  ArrayList<Product> All_Product_Of_Store_Two = new ArrayList<Product>();
	  ArrayList<Survey> Survey_Of_Store_One = new ArrayList<Survey>();
	  ArrayList<Survey> Survey_Of_Store_Two = new ArrayList<Survey>();
	  int Store_One_ID = (int)Date_Report_And_Store_ID.get(0);
	  int Store_Two_ID = (int)Date_Report_And_Store_ID.get(1);
	  Date Date_Report_Store_One = (Date)Date_Report_And_Store_ID.get(2);
	  Date Date_Report_Store_Two = (Date)Date_Report_And_Store_ID.get(3);
	  String Report_Field;
	  String Order_Field;
	  String Product_In_Order_Field;
	  String Complaint_Field;
	  String Survey_Field;
	  Order temp_Order = null;
	  Product temp_Product = null;
	  Survey temp_Survey = null;
	  
	  /* The Expected ArrayList<Object> --->
	   * Index 0 = First Store ID 
	   * Index 1 = Second Store ID 
	   * Index 2 = Quarter Report - First Store
	   * Index 3 = Quarter Report - Second Store 
	   * Index 4 = Quantity Of Order - First Store
	   * Index 5 = Quantity Of Order - Second Store 
	   * Index 6 = Type Of Product In Order - First Store
	   * Index 7 = Type Of Product In Order - Second Store
	   * Index 8 = Quantity Of Each Product Type In Order - First Store
	   * Index 9 = Quantity Of Each Product Type In Order - Second Store
	   * Index 10 = The Revenue Of Store - First Store
	   * Index 11 = The Revenue Of Store - Second Store
	   * Index 12 = The Number Of Complaint - First Store
	   * Index 13 = The Number Of Complaint - Second Store
	   * Index 14 = Number Of Client That Fill Survey - First Store
	   * Index 15 = Number Of Client That Fill Survey - Second Store
	   * Index 16 = Total Average Of Survey Answer - First Store
	   * Index 17 = Total Average Of Survey Answer - Second Store 
	   * */
	  
	  /* ---------------------------------------------------------------------------------------------------------------------- */
	  
	  /* Add To The 'All_The_Object_To_Return' ---> Store ID Of Store One & Store ID Of Store Two */
	  All_The_Object_To_Return.add(Store_One_ID);      /* Index Number ---> 0  */
	  All_The_Object_To_Return.add(Store_Two_ID);      /* Index Number ---> 1  */
	   
	  try {
		  
	  /* ---------------------------------------------------------------------------------------------------------------------- */
		  
		  /* Add To The 'All_The_Object_To_Return' ---> Number Of Quarter Of Store One & Number Of Quarter Of Store Two */
		  
		  stmt = conn.createStatement();
		  String getQuarterNumber_Store_One_Table = "SELECT * FROM project.report WHERE storeID = " + "'" + Store_One_ID + "'" + "AND DateOfCreateReport = " + "'" + Date_Report_Store_One + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getQuarterNumber_Store_One_Table);
		  while(rs.next())
	 	  {
			  Report_Field = rs.getString("QuarterNumber");
			  All_The_Object_To_Return.add(Report_Field);   /* Index Number ---> 2  */
	 	  }
		  
		  String getQuarterNumberStore_Two_Table = "SELECT * FROM project.report WHERE storeID = " + "'" + Store_Two_ID + "'" + "AND DateOfCreateReport = " + "'" + Date_Report_Store_Two + "'" + ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getQuarterNumberStore_Two_Table);
		  while(rs_2.next())
	 	  {
			  Report_Field = rs_2.getString("QuarterNumber");
			  All_The_Object_To_Return.add(Report_Field);   /* Index Number ---> 3  */
	 	  }
		  
	  /* ---------------------------------------------------------------------------------------------------------------------- */
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Number Of Quantity Order At Store One & Store Two */
		  int Count_The_Number_Of_Order_In_Store_One = 0;
		  int Count_The_Number_Of_Order_In_Store_Two = 0;
		  int Integer_Help_Month_In_Order_Table_1;
		  int Integer_Help_Year_In_Order_Table_1;
		  int Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(2))));
		  int Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_One).substring(0,4)); /* The Real Year */
		  String String_Help_Date_In_Order_Table_1;
		  String getOrderNumber_Of_Store_One = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_One_ID + "'" + ";"; 
		  ResultSet rs_3 = stmt.executeQuery(getOrderNumber_Of_Store_One);
		  while(rs_3.next())
	 	  {
			  String_Help_Date_In_Order_Table_1 = rs_3.getString("orderDate");
			  Integer_Help_Month_In_Order_Table_1 = Integer.parseInt(String_Help_Date_In_Order_Table_1.substring(5, 7));
			  if(((Integer_Help_Month_In_Order_Table_1 + 2) / 3) == Real_Quarter_Number)
			  {
				  Integer_Help_Year_In_Order_Table_1 = Integer.parseInt(String_Help_Date_In_Order_Table_1.substring(0, 4));
				  if(Real_Year == Integer_Help_Year_In_Order_Table_1)
				  {
					  temp_Order = new Order();
					  Order_Field = rs_3.getString("orderID");
					  temp_Order.setOrderID(Integer.parseInt(Order_Field));
					  Order_Field = rs_3.getString("orderTotalPrice");
					  temp_Order.setOrderTotalPrice(Integer.parseInt(Order_Field));
					  Order_Field = rs_3.getString("orderDate");
					  temp_Order.setOrderDate(Date.valueOf(Order_Field));
					  Order_From_DB_Store_1.add(temp_Order);
					  Count_The_Number_Of_Order_In_Store_One++;
				  }
		 	  }
	 	  }  
		  
		  All_The_Object_To_Return.add(Count_The_Number_Of_Order_In_Store_One);      /* Index Number ---> 4  */
		  
		  int Integer_Help_Month_In_Order_Table_2;
		  int Integer_Help_Year_In_Order_Table_2;
		  String String_Help_Date_In_Order_Table_2;
		  Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(3)))); 
		  Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_Two).substring(0,4)); /* The Real Year */
		  
		  String getOrderNumber_Of_Store_Two = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_Two_ID + "'" + ";"; 
		  ResultSet rs_4 = stmt.executeQuery(getOrderNumber_Of_Store_Two);
		  while(rs_4.next())
	 	  {
			  String_Help_Date_In_Order_Table_2 = rs_4.getString("orderDate");
			  Integer_Help_Month_In_Order_Table_2 = Integer.parseInt(String_Help_Date_In_Order_Table_2.substring(5, 7));
			  if(((Integer_Help_Month_In_Order_Table_2 + 2) / 3) == Real_Quarter_Number)
			  {
				  Integer_Help_Year_In_Order_Table_2 = Integer.parseInt(String_Help_Date_In_Order_Table_2.substring(0, 4));
				  if(Real_Year == Integer_Help_Year_In_Order_Table_2)
				  {
					  temp_Order = new Order();
					  Order_Field = rs_4.getString("orderID");
					  temp_Order.setOrderID(Integer.parseInt(Order_Field));
					  Order_Field = rs_4.getString("orderTotalPrice");
					  temp_Order.setOrderTotalPrice(Integer.parseInt(Order_Field));
					  Order_Field = rs_4.getString("orderDate");
					  temp_Order.setOrderDate(Date.valueOf(Order_Field));
					  Order_From_DB_Store_2.add(temp_Order);
					  Count_The_Number_Of_Order_In_Store_Two++;
				  }
			  }  
	 	  } 
		  
		  All_The_Object_To_Return.add(Count_The_Number_Of_Order_In_Store_Two);      /* Index Number ---> 5  */
		
     /* ---------------------------------------------------------------------------------------------------------------------- */
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Product Type Of Order At Store One & Store Two */
		  
		  String temp_Product_In_Order_Field;
		  
		  for(int i = 0 ; i < Order_From_DB_Store_1.size() ; i++)
		  {
			  String getProduct_Type_Of_Store_One = "SELECT * FROM project.productinorder WHERE OrderID = " + "'" + Order_From_DB_Store_1.get(i).getOrderID() + "'" + ";"; 
			  ResultSet rs_5 = stmt.executeQuery(getProduct_Type_Of_Store_One);
			  while(rs_5.next())
		 	  {
				  Product_In_Order_Field =  rs_5.getString("ProductType");
				  if(Product_Type_Of_Store_One.contains(Product.ProductType.valueOf(Product_In_Order_Field)) == false) /* I He Not Contain */
				  {
					  Product_Type_Of_Store_One.add(Product.ProductType.valueOf(Product_In_Order_Field));
					  
					  temp_Product = new Product();
					  temp_Product.setpType(Product.ProductType.valueOf(Product_In_Order_Field));
					  Product_In_Order_Field =  rs_5.getString("QuantityOfProduct");
					  temp_Product.setQuantity(Integer.parseInt(Product_In_Order_Field));
					  All_Product_Of_Store_One.add(temp_Product);
				  }
				  else
				  {
					  Product_In_Order_Field =  rs_5.getString("QuantityOfProduct");
					  temp_Product_In_Order_Field = rs_5.getString("ProductType");
					  for(int j = 0 ; j < All_Product_Of_Store_One.size() ; j++)
					  {
						  if(String.valueOf(All_Product_Of_Store_One.get(j).getpType()).compareTo(temp_Product_In_Order_Field) == 0) /* If The Type In The Array List Equal to The Type In DB */
						  {
							  All_Product_Of_Store_One.get(j).setQuantity(All_Product_Of_Store_One.get(j).getQuantity() + Integer.parseInt(Product_In_Order_Field));
						  }
					  }
				  }
		 	  }
			  
			
		  }
		  
		  All_The_Object_To_Return.add(Product_Type_Of_Store_One);   /* Index Number ---> 6 */
		  
		  for(int i = 0 ; i < Order_From_DB_Store_2.size() ; i++)
		  {
			  String getProduct_Type_Of_Store_Two = "SELECT * FROM project.productinorder WHERE OrderID = " + "'" + Order_From_DB_Store_2.get(i).getOrderID() + "'" + ";"; 
			  ResultSet rs_6 = stmt.executeQuery(getProduct_Type_Of_Store_Two);
			  while(rs_6.next())
		 	  {
				  Product_In_Order_Field =  rs_6.getString("ProductType");
				  if(Product_Type_Of_Store_Two.contains(Product.ProductType.valueOf(Product_In_Order_Field)) == false) /* I He Not Contain */
				  {
					  Product_Type_Of_Store_Two.add(Product.ProductType.valueOf(Product_In_Order_Field));
					  
					  temp_Product = new Product();
					  temp_Product.setpType(Product.ProductType.valueOf(Product_In_Order_Field));
					  Product_In_Order_Field = rs_6.getString("QuantityOfProduct");
					  temp_Product.setQuantity(Integer.parseInt(Product_In_Order_Field));
					  All_Product_Of_Store_Two.add(temp_Product);
				  }
				  else
				  {
					  Product_In_Order_Field =  rs_6.getString("QuantityOfProduct");
					  temp_Product_In_Order_Field = rs_6.getString("ProductType");
					  for(int j = 0 ; j < All_Product_Of_Store_Two.size() ; j++)
					  {
						  if(String.valueOf(All_Product_Of_Store_Two.get(j).getpType()).compareTo(temp_Product_In_Order_Field) == 0) /* If The Type In The Array List Equal to The Type In DB */
						  {
							  All_Product_Of_Store_Two.get(j).setQuantity(All_Product_Of_Store_Two.get(j).getQuantity() + Integer.parseInt(Product_In_Order_Field));
						  }
					  }
				  }
		 	  }
		  }
		  
		  All_The_Object_To_Return.add(Product_Type_Of_Store_Two);        /* Index Number ---> 7 */
		  
		  
		  
	/* ---------------------------------------------------------------------------------------------------------------------- */	  
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Size Of Each Product Type In Order - At Store One & Store Two */
		  
		  All_The_Object_To_Return.add(All_Product_Of_Store_One); /* Index Number ---> 8 */
		  All_The_Object_To_Return.add(All_Product_Of_Store_Two); /* Index Number ---> 9 */
		  
	 /* ---------------------------------------------------------------------------------------------------------------------- */  
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Revenue Of - Store One & Store Two */
		  
		  int Integer_Help_Year_In_Complaint_Table_1;
		  int Integer_Help_Month_In_Complaint_Table_1;
		  String String_Help_Date_In_Complaint_Table_1;
		  int Count_Number_Of_Complaint_In_Store_One = 0;
		  int Count_Number_Of_Complaint_In_Store_Two = 0;
		  double Revenue_Of_Specific_Order = 0;
		  double Sum_The_Revenue_Of_Store_One = 0;
		  double Sum_The_Revenue_Of_Store_Two = 0;
		  Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(2)))); 
		  Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_One).substring(0,4)); /* The Real Year */
		  
		  for(int i = 0 ; i < Order_From_DB_Store_1.size() ; i++)
		  {
			  Revenue_Of_Specific_Order = Order_From_DB_Store_1.get(i).getOrderTotalPrice();
			  String getComlaint_Of_Store_One = "SELECT * FROM project.complaint WHERE ComplaintOrderId = " + "'" + Order_From_DB_Store_1.get(i).getOrderID() + "'" + ";"; 
			  ResultSet rs_7 = stmt.executeQuery(getComlaint_Of_Store_One);
			  
			  while(rs_7.next())
		 	  {
				  String_Help_Date_In_Complaint_Table_1 = rs_7.getString("ComplaintDate");
				  Integer_Help_Month_In_Complaint_Table_1 = Integer.parseInt(String_Help_Date_In_Complaint_Table_1.substring(5,7));
				  if(((Integer_Help_Month_In_Complaint_Table_1 + 2) / 3) == Real_Quarter_Number)
				  {
					  Integer_Help_Year_In_Complaint_Table_1 = Integer.parseInt(String_Help_Date_In_Complaint_Table_1.substring(0,4));
					  if(Real_Year == Integer_Help_Year_In_Complaint_Table_1)
					  {
						  Count_Number_Of_Complaint_In_Store_One++;
						  Complaint_Field = rs_7.getString("ComplaintCompansation");
						  Revenue_Of_Specific_Order = Revenue_Of_Specific_Order - Double.parseDouble(Complaint_Field);
						  Order_From_DB_Store_1.get(i).setOrderTotalPrice(Revenue_Of_Specific_Order);
						  Revenue_Of_Specific_Order = Order_From_DB_Store_1.get(i).getOrderTotalPrice();
					  }
				  } 
		 	  } 
			  
			  Sum_The_Revenue_Of_Store_One += Order_From_DB_Store_1.get(i).getOrderTotalPrice();;
		  }
		  
		  All_The_Object_To_Return.add(Sum_The_Revenue_Of_Store_One); /* Index Number ---> 10 */
		  
		  Revenue_Of_Specific_Order = 0;
		  int Integer_Help_Year_In_Complaint_Table_2;
		  int Integer_Help_Month_In_Complaint_Table_2;
		  String String_Help_Date_In_Complaint_Table_2;
		  Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(3)))); 
		  Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_Two).substring(0,4)); /* The Real Year */
		  
		  for(int i = 0 ; i < Order_From_DB_Store_2.size() ; i++)
		  {
			  Revenue_Of_Specific_Order = Order_From_DB_Store_2.get(i).getOrderTotalPrice();
			  String getComlaint_Of_Store_Two = "SELECT * FROM project.complaint WHERE ComplaintOrderId = " + "'" + Order_From_DB_Store_2.get(i).getOrderID() + "'" + ";"; 
			  ResultSet rs_8 = stmt.executeQuery(getComlaint_Of_Store_Two);
			  
			  while(rs_8.next())
		 	  {
				  String_Help_Date_In_Complaint_Table_2 = rs_8.getString("ComplaintDate");
				  Integer_Help_Month_In_Complaint_Table_2 = Integer.parseInt(String_Help_Date_In_Complaint_Table_2.substring(5,7));
				  if(((Integer_Help_Month_In_Complaint_Table_2 + 2) / 3) == Real_Quarter_Number)
				  {
					  Integer_Help_Year_In_Complaint_Table_2 = Integer.parseInt(String_Help_Date_In_Complaint_Table_2.substring(0,4));
					  if(Real_Year == Integer_Help_Year_In_Complaint_Table_2)
					  {
						  Count_Number_Of_Complaint_In_Store_Two++;
						  Complaint_Field = rs_8.getString("ComplaintCompansation");
						  Revenue_Of_Specific_Order = Revenue_Of_Specific_Order - Double.parseDouble(Complaint_Field);
						  Order_From_DB_Store_2.get(i).setOrderTotalPrice(Revenue_Of_Specific_Order);
						  Revenue_Of_Specific_Order = Order_From_DB_Store_2.get(i).getOrderTotalPrice();
					  }
				  } 
		 	  } 
			  
			  Sum_The_Revenue_Of_Store_Two += Order_From_DB_Store_2.get(i).getOrderTotalPrice();;
		  }
		  
		  All_The_Object_To_Return.add(Sum_The_Revenue_Of_Store_Two); /* Index Number ---> 11 */
		  
     /* ---------------------------------------------------------------------------------------------------------------------- */  
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Number Of Complaint At - Store One & Store Two */
		  
		  All_The_Object_To_Return.add(Count_Number_Of_Complaint_In_Store_One); /* Index Number ---> 12 */
		  All_The_Object_To_Return.add(Count_Number_Of_Complaint_In_Store_Two); /* Index Number ---> 13 */
		  
	 /* ---------------------------------------------------------------------------------------------------------------------- */  
		  
		  /* Add To The 'All_The_Object_To_Return' ---> The Number Of Client That Fill The Survey At - Store One & Store Two */
		  
		  /* -------------------------------------- For Store - 1 ------------------------------------------- */
		  
		  int Integer_Help_Year_In_Survey_Table_1;
		  int Integer_Help_Month_In_Survey_Table_1;
		  String String_Help_Date_In_Survey_Table_1;
		  double [] Sum_Of_Specific_Question_At_Store_One = new double[6];
		  double Sum_Number_Of_Client_Store_One = 0;
		  double Total_Avg_At_Store_One = 0;
		  Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(2))));
		  Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_One).substring(0,4));      /* The Real Year */
		  
		  String getSurvey_Of_Store_One = "SELECT * FROM project.survey ;" ; 
		  ResultSet rs_9 = stmt.executeQuery(getSurvey_Of_Store_One);
		  while(rs_9.next())
		  {
			  String_Help_Date_In_Survey_Table_1 = rs_9.getString("SurveyDate");
			  Integer_Help_Month_In_Survey_Table_1 = Integer.parseInt(String_Help_Date_In_Survey_Table_1.substring(5,7));
			  if(((Integer_Help_Month_In_Survey_Table_1 + 2) / 3) == Real_Quarter_Number)
			  {
				  Integer_Help_Year_In_Survey_Table_1 = Integer.parseInt(String_Help_Date_In_Survey_Table_1.substring(0,4));
				  if(Real_Year == Integer_Help_Year_In_Survey_Table_1)
				  {
					  temp_Survey = new Survey();
					  Survey_Field  = rs_9.getString("Surveyid");
					  temp_Survey.setSurvey_Id(Integer.parseInt(Survey_Field));
					  Survey_Of_Store_One.add(temp_Survey);
				  }
			  } 
		  }
		  
		  for(int i = 0 ; i < Survey_Of_Store_One.size() ; i++)
		  {
			  String getAnswer_Of_Specific_Survey_At_Store_One = "SELECT * FROM project.survey_result WHERE Surveyid = " + "'" + Survey_Of_Store_One.get(i).getSurvey_Id() + "'" + ";"; 
			  ResultSet rs_10 = stmt.executeQuery(getAnswer_Of_Specific_Survey_At_Store_One);
			  while(rs_10.next())
			  {
				  Survey_Field = rs_10.getString("numOfClients");
				  Survey_Of_Store_One.get(i).setNum_Of_Clients(Integer.parseInt(Survey_Field));
				  Survey_Field = rs_10.getString("sumQ1");
				  Sum_Of_Specific_Question_At_Store_One[0] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_10.getString("sumQ2");
				  Sum_Of_Specific_Question_At_Store_One[1] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_10.getString("sumQ3");
				  Sum_Of_Specific_Question_At_Store_One[2] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_10.getString("sumQ4");
				  Sum_Of_Specific_Question_At_Store_One[3] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_10.getString("sumQ5");
				  Sum_Of_Specific_Question_At_Store_One[4] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_10.getString("sumQ6");
				  Sum_Of_Specific_Question_At_Store_One[5] += Integer.parseInt(Survey_Field);
			  }
		  }
		  
		  for(int i = 0 ; i < Survey_Of_Store_One.size() ; i++)
		  {
			  Sum_Number_Of_Client_Store_One += Survey_Of_Store_One.get(i).getNum_Of_Clients();
		  }
		  
		  for(int i = 0 ; i < Sum_Of_Specific_Question_At_Store_One.length ; i++)
		  {
			  Sum_Of_Specific_Question_At_Store_One[i] /= Sum_Number_Of_Client_Store_One;     /* In Each Cell I get The Average Of Each Question */
			  Total_Avg_At_Store_One += Sum_Of_Specific_Question_At_Store_One[i];
		  }
		  
		  All_The_Object_To_Return.add(Sum_Number_Of_Client_Store_One); /* Index Number ---> 14 */
		  
		  /* -------------------------------------- For Store - 2 ------------------------------------------- */
		  
		  double [] Sum_Of_Specific_Question_At_Store_Two = new double[6];
		  double Sum_Number_Of_Client_Store_Two = 0;
		  double Total_Avg_At_Store_Two = 0;
		  int Integer_Help_Year_In_Survey_Table_2;
		  int Integer_Help_Month_In_Survey_Table_2;
		  String String_Help_Date_In_Survey_Table_2;
		  Real_Quarter_Number = Integer.parseInt((String.valueOf(All_The_Object_To_Return.get(3))));
		  Real_Year = Integer.parseInt(String.valueOf(Date_Report_Store_Two).substring(0,4)); /* The Real Year */
		 
		  String getSurvey_Of_Store_Two = "SELECT * FROM project.survey ;" ; 
		  ResultSet rs_11 = stmt.executeQuery(getSurvey_Of_Store_Two);
		  while(rs_11.next())
		  {
			  String_Help_Date_In_Survey_Table_2 = rs_11.getString("SurveyDate");
			  Integer_Help_Month_In_Survey_Table_2 = Integer.parseInt(String_Help_Date_In_Survey_Table_2.substring(5,7));
			  if(((Integer_Help_Month_In_Survey_Table_2 + 2) / 3) == Real_Quarter_Number)
			  {
				  Integer_Help_Year_In_Survey_Table_2 = Integer.parseInt(String_Help_Date_In_Survey_Table_2.substring(0,4));
				  if(Real_Year == Integer_Help_Year_In_Survey_Table_2)
				  {
					  temp_Survey = new Survey();
					  Survey_Field  = rs_11.getString("Surveyid");
					  temp_Survey.setSurvey_Id(Integer.parseInt(Survey_Field));
					  Survey_Of_Store_Two.add(temp_Survey);
				  }
			  } 
		  }
		  
		  for(int i = 0 ; i < Survey_Of_Store_Two.size() ; i++)
		  {
			  String getAnswer_Of_Specific_Survey_At_Store_Two = "SELECT * FROM project.survey_result WHERE Surveyid = " + "'" + Survey_Of_Store_Two.get(i).getSurvey_Id() + "'" + ";"; 
			  ResultSet rs_12 = stmt.executeQuery(getAnswer_Of_Specific_Survey_At_Store_Two);
			  while(rs_12.next())
			  {
				  Survey_Field = rs_12.getString("numOfClients");
				  Survey_Of_Store_Two.get(i).setNum_Of_Clients(Integer.parseInt(Survey_Field));
				  Survey_Field = rs_12.getString("sumQ1");
				  Sum_Of_Specific_Question_At_Store_Two[0] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_12.getString("sumQ2");
				  Sum_Of_Specific_Question_At_Store_Two[1] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_12.getString("sumQ3");
				  Sum_Of_Specific_Question_At_Store_Two[2] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_12.getString("sumQ4");
				  Sum_Of_Specific_Question_At_Store_Two[3] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_12.getString("sumQ5");
				  Sum_Of_Specific_Question_At_Store_Two[4] += Integer.parseInt(Survey_Field);
				  Survey_Field = rs_12.getString("sumQ6");
				  Sum_Of_Specific_Question_At_Store_Two[5] += Integer.parseInt(Survey_Field);
			  }
		  }
		  
		  for(int i = 0 ; i < Survey_Of_Store_Two.size() ; i++)
		  {
			  Sum_Number_Of_Client_Store_Two += Survey_Of_Store_Two.get(i).getNum_Of_Clients();
		  }
		  
		  for(int i = 0 ; i < Sum_Of_Specific_Question_At_Store_Two.length ; i++)
		  {
			  Sum_Of_Specific_Question_At_Store_Two[i] /= Sum_Number_Of_Client_Store_Two;     /* In Each Cell I get The Average Of Each Question */
			  Total_Avg_At_Store_Two += Sum_Of_Specific_Question_At_Store_Two[i];
		  }
		  
		  All_The_Object_To_Return.add(Sum_Number_Of_Client_Store_Two); /* Index Number ---> 15 */
		  
		  All_The_Object_To_Return.add(Total_Avg_At_Store_One);         /* Index Number ---> 16 */
		  All_The_Object_To_Return.add(Total_Avg_At_Store_Two);         /* Index Number ---> 17 */
		  
	  } 
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }	
	  return All_The_Object_To_Return;
  }
  
  @SuppressWarnings("unchecked")
  protected ArrayList<Double> Get_All_The_Survey_Of_Specific_Quarter_Of_Specific_Store_From_DB(Object msg , Connection conn)
  {
	  Vector<double []> All_The_Survey_To_Return = new Vector<double []>();
	  ArrayList<Object> StoreID_And_Date_Of_Report = (ArrayList<Object>)(((Message)msg).getMsg());
	  ArrayList<Survey> Surveys_Of_Specific_Store = new ArrayList<Survey>();
	  ArrayList<Survey> Final_Survey_ArrayList_To_Return = new ArrayList<Survey>();
	  ArrayList<Double> Final_Average_Of_Each_Question = new ArrayList<Double>();
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
			  
			  String getSurveysOfSpecificStoreTable = "SELECT * FROM project.survey ;" ;  
			  ResultSet rs_2 = stmt.executeQuery(getSurveysOfSpecificStoreTable);
			  int Integer_Help_Month_In_Survey_Table;
			  int Real_Quarter_Number = Integer.parseInt(temp_Report.getQaurterReportNumber());
			  String String_Help_Date_In_Survey_Table;
			  
			  while(rs_2.next())
		 	  {
				  String_Help_Date_In_Survey_Table = rs_2.getString("SurveyDate");
				  Integer_Help_Month_In_Survey_Table = Integer.parseInt(String_Help_Date_In_Survey_Table.substring(5, 7));
				  if(((Integer_Help_Month_In_Survey_Table + 2) / 3) == Real_Quarter_Number)
				  {
					   temp_Survey = new Survey();
					   survey_field = rs_2.getString("Surveyid");
					   temp_Survey.setSurvey_Id(Integer.parseInt(survey_field));     		/* Save The Survey ID of Specific Survey */
					   survey_field = rs_2.getString("SurveyDate");
					   temp_Survey.setSurvey_Date(Date.valueOf(survey_field));     		    /* Save The Survey ID of Specific Survey */
					   temp_Survey.setQuarterNumber(temp_Report.getQaurterReportNumber());  /* Save The Quarter Number That We Make The Survey */
					   temp_Survey.setStore_ID(Store_ID);								    /* Save The Store ID that We Make The Survey */			
					   Surveys_Of_Specific_Store.add(temp_Survey);
				  }   
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
			  int Sum_Of_Clients_Per_Survey = 0;
			  double [] Sum_Result_Per_Question_Per_Survey = new double[6];
			  double [] All_Sum_Per_Qustiones_Of_All_Survey = new double[6];
			  double [] Temp_Array;
			  
			  for(int i = 0 ; i < Final_Survey_ArrayList_To_Return.size() ; i++)
			  {
				  Sum_Of_Clients_Per_Survey = 0;
				  Sum_Result_Per_Question_Per_Survey = new double[6];
				  String getAnswerSurveyOfSpecificStoreTable = "SELECT * FROM project.survey_result WHERE Surveyid =" + "'" + Final_Survey_ArrayList_To_Return.get(i).getSurvey_Id() + "'" + ";"; 
				  ResultSet rs_3 = stmt.executeQuery(getAnswerSurveyOfSpecificStoreTable);
			  
				  while(rs_3.next())
			 	  {
					  for(int Index_Of_Question = 1 ; Index_Of_Question < 7 ; Index_Of_Question++)                 /* The Iteration is ---> 1 To 7 Because We Have 6 Question's */
					  {														
						  survey_field = rs_3.getString("sumQ" + Index_Of_Question);
						  Sum_Result_Per_Question_Per_Survey[Index_Of_Question - 1] += Integer.parseInt(survey_field);
					  }
					  
					  survey_field = rs_3.getString("numOfClients");
					  Sum_Of_Clients_Per_Survey += Integer.parseInt(survey_field);
			 	  }
				  
				  for(int j = 0 ; j < Sum_Result_Per_Question_Per_Survey.length ; j++)
				  {
					  Sum_Result_Per_Question_Per_Survey[j] = Sum_Result_Per_Question_Per_Survey[j] / Sum_Of_Clients_Per_Survey;
				  }
				  
				  Sum_Of_Clients += Sum_Of_Clients_Per_Survey;
				  All_The_Survey_To_Return.add(Sum_Result_Per_Question_Per_Survey);
			  }
			  
			  for(int i = 0 ; i < All_The_Survey_To_Return.size() ; i++)
			  {
				  	 Temp_Array = new double[6];
				  	 for(int j = 0 ; j < All_The_Survey_To_Return.get(i).length ; j++)
				  	 {
				  		Temp_Array[j] = All_The_Survey_To_Return.get(i)[j];
				  	 }
				  	 
					 for(int k = 0 ; k < All_The_Survey_To_Return.get(i).length ; k++)
					 {
						 All_Sum_Per_Qustiones_Of_All_Survey[k] += Temp_Array[k];
					 }
			  }
			  
			  for(int i = 0 ; i < All_Sum_Per_Qustiones_Of_All_Survey.length ; i++)
			  {
				  All_Sum_Per_Qustiones_Of_All_Survey[i] = All_Sum_Per_Qustiones_Of_All_Survey[i] / All_The_Survey_To_Return.size();
				  Final_Average_Of_Each_Question.add(All_Sum_Per_Qustiones_Of_All_Survey[i] );
			  }
			  
			  for(int i = 0 ; i < Final_Average_Of_Each_Question.size() ; i++)
			  {
				  Total_Average_Rank += Final_Average_Of_Each_Question.get(i); 
			  }
			  
			  Final_Average_Of_Each_Question.add(Total_Average_Rank); /* At Index 7 Will Be The Total Average Of All The Survey */
			  Final_Average_Of_Each_Question.add((double)(Sum_Of_Clients));     /* At Index 8 Will Be The Number Of Client */
	  }
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }
	  return Final_Average_Of_Each_Question;
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
					  String getComplaintOfSpecificStoreTable = "SELECT * FROM project.complaint WHERE ComplaintOrderId = " + "'" + Order_Of_Spicific_Store.get(i).getOrderID() + "'" + ";";
					  ResultSet rs_2 = stmt.executeQuery(getComplaintOfSpecificStoreTable);
					  while(rs_2.next())
				 	  {
						   temp_Complaint = new Complaint();
						   Complaint_Field = rs_2.getString("ComplaintCompansation");
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
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_ID + "'" + ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  int Integer_Help_Month_In_Order_Table;
		  int Real_Quarter_Number = Integer.parseInt(temp_Report.getQaurterReportNumber());
		  String String_Help_Date_In_Order_Table;
		  
		  while(rs_2.next())
	 	  {
			  String_Help_Date_In_Order_Table = rs_2.getString("orderDate");
			  Integer_Help_Month_In_Order_Table = Integer.parseInt(String_Help_Date_In_Order_Table.substring(5, 7));
			  if(((Integer_Help_Month_In_Order_Table + 2) / 3) == Real_Quarter_Number)
			  {
				   temp_Order = new Order();
				   order_field = rs_2.getString("orderID");
				   temp_Order.setOrderID(Integer.parseInt(order_field));
				   order_field = rs_2.getString("orderDate");
				   temp_Order.setOrderDate(Date.valueOf(order_field));
				   orders_Of_Specific_Store.add(temp_Order);
			  }
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
			  HashMap<Product.ProductType, Integer> productsInOrderType = new HashMap<ProductType, Integer>();
			  String getOrdersProductTable = "SELECT * FROM project.productinorder WHERE OrderID = " + "'" + Final_Order_Of_Specific_Quarter.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_3 = stmt.executeQuery(getOrdersProductTable);
			  while(rs_3.next())
		 	  {
				   temp_Product = new Product();
				   product_In_OrderField = rs_3.getString("ProductType");
				   temp_Product.setpType(ProductType.valueOf(product_In_OrderField));
				   product_In_OrderField = rs_3.getString("QuantityOfProduct");
				   temp_Product.setQuantity(Integer.parseInt((product_In_OrderField)));
				   productsInOrderType.put(temp_Product.getpType(),Integer.parseInt(product_In_OrderField));
		 	  }  
			  
			  Final_Order_Of_Specific_Quarter.get(i).setProductInOrderType(productsInOrderType);  
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
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + Store_ID + "'" + ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  int Integer_Help_Month_In_Order_Table;
		  int Real_Quarter_Number = Integer.parseInt(temp_Report.getQaurterReportNumber());
		  String String_Help_Date_In_Order_Table;
		  
		  while(rs_2.next())
	 	  {
			  String_Help_Date_In_Order_Table = rs_2.getString("orderDate");
			  Integer_Help_Month_In_Order_Table = Integer.parseInt(String_Help_Date_In_Order_Table.substring(5, 7));
			  if(((Integer_Help_Month_In_Order_Table + 2) / 3) == Real_Quarter_Number)
			  {
				   temp_Order = new Order();
				   order_field = rs_2.getString("orderID");
				   temp_Order.setOrderID(Integer.parseInt(order_field));
				   order_field = rs_2.getString("orderDate");
				   temp_Order.setOrderDate(Date.valueOf(order_field));
				   orders_Of_Specific_Store.add(temp_Order);
			  }
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
			  String getComplaintTable = "SELECT * FROM project.complaint WHERE ComplaintOrderId = " + "'" + Final_Order_Of_Specific_Quarter.get(i).getOrderID() + "'" + ";";
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
		  
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + temp_Store_Id + "'" + ";"; 
		  ResultSet rs_2 = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  int Integer_Help_Month_In_Order_Table;
		  int Real_Quarter_Number = Integer.parseInt(temp_Report.getQaurterReportNumber());
		  String String_Help_Date_In_Order_Table;
		  
		  while(rs_2.next())
	 	  {
			  String_Help_Date_In_Order_Table = rs_2.getString("orderDate");
			  Integer_Help_Month_In_Order_Table = Integer.parseInt(String_Help_Date_In_Order_Table.substring(5, 7));
			  if(((Integer_Help_Month_In_Order_Table + 2) / 3) == Real_Quarter_Number)
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
	 	  }
		  
		  /* -------------------------------- Take All The Complaint Of Specific Store ------------------------- */
		  
		  for(int i = 0 ; i < orders_Of_Specific_Store.size() ; i++)
		  {
			  String getComplaintOfSpecificStoreTable = "SELECT * FROM project.complaint WHERE ComplaintOrderId = " + "'" + orders_Of_Specific_Store.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_3 = stmt.executeQuery(getComplaintOfSpecificStoreTable);
			  while(rs_3.next())
		 	  {
				   temp_Complaint = new Complaint();
				   Complaint_Field = rs_3.getString("ComplaintCompansation");
				   temp_Complaint.setComplaintCompansation(Double.parseDouble(Complaint_Field));
				   Complaint_Field = rs_3.getString("ComplaintDate");
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
		  
		  /* UPDATE `project`.`user` SET `UserPermission`='BLOCKED' WHERE `UserName`='DinGolan'; */
		  String UpdateTableUsersPremmision = "UPDATE project.user SET UserPermission =" + "'" + temp_User.getPermission() + "'" + "WHERE UserName=" + "'" + temp_User.getUserName() + "'" + ";" ;
		  
		  /* UPDATE `project`.`user` SET `UserStatus`='STORE_MANAGER' WHERE `UserName`='DinGolan'; */
		  String UpdateTableUsersStatus = "UPDATE project.user SET UserStatus =" + "'" + temp_User.getStatus() + "'" + "WHERE UserName=" + "'" + temp_User.getUserName() + "'" + ";" ;
		  
		  stmt.executeUpdate(UpdateTableUsersPremmision);
		  stmt.executeUpdate(UpdateTableUsersStatus);
	  } 
	  catch (SQLException e) {	e.printStackTrace();}	  
  }
  
     @SuppressWarnings("unchecked")
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
     
     @SuppressWarnings("unchecked")
  protected void addSurveyResult(Object msg, Connection conn) {
    	 int id = ((ArrayList<Integer>)(((Message)msg).getMsg())).get(0);
    	 resulrId.add(id);
    	        String query = "INSERT INTO project.survey_result (Surveyid, sumQ1 ,sumQ2, sumQ3, sumQ4, sumQ5, sumQ6 , numOfClients)"
    	                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    	        try {
    	              PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
    	              preparedStmt.setInt(1, id);
    	              preparedStmt.setInt (2, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(1));
    	              preparedStmt.setInt (3, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(2));
    	              preparedStmt.setInt (4, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(3));
    	              preparedStmt.setInt (5, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(4));
    	              preparedStmt.setInt (6, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(5));
    	              preparedStmt.setInt (7, ((ArrayList<Integer>)(((Message)msg).getMsg())).get(6));
    	              preparedStmt.setInt (8, 1);
    	              preparedStmt.execute();
    	        }catch (SQLException e) {	e.printStackTrace();}	

    	 //}
     }
     
     @SuppressWarnings("unchecked")
  protected void updateSurveyResult(Object msg, Connection conn) {
	  		Statement stmt;
	  		ArrayList<Integer> temp = new ArrayList<Integer>();
	  		ArrayList<Integer> oldId = new ArrayList<Integer>();

	  		temp =(ArrayList<Integer>)(((Message)msg).getMsg());
		 try {
			  stmt = conn.createStatement();
			  String getSurveyTable = "SELECT * FROM project.survey_result WHERE Surveyid="+temp.get(0)+";"; // get the survey that connected to new survey id of exist
			  ResultSet rs = stmt.executeQuery(getSurveyTable);
			  while(rs.next())  // get all the surveys
		 	{
				  oldId.add(rs.getInt("Surveyid"));
				  oldId.add(rs.getInt("sumQ1"));
				  oldId.add(rs.getInt("sumQ2"));
				  oldId.add(rs.getInt("sumQ3"));
				  oldId.add(rs.getInt("sumQ4"));
				  oldId.add(rs.getInt("sumQ5"));
				  oldId.add(rs.getInt("sumQ6"));
				  oldId.add(rs.getInt("numOfClients"));
		 	}

			  
			  // update the survey to sum the answar
			  int sum = temp.get(1)+oldId.get(1);
			  String updateSurveyTable = "UPDATE survey_result SET sumQ1 =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =temp.get(2)+oldId.get(2);
			   updateSurveyTable = "UPDATE survey_result SET sumQ2 =" + "'" +  sum + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =temp.get(3)+oldId.get(3);
			   updateSurveyTable = "UPDATE survey_result SET sumQ3 =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =temp.get(4)+oldId.get(4);
			   updateSurveyTable = "UPDATE survey_result SET sumQ4 =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =temp.get(5)+oldId.get(5);
			   updateSurveyTable = "UPDATE survey_result SET sumQ5 =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =temp.get(6)+oldId.get(6);
			   updateSurveyTable = "UPDATE survey_result SET sumQ6 =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
			  stmt.executeUpdate(updateSurveyTable);
			  sum =oldId.get(7)+1;
			   updateSurveyTable = "UPDATE survey_result SET numOfClients =" + "'" + sum  + "'" + "WHERE Surveyid=" +"'" +temp.get(0) + "'" +";";
				  stmt.executeUpdate(updateSurveyTable);

		  } 
		  catch (SQLException e) {	e.printStackTrace();}	
	  

    	 
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
		  pr = new Product();
		  pr.setpID(rs.getString("ProductID"));
		  pr.setpName(rs.getString("ProductName"));
		  pr.setpType(ProductType.valueOf(rs.getString("productType")));
		  pr.setpPrice(rs.getDouble("productPrice"));
		  products.add(pr);
	 	}
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return products;
  }
  
  protected ArrayList<User> getUsersFromDB(Connection conn) /* This method get Users table details from DB */
  {
	  ArrayList<User> users_Before_Change = new ArrayList<User>();
	  ArrayList<User> Users_With_Negetive_Account = new ArrayList<User>();
	  ArrayList<User> users_After_Change = new ArrayList<User>();
	  Statement stmt;
	  String user_Field;
	  String user_Account_Field;
	  User temp_User = null;
	  try {
		  stmt = conn.createStatement();
		  String getUsersTable = "SELECT * FROM project.user ;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getUsersTable);
		  while(rs.next())
	 	  {
			  	  temp_User = new User();
				  user_Field = rs.getString("UserId");
				  temp_User.setId(user_Field);
				  user_Field = rs.getString("UserName");
				  temp_User.setUserName(user_Field);
				  user_Field = rs.getString("UserPhone");
				  temp_User.setPhone(user_Field);
				  user_Field = rs.getString("UserPassword");
				  temp_User.setPassword(user_Field);
				  user_Field = rs.getString("UserPermission");
				  temp_User.setPermission(User.UserPermission.valueOf(user_Field));
				  user_Field = rs.getString("UserStatus");
				  temp_User.setStatus(User.UserStatus.valueOf(user_Field));
				  users_Before_Change.add(temp_User);
	 	  }
		  
		  temp_User = new User();
		  String getAccountUser_With_Negetive_Balance = "SELECT * FROM project.account WHERE AccountBalanceCard < 0 ;"; 
		  ResultSet rs_4 = stmt.executeQuery(getAccountUser_With_Negetive_Balance);
		  while(rs_4.next())
		  {
			  temp_User = new User();
			  user_Account_Field = rs_4.getString("AccountUserId");
			  temp_User.setId(user_Account_Field);
			  Users_With_Negetive_Account.add(temp_User);
		  }
		  
		  for(int i = 0 ; i < Users_With_Negetive_Account.size() ; i++)
		  {
			  String Update_The_User_Table_With_User_With_Negetive_Balanced = "UPDATE project.user SET UserStatus =" + "'" + "BLOCKED" + "'" + "WHERE UserId=" + "'" + Users_With_Negetive_Account.get(i).getId() + "'" + ";" ;
			  stmt.executeUpdate(Update_The_User_Table_With_User_With_Negetive_Balanced);
		  }
		  
		  temp_User = new User();
		  String getUsersTable_After_Change = "SELECT * FROM project.user;"; /* Get all the Table from the DB */
		  ResultSet rs_5 = stmt.executeQuery(getUsersTable_After_Change);
		  while(rs_5.next())
	 	  {
			  	  temp_User = new User();
				  user_Field = rs_5.getString("UserId");
				  temp_User.setId(user_Field);
				  user_Field = rs_5.getString("UserName");
				  temp_User.setUserName(user_Field);
				  user_Field = rs_5.getString("UserPhone");
				  temp_User.setPhone(user_Field);
				  user_Field = rs_5.getString("UserPassword");
				  temp_User.setPassword(user_Field);
				  user_Field = rs_5.getString("UserPermission");
				  temp_User.setPermission(User.UserPermission.valueOf(user_Field));
				  user_Field = rs_5.getString("UserStatus");
				  temp_User.setStatus(User.UserStatus.valueOf(user_Field));
				  users_After_Change.add(temp_User);
	 	  }
	  } 
	  catch (SQLException e)
	  {	
		  e.printStackTrace();
	  }	
	  
	  return users_After_Change;
  }
  
  protected ArrayList<Integer> getAllOrdersToCustomer(Object msg, Connection conn) //this method get all the orders that match to specific customer
  {
	  String requestedCustomerId=(String)(((Message)msg).getMsg());
	  ArrayList<Integer> ordersNums = new ArrayList<Integer>();
	  Statement stmt;
	  Integer co;

	  try {
		  	stmt = conn.createStatement();
		  	String getCustomerExist = "SELECT * FROM project.user WHERE UserId='"+requestedCustomerId+"'AND UserPermission='CUSTOMER';"; // get if the customer is already at DB
		  	ResultSet rs1 = stmt.executeQuery(getCustomerExist);
		  	if(rs1.isBeforeFirst()) //we have customer at DB
		  	{
		  		stmt = conn.createStatement();
		  		String getOrders = "SELECT * FROM project.order WHERE customerID='"+requestedCustomerId+"';"; //get all the orders numbers that connected to this customer
		  		ResultSet rs2 = stmt.executeQuery(getOrders);
		  		if(rs2.isBeforeFirst()) //we have orders to this customer at DB
		  		{
		  			while(rs2.next())
		  			{
		  				co = rs2.getInt("orderID");
		  				ordersNums.add(co);
		  			}
		  		}
		  		else
		  			ordersNums.add(-1); //to know that we didn't have orders to this customer at DB
		  	}
		  	else
		  		ordersNums.add(-2); //to know that we didn't have this customer at DB
		  } catch (SQLException e) {	e.printStackTrace();}			  
	  return ordersNums;
  }
  
  protected ArrayList<Integer> getAllComplaintsForWorker(Object msg, Connection conn) //this method get all the complaints that match to specific customer service worker
  {
	  String requestedCustomerServiceWorkerName=(String)(((Message)msg).getMsg());
	  ArrayList<Integer> complaintsNums = new ArrayList<Integer>();
	  Statement stmt;
	  Integer co;

	  try {
		  stmt = conn.createStatement();
		  String getComplaints = "SELECT * FROM project.complaint WHERE ComplaintServiceWorkerUserName='"+requestedCustomerServiceWorkerName+"' AND ComplaintStatus!='CLOSE';"; //get all the unclosed complaints numbers that connected to this customer service worker
		  ResultSet rs = stmt.executeQuery(getComplaints);
		  if(rs.isBeforeFirst()) //we have complaints to this customer at DB
		  {
			  while(rs.next())
			  {
				  co = rs.getInt("ComplaintNum");
				  complaintsNums.add(co);
			  }
		  }
		  else
			  complaintsNums.add(-1); //to know that we didn't have complaints to handle to this customer service worker at DB
		  } catch (SQLException e) {	e.printStackTrace();}			  
	  return complaintsNums;
  }
  
  protected Account addNewAccountToDB(Object msg, Connection conn) //this method add new account to DB
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
  
  protected Complaint addNewComplaintToDB(Object msg, Connection conn) //this method add new complaint to DB
  {
	  Complaint newComplaint = (Complaint)(((Message)msg).getMsg());
	  Complaint complaint=new Complaint();
	  Statement stmt;	  
	  try {
		  stmt = conn.createStatement(); //this statement check if we didn't have this complaint in the DB
		  String getComplaintexist = "SELECT * FROM project.complaint WHERE ComplaintOrderId="+newComplaint.getComplaintOrderId()+" AND ComplaintDetails='"+newComplaint.getComplaintDetails()+"';"; // get the complaint that already at DB (if the order is the same and the details of the complaint are the same)
		  ResultSet rs = stmt.executeQuery(getComplaintexist);
		  if(!rs.isBeforeFirst()) //this statement try to enter new complaint to the DB  
		  {
			  //stmt = conn.createStatement();
			  //String getCustomerServiceWorkerExist = "SELECT * FROM project.user WHERE UserName='"+newComplaint.getComplaintServiceWorkerUserName()+"' AND UserPermission='CUSTOMER_SERVICE_WORKER'"+";"; // get if the customer service worker is at DB
			  //ResultSet rs1 = stmt.executeQuery(getCustomerServiceWorkerExist);
			  //if(rs1.isBeforeFirst()) //we have customer service worker connected to this name at DB
			 // {
				  stmt = conn.createStatement(); 
				  String InsertComplaint = "INSERT INTO project.complaint(ComplaintUserId, ComplaintStatus, ComplaintDate, ComplaintDetails, ComplaintOrderId, ComplaintServiceWorkerUserName)" + 
						"VALUES('"+newComplaint.getComplaintUserId()+"','"+newComplaint.getComplaintStat()+"','"+newComplaint.getComplaintDate()+"','"+newComplaint.getComplaintDetails()+"',"+newComplaint.getComplaintOrderId()+",'"+newComplaint.getComplaintServiceWorkerUserName()+"');";
				  stmt.executeUpdate(InsertComplaint);	
				  //complaint.setComplaintNum(newComplaint.getComplaintNum());
				  complaint.setComplaintStat(newComplaint.getComplaintStat());
				  complaint.setComplaintUserId(newComplaint.getComplaintUserId());
				  complaint.setComplaintDate(newComplaint.getComplaintDate());
				  complaint.setComplaintDetails(newComplaint.getComplaintDetails());
				  complaint.setComplaintOrderId(newComplaint.getComplaintOrderId());
				  complaint.setComplaintServiceWorkerUserName(newComplaint.getComplaintServiceWorkerUserName());		  	  
			 // }//אולי לבטל את כל הSET הזה
			 // else
				  complaint.setComplaintDetails("Customer service worker doesn't exist");  					    
		  }
		  else //if this complaint is already exist
			  complaint.setComplaintDetails("Complaint already exist");

	  } catch (SQLException e) {	e.printStackTrace();}	  
	  return complaint;
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
  
  protected ArrayList<Integer> getSurvey(Object msg, Connection conn){
	  
	  ArrayList<Integer> Id = new ArrayList<Integer>();
	  Statement stmt;
	  try {
		  stmt = conn.createStatement();
		  String getSurveyTable = "SELECT * FROM survey;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getSurveyTable);
		  while(rs.next())
	 	{
			  Id.add(rs.getInt("Surveyid"));

	 	}
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return Id;
  }
  
  protected void AddNewOrderToDB(Object msg, Connection conn) //this method add new account to DB
  {
	  Order newOrder = (Order)(((Message)msg).getMsg());
	  Statement stmt;	  
	  try {
			  stmt = conn.createStatement(); 
			  String InsertAccountToID = "INSERT INTO project.order(customerID, orderSupplyOption, orderTotalPrice, orderRequiredSupplyDate, orderRequiredSupplyTime, orderRecipientAddress , orderRecipientName , orderRecipientPhoneNumber, orderPostcard ,orderDate)" + 
			  		"VALUES('"+newOrder.getCustomerID()+"','"+newOrder.getSupply()+ "',"+newOrder.getOrderTotalPrice()+",'"+newOrder.getRequiredSupplyDate()+"','"+newOrder.getRequiredSupplyTime()+"','"+newOrder.getRecipientAddress()+"','"+newOrder.getRecipientName()+"','"+newOrder.getRecipienPhoneNum()+"','"+newOrder.getPostCard()+"','"+newOrder.getOrderDate()+"');";
			  stmt.executeUpdate(InsertAccountToID);	 
		 
	  } catch (SQLException e) {	e.printStackTrace();}	
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

