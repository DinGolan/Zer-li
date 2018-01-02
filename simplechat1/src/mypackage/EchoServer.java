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


import entity.Account;


import com.mysql.jdbc.PreparedStatement;

import boundery.AccountUI;
import entity.Message;
import entity.Product;
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
  public static int counter=2;
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
	    
	    if(((Message)msg).getOption().compareTo("1") ==0) 	    /* Check that we get from DB Because We want to Initialized */
	    {										
				/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    		((Message)msg).setMsg(getProductsFromDB(conn));	    
	    		this.sendToAllClients(msg);
  		}
	    
	    if(((Message)msg).getOption().compareTo("Add User To Combo Box From DB") == 0) 	    /* Check that we get from DB Because We want to Initialized */
        {										
			/* ArrayList<Product> aa = new ArrayList<Product>(); */
	    	((Message)msg).setMsg(getProductsFromDB(conn));	    
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
	    
	    if(((Message)msg).getOption().compareTo("Add new account") == 0) //check if we add new account
        {
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
	  ArrayList<String> temp = new ArrayList<String>();
	  ArrayList<String> temp2 = (ArrayList<String>)(((Message)msg).getMsg());

	  for (String s : temp2) 
	  {
		  	temp.add(s);
	  }
	  Statement stmt;
	  try {
		  stmt = conn.createStatement();
		  String createTablecourses = "UPDATE project.user SET UserPremission =" + "'" + temp.get(4) + "'" + "WHERE UserStatus=" +"'" +temp.get(5) + "'" +";";
		  stmt.executeUpdate(createTablecourses);
			
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
		  pr = new Product("", "", "");
		  p = rs.getString("ProductID");
		  pr.setpID(p);
		  p=rs.getString("ProductName");
		  pr.setpName(p);
		  p= rs.getString("productType");
		  pr.setpType(p);
		  products.add(pr);
	 	}
	  } catch (SQLException e) {	e.printStackTrace();}	
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
		  String getProductsTable = "SELECT * FROM product;"; /* Get all the Table from the DB */
		  ResultSet rs = stmt.executeQuery(getProductsTable);
		  while(rs.next())
	 	{
		  ur = new User();
		  u = rs.getString("UserID");
		  ur.setId(u);
		  u = rs.getString("UserName");
		  ur.setUserName(u);
		  u = rs.getString("UserPhone");
		  ur.setPhone(u);
		  u = rs.getString("UserPassword");
		  ur.setPassword(u);
		  u = rs.getString("UserPremission");
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
			  		"VALUES("+newAccount.getAccountUserId()+","+newAccount.getAccountBalanceCard()+ ",'"+newAccount.getAccountPaymentMethod()+"','"+newAccount.getAccountPaymentArrangement()+"',"+newAccount.getAccountCreditCardNum()+","+"'2018-12-02'"+");";
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
	  Statement stmt;
	  try {
		  stmt = conn.createStatement();
		  String createTablecourses = "UPDATE project.user SET UserStatus =" + "'" + "CONNECTED" + "'" + "WHERE UserId=" +"'" +userId + "'" +";";
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
    

  System.out.println("Please enter the mySQL scheme name:");
		Scanner scanner = new Scanner(System.in);
		 name=scanner.next();
		 url = "jdbc:mysql://localhost/"+name;/* Enter jbdc mySQL */
		//String sql = "jdbc:mysql://localhost/project";
	
	System.out.println("Please enter the mySQL user name:");
		 username = scanner.next(); /* Enter mySQL name */
	
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

