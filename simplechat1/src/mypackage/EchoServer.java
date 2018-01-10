package mypackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map.Entry;

import com.mysql.jdbc.PreparedStatement;

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
  public static int counter=1;
	public static ArrayList<Integer> resulrId = new ArrayList<Integer>();
	public static ArrayList<Integer> conclusionId = new ArrayList<Integer>();

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
	    
	    
	    if(((Message)msg).getOption().compareTo("0") == 0) 		/* Check that its update */
	    		UpdateProductName(msg,conn); 
	    
	    if(((Message)msg).getOption().compareTo("get all products in DB") ==0) 	    /* Check that we get from DB Because We want to Initialized */
	    {										
				/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    		((Message)msg).setMsg(getProductsFromDB(conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    if(((Message)msg).getOption().compareTo("get all products in sale from DB") ==0) 	    /* Check that we get from DB Because We want to Initialized */
	    {										
				/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    		((Message)msg).setMsg(getProductsInSaleFromDB(msg, conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    
	    if(((Message)msg).getOption().compareTo("get all stores from DB") ==0) 	    /* Check that we get from DB Because We want to Initialized */
	    {										
	    		((Message)msg).setMsg(getStoresFromDB(conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    if(((Message)msg).getOption().compareTo("Add User To Combo Box From DB") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {			
	    	((Message)msg).setMsg(getUsersFromDB(conn));	
    		this.sendToAllClients(msg);
        }
	    
	    if(((Message)msg).getOption().compareTo("add survey") ==0) // add survey to db
	    {
	    	AddSurveyToDB(msg,conn);
	    }
	    
	    if(((Message)msg).getOption().compareTo("Update User At Data Base") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {										
	    	UpdateUserAtDB(msg,conn);
		}
	    
	    if(((Message)msg).getOption().compareTo("Update customer account") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {						
	    	((Message)msg).setMsg(UpdateUserAccountAtDB(msg,conn));
	    	this.sendToAllClients(msg);	
		}
	    
	    if(((Message)msg).getOption().compareTo("insert order to DB") == 0) //add new Order
	    {
((Message)msg).setMsg(AddNewOrderToDB(msg,conn));	
	    	this.sendToAllClients(msg);<<<<<<< .mine
	    }
		    


=======
	    	this.sendToAllClients(msg);	
	    }
	
	    
>>>>>>> .theirs
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
	    	resulrId = (ArrayList<Integer>)(((Message)msg).getMsg());
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
	    
	    if(((Message)msg).getOption().compareTo("add surveyConclusion") ==0)							
	    {
			addSurveyConclusion(msg,conn);
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
     
     protected void addSurveyConclusion(Object msg, Connection conn) {
    	 int id = Integer.parseInt(((ArrayList<String>)(((Message)msg).getMsg())).get(0));
    	 if(conclusionId.contains(id) == true)//update one raw only once !! mybe add error msg
    		 return;
    	 conclusionId.add(id);
    	        String query = "INSERT INTO project.expertaddconclusion (Surveyid, Conclusion)"
    	                + " VALUES (?, ?)";
    	        try {
    	              PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
    	              preparedStmt.setInt(1, id);
    	              preparedStmt.setString(2, ((ArrayList<String>)(((Message)msg).getMsg())).get(1));
    	              preparedStmt.execute();
    	        }catch (SQLException e) {	e.printStackTrace();}	

    	 //}
     }
     
     protected void addSurveyResult(Object msg, Connection conn) {
    	 int id = ((ArrayList<Integer>)(((Message)msg).getMsg())).get(0);
    	 //resulrId.add(id);
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

     }
     
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
   	  File file = new File("newfile");
   	  FileOutputStream output;
   	  try {
   	      output = new FileOutputStream(file);
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
   		  pr.setImage(rs.getBinaryStream("ProductPicure"));
   		  products.add(pr);
   	 	}
   	  } catch (SQLException e) {e.printStackTrace();} 
   	  catch (FileNotFoundException e){e.printStackTrace(); } 
   	  catch (IOException e) {e.printStackTrace();}	
   	  return products;
     }
     
     protected ArrayList<Product> getProductsInSaleFromDB(Object msg, Connection conn) /* This method get products table details from DB */
     {
   	  ArrayList<Product> products = new ArrayList<Product>();
   	  int storeId = ((Store)(((Message)msg).getMsg())).getStoreId();
   	  Statement stmt;
   	  String p;
   	  Product pr;
   	  try {
   		  stmt = conn.createStatement();
   		  String getProductsTable = "SELECT * FROM productinsale WHERE StoreID = "+storeId+";"; /* Get all the Table from the DB */
   		  ResultSet rs = stmt.executeQuery(getProductsTable);
   		  while(rs.next())
   	 	{
   		  pr = new Product();
   		  pr.setpID(rs.getString("ProductID"));
   		  pr.setpStore(rs.getInt("StoreID"));
   		  pr.setpPrice(rs.getDouble("productPrice"));
   		  products.add(pr);
   	 	}
   	  } catch (SQLException e) {e.printStackTrace();}	
   	  return products;
     }
  
  protected ArrayList<User> getUsersFromDB(Connection conn) /* This method get products table details from DB */
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
			  stmt = conn.createStatement();
			  String getCustomerServiceWorkerExist = "SELECT * FROM project.user WHERE UserName='"+newComplaint.getComplaintServiceWorkerUserName()+"' AND UserPermission='CUSTOMER_SERVICE_WORKER'"+";"; // get if the customer service worker is at DB
			  ResultSet rs1 = stmt.executeQuery(getCustomerServiceWorkerExist);
			  if(rs1.isBeforeFirst()) //we have customer service worker connected to this name at DB
			  {
<<<<<<< .mine
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
			  }//אולי לבטל את כל הSET הזה
			  else
				  complaint.setComplaintDetails("Customer service worker doesn't exist");  					    
























=======
				  stmt = conn.createStatement();
				  String getCustomerExist = "SELECT * FROM project.user WHERE UserId="+newComplaint.getComplaintUserId()+";"; // get if the customer is already at DB
				  ResultSet rs2 = stmt.executeQuery(getCustomerExist);
				  if(rs2.isBeforeFirst()) //we have customer at DB
				  {
					  stmt = conn.createStatement();
					  String getOrderToCustomerExist = "SELECT * FROM project.order WHERE orderID="+newComplaint.getComplaintOrderId()+"AND customerID="+newComplaint.getComplaintUserId()+";"; // get if the customer match to this order at DB
					  ResultSet rs3 = stmt.executeQuery(getOrderToCustomerExist);
					  if(rs3.isBeforeFirst()) //we have customer connected to this order at DB
					  {
						  stmt = conn.createStatement();
						  String getCustomerServiceWorkerExist = "SELECT * FROM project.user WHERE UserName="+newComplaint.getComplaintServiceWorkerUserName()+"AND UserPermission='CUSTOMER SERVICE WORKER'"+";"; // get if the customer service worker is at DB
						  ResultSet rs4 = stmt.executeQuery(getCustomerServiceWorkerExist);
						  if(rs4.isBeforeFirst()) //we have customer service worker connected to this name at DB
						  {
							  stmt = conn.createStatement(); 
							  String InsertComplaint = "INSERT INTO project.complaint(ComplaintNum, ComplaintUserId, ComplaintStatus, ComplaintDate, ComplaintDetails, ComplaintOrderId, ComplaintServiceWorkerUserName, ComplaintCompanyServiceWorkerAnswer, ComplaintCompansation)" + 
							  		"VALUES("+newComplaint.getComplaintNum()+","+newComplaint.getComplaintUserId()+",'"+newComplaint.getComplaintStat()+"','"+newComplaint.getComplaintDate()+"',"+newComplaint.getComplaintDetails()+","+newComplaint.getComplaintOrderId()+","+newComplaint.getComplaintServiceWorkerUserName()+","+newComplaint.getComplaintCompanyServiceWorkerAnswer()+","+newComplaint.getComplaintCompansation()+");";
							  stmt.executeUpdate(InsertComplaint);	 //����� �� ���� ������ ����� ����� ��� ��� ������
							  complaint.setComplaintNum(newComplaint.getComplaintNum());
							  complaint.setComplaintStat(newComplaint.getComplaintStat());
							  complaint.setComplaintUserId(newComplaint.getComplaintUserId());
							  complaint.setComplaintDate(newComplaint.getComplaintDate());
							  complaint.setComplaintDetails(newComplaint.getComplaintDetails());
							  complaint.setComplaintOrderId(newComplaint.getComplaintOrderId());
							  complaint.setComplaintServiceWorkerUserName(newComplaint.getComplaintServiceWorkerUserName());
						  }
						  else
							  complaint.setComplaintDetails("Customer service worker doesn't exist");  					  
					  }
					  else //if the order match to other customer
						  complaint.setComplaintDetails("Customer id and this order number doesn't match");				  
				  }
				  else //if the customer id that complain is wrong
					  complaint.setComplaintDetails("Customer id that complain doesn't exist");
			  }
			  else //if the order number to complain is wrong
				  complaint.setComplaintDetails("Order number to complain doesn't exist");	  
>>>>>>> .theirs
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
  
  
  protected String AddNewOrderToDB(Object msg, Connection conn) //this method add new account to DB
  {
	  Order newOrder = (Order)(((Message)msg).getMsg());
	  int orderID=0;
	  Statement stmt;
	  Account.PaymentMethod method = null;
	  try {
			  stmt = conn.createStatement(); 
			  String InsertAccountToID = "SELECT AccountPaymentMethod FROM project.account WHERE AccountUserId = '"+newOrder.getCustomerID()+"' AND AccountStoreId= "+newOrder.getStoreID()+";";
			  ResultSet rs = stmt.executeQuery(InsertAccountToID);
			  while(rs.next())
			 	{
				  method=Account.PaymentMethod.valueOf(rs.getString("AccountPaymentMethod"));
			 	}
			  if(method != null) {
			  InsertAccountToID = "INSERT INTO project.order(customerID, orderSupplyOption, orderTotalPrice, orderRequiredSupplyDate, orderRequiredSupplyTime, orderRecipientAddress , orderRecipientName , orderRecipientPhoneNumber, orderPostcard ,orderDate, StoreID ,paymentMethod)" + 
			  		"VALUES('"+newOrder.getCustomerID()+"','"+newOrder.getSupply()+ "',"+newOrder.getOrderTotalPrice()+",'"+newOrder.getRequiredSupplyDate()+"','"+newOrder.getRequiredSupplyTime()+"','"+newOrder.getRecipientAddress()+"','"+newOrder.getRecipientName()+"','"+newOrder.getRecipienPhoneNum()+"','"+newOrder.getPostCard()+"','"+newOrder.getOrderDate()+"' , "+newOrder.getStoreID()+",'"+method+"');";
			  stmt.executeUpdate(InsertAccountToID);
			  InsertAccountToID = "SELECT orderID FROM project.`order` WHERE customerID = '"+newOrder.getCustomerID()+"' AND orderDate = '"+newOrder.getOrderDate()+"' AND orderTotalPrice = "+newOrder.getOrderTotalPrice()+" AND orderRequiredSupplyTime ='"+newOrder.getRequiredSupplyTime()+"';";
			  rs = stmt.executeQuery(InsertAccountToID);
			  while(rs.next())
			 	{
			  orderID=rs.getInt("orderID");
			 	}
			  for(Entry<Product, Integer> e : ((Order)(((Message)msg).getMsg())).getProductsInOrder().entrySet())
			  {
				  InsertAccountToID = "INSERT INTO project.productinorder(ProductID, OrderID, QuantityOfProduct, ProductType, ProductName, productPrice)"+ 
					  		"VALUES('"+e.getKey().getpID()+"',"+orderID+ ","+e.getValue()+",'"+e.getKey().getpType()+"','"+e.getKey().getpName()+"',"+e.getKey().getpPrice()+");";
					  stmt.executeUpdate(InsertAccountToID);
			  }
			  return "";
			  }
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return "No account";
  }
  
  protected ArrayList<Store> getStoresFromDB(Connection conn) /* This method get products table details from DB */
  {
	  ArrayList<Store> stores = new ArrayList<Store>();
	  Statement stmt;
	  String p;
	  Store pr;
	  try {
		  stmt = conn.createStatement();
		  String getProductsTable = "SELECT * FROM store;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getProductsTable);
		  while(rs.next())
	 	{
		  pr = new Store();
		  pr.setStoreId(rs.getInt("StoreID"));
		  pr.setStore_Address(rs.getString("StoreAddress"));
		  stores.add(pr);
	 	}
	  } catch (SQLException e) {	e.printStackTrace();}	
	  return stores;
  }
  
  
  protected Account UpdateUserAccountAtDB(Object msg, Connection conn) /* This Method Update the DB */
  {
	  Account account = new Account();
	  Statement stmt;
	  double prevBalance=0;
	  Account.PaymentArrangement arrangement = null;
	  Order customerOrder = (Order)((Message)msg).getMsg();
	  try {
		  stmt = conn.createStatement();
		  String getCustomerAccount = "SELECT * FROM project.account WHERE AccountUserId='"+customerOrder.getCustomerID()+"'; " ;
		  ResultSet rs = stmt.executeQuery(getCustomerAccount);
		  while(rs.next())
		 	{
		  prevBalance = rs.getDouble("AccountBalanceCard");
		  arrangement = Account.PaymentArrangement.valueOf(rs.getString("AccountPaymentArrangement"));
		 	}
		  if(arrangement.equals(Account.PaymentArrangement.FULLPRICE))
			  prevBalance -= customerOrder.getOrderTotalPrice();
		  else if(arrangement.equals(Account.PaymentArrangement.ANNUAL))
			  prevBalance -= customerOrder.getOrderTotalPrice()*0.9;
		  else if(arrangement.equals(Account.PaymentArrangement.MONTHLY))
			  prevBalance -= customerOrder.getOrderTotalPrice()*0.95;		  
		  String UpdateTableAccount = "UPDATE project.account SET AccountBalanceCard =" +  prevBalance  + "WHERE AccountUserId='"+customerOrder.getCustomerID()+"'; " ;
		  stmt.executeUpdate(UpdateTableAccount);
		  getCustomerAccount = "SELECT * FROM project.account WHERE AccountUserId='"+customerOrder.getCustomerID()+"'; " ;
		  rs = stmt.executeQuery(getCustomerAccount);
		  while(rs.next())
		 	{
			  account.setAccountUserId(rs.getString("AccountUserId"));
			  account.setAccountBalanceCard(rs.getDouble("AccountBalanceCard"));
			  account.setAccountCreditCardNum(rs.getString("AccountCreditCardNum"));
			  account.setAccountSubscriptionEndDate(rs.getDate("AccountSubscriptionEndDate"));
			  account.setAccountPaymentMethod(Account.PaymentMethod.valueOf(rs.getString("AccountPaymentMethod")));
			  account.setAccountPaymentArrangement(Account.PaymentArrangement.valueOf(rs.getString("AccountPaymentArrangement")));
		 	}
	  } 
	  catch (SQLException e) {	e.printStackTrace();}	  
	  return account;
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
    

  System.out.println("Please enter the mySQL scheme name:");
		Scanner scanner = new Scanner(System.in);
		 name= scanner.next();
		 url = "jdbc:mysql://localhost/"+name;/* Enter jbdc mySQL */
		//String sql = "jdbc:mysql://localhost/project";
	
	System.out.println("Please enter the mySQL user name:");
		 username =scanner.next(); /* Enter mySQL name */
	
	System.out.println("Please enter the mySQL password:");
		 password = scanner.next(); /* Enter mySQL password */
	
    
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

