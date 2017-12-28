package boundery;



import entity.Message;
import entity.User;
import entity.User.UserStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserController {
	
	private User u;
	public User toCompare;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private Button btnLogin = null;

	@FXML
	private TextField txtUserName;
	
	@FXML
	private TextField txtPassword;
	
	
	public void loadUser(User u) /* To load the product details to the text fields */
	{ 
		u.setUserName(this.txtUserName.getText());
		u.setPassword(this.txtPassword.getText());
		
		Message msg = new Message(u.getUserName(), "UserStatus");
		
		UserUI.myClient.accept(msg);
		while(toCompare == null);
		if(toCompare.getId().compareTo(null) == 0) // user dos NOT exist
		{
			System.out.println("user dos NOT exist");
		}
		else //user exist
			if(toCompare.getStatus().equals(User.UserStatus.DISCONNECTED))
			{
				System.out.println("user can do Login");
			}
		
			else
			{
				if(toCompare.getStatus().equals(User.UserStatus.CONNECTED))
					System.out.println("user all ready logged in");
				else // user Blocked
					System.out.println("user Blocked");
			}
	}
	
}
