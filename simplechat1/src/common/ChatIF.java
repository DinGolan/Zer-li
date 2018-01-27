/* This file contains material supporting section 3.7 of the textbook: */
/* "Object Oriented Software Engineering" and is issued under the open-source */
/* license found at www.lloseng.com */

package common;

/**
 * This interface implements the abstract method used to display
 * objects onto the client or server UIs.
 *
 * @author Dr Robert Lagani&egrave;re
 * @author Dr Timothy C. Lethbridge
 * @version July 2000
 */
public interface ChatIF 
{
	
/**
 * Method that when overridden is used to display objects onto console
 */
  public abstract void display(String message);
  
  /**
   * Method that when overridden is used to display objects onto
   * a UI.
   */
  public abstract void displayUI(Object message);
  
  public abstract void sendUser(Object message);
  
  /**
   * Get an object of message with option to add new account
   * @param message- object of message
   */
  public abstract void addAccount(Object message);
  
  /**
   * Get an object of message with option to add new complaint
   * @param msg- object of message
   */
  public abstract void addComplaint(Object msg);
}
