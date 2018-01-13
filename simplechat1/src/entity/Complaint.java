package entity;

import java.io.Serializable;
import java.sql.Date;

public class Complaint implements Serializable{
	private int complaintNum; //save the complaint number
	private Date complaintDate;
	private ComplaintStatus complaintStat;
	private double complaintCompansation;
	private String complaintCompanyServiceWorkerAnswer;
	private String complaintDetails;
	private String complaintUserId;
	private String complaintServiceWorkerUserName;
	private int complaintOrderId;
	private String complaintMonth;
	
	public enum ComplaintStatus{ //enum for the complaint status
		OPEN,INPROGRESS,CLOSE
	}

	/*public Complaint(int complaintNum, Date complaintDate, ComplaintStatus complaintStat, double complaintCompansation,
			String companyServiceWorkerAnswer, String complaintDetails) {
		super();
		this.complaintNum = complaintNum;
		this.complaintDate = complaintDate;
		this.complaintStat = complaintStat;
		this.complaintCompansation = complaintCompansation;
		this.companyServiceWorkerAnswer = companyServiceWorkerAnswer;
		this.complaintDetails = complaintDetails;
	}*/
	
	/*public Complaint() { //Constructor
		super();
		this.complaintNum = complaintNum;
		this.complaintDate = null;
		this.complaintStat = ComplaintStatus.OPEN;
		this.complaintCompansation = 0;
		this.companyServiceWorkerAnswer = null;
		this.complaintDetails = null;
	}*/
	
	//getters and setters
	
	public int getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(int complaintNum) {
		this.complaintNum = complaintNum;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public ComplaintStatus getComplaintStat() {
		return complaintStat;
	}

	public void setComplaintStat(ComplaintStatus complaintStat) {
		this.complaintStat = complaintStat;
	}

	public double getComplaintCompansation() {
		return complaintCompansation;
	}

	public void setComplaintCompansation(double complaintCompansation) {
		this.complaintCompansation = complaintCompansation;
	}

	public String getComplaintDetails() {
		return complaintDetails;
	}

	public void setComplaintDetails(String complaintDetails) {
		this.complaintDetails = complaintDetails;
	}

	public String getComplaintCompanyServiceWorkerAnswer() {
		return complaintCompanyServiceWorkerAnswer;
	}

	public void setComplaintCompanyServiceWorkerAnswer(String complaintCompanyServiceWorkerAnswer) {
		this.complaintCompanyServiceWorkerAnswer = complaintCompanyServiceWorkerAnswer;
	}

	public String getComplaintUserId() {
		return complaintUserId;
	}

	public void setComplaintUserId(String complaintUserId) {
		this.complaintUserId = complaintUserId;
	}

	public String getComplaintServiceWorkerUserName() {
		return complaintServiceWorkerUserName;
	}

	public void setComplaintServiceWorkerUserName(String complaintServiceWorkerUserName) {
		this.complaintServiceWorkerUserName = complaintServiceWorkerUserName;
	}

	public int getComplaintOrderId() {
		return complaintOrderId;
	}

	public void setComplaintOrderId(int complaintOrderId) {
		this.complaintOrderId = complaintOrderId;
	}
	
	public String getComplaintMonth() {
		return complaintMonth;
	}

	public void setComplaintMonth(String complaintMonth) {
		this.complaintMonth = complaintMonth;
	}


	@Override
	public String toString() {
		return "Complaint [complaintNum=" + complaintNum + ", complaintDate=" + complaintDate + ", complaintStat="
				+ complaintStat + ", complaintCompansation=" + complaintCompansation
				+ ", complaintCompanyServiceWorkerAnswer=" + complaintCompanyServiceWorkerAnswer + ", complaintDetails="
				+ complaintDetails + ", complaintUserId=" + complaintUserId + ", complaintServiceWorkerUserName="
				+ complaintServiceWorkerUserName + ", complaintOrderId=" + complaintOrderId + "]";
	}
	
}
	
	
	


