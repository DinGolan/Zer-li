package entity;

import java.io.Serializable;

/**
 * entity of user
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private String id;
	
	private String phone;
	
	private String UserName;
	
	private String password;
	
	private UserStatus status;
	
	private UserPermission permission;
	
	/**
	 * enum for options of User Status (BLOCKED, CONNECTED, DISCONNECTED)
	 */
	public enum UserStatus {BLOCKED , CONNECTED , DISCONNECTED}
	
	/**
	 * enum for options of User Permission 
	 * (COMPANY_MANAGER, STORE_MANAGER, EXPERT,CUSTOMER_SERVICE_WORKER
	 *  ,CUSTOMER ,DATA_COMPANY_MANAGER ,COMPANY_WORKER, STORE_WORKER )
	 */
	public enum UserPermission {COMPANY_MANAGER , STORE_MANAGER , EXPERT ,
		CUSTOMER_SERVICE_WORKER , CUSTOMER , DATA_COMPANY_MANAGER, COMPANY_WORKER , STORE_WORKER}
	
	/**
	 * constructor of user
	 * @param id - user Id
	 * @param UserName - user name
	 * @param phone - user phone number
	 * @param password - user password
	 * @param status - user status
	 * @param permission - user permission
	 */
	public User(String id , String UserName , String phone, String password , UserStatus status , UserPermission permission) 
	{
		super();
		this.id = id;
		this.UserName = UserName;
		this.phone = phone;
		this.password = password; 
		this.status = status;
		this.permission = permission;
	}

	/**
	 * constructor that get user premission as string 
	 * @param id
	 * @param UserName
	 * @param phone
	 * @param password
	 * @param status
	 * @param premission
	 */
	public User(String id, String UserName , String phone, String password, String status, String premission) 
	{
		this(id,UserName,phone,password,UserStatus.valueOf(status),UserPermission.valueOf(premission));
	}
	
	/**
	 * default constructor
	 */
	public User()
	{
		
	}

	/**
	 * @return user Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id - user Id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return user phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone - user phone number to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return user name
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * @param userName - user name to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password - user password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return user status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status - user status to set
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * @return user permission
	 */
	public UserPermission getPermission() {
		return permission;
	}

	/**
	 * @param permission - user permission to set
	 */
	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}
	
	/**
	 * return string of user fields
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", UserName=" + UserName + ", password=" + password + ", status="
				+ status + ", permission=" + permission + "]";
	}
	
}
