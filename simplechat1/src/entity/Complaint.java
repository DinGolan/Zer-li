package entity;

import java.io.Serializable;
import java.sql.Date;


/**
 * Entity class for the Complaint with the enum,get,set,variables
 */
public class Complaint implements Serializable{
	private int complaintNum; //save the complaint number
	private Date complaintDate;
	private String complaintTime;
	private ComplaintStatus complaintStat;
	private double complaintCompansation;
	private String complaintCompanyServiceWorkerAnswer;
	private String complaintDetails;
	private String complaintUserId;
	private String complaintServiceWorkerUserName;
	private int complaintOrderId;
	private String complaintMonth;
	
	/**
	 * enum to choose the Complaint Status
	 */
	public enum ComplaintStatus{ //enum for the complaint status
		OPEN,INPROGRESS,CLOSE
	}

	/**
	 * auto empty Complaint constructor
	 */
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * auto filed Complaint constructor
	 * @param complaintNum- int
	 * @param complaintDate- Date
	 * @param complaintTime- String
	 * @param complaintStat- enum
	 * @param complaintCompansation- double
	 * @param complaintCompanyServiceWorkerAnswer- String
	 * @param complaintDetails- String
	 * @param complaintUserId- String 
	 * @param complaintServiceWorkerUserName- String
	 * @param complaintOrderId- int
	 * @param complaintMonth- String
	 */
	public Complaint(int complaintNum, Date complaintDate, String complaintTime, ComplaintStatus complaintStat,
			double complaintCompansation, String complaintCompanyServiceWorkerAnswer, String complaintDetails,
			String complaintUserId, String complaintServiceWorkerUserName, int complaintOrderId,
			String complaintMonth) {
		super();
		this.complaintNum = complaintNum;
		this.complaintDate = complaintDate;
		this.complaintTime = complaintTime;
		this.complaintStat = complaintStat;
		this.complaintCompansation = complaintCompansation;
		this.complaintCompanyServiceWorkerAnswer = complaintCompanyServiceWorkerAnswer;
		this.complaintDetails = complaintDetails;
		this.complaintUserId = complaintUserId;
		this.complaintServiceWorkerUserName = complaintServiceWorkerUserName;
		this.complaintOrderId = complaintOrderId;
		this.complaintMonth = complaintMonth;
	}

	//getters and setters
	
	/**
	 * complaint number getter
	 * @return int- complaintNum
	 */
	public int getComplaintNum() {
		return complaintNum;
	}

	/**
	 * complaint number setter
	 * @param complaintNum- int
	 */
	public void setComplaintNum(int complaintNum) {
		this.complaintNum = complaintNum;
	}

	/**
	 * complaint date getter
	 * @return date- complaint date
	 */
	public Date getComplaintDate() {
		return complaintDate;
	}

	/**
	 * complaint date setter
	 * @param complaintDate- Date
	 */
	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	
	/**
	 * complaint time getter
	 * @return String- complaint time hh:mm
	 */
	public String getComplaintTime() {
		return complaintTime;
	}

	/**
	 * complaint time setter
	 * @param complaintTime- String hh:mm
	 */
	public void setComplaintTime(String complaintTime) {
		this.complaintTime = complaintTime;
	}

	/**
	 * complaint status getter
	 * @return enum- complaintStatus
	 */
	public ComplaintStatus getComplaintStat() {
		return complaintStat;
	}

	/**
	 * complaint status setter
	 * @param complaintStat- enum
	 */
	public void setComplaintStat(ComplaintStatus complaintStat) {
		this.complaintStat = complaintStat;
	}

	/**
	 * Complaint Compansation getter
	 * @return double- Complaint Compansation
	 */
	public double getComplaintCompansation() {
		return complaintCompansation;
	}

	/**
	 * Complaint Compansation setter
	 * @param complaintCompansation- double
	 */
	public void setComplaintCompansation(double complaintCompansation) {
		this.complaintCompansation = complaintCompansation;
	}

	/**
	 * Complaint Details getter
	 * @return String- Complaint Details
	 */
	public String getComplaintDetails() {
		return complaintDetails;
	}

	/**
	 * Complaint Details setter
	 * @param complaintDetails- String
	 */
	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	/**
	 * Complaint Company Service Worker Answer getter
	 * @return String- answer
	 */
	public String getComplaintCompanyServiceWorkerAnswer() {
		return complaintCompanyServiceWorkerAnswer;
	}

	/**
	 * Complaint Company Service Worker Answer setter
	 * @param complaintCompanyServiceWorkerAnswer- String
	 */
	public void setComplaintCompanyServiceWorkerAnswer(String complaintCompanyServiceWorkerAnswer) {
		this.complaintCompanyServiceWorkerAnswer = complaintCompanyServiceWorkerAnswer;
	}

	/**
	 * user id that complaint getter
	 * @return String- usser id
	 */
	public String getComplaintUserId() {
		return complaintUserId;
	}

	/**
	 * user id that complaint setter
	 * @param complaintUserId-String
	 */
	public void setComplaintUserId(String complaintUserId) {
		this.complaintUserId = complaintUserId;
	}

	/**
	 * Service Worker User Name getter
	 * @return String - Service Worker User Name
	 */
	public String getComplaintServiceWorkerUserName() {
		return complaintServiceWorkerUserName;
	}

	/**
	 * Service Worker User Name setter
	 * @param complaintServiceWorkerUserName- String
	 */
	public void setComplaintServiceWorkerUserName(String complaintServiceWorkerUserName) {
		this.complaintServiceWorkerUserName = complaintServiceWorkerUserName;
	}

	/**
	 * Order Id  he complain about getter
	 * @return int- order id
	 */
	public int getComplaintOrderId() {
		return complaintOrderId;
	}

	/**
	 * Order Id  he complain about setter
	 * @param complaintOrderId-int
	 */
	public void setComplaintOrderId(int complaintOrderId) {
		this.complaintOrderId = complaintOrderId;
	}
	
	/**
	 * complaint month getter
	 * @return String- complaint month
	 */
	public String getComplaintMonth() {
		return complaintMonth;
	}

	/**
	 * complaint month setter
	 * @param complaintMonth- String
	 */
	public void setComplaintMonth(String complaintMonth) {
		this.complaintMonth = complaintMonth;
	}

	/**
	 * override toString method for Complaint entity
	 */
	@Override
	public String toString() {
		return "Complaint [complaintNum=" + complaintNum + ", complaintDate=" + complaintDate + ", complaintTime="
				+ complaintTime + ", complaintStat=" + complaintStat + ", complaintCompansation="
				+ complaintCompansation + ", complaintCompanyServiceWorkerAnswer=" + complaintCompanyServiceWorkerAnswer
				+ ", complaintDetails=" + complaintDetails + ", complaintUserId=" + complaintUserId
				+ ", complaintServiceWorkerUserName=" + complaintServiceWorkerUserName + ", complaintOrderId="
				+ complaintOrderId + ", complaintMonth=" + complaintMonth + "]";
	}
	
}
	
	
	


