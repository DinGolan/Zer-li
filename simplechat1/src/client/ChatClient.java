/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

package client;
import ocsf.client.*;
import common.*;
import entity.Message;

import java.io.*;
import java.util.ArrayList;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 

  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host - The server to connect to.
   * @param port - The port number to connect on.
   * @param clientUI - The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); 		/* Call the superclass constructor */
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg - The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  if(((Message)msg).getOption().compareTo("Add new account") ==0)
		  clientUI.addAccount(msg);
	  else if(((Message)msg).getOption().compareTo("get all products in DB") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Add User To Combo Box From DB") == 0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("UserStatus") ==0)
		  clientUI.sendUser(msg);
	  else if(((Message)msg).getOption().compareTo("Add new complaint") ==0)
		  clientUI.addComplaint(msg);	  
	  else if(((Message)msg).getOption().compareTo("get all the survey") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("get all the survey") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("get all stores from DB") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Update customer account") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("insert order to DB") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("get all products in sale from DB") ==0)
		  clientUI.displayUI(msg);	
	  else if(((Message)msg).getOption().compareTo("Get all orders for this customer") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Get all complaints numbers for this customer service worker") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Get complaint details") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Get order details") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store manager want store number") ==0)
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Get all orders numbers for this customer can cancel") ==0)
		  clientUI.displayUI(msg);
	  //else if(((Message)msg).getOption().compareTo("Update complaint") ==0)
	//	  clientUI.displayUI(msg);	  
	  else if(((Message)msg).getOption().compareTo("Store Manager - Add Store To Combo Box From DB") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store Manager - Take The Orders Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store Manager - Take The Complaints Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store Manager - Take The Date Of All the Report Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Store Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Add Store To Combo Box From DB") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Take The Orders Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Take The Complaints Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Take the Revenue Of Specific Quarter Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Comapny Manager - Take The Date Of All the Report Of Specific Store") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Take The Surveys Of Specific Store In Specific Quarter") == 0) 
		  clientUI.displayUI(msg);
	  else if(((Message)msg).getOption().compareTo("Company Manager - Compare Between Two Different Quarter") == 0) 
		  clientUI.displayUI(msg);
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message - The message from the UI.    
   */
  public void handleMessageFromClientUI(Object message )  
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}

