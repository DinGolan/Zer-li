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
import com.mysql.jdbc.PreparedStatement;

import boundery.ReportUI;
import entity.Account;
import entity.Complaint;
import entity.Message;
import entity.Order;
import entity.Product;
import entity.Product.ProductType;
import entity.Store;
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
	    
	    if(((Message)msg).getOption().compareTo("Take All the Revenue Of Specific Store") == 0) /* Taking All the Revenue Of Specific Store */							
	    {
	    	((Message)msg).setMsg(GetTheRevenueOfSpecificStoreFromDB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Take The Orders Of Specific Store") == 0) 	    /* Taking All the Orders Of Specific Store */							
	    {
	    	((Message)msg).setMsg(GetOrdersFromDB(msg,conn));  
	    	this.sendToAllClients(msg);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Take The Complaints Of Specific Store") == 0) 	    /* Taking All the Complaints Of Specific Store */							
	    {
	    	((Message)msg).setMsg(GetComplaintsFromDB(msg,conn));  
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
  protected ArrayList<Order> GetOrdersFromDB(Object msg , Connection conn) /* This method get Orders Of Specific Store from DB */
  {
	  ArrayList<Order> orders = (ArrayList<Order>)(((Message)msg).getMsg());
	  Statement stmt;
	  String order_field;
	  String product_In_OrderField;
	  Order temp_Order;
	  Product temp_Product;
	  
	  /* -------------------------------- We Take The Order Of Specific Store ------------------------- */
	  
	  try {
		  stmt = conn.createStatement();
		  String getOrdersNumbersTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + orders.get(0).getStoreId() + "'" + ";"; 
		  orders.clear();
		  ResultSet rs = stmt.executeQuery(getOrdersNumbersTable);
		  
		  while(rs.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   orders.add(temp_Order);
	 	  }
		  
		  /* -------------------------------- We Take The Products Type Of Each Order ------------------------- */
		  
		  for(int i = 0 ; i < orders.size() ; i++)
		  {
			  ArrayList<Product> productsInOrder = new ArrayList<Product>();
			  String getOrdersProductTable = "SELECT * FROM project.productinorder WHERE OrderID = " + "'" + orders.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_2 = stmt.executeQuery(getOrdersProductTable);
			  while(rs_2.next())
		 	  {
				   temp_Product = new Product();
				   product_In_OrderField = rs_2.getString("ProductType");
				   temp_Product.setpType(ProductType.valueOf(product_In_OrderField));
				   productsInOrder.add(temp_Product);
		 	  }  
			  
			  orders.get(i).setProductsInOrder(productsInOrder);
		  }
		  
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return orders;
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
  protected ArrayList<Complaint> GetComplaintsFromDB(Object msg , Connection conn)
  {
	  ArrayList<Order> orders = (ArrayList<Order>)(((Message)msg).getMsg());
	  ArrayList<Complaint> complaints = new ArrayList<Complaint>();
	  Statement stmt;
	  String order_field;
	  String complaint_Field;
	  Order temp_Order;
	  Complaint temp_Complaint;
	  
	  /* -------------------------------- We Take The Order Of Specific Store ------------------------- */
	  
	  try {
		  stmt = conn.createStatement();
		  String getOrdersNumbersTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + orders.get(0).getStoreId() + "'" + ";"; 
		  orders.clear();
		  ResultSet rs = stmt.executeQuery(getOrdersNumbersTable);
		  
		  while(rs.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   orders.add(temp_Order);
	 	  }
		  
		  /* -------------------------------- We Take The Complaints Of Each Order ------------------------- */
		  
		  for(int i = 0 ; i < orders.size() ; i++)
		  {
			  String getComplaintTable = "SELECT * FROM project.complaint WHERE OrderID = " + "'" + orders.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_2 = stmt.executeQuery(getComplaintTable);
			  while(rs_2.next())
		 	  {
				  temp_Complaint = new Complaint();
				  complaint_Field = rs_2.getString("complaintMonth");
				  temp_Complaint.setComplaintMonth(complaint_Field);
				  complaints.add(temp_Complaint);
		 	  }  
		  }
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return complaints;
  }
  
  protected Store GetTheRevenueOfSpecificStoreFromDB(Object msg , Connection conn)
  {
	  Store temp_Store_With_ID = (Store)(((Message)msg).getMsg());
	  Store Store_To_Return = new Store();
	  ArrayList<Order> orders = new ArrayList<Order>();
	  ArrayList<Complaint> Complaint_Of_Specific_Store = new ArrayList<Complaint>();
	  ReportUI.report_For_Take_Quarter.setQaurterReportNumber(temp_Store_With_ID.getStore_Address()); /* temp_Store_With_ID.getStore_Address() - Bring Me Only The Number Of the Quarter Because Its Help Me TO work With The DB */
	  temp_Store_With_ID.setStore_Address(null);
	  Statement stmt;
	  double Sum_Of_Revenue = 0;
	  double Sum_Of_Compansation = 0;
	  String order_field;
	  String Complaint_Field;
	  Order temp_Order;
	  Complaint temp_Complaint;
	  try {
		  stmt = conn.createStatement();
		  String getOrdersOfSpecificStoreTable = "SELECT * FROM project.order WHERE StoreID = " + "'" + temp_Store_With_ID.getStoreId() + "'" + ";"; 
		  ResultSet rs = stmt.executeQuery(getOrdersOfSpecificStoreTable);
		  
		  while(rs.next())
	 	  {
			   temp_Order = new Order();
			   order_field = rs.getString("orderID");
			   temp_Order.setOrderID(Integer.parseInt(order_field));
			   order_field = rs.getString("orderTotalPrice");
			   temp_Order.setOrderTotalPrice(Double.parseDouble(order_field));
			   order_field = rs.getString("orderDate");
			   temp_Order.setOrderDate(Date.valueOf(order_field));
			   orders.add(temp_Order);
	 	  }
		  
		  /* -------------------------------- We Take The Compensation of Each Complaint About The Order's Of Specific Store ------------------------- */
		  
		  for(int i = 0 ; i < orders.size() ; i++)
		  {
			  String getComplaintOfSpecificStoreTable = "SELECT * FROM project.complaint WHERE OrderID = " + "'" + orders.get(i).getOrderID() + "'" + ";";
			  ResultSet rs_2 = stmt.executeQuery(getComplaintOfSpecificStoreTable);
			  while(rs_2.next())
		 	  {
				   temp_Complaint = new Complaint();
				   Complaint_Field = rs_2.getString("complaintDate");
				   temp_Complaint.setComplaintDate(Date.valueOf(Complaint_Field));
				   Complaint_Field = rs_2.getString("complaintCompansation");
				   temp_Complaint.setComplaintCompansation(Double.parseDouble(Complaint_Field));
				   Complaint_Of_Specific_Store.add(temp_Complaint);
		 	  }  
		  }
		  
		  /* -------------------------------- Calculate The Sum of Compensation Of Specific Store ------------------------- */
		  
		  for(int i = 0 ; i < Complaint_Of_Specific_Store.size() ; i++)
		  {
			  Sum_Of_Compansation += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
		  }
		  
		  /* -------------------------------- Calculate The Sum of Revenue Of Specific Store ------------------------- */
		  
		  for(int i = 0 ; i < orders.size() ; i++)
		  {
			  Sum_Of_Revenue += orders.get(i).getOrderTotalPrice();
		  }
		  
		  /* -------------------------------- The Final Sum ------------------------- */
		  
		  Sum_Of_Revenue = Sum_Of_Revenue - Sum_Of_Compansation;
		  
		  /* -------------------------------- Update The DB ------------------------- */
		  
		  String Update_Table_Store_Revenue = "UPDATE project.store SET TotalRevenue =" + "'" + Sum_Of_Revenue + "'" + "WHERE StoreID=" + "'" + temp_Store_With_ID.getStoreId() + "'" + ";" ;
		  stmt.executeUpdate(Update_Table_Store_Revenue);
		  
		  /* -------------------------------- Calculate The Revenue According To Quarter ------------------------- */
		  
		  String temp_Date_String;
		  Date temp_Date;
		  int temp_Date_Integer = 0;
		  double Revenue_Of_Specific_Quarter = 0;
		  
		  for(int i = 0 ; i < orders.size() ; i++)
		  {
			  if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("1") == 0)
			  {
				  temp_Date = orders.get(i).getOrderDate();
				  temp_Date_String = String.valueOf(temp_Date);
				  temp_Date_String = temp_Date_String.substring(6, 7);
				  temp_Date_Integer = Integer.parseInt(temp_Date_String);
				  if(temp_Date_Integer == 1 || temp_Date_Integer == 2 || temp_Date_Integer == 3)
				  {
					  Revenue_Of_Specific_Quarter += orders.get(i).getOrderTotalPrice();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("2") == 0)
			  {
				  temp_Date = orders.get(i).getOrderDate();
				  temp_Date_String = String.valueOf(temp_Date);
				  temp_Date_String = temp_Date_String.substring(6, 7);
				  temp_Date_Integer = Integer.parseInt(temp_Date_String);
				  if(temp_Date_Integer == 4 || temp_Date_Integer == 5 || temp_Date_Integer == 6)
				  {
					  Revenue_Of_Specific_Quarter += orders.get(i).getOrderTotalPrice();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("3") == 0)
			  {
				  temp_Date = orders.get(i).getOrderDate();
				  temp_Date_String = String.valueOf(temp_Date);
				  temp_Date_String = temp_Date_String.substring(6, 7);
				  temp_Date_Integer = Integer.parseInt(temp_Date_String);
				  if(temp_Date_Integer == 7 || temp_Date_Integer == 8 || temp_Date_Integer == 9)
				  {
					  Revenue_Of_Specific_Quarter += orders.get(i).getOrderTotalPrice();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("4") == 0)
			  {
				  temp_Date = orders.get(i).getOrderDate();
				  temp_Date_String = String.valueOf(temp_Date);
				  temp_Date_String = temp_Date_String.substring(5, 7);
				  temp_Date_Integer = Integer.parseInt(temp_Date_String);
				  if(temp_Date_Integer == 10 || temp_Date_Integer == 11 || temp_Date_Integer == 12)
				  {
					  Revenue_Of_Specific_Quarter += orders.get(i).getOrderTotalPrice();
				  }
			  }
		  }
		  
		  /* -------------------------------- Calculate The Compensation According To Quarter ------------------------- */
		  
		  String temp_Date_Complaint_String;
		  Date temp_Date_Complaint;
		  int temp_Date_Complaint_Integer = 0;
		  double Compensation_Of_Specific_Quarter = 0;
		  
		  for(int i = 0 ; i < Complaint_Of_Specific_Store.size() ; i++)
		  {
			  if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("1") == 0)
			  {
				  temp_Date_Complaint = Complaint_Of_Specific_Store.get(i).getComplaintDate();
				  temp_Date_Complaint_String = String.valueOf(temp_Date_Complaint);
				  temp_Date_Complaint_String = temp_Date_Complaint_String.substring(6, 7);
				  temp_Date_Complaint_Integer = Integer.parseInt(temp_Date_Complaint_String);
				  if(temp_Date_Complaint_Integer == 1 || temp_Date_Complaint_Integer == 2 || temp_Date_Complaint_Integer == 3)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("2") == 0)
			  {
				  temp_Date_Complaint = Complaint_Of_Specific_Store.get(i).getComplaintDate();
				  temp_Date_Complaint_String = String.valueOf(temp_Date_Complaint);
				  temp_Date_Complaint_String = temp_Date_Complaint_String.substring(6, 7);
				  temp_Date_Complaint_Integer = Integer.parseInt(temp_Date_Complaint_String);
				  if(temp_Date_Complaint_Integer == 4 || temp_Date_Complaint_Integer == 5 || temp_Date_Complaint_Integer == 6)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("3") == 0)
			  {
				  temp_Date_Complaint = Complaint_Of_Specific_Store.get(i).getComplaintDate();
				  temp_Date_Complaint_String = String.valueOf(temp_Date_Complaint);
				  temp_Date_Complaint_String = temp_Date_Complaint_String.substring(6, 7);
				  temp_Date_Complaint_Integer = Integer.parseInt(temp_Date_Complaint_String);
				  if(temp_Date_Complaint_Integer == 7 || temp_Date_Complaint_Integer == 8 || temp_Date_Complaint_Integer == 9)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  }
			  }
			  
			  else if(ReportUI.report_For_Take_Quarter.getQaurterReportNumber().compareTo("4") == 0)
			  {
				  temp_Date_Complaint = Complaint_Of_Specific_Store.get(i).getComplaintDate();
				  temp_Date_Complaint_String = String.valueOf(temp_Date_Complaint);
				  temp_Date_Complaint_String = temp_Date_Complaint_String.substring(5, 7);
				  temp_Date_Complaint_Integer = Integer.parseInt(temp_Date_Complaint_String);
				  if(temp_Date_Complaint_Integer == 10 || temp_Date_Complaint_Integer == 11 || temp_Date_Complaint_Integer == 12)
				  {
					  Compensation_Of_Specific_Quarter += Complaint_Of_Specific_Store.get(i).getComplaintCompansation();
				  }
			  }
		  }
		  
		  Revenue_Of_Specific_Quarter = Revenue_Of_Specific_Quarter - Compensation_Of_Specific_Quarter;
		  Store_To_Return.setTotalRevenue(Revenue_Of_Specific_Quarter);
		  Store_To_Return.setStoreId(temp_Store_With_ID.getStoreId());
		  String Store_Field;
		  
		  String getDetailsOfSpecificStoreTable = "SELECT * FROM project.store WHERE StoreID = " + "'" + Store_To_Return.getStoreId() + "'" + ";"; 
		  ResultSet rs_3 = stmt.executeQuery(getDetailsOfSpecificStoreTable);
		  while(rs_3.next())
	 	  {
			  Store_Field = rs_3.getString("StoreAddress");
			  Store_To_Return.setStore_Address(Store_Field);
		      Store_Field = rs_3.getString("QuantityOfOrder");
		      Store_To_Return.setQuantityOfOrders(Integer.parseInt(Store_Field));
	 	  }  
	  }
	  catch (SQLException e) 
	  {	
		  e.printStackTrace();
	  }
	  
	  return Store_To_Return;
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

